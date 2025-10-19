package data_structure.tree.segment_tree;

import java.io.*;
import java.util.*;

/**
 * 성능 비교: Non-Lazy(포인트) vs Lazy(구간 가산)
 * - 유지 정보: 합(long) + 최댓값(int)
 * - 공통 질의: querySum, queryMax
 * - 업데이트:
 *     * NonLazy: point add (idx += delta) / point set
 *     * Lazy: range add ([l,r] += delta)
 *
 * 시나리오
 *   A. point-heavy:   pointAdd 60% + queries 40%
 *   B. range-heavy:   rangeAdd 60% + queries 40%      (NonLazy는 rangeAdd를 pointAdd 루프로 시뮬)
 *   C. query-only:    queries 100%
 *
 * 출력: 배열 크기, 빌드시간, 각 시나리오별 실행시간(ms)
 */
public class SegtreePerfCompare {

    /* ===================== Non-Lazy (point updates) ===================== */
    static final class NonLazySegTree {
        final int n, m;
        final long[] sum;
        final int[] mx;

        NonLazySegTree(int[] arr) {
            this.n = arr.length;
            this.m = nextPow2(n);
            this.sum = new long[2 * m];
            this.mx  = new int[2 * m];
            if (n > 0) build(1, 0, m - 1, arr);
        }

        private static int nextPow2(int x) {
            if (x <= 1) return 1;
            return 1 << (32 - Integer.numberOfLeadingZeros(x - 1));
        }

