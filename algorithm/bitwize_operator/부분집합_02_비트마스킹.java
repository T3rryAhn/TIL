package algorithm.bitwize_operator;
/*
 * 재료가 3개인 김밥의 조합을 보는 코드
 */
public class 부분집합_02_비트마스킹 {
    static int N = 4;
    static String[] 재료 = { "단무지", "햄", "오이", "계란" };

    public static void main(String[] args) {
        // 비트마스킹 부분 집합
        for (int i = 0; i < (1 << N); i++) { // 0 ~ 2^N -1
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) { // j번째 비트가 1인지 확인
                    System.out.print(재료[j] + " ");
                }
            }
            System.out.println(":김밥(" + i + ")\n"); // i는 부분집합의 번호
        }
    }
}