package algorithm.permutation;

import java.util.Arrays;

public class 순열_02_swap {
    static int[] nums = {1,2,3,4};
    static int N = 4;

    public static void main(String[] args) {
        permutation(0);
    }

    //idx : 현재 판단 위치
    static void permutation(int idx) {
        if(idx == N) {
            System.out.println(Arrays.toString(nums));
            return;
        }

        for(int i = idx; i < N; i++) {
            swap(i, idx);
            permutation(idx + 1);
            swap(i, idx); // 원상복구
        }
    }

    //a, b : index
    static void swap(int a, int b){
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
