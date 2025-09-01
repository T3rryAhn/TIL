package algorithm.divide_and_conquer;

import java.util.Arrays;

public class 분할정복_03_mergesort {
    static int[] arr = { 17, 66, 4000, 22, 55, 33, 818, 101 };
    static int N = arr.length;
    static int[] tmp = new int[N]; // 임시 공간을 할당한다.

    public static void main(String[] args) {
        mergeSort(0, N - 1);
        System.out.println(Arrays.toString(arr));
    }

    static void mergeSort(int start, int end) {
        // 1. 교차되었는가? >> dont

        // 2. 아니라면, doit
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(start, mid);
            mergeSort(mid + 1, end);

            // 병합코드
            merge(start, mid, end);
        }
    }

    static void merge(int start, int mid, int end) {
        // 왼쪽 구간의 시작점, 오른쪽 구간의 시작점
        int L = start;
        int R = mid + 1;

        int idx = start;

        while (L <= mid && R <= end) {
            // 안정정렬
            if (arr[L] <= arr[R]) {
                tmp[idx++] = arr[L++];
            } else {
                tmp[idx++] = arr[R++];
            }
        } // 한쪽 구간 종료

        if (L <= mid) {
            for (int i = L; i <= mid; i++) {
                tmp[idx++] = arr[i]++;
            }
        } else {
            for (int i = R; i <= end; i++) {
                tmp[idx++] = arr[i]++;
            }
        }// 나머지 종료

        // 원본에 덮어쓰기
        for(int i = start; i <= end; i++) {
            arr[i] = tmp[i];
        }
    }
}
