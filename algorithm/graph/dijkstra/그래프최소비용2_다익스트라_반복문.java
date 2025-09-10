package algorithm.graph.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 그래프최소비용2_다익스트라_반복문 {
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
        dist[start] = 0; // 시작 정점의 거리 값을 0 으로 갱신 -> 시작지점이 뽑히도록 유도

        for (int i = 0; i < V - 1; i++) {
            // 1. dist가 가장 작으면서 방문하지 않았으면 뽑아
            int min = INF;
            int idx = -1;
            for (int j = 0; j < V; j++) {
                if (!visited[j] && dist[j] < min) {
                    min = dist[j];
                    idx = j;
                }
            } // 가장 작은 것을 뽑음

            if(idx == -1) break;    // 선택할 수 있는게 없다. 연결이 안되어있다 등.

            visited[idx] = true; // start 정점 부터 idx 정점까지의 최소거리는 결정 됨.

            // 2. 갱신
            for (Edge e : adj[idx]) {
                // 방문 x , 갱신 여지가 있다면 갱신
                if (!visited[e.to] && dist[e.to] > dist[idx] + e.cost) {
                    dist[e.to] = dist[idx] + e.cost;
                }
            }
        }
    }

}
