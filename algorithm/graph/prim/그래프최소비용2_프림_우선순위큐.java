package algorithm.graph.prim;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 그래프최소비용2_프림_우선순위큐 {
    // input
    static String input = "7 11\r\n" + //
            "0 1 32\r\n" + //
            "0 2 31\r\n" + //
            "0 5 60\r\n" + //
            "0 6 51\r\n" + //
            "1 2 21\r\n" + //
            "2 4 46\r\n" + //
            "2 6 25\r\n" + //
            "3 4 34\r\n" + //
            "3 5 18\r\n" + //
            "4 5 40\r\n" + //
            "4 6 51\r\n" + //
            "";

    static final int INF = Integer.MAX_VALUE;

    static class Edge implements Comparable<Edge> {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(input);

        int V = sc.nextInt(); // 정점
        int E = sc.nextInt(); // 간선

        List<Edge>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            adj[from].add(new Edge(to, cost));
            adj[to].add(new Edge(from, cost));
        }

        // 프림을 위한 정보들
        // 부모 정보, 가중치 정보, 방문기록
        boolean[] visited = new boolean[V];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int answer = 0;

        int pick = 0; // V - 1 개 뽑기
        visited[0] = true;

        for (int i = 0; i < adj[0].size(); i++) {
            pq.add(adj[0].get(i));
        }

        while (pick < V - 1) {
            Edge e = pq.poll();
            if (visited[e.to])
                continue;

            answer += e.cost;
            visited[e.to] = true;
            pick++;

            pq.addAll(adj[e.to]);
        }

        System.out.println(answer);
    }
}
