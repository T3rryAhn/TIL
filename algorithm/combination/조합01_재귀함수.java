package algorithm.combination;

import java.util.Arrays;

/*
 * 햄버거의 재료를 고르는 조합 코드.
 */
public class 조합01_재귀함수 {
    static String[] 재료;
    static String[] sel; // 뽑은거 저장
    static int N, R; // 전체 재료의 수, R : 넣을 재료의수

    public static void main(String[] args) {
        N = 4;
        R = 2;
        재료 = new String[] { "상추", "패티", "토마토", "치즈" };
        sel = new String[R];

        combination(0, 0);

    }

    // idx : 이번에 고려할 재료의 인덱스
    // sidx : 뽑은 재료의 인덱스
    public static void combination(int idx, int sidx) {
        // 종료 파트
        if (sidx == R) {
            System.out.println(Arrays.toString(sel));
            return;
        }
        if (idx >= N) {
            // 더이상 고려할 재료가 없다!
            return;
        }

        // 재귀 파트
        // 현재 재료를 뽑은 경우
        sel[sidx] = 재료[idx];
        combination(idx + 1, sidx + 1);

        // 현재 재료를 뽑지 않은 경우
        // sel[idx] = null; << 다음 재귀때 덮어씌우기가 되기 때문에 초기화 필요없음.
        combination(idx + 1, sidx);
    }
}
