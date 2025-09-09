package algorithm.graph.kruskal;

import java.util.Arrays;
import java.util.Scanner;

public class 그래프최소비용01_크루스칼 {
    static int V, E; // 정점 개수, 간선 개수
    static int[] p; // 대표를 저장할 배열
    // static int[] rank; // 균형있게 만들기 -> 완벽한 균형은 아님

    // 간선 배열
    // 1. edge 클래스
    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

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

    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(input);

        V = sc.nextInt();
        E = sc.nextInt();

        Edge[] edges = new Edge[E];
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int cost = sc.nextInt();

            edges[i] = new Edge(u, v, cost);
        }

        // 간선 정렬. 1.comparable, 2. comparator

        Arrays.sort(edges, (o1, o2) -> o1.cost - o2.cost);

        // 1. 대표집합을 만든다
        p = new int[V];
        // rank = new int[V];

        for (int i = 0; i < V; i++) {
            makeSet(i);
            // p[i] = i;
        }

        int ans = 0;
        // v-1개의 간선 뽑기
        for (int i = 0, pick = 0; i < E && pick < V - 1; i++) {
            int from = edges[i].from;
            int to = edges[i].to;

            if(findSet(from) != findSet(to)) {
                //union
                union(from, to);
                pick++;
                ans += edges[i].cost;
            }
        }

        System.out.println(ans);

    }// main

    // x를 자기자신의 집합의 대표로 세팅
    static void makeSet(int x) {
        p[x] = x;
    }

    // 해당 원소가 속한 집합 대표 찾기
    static int findSet(int x) {
        // 기본 형태
        // if(x == p[x]) return x;

        // return findSet(p[x]);

        // 경로 압축
        if(x != p[x]) {
            p[x] = findSet(p[x]);
        }
        return p[x];
    }

    // 두 집합 합치기
    static void union(int x, int y) {
        // 집합의 대표를 연결하기
        p[findSet(y)] = findSet(x);

        // if(rank[findSet(x)] > rank[findSet(y)]) {
        //     p[findSet(y)] = findSet(x);
        // }
        // else {
        //     // rank 가 같다면 rank + 1
        // }
    }
}
