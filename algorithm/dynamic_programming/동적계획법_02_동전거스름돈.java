package algorithm.dynamic_programming;

import java.util.Arrays;

public class 동적계획법_02_동전거스름돈 {
    public static void main(String[] args) {
        // 동전 단위 1, 4, 6

        int money = 8; // 8원 최적 해 4, 4
        int[] dp = new int[money + 1];

        for(int i = 1; i <= money; i++) {
            int minCount = Integer.MAX_VALUE;

            //1원 고려하기
            minCount = Math.min(minCount, dp[i - 1] + 1);

            //4원 고려하기
            if(i >= 4)  // (1원을 이용하여  거슬러준 경우, 4원전의 값에 4원 동전을 한개 추가)
                minCount = Math.min(minCount, dp[i -4] + 1);

            //6원 고려
            if(i >= 6)  // 1원, 4원을 이용하여 거슬러준 경우, 6원전의 값에 6원 동전을 한개 추가
                minCount = Math.min(minCount, dp[i - 6] + 1);
            
            // i 원을 거슬러주려고 했을때 모든 동전을 고려했고, 가장 작은 값이 들어가 있다.
            dp[i] = minCount;


        }
        System.out.println(Arrays.toString(dp));
    }
}
