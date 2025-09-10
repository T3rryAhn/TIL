package algorithm.graph.prim;

import java.util.Scanner;

public class 그래프최소비용2_프림_반복문 {
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(input);

        int V = sc.nextInt(); // 정점
        int E = sc.nextInt(); // 간선

        int[][] adj = new int[V][V]; // 인접 행렬

        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            adj[from][to] = adj[to][from] = cost;
        }

        // 프림을 위한 정보들
        // 부모 정보, 가중치 정보, 방문기록
        int[] p = new int[V];
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];

        // 1. dist 배열 초기화 -> 무한대로
        for (int i = 0; i < V; i++) {
            dist[i] = INF;
            p[i] = -1;
        }

        // 2. 시작 정점 고르기
        dist[0] = 0;
        int answer = 0;

        // 3. 전체 반복문을 수행하면서 정점을 뽑겠다.
        for (int i = 0; i < V - 1; i++) {
            // 3.1 key중 뽑히지 않았으면서, 가장 작은 정점 선택
            int min = INF;
            int idx = -1;
            for (int j = 0; j < V; j++) {
                if(!visited[j] && dist[j] < min) {
                    min = dist[j];
                    idx = j;
                }
            }// 정점을 선택

            // idx : 현재 트리가 아니면서 가장 작은 값을 가지고 있는 정점이 선택되었다.
            visited[idx] = true;
            // 3.2 뽑은 정점과 연결된 간선이 있다면 그리고 갱신할 수 있다면 갱신
            for(int j = 0; j < V; j++) {
                if(!visited[j] && adj[idx][j] != 0 && dist[j] > adj[idx][j]) {
                    dist[j] = adj[idx][j];
                    p[j] = idx;
                }
            }
        }// 뽑는 횟수

        for (int i = 0; i < V; i++) {
            answer += dist[i];
        }

        System.out.println(answer);
    }
}
