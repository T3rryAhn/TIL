package data_structure.tree.segment_tree;

import java.util.*;

/**
 * 세그먼트 트리 리프수 4n vs 2m (m은 n보다 크거나 같은 가장 작은 2의 제곱수)
 */
public class SegmentTreeMemoryCompare {
    static class SegmentTree {
        long[] sum;
        int n;

        SegmentTree(int n, boolean usePowerOfTwo) {
            this.n = n;
            if (usePowerOfTwo) {
                int m = 1;
                while (m < n) m <<= 1;
                sum = new long[2 * m];
            } else {
                sum = new long[4 * n];
            }
        }

        void build(long[] arr) {
            build(1, 0, n - 1, arr);
        }

        void build(int node, int s, int e, long[] arr) {
            if (s == e) {
                sum[node] = arr[s];
                return;
            }
            int mid = (s + e) >>> 1;
            build(node * 2, s, mid, arr);
            build(node * 2 + 1, mid + 1, e, arr);
            sum[node] = sum[node * 2] + sum[node * 2 + 1];
        }
    }

    public static void main(String[] args) {
        int n = 1_000_000;
        long[] arr = new long[n];
        Random r = new Random(0);
        for (int i = 0; i < n; i++) arr[i] = r.nextInt(100);

        // 1️⃣ 4n 방식
        long t1 = System.nanoTime();
        SegmentTree st4n = new SegmentTree(n, false);
        st4n.build(arr);
        long t2 = System.nanoTime();

        // 2️⃣ 2m 방식
        long t3 = System.nanoTime();
        SegmentTree st2m = new SegmentTree(n, true);
        st2m.build(arr);
        long t4 = System.nanoTime();

        System.out.println("==== 결과 ====");
        System.out.printf("n = %d%n", n);
        System.out.printf("4n 배열 크기: %,d%n", st4n.sum.length);
        System.out.printf("2m 배열 크기: %,d%n", st2m.sum.length);
        System.out.printf("빌드 시간 (4n): %.3f ms%n", (t2 - t1) / 1e6);
        System.out.printf("빌드 시간 (2m): %.3f ms%n", (t4 - t3) / 1e6);

        long bytes4n = st4n.sum.length * 8L;
        long bytes2m = st2m.sum.length * 8L;
        System.out.printf("메모리 (4n): %.2f MB%n", bytes4n / 1_000_000.0);
        System.out.printf("메모리 (2m): %.2f MB%n", bytes2m / 1_000_000.0);
        System.out.printf("절약 비율: %.1f%% 절약%n", 100.0 * (1 - (double)bytes2m / bytes4n));
    }
}
