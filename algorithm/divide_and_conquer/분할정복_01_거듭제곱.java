package algorithm.divide_and_conquer;

public class 분할정복_01_거듭제곱 {
    public static void main(String[] args) {
        long start, end;

        int c = 2;
        int n = 50;

        start = System.nanoTime();
        long a1 = pow1(c, n);
        end = System.nanoTime();
        System.out.printf("pow%d(): %d time : %d%n", 1, a1, end - start);

        start = System.nanoTime();
        long a2 = pow2(c, n);
        end = System.nanoTime();
        System.out.printf("pow%d() %d time : %d%n", 2, a2, end - start);

        start = System.nanoTime();
        long a3 = pow3(c, n);
        end = System.nanoTime();
        System.out.printf("pow%d() %d time : %d%n", 3, a3, end - start);

        start = System.nanoTime();
        long a4 = pow4(c, n);
        end = System.nanoTime();
        System.out.printf("pow%d() %d time : %d%n", 4, a4, end - start);


    }

    // c : 밑수
    // n : 지수
    static long pow1(int c, int n) {
        // 반복문

        long tmp = 1;
        for (int i = 1; i <= n; i++) {
            tmp *= c;
        }

        return tmp;
    }

    // 재귀
    static long pow2(int c, int n) {
        if (n == 0)
            return 1;
        return c * pow2(c, n - 1);
    }

    // 분할정복
    static long pow3(int c, int n) {
        if (n == 0)
            return 1;

        // 홀수 일때, 짝수 일때
        if (n % 2 == 1) {
            return pow3(c, (n - 1) / 2) * pow3(c, (n - 1) / 2) * c;
        } else {
            return pow3(c, n / 2) * pow3(c, n / 2);
        }
    }
    // 위의 코드는 같은 값을 구하는 함수를 두번 호출함

    // 분할정복2
    static long pow4(int c, int n) {
        if (n == 0)
            return 1;

        // 홀수 일때, 짝수 일때
        if (n % 2 == 1) {
            long tmp = pow4(c, (n - 1) / 2);
            return tmp * tmp * c;
        } else {
            long tmp = pow4(c, n / 2);
            return tmp * tmp;
        }
    }

}
