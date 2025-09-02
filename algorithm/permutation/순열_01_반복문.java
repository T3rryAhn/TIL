package algorithm.permutation;

public class 순열_01_반복문 {
    public static void main(String[] args) {
        int N = 3;
        int[] nums = { 1, 2, 3 }; // 경우의 수 전부 나열.

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 중복 x
                if(i != j) {
                    for(int k = 0; k < N; k++) {
                        if(i != k && j != k) {
                            System.out.printf("%d %d %d%n", nums[i], nums[j], nums[k]);
                        }
                    }
                }
            }
        }
    }
}
