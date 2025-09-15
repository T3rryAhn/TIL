package algorithm.dynamic_programming;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 제한된 무게를 담을 수 있는 배낭에 최대한의 값어치를 담았을때 그 값을 구하는 문제.
 */
public class 동적계획법_03_배낭문제 {
    static String input = "4\r\n" + //
            "10\r\n" + //
            "10 5\r\n" + //
            "40 4\r\n" + //
            "30 6\r\n" + //
            "50 3\r\n" +
            "";

    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(input);

        int N = sc.nextInt(); // 물건 개수
        int W = sc.nextInt(); // 배낭 무게

        int[] weights = new int[N + 1];
        int[] cost = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            cost[i] = sc.nextInt();
            weights[i] = sc.nextInt();
        }

        int[][] dp = new int[N + 1][W + 1];

        // 물건은 하나씩만 존재
        for (int i = 1; i <= N; i++) {
            for (int w = 0; w <= W; w++) {
                // 1. 내가 고려할 물건의 무게가 임시무게(w) 보다 작거나 같으면
                if (weights[i] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i]] + cost[i]);
                }
                // 2. 내가 고려할 물건의 무게보다 임시무게(w) 가 작은 경우
                else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println(Arrays.toString(dp[N]));
    }
}
