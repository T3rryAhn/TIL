package algorythm.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 조합03_반복문_재귀 {
    static String[] 재료;
    static String[] sel; // 뽑은거 저장
    static int N, R; // 전체 재료의 수, R : 넣을 재료의수
    static List<String[]> result = new ArrayList<>();
    public static void main(String[] args) {
        N = 4;
        R = 2;
        재료 = new String[] { "상추", "패티", "토마토", "치즈" };
        sel = new String[R];

        combination(0, 0);
        
        System.out.println(Arrays.deepToString(result.toArray()));

    }

    // idx : 이번에 고려할 재료의 인덱스
    // sidx : 뽑은 재료의 인덱스
    public static void combination(int idx, int sidx) {
        // System.out.printf("combination(idx:%d, sidx:%d)%n", idx, sidx);
        // 종료 파트
        if (sidx == R) {
            System.out.println(Arrays.toString(sel));
            result.add(Arrays.copyOf(sel, 2));
            return;
        }
        
        // 재귀 파트
        // 반복문을 통해 내가 할 수 있는 범위 만큼만 돌리겠다.
        for(int i = idx; i <= N - R + sidx; i++) {
            sel[sidx] = 재료[i];
            combination(i + 1, sidx + 1);
        }
    }
}
