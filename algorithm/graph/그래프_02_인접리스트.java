package algorithm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 그래프_02_인접리스트 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // V, E가 주어짐.
        int V = sc.nextInt(); // 정점의 개수 -> 0부터 시작인지, 1부터 시작인지 확인할것.
        int E = sc.nextInt(); // 간선의 개수

        // 인접리스트로 저장
        // List<List<Integer>> adjList;
        List<Integer>[] adjList = new ArrayList[V];
        for(int i =0; i < V; i++) {
            adjList[i] = new ArrayList<>();
        }

        // 간선 정보 입력
        for (int i = 0; i < E; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            // int W = sc.nextInt(); // 가중치, 가중치가 아니면 1저장

            adjList[A].add(B);   // << 유향, 무향 상관없이 저장해야함.
            adjList[B].add(A);   // << 무향 용

            
        }
    }
}