package algorithm.graph.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 그래프최소비용2_다익스트라_우선순위큐 {
    static class Edge {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static int V, E; // 정점의 수, 간선의 수
    static List<Edge>[] adj; // 인접 리스트
    static int[] dist; // 거리 저장
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        V = sc.nextInt();
        E = sc.nextInt();

        visited = new boolean[V];

        adj = new ArrayList[V];
        dist = new int[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
            dist[i] = INF;
        }

        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            adj[from].add(new Edge(to, cost));
        }

        dijkstra(0);

        System.out.println(Arrays.toString(dist));
    }

    static void dijkstra(int start) {
        // @todo
    }

}
