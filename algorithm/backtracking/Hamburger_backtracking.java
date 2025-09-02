package algorithm.backtracking;

import java.util.Scanner;

/*
 * swea_5215_햄버거다이어트
 */
public class Hamburger_backtracking {
    static int N, L; // 재료의수, 제한 칼로리
    // 재료의 점수와 칼로리를 관리
    // 1. 재료 클래스를 만들어서 배열로 관리
    // 2. 2차원 배열을 이용해 [0] 점수, [1] 칼로리
    // 3. 각각 1차원 배열로 관리한다. << 이번에 구현할 방법
    static int[] scores;
    static int[] cals;
    static int ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            // case start

            N = sc.nextInt();
            L = sc.nextInt();
            scores = new int[N];
            cals = new int[N];
            ans = 0;

            for (int i = 0; i < N; i++) {
                scores[i] = sc.nextInt();
                cals[i] = sc.nextInt();
            }

            // case end
        }
    }// main

    static void make(int idx, int sumKcal, int sumScore) {
        // 유망성 검사
        // 지금 모든 재료를 판단하지 않았지만, 제한 칼로리를 벗어났다면 중지.
        if(sumKcal > L ) return;

        // 모든 재료 판단 끝
        if(idx == N) {
            ans = Math.max(ans, sumScore);
            return;
        }


        // 재료를 사용한 경우
        make(idx + 1, sumKcal + cals[idx], sumScore + scores[idx]);
        
        // 재료를 사용하지 않은 경우
        make(idx + 1, sumKcal, sumScore);
    }
}
