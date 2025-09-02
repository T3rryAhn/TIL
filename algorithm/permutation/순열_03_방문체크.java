package algorithm.permutation;

import java.util.Arrays;

public class 순열_03_방문체크 {
    static int[] nums = { 1, 2, 3, 4 };
    static int N = 4;
    static int[] result = new int[N];
    static boolean[] visited = new boolean[N];
    static int visitedBit = 0;

    public static void main(String[] args) {
        permutation(0);
    }

    // idx : result의 index
    static void permutation(int idx) {
        // 기저 조건
        if(idx == N) {
            System.out.println(Arrays.toString(result));
            return;
        }

        for(int i = 0; i < N; i++) {
            //1. i번째 원소를 사용한 경우
            // if(visited[i]) continue;
            if((visitedBit & (1 << i)) != 0) continue;


            //2. i번째 원소를 사용하지 않은 경우
            result[idx] = nums[i];
            // visited[i] = true;
            visitedBit |= (1 << i);
            permutation(idx + 1);
            // visited[i] = false; // 원상복구
            visitedBit ^= (1 << i);
        }
    }

}
