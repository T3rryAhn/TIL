package algorithm.dynamic_programming;

public class 동적계획법_01_피보나치 {
    /*
     * 재귀가 가능하고 dag 그래프 형태이니 dp 사용가능
     */
    static int[] memo = new int[1000];
    static {
        memo[0] = 0;
        memo[1] = 1;
    }

    public static void main(String[] args) {
        System.out.println(fibo(10));
    }

    static int fibo(int n) {
        // 종료
        if(n < 2) return n;

        if(memo[n] == 0) memo[n] = fibo(n - 1) + fibo(n - 2);
        return memo[n];
    }
}
