package data_structure.tree.segment_tree;

import java.io.*;
import java.util.*;

/**
 * 세그먼트 트리 (합 + 최댓값 동시 유지)
 * - build: O(N)
 * - querySum / queryMax: O(log N)
 * - updateSet / updateAdd: O(log N)
 *
 * 구현 포인트
 * 1) 부모 노드는 자식 둘을 merge: 
 *      sum[parent] = sum[L] + sum[R]
 *      max[parent] = Math.max(max[L], max[R])
 * 2) point update(교체/가산) 시 리프를 갱신하고 올라가며 재계산
 */
public class SegmentTreeSumMax {

    static class SegmentTree {
        final int n;
        final int[] a;        // 원본 배열 (int)
        final long[] sum;     // 구간 합 (long)
        final int[] mx;       // 구간 최댓값 (int)

        // 성능 계측용
        int queryCalls = 0;   // querySum + queryMax 호출 횟수
        int updateCalls = 0;  // updateSet + updateAdd 호출 횟수

        SegmentTree(int[] arr) {
            this.n = arr.length;
            this.a = Arrays.copyOf(arr, n);
            // 안전하게 4*n 할당
            this.sum = new long[4 * n];
            this.mx  = new int[4 * n];
            if (n > 0) build(1, 0, n - 1);
        }

        // 트리 구축: O(N)
        private void build(int node, int s, int e) {
            if (s == e) {
                sum[node] = a[s];
                mx[node]  = a[s];
                return;
            }
            int m = (s + e) >>> 1;
            build(node << 1, s, m);
            build(node << 1 | 1, m + 1, e);
            pull(node);
        }

        // 부모 노드를 자식에서 재계산
        private void pull(int node) {
            sum[node] = sum[node << 1] + sum[node << 1 | 1];
            mx[node]  = Math.max(mx[node << 1], mx[node << 1 | 1]);
        }

        /* -------------------- Public API -------------------- */

        // [l, r] 구간 합
        public long querySum(int l, int r) {
            queryCalls++;
            if (l < 0) l = 0;
            if (r >= n) r = n - 1;
            if (l > r || n == 0) return 0L;
            return querySum(1, 0, n - 1, l, r);
        }

        // [l, r] 구간 최댓값
        public int queryMax(int l, int r) {
            queryCalls++;
            if (l < 0) l = 0;
            if (r >= n) r = n - 1;
            if (l > r || n == 0) return Integer.MIN_VALUE;
            return queryMax(1, 0, n - 1, l, r);
        }

        // 값 교체: a[idx] = newVal
        public void updateSet(int idx, int newVal) {
            updateCalls++;
            if (idx < 0 || idx >= n) return;
            int diff = newVal - a[idx];
            a[idx] = newVal;
            updateAdd(1, 0, n - 1, idx, diff); // 합은 diff만 더해주면 되고, max는 리프부터 재계산됨
        }

        // 값 가산: a[idx] += delta
        public void updateAdd(int idx, int delta) {
            updateCalls++;
            if (idx < 0 || idx >= n) return;
            a[idx] += delta;
            updateAdd(1, 0, n - 1, idx, delta);
        }

        // 대략적 메모리 사용량 추정 (bytes)
        public long estimateBytes() {
            // a: int * n, sum: long * 4n, mx: int * 4n
            long bytes = 0;
            bytes += 4L * n;         // a
            bytes += 8L * 4 * n;     // sum
            bytes += 4L * 4 * n;     // mx
            return bytes;
        }

        /* -------------------- Internal (recursive) -------------------- */

        private long querySum(int node, int s, int e, int l, int r) {
            if (r < s || e < l) return 0L;              // 불포함
            if (l <= s && e <= r) return sum[node];      // 완전 포함
            int m = (s + e) >>> 1;
            long left  = querySum(node << 1, s, m, l, r);
            long right = querySum(node << 1 | 1, m + 1, e, l, r);
            return left + right;
        }

        private int queryMax(int node, int s, int e, int l, int r) {
            if (r < s || e < l) return Integer.MIN_VALUE; // 불포함
            if (l <= s && e <= r) return mx[node];         // 완전 포함
            int m = (s + e) >>> 1;
            int left  = queryMax(node << 1, s, m, l, r);
            int right = queryMax(node << 1 | 1, m + 1, e, l, r);
            return Math.max(left, right);
        }

        // 리프에서 diff 적용 후 위로 끌어올리며 재계산
        private void updateAdd(int node, int s, int e, int idx, int diff) {
            if (idx < s || idx > e) return;
            if (s == e) {
                sum[node] += diff;
                mx[node]  = (int) sum[node]; // s==e면 sum==a[idx]
                return;
            }
            int m = (s + e) >>> 1;
            if (idx <= m) updateAdd(node << 1, s, m, idx, diff);
            else          updateAdd(node << 1 | 1, m + 1, e, idx, diff);
            pull(node);
        }
    }

    /* -------------------- 데모 -------------------- */
    public static void main(String[] args) throws Exception {
        // 예시 입력
        System.out.println("init");
        int[] arr = {5, 3, 8, 7, 2, 6};
        System.out.println("arr[] : " + Arrays.toString(arr));
        SegmentTree st = new SegmentTree(arr);
        System.out.println();
        
        // 질의
        System.out.println("query");
        System.out.println("sum[1..4] = " + st.querySum(1, 4));   // 3+8+7+2 = 20
        System.out.println("max[1..4] = " + st.queryMax(1, 4));   // 8
        System.out.println();


        // 업데이트 (교체)
        System.out.println("update (change)");
        st.updateSet(3, 10); // a[3]=7 -> 10
        System.out.println("arr[] : " + Arrays.toString(st.a));
        System.out.println("sum[1..4] after set = " + st.querySum(1, 4)); // 3+8+10+2 = 23
        System.out.println("max[1..4] after set = " + st.queryMax(1, 4)); // 10
        System.out.println();


        // 업데이트 (가산)
        System.out.println("update (add)");
        st.updateAdd(1, -2); // a[1]=3 -> 1
        System.out.println("sum[0..5] = " + st.querySum(0, 5)); // 전체 합
        System.out.println("max[0..5] = " + st.queryMax(0, 5)); // 전체 최대
        System.out.println();


        // 성능/메모리 지표
        System.out.println("performance / memory");
        System.out.println("queryCalls = " + st.queryCalls);
        System.out.println("updateCalls = " + st.updateCalls);
        System.out.println("~memory(bytes) ≈ " + st.estimateBytes());
    }
}
