package algorithm.graph.bellman_ford;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 그래프최소비용3_플로이드워셜 {
        // input1 음의 사이클 x
    static String input1 = "5 9\r\n" + //
                "0 1 3\r\n" + //
                "0 2 8\r\n" + //
                "0 4 -4\r\n" + //
                "1 3 1\r\n" + //
                "1 4 7\r\n" + //
                "2 1 4\r\n" + //
                "3 0 2\r\n" + //
                "3 2 -5\r\n" + //
                "4 3 6";
    // input2 음의 사이클 o
    static String input2 = "4 5\r\n" + //
                "0 1 1\r\n" + //
                "0 3 4\r\n" + //
                "1 2 1\r\n" + //
                "2 0 -3\r\n" + //
                "3 2 2";

    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static int[][] dist; // 거리 저장
    static final int INF = Integer.MAX_VALUE;
    static int V, E; // 정점, 간선 수
    static List<Edge> edges;

    public static void main(String[] args) {
        Scanner sc = new Scanner(input1);

        V = sc.nextInt();
        E = sc.nextInt();

        int[][] dist = new int[V][V];

        //초기화 -> 나 자신은 0. 나머지는 INF
        for(int i = 0; i < V; i++) {
            for(int j = 0; j < V; j++) {
                if(i != j) dist[i][j] = INF;
            }
        }
        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            dist[from][to] = cost;
        }

        //플로이드 워셜
        for(int k = 0; k < V; k++ ) {                   // 경유지 k
            for(int from = 0; from < V; from ++ ) {     // 출발지 from
                if(dist[from][k] == INF) continue;      // 출발지 -> 경유지로 연결이 안되어있는 상태.
                for(int to = 0; to < V; to++) {         // 도착지 to
                    if(dist[k][to] == INF) continue;    // 경유지 -> 도착지로 연결이 안되어있는 상태.
                    dist[from][to] = Math.min(dist[from][to], dist[from][k] + dist[k][to]);
                }   // /도착지
            }// /출발지
        }// /경유지


        // 음수 사이클 검사. -> 옵션
        boolean negativeCycle = false;
        for(int i = 0; i < V; i++) {
            if(dist[i][i] < 0) {
                negativeCycle = true;
                break;
            }
        }

        if(negativeCycle) {
            System.out.println("음수 사이클 존재");
        }
        else {
            System.err.println("음수 사이클 미존재");
        }

        for(int[] row : dist) {
            System.out.println(Arrays.toString(row));
        }
    }// main
}
