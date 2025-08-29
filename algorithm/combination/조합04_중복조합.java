package algorithm.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 조합04_중복조합 {
    static String[] 재료;
    static int N, R; // 전체 재료의 수, R : 넣을 재료의수
    static List<String[]> result = new ArrayList<>();

    public static void main(String[] args) {
        N = 4;
        R = 2;
        재료 = new String[] { "상추", "패티", "토마토", "치즈" };

        combination(0, new ArrayList<String>());


    }

    // idx : 이번에 고려할 재료의 인덱스
    // sel : 뽑을 재료
    public static void combination(int idx, ArrayList<String> sel) {
        // System.out.printf("combination(idx:%d, sidx:%d)%n", idx, sidx);
        // 종료 파트
        if (sel.size() == R) {
            System.out.println(sel);
            return;
        }

        // 재귀 파트
        // 반복문을 통해 내가 할 수 있는 범위 만큼만 돌리겠다.
        for (int i = idx; i < N; i++) {
            sel.add(재료[i]);
            combination(i, sel);
            sel.remove(sel.size() - 1);
        }
    }
}