        private void build(int node, int s, int e, int[] arr) {
            if (s == e) {
                if (s < n) {
                    int v = arr[s];
                    sum[node] = v;
                    mx[node]  = v;
                } else {
                    sum[node] = 0;
                    mx[node]  = Integer.MIN_VALUE / 4;
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

        public void pointAdd(int idx, int delta) {
            if (n == 0 || idx < 0 || idx >= n) return;
            pointAdd(1, 0, m - 1, idx, delta);
        }

        private void pointAdd(int node, int s, int e, int idx, int delta) {
            if (s == e) {
                sum[node] += delta;
                mx[node]  += delta;
                return;
            }
            int mid = (s + e) >>> 1;
            if (idx <= mid) pointAdd(node << 1, s, mid, idx, delta);
            else            pointAdd(node << 1 | 1, mid + 1, e, idx, delta);
            pull(node);
        }

        public long querySum(int l, int r) {
            if (n == 0) return 0;
            l = Math.max(l, 0); r = Math.min(r, n - 1);
            if (l > r) return 0;
            return querySum(1, 0, m - 1, l, r);
        }

        private long querySum(int node, int s, int e, int l, int r) {
            if (r < s || e < l) return 0;
            if (l <= s && e <= r) return sum[node];
            int mid = (s + e) >>> 1;
            return querySum(node << 1, s, mid, l, r)
                 + querySum(node << 1 | 1, mid + 1, e, l, r);
        }

        public int queryMax(int l, int r) {
            if (n == 0) return Integer.MIN_VALUE / 4;
            l = Math.max(l, 0); r = Math.min(r, n - 1);
            if (l > r) return Integer.MIN_VALUE / 4;
            return queryMax(1, 0, m - 1, l, r);
        }

        private int queryMax(int node, int s, int e, int l, int r) {
            if (r < s || e < l) return Integer.MIN_VALUE / 4;
            if (l <= s && e <= r) return mx[node];
            int mid = (s + e) >>> 1;
            int left  = queryMax(node << 1, s, mid, l, r);
            int right = queryMax(node << 1 | 1, mid + 1, e, l, r);
            return Math.max(left, right);
        }
    }

    /* ===================== Lazy (range add) ===================== */
    static final class LazySegTree {
        final int n, m;
        final long[] sum;
        final int[] mx;
        final int[] lazy;

        LazySegTree(int[] arr) {
            this.n = arr.length;
            this.m = nextPow2(n);
            this.sum  = new long[2 * m];
            this.mx   = new int[2 * m];
            this.lazy = new int[2 * m];
            if (n > 0) build(1, 0, m - 1, arr);
        }

        private static int nextPow2(int x) {
            if (x <= 1) return 1;
            return 1 << (32 - Integer.numberOfLeadingZeros(x - 1));
        }

        private void build(int node, int s, int e, int[] arr) {
            if (s == e) {
                if (s < n) {
                    int v = arr[s];
                    sum[node] = v;
                    mx[node]  = v;
                } else {
                    sum[node] = 0;
                    mx[node]  = Integer.MIN_VALUE / 4;
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
            sum[node] += (long) add * len;
            mx[node]  += add;
            lazy[node] += add;
        }

        private void push(int node, int s, int e) {
            int tag = lazy[node];
            if (tag == 0 || s == e) return;
            int mid = (s + e) >>> 1;
            apply(node << 1,       mid - s + 1, tag);
            apply(node << 1 | 1,   e - mid,     tag);
            lazy[node] = 0;
        }

        public void rangeAdd(int l, int r, int delta) {
            if (n == 0) return;
            l = Math.max(l, 0); r = Math.min(r, n - 1);
            if (l > r) return;
            rangeAdd(1, 0, m - 1, l, r, delta);
        }

        private void rangeAdd(int node, int s, int e, int l, int r, int delta) {
            if (r < s || e < l) return;
            if (l <= s && e <= r) { apply(node, e - s + 1, delta); return; }
            push(node, s, e);
            int mid = (s + e) >>> 1;
            rangeAdd(node << 1, s, mid, l, r, delta);
            rangeAdd(node << 1 | 1, mid + 1, e, l, r, delta);
            pull(node);
        }

        public long querySum(int l, int r) {
            if (n == 0) return 0;
            l = Math.max(l, 0); r = Math.min(r, n - 1);
            if (l > r) return 0;
            return querySum(1, 0, m - 1, l, r);
        }

        private long querySum(int node, int s, int e, int l, int r) {
            if (r < s || e < l) return 0;
            if (l <= s && e <= r) return sum[node];
            push(node, s, e);
            int mid = (s + e) >>> 1;
            return querySum(node << 1, s, mid, l, r)
                 + querySum(node << 1 | 1, mid + 1, e, l, r);
        }

        public int queryMax(int l, int r) {
            if (n == 0) return Integer.MIN_VALUE / 4;
            l = Math.max(l, 0); r = Math.min(r, n - 1);
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

    /* ===================== 실험 러너 ===================== */
    public static void main(String[] args) {
        final int n  = 200_000;     // 배열 크기
        // final int QA = 100_000;     // 시나리오 A 연산 수 (point-heavy)
        // final int QB = 100_000;     // 시나리오 B 연산 수 (range-heavy)
        // final int QC = 100_000;     // 시나리오 C 연산 수 (query-only)
        final int QA = 1000;     // 시나리오 A 연산 수 (point-heavy)
        final int QB = 1000;     // 시나리오 B 연산 수 (range-heavy)
        final int QC = 1000;     // 시나리오 C 연산 수 (query-only)

        int[] base = new int[n];
        Random rnd = new Random(42);
        for (int i = 0; i < n; i++) base[i] = rnd.nextInt(1000) - 500;

        // 빌드
        long t0 = System.nanoTime();
        NonLazySegTree non = new NonLazySegTree(base);
        long t1 = System.nanoTime();
        LazySegTree lazy = new LazySegTree(base);
        long t2 = System.nanoTime();

        System.out.printf("n=%d, NonLazy size=%d, Lazy size=%d%n",
                n, non.sum.length, lazy.sum.length);
        System.out.printf("Build NonLazy: %.2f ms, Lazy: %.2f ms%n",
                (t1 - t0)/1e6, (t2 - t1)/1e6);

        /* ---------- 시나리오 A: point-heavy (pointAdd 60% + queries 40%) ---------- */
        rnd.setSeed(7);
        long aStart = System.nanoTime();
        {
            for (int k = 0; k < QA; k++) {
                if (rnd.nextDouble() < 0.60) {
                    int idx = rnd.nextInt(n);
                    int delta = rnd.nextInt(21) - 10; // [-10,10]
                    non.pointAdd(idx, delta);
                } else {
                    int l = rnd.nextInt(n);
                    int r = l + rnd.nextInt(n - l);
                    // 합/최대 번갈아 호출
                    if ((k & 1) == 0) non.querySum(l, r);
                    else              non.queryMax(l, r);
                }
            }
        }
        long aMid = System.nanoTime();
        {
            for (int k = 0; k < QA; k++) {
                if (rnd.nextDouble() < 0.60) {
                    int idx = rnd.nextInt(n);
                    int delta = rnd.nextInt(21) - 10;
                    lazy.rangeAdd(idx, idx, delta); // point는 range로 처리 가능
                } else {
                    int l = rnd.nextInt(n);
                    int r = l + rnd.nextInt(n - l);
                    if ((k & 1) == 0) lazy.querySum(l, r);
                    else              lazy.queryMax(l, r);
                }
            }
        }
        long aEnd = System.nanoTime();

        /* ---------- 시나리오 B: range-heavy (rangeAdd 60% + queries 40%) ---------- */
        rnd.setSeed(8);
        long bStart = System.nanoTime();
        {
            for (int k = 0; k < QB; k++) {
                if (rnd.nextDouble() < 0.60) {
                    int l = rnd.nextInt(n);
                    int r = l + rnd.nextInt(n - l);
                    int delta = rnd.nextInt(21) - 10;
                    // NonLazy는 rangeAdd를 pointAdd 루프로 시뮬 (느림)
                    for (int i = l; i <= r; i++) non.pointAdd(i, delta);
                } else {
                    int l = rnd.nextInt(n);
                    int r = l + rnd.nextInt(n - l);
                    if ((k & 1) == 0) non.querySum(l, r);
                    else              non.queryMax(l, r);
                }
            }
        }
        long bMid = System.nanoTime();
        {
            for (int k = 0; k < QB; k++) {
                if (rnd.nextDouble() < 0.60) {
                    int l = rnd.nextInt(n);
                    int r = l + rnd.nextInt(n - l);
                    int delta = rnd.nextInt(21) - 10;
                    lazy.rangeAdd(l, r, delta);
                } else {
                    int l = rnd.nextInt(n);
                    int r = l + rnd.nextInt(n - l);
                    if ((k & 1) == 0) lazy.querySum(l, r);
                    else              lazy.queryMax(l, r);
                }
            }
        }
        long bEnd = System.nanoTime();

        /* ---------- 시나리오 C: query-only (100%) ---------- */
        rnd.setSeed(9);
        long cStart = System.nanoTime();
        {
            for (int k = 0; k < QC; k++) {
                int l = rnd.nextInt(n);
                int r = l + rnd.nextInt(n - l);
                if ((k & 1) == 0) non.querySum(l, r);
                else              non.queryMax(l, r);
            }
        }
        long cMid = System.nanoTime();
        {
            for (int k = 0; k < QC; k++) {
                int l = rnd.nextInt(n);
                int r = l + rnd.nextInt(n - l);
                if ((k & 1) == 0) lazy.querySum(l, r);
                else              lazy.queryMax(l, r);
            }
        }
        long cEnd = System.nanoTime();

        /* ---------- 결과 출력 ---------- */
        System.out.println("== Scenario A: point-heavy ==");
        System.out.printf("NonLazy: %.2f ms | Lazy: %.2f ms%n",
                (aMid - aStart)/1e6, (aEnd - aMid)/1e6);

        System.out.println("== Scenario B: range-heavy ==");
        System.out.printf("NonLazy(range via point loop): %.2f ms | Lazy(range): %.2f ms%n",
                (bMid - bStart)/1e6, (bEnd - bMid)/1e6);

        System.out.println("== Scenario C: query-only ==");
        System.out.printf("NonLazy: %.2f ms | Lazy: %.2f ms%n",
                (cMid - cStart)/1e6, (cEnd - cMid)/1e6);
    }
}
