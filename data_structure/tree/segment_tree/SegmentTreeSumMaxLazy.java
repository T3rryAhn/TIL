package data_structure.tree.segment_tree;

import java.util.*;
import java.io.*;

/**
 * Segment Tree (Lazy Propagation)
 * - 유지 정보: 구간 합(long), 구간 최댓값(int)
 * - 연산: 구간 가산(range add), 구간 합/최대 질의
 * - 메모리: n 이상인 2의 거듭제곱 m을 찾아 배열 크기 2*m 사용
 *
 * 시간 복잡도:
 *   - build: O(n)
 *   - rangeAdd / querySum / queryMax: O(log n)
 */
public class SegmentTreeSumMaxLazy {
    static final class SegTree {
        final int n;         // 원본 배열 크기
        final int m;         // n 이상인 2의 거듭제곱
        final long[] sum;    // 구간 합
        final int[] mx;      // 구간 최댓값
        final int[] lazy;    // 지연 가산 태그 (자식에 내려보낼 값)

        SegTree(int[] arr) {
            this.n = arr.length;
            this.m = nextPow2(n);
            // 1-based 인덱싱, 총 노드 수는 최대 2*m - 1 → 크기 2*m면 안전
            this.sum  = new long[2 * m];
            this.mx   = new int[2 * m];
            this.lazy = new int[2 * m];
            if (n > 0) build(1, 0, m - 1, arr);
        }

        // n <= x를 만족하는 최소 2의 거듭제곱
        private static int nextPow2(int x) {
            if (x <= 1) return 1;
            return 1 << (32 - Integer.numberOfLeadingZeros(x - 1));
        }

        // 리프가 m개인 완전이진트리로 빌드하되, n..m-1는 0으로 패딩
        private void build(int node, int s, int e, int[] arr) {
            if (s == e) {
                if (s < n) {
                    int v = arr[s];
                    sum[node] = v;
                    mx[node]  = v;
                } else {
                    sum[node] = 0;
                    mx[node]  = Integer.MIN_VALUE / 4; // 패딩 리프는 최소값 매우 작게
                }
                return;
            }
            int mid = (s + e) >>> 1;
            build(node << 1, s, mid, arr);
            build(node << 1 | 1, mid + 1, e, arr);
            pull(node);
        }

        private void pull(int node) {
            sum[node] = sum[node << 1] + sum[node << 1 | 1];
            mx[node]  = Math.max(mx[node << 1], mx[node << 1 | 1]);
        }

        private void apply(int node, int len, int add) {
            // 이 노드에 add를 적용
            sum[node] += (long) add * len;
            mx[node]  += add;
            lazy[node] += add;
        }

        private void push(int node, int s, int e) {
            int tag = lazy[node];
            if (tag == 0 || s == e) return;
            int mid = (s + e) >>> 1;
            apply(node << 1,       mid - s + 1, tag);
            apply(node << 1 | 1,   e - (mid + 1) + 1, tag);
            lazy[node] = 0;
        }

        /** [l, r] 구간에 delta를 더한다 */
        public void rangeAdd(int l, int r, int delta) {
            if (n == 0) return;
            // m-1을 상한으로 쓰되, 실제 의미있는 구간은 [0, n-1]
            l = Math.max(l, 0);
            r = Math.min(r, n - 1);
            if (l > r) return;
            rangeAdd(1, 0, m - 1, l, r, delta);
        }

        private void rangeAdd(int node, int s, int e, int l, int r, int delta) {
            if (r < s || e < l) return;
            if (l <= s && e <= r) {
                // s..e 중 실제 데이터 레인지와 겹치는 길이만큼만 합에 반영되어야 함
                // 다만 우리는 패딩 리프를 매우 작은 값으로 두어 max가 오염되지 않도록 했고,
                // sum은 패딩 영역(> n-1)에 대해선 0이므로 길이 계산을 s..e 전체 길이로 해도 무방.
                apply(node, e - s + 1, delta);
                return;
            }
            push(node, s, e);
            int mid = (s + e) >>> 1;
            rangeAdd(node << 1, s, mid, l, r, delta);
            rangeAdd(node << 1 | 1, mid + 1, e, l, r, delta);
            pull(node);
        }

        /** [l, r] 구간 합 */
        public long querySum(int l, int r) {
            if (n == 0) return 0L;
            l = Math.max(l, 0);
            r = Math.min(r, n - 1);
            if (l > r) return 0L;
            return querySum(1, 0, m - 1, l, r);
        }

        private long querySum(int node, int s, int e, int l, int r) {
            if (r < s || e < l) return 0L;
            if (l <= s && e <= r) return sum[node];
            push(node, s, e);
            int mid = (s + e) >>> 1;
            return querySum(node << 1, s, mid, l, r)
                 + querySum(node << 1 | 1, mid + 1, e, l, r);
        }

        /** [l, r] 구간 최댓값 */
        public int queryMax(int l, int r) {
            if (n == 0) return Integer.MIN_VALUE / 4;
            l = Math.max(l, 0);
            r = Math.min(r, n - 1);
            if (l > r) return Integer.MIN_VALUE / 4;
            return queryMax(1, 0, m - 1, l, r);
        }

        private int queryMax(int node, int s, int e, int l, int r) {
            if (r < s || e < l) return Integer.MIN_VALUE / 4;
            if (l <= s && e <= r) return mx[node];
            push(node, s, e);
            int mid = (s + e) >>> 1;
            int left  = queryMax(node << 1, s, mid, l, r);
            int right = queryMax(node << 1 | 1, mid + 1, e, l, r);
            return Math.max(left, right);
        }
    }

    /* --------------------- 데모 --------------------- */
    public static void main(String[] args) throws Exception {
        int[] arr = {5, 3, 8, 7, 2, 6}; // n=6
        SegTree st = new SegTree(arr);

        // 기본 질의
        System.out.println("sum[1..4] = " + st.querySum(1, 4)); // 3+8+7+2 = 20
        System.out.println("max[1..4] = " + st.queryMax(1, 4)); // 8

        // 구간 가산: [1,3] 에 +2
        st.rangeAdd(1, 3, 2); // arr: 5,5,10,9,2,6
        System.out.println("after add +2 to [1,3]");
        System.out.println("sum[1..4] = " + st.querySum(1, 4)); // 5+10+9+2 = 26
        System.out.println("max[0..5] = " + st.queryMax(0, 5)); // 10

        // 구간 가산: [0,5] 에 -1
        st.rangeAdd(0, 5, -1); // arr: 4,4,9,8,1,5
        System.out.println("after add -1 to [0,5]");
        System.out.println("sum[0..5] = " + st.querySum(0, 5)); // 31
        System.out.println("max[2..5] = " + st.queryMax(2, 5)); // 9
    }
}
