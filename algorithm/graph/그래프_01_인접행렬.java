package algorithm.graph;

import java.util.Scanner;

public class 그래프_01_인접행렬 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // V, E가 주어짐.
        int V = sc.nextInt(); // 정점의 개수 -> 0부터 시작인지, 1부터 시작인지 확인할것.
        int E = sc.nextInt(); // 간선의 개수

        // 간선의 유무, 또는 가중치 저장용 배열
        int[][] adjArr = new int[V][V]; // 0번 정점부터 N - 1 정점인것에 유의

        // 간선 정보 입력
        for (int i = 0; i < E; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            // int W = sc.nextInt(); // 가중치, 가중치가 아니면 1저장

            adjArr[A][B] = 1;   // << 유향, 무향 상관없이 저장해야함.
            adjArr[B][A] = 1;   // << 무향 용

            // adjArr[A][B] = adjArr[B][A] = W;
            
        }
    }
}