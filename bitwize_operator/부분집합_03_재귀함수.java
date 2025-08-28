package bitwize_operator;
/*
 * 재료가 3개인 김밥의 조합을 보는 코드
 */
public class 부분집합_03_재귀함수 {
    static int N = 3;
    static boolean[] sel = new boolean[N]; // 재료가 있다/없다.
    static String[] 재료 = { "단무지", "햄", "오이"};

    public static void main(String[] args) {
        powerset(0);
    }

    public static void powerset(int idx) {
        // 종료 조건
        if (idx == N) {
            for (int i = 0; i < N; i++) {
                if (sel[i]) {
                    System.out.print(재료[i] + " ");
                }
            }
            System.out.println(":김밥");
            return;
        }

        // 재귀 호출
        sel[idx] = true;    // idx번째 재료를 선택
        powerset(idx + 1);  // 다음 재료 선택

        sel[idx] = false;   // idx번째 재료를 선택하지 않음
        powerset(idx + 1);  // 다음 재료 선택
    }
}