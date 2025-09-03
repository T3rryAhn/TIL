package algorithm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 그래프_03_간선배열 {
    static class Edge {
        int A, B, W; // 시작, 끝, 가중치

        Edge(int A, int B, int W) {
            this.A = A;
            this.B = B;
            this.W = W;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // V, E가 주어짐.
        int V = sc.nextInt(); // 정점의 개수 -> 0부터 시작인지, 1부터 시작인지 확인할것.
        int E = sc.nextInt(); // 간선의 개수

        // 객체 배열 형태로 저장
        Edge[] edges = new Edge[E];

        // 간선 정보 입력
        for (int i = 0; i < E; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            int W = sc.nextInt(); // 가중치, 가중치가 아니면 1저장

            edges[i] = new Edge(A, B, W);
            
        }

        // 객체 리스트로도 저장가능
        List<Edge> edgeList = new ArrayList<>();
        for(int i =0; i < E; i++) {
            // edgeList.add(new Edge(A, B, W));
        }

    }
}