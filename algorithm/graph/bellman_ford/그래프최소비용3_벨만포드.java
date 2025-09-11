package algorithm.graph.bellman_ford;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 그래프최소비용3_벨만포드 {
    // input1 음의 사이클 x
    static String input1 = "6 7\r\n" + //
            "0 1 4\r\n" + //
            "0 2 5\r\n" + //
            "1 3 -2\r\n" + //
            "2 4 8\r\n" + //
            "3 5 7\r\n" + //
            "4 2 -3\r\n" + //
            "4 5 6";
    // input2 음의 사이클 o
    static String input2 = "4 4\r\n" + //
            "0 1 5\r\n" + //
            "1 2 -8\r\n" + //
            "2 1 3\r\n" + //
            "2 3 6";

    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static int[] dist; // 거리 저장
    static final int INF = Integer.MAX_VALUE;
    static int V, E; // 정점, 간선 수
    static List<Edge> edges;

    public static void main(String[] args) {
        Scanner sc = new Scanner(input2);

        V = sc.nextInt();
        E = sc.nextInt();

        // 간선 배열 저장
        edges = new ArrayList<>();
        dist = new int[V];

        for (int i = 0; i < E; i++) {
            // 유향
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            edges.add(new Edge(from, to, cost));
        }


        bellmanFord(0);
    }// main

    static void bellmanFord(int start) {
        // dist 초기화 -> 시작 정점을 제외한 나머지 INF
        Arrays.fill(dist, INF);
        dist[start] = 0;

        // 1. 모든 간선을 (v - 1)번 반복 -> relaxation 작업 수행
        for (int i = 0; i < V - 1; i++) {
            boolean flag = false; // 조기 종료 조건
            // 모든 간선
            for (Edge e : edges) {
                // 갱신 : 출발점이 무한대 아닌 것들만 갱신
                if (dist[e.from] != INF && dist[e.to] > dist[e.from] + e.cost) {
                    dist[e.to] = dist[e.from] + e.cost;
                    flag = true;
                }
            }
            if (!flag)
                break; // 갱신이 없었다면 종료.
        } // 반복 횟수

        // 2. 음수 사이클 확인
        boolean negativeCycle = false;
        // 모든 간선
        for (Edge e : edges) {
            // 갱신 : 출발점이 무한대 아닌 것들만 갱신
            if (dist[e.from] != INF && dist[e.to] > dist[e.from] + e.cost) {
                negativeCycle = true;
                break;
            }
        }

        if(negativeCycle) {
            System.out.println("음의 사이클 존재");
        }
        else {
            System.out.println("음의 사이클 없음");
        }

        System.out.println(Arrays.toString(dist));
    }
}
