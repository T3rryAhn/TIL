package algorithm.graph.dfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import data_structure.stack.array_stack.Stack;

public class DFS_01_그래프 {
    static int V, E; // 정점의 개수(1번 부터), 간선의 개수
    static int[][] adj; // 인접 행렬
    static boolean[] visited; // 방문처리

    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("algorithm\\graph\\dfs\\input.txt");
        Scanner sc = new Scanner(input);
        // Scanner sc = new Scanner(System.in);

        V = sc.nextInt();
        E = sc.nextInt();

        adj = new int[V + 1][V + 1];
        visited = new boolean[V + 1];

        // 간선 입력
        for (int i = 0; i < E; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            adj[A][B] = adj[B][A] = 1;
        }

        // dfs(1);
        dfsStack(1);
    }

    // v 현재 방문 정점
    static void dfs(int v) {
        visited[v] = true;

        System.out.println(v);

        for (int i = 1; i <= V; i++) {
            if (!visited[i] && adj[v][i] != 0) {
                dfs(i);
            }
        }
    }// /dfs

    static void dfsStack(int start) {
        Stack stack = new Stack();      // << 이전에 내가 만든 stack
        visited = new boolean[V+1];

        stack.push(start);              // 시작 정점 스택에 넣고 시작
        visited[start] = true;

        while(!stack.isEmpty()) {
            // 현재 정점의 번호를 꺼냄.
            // 여러개의 정보라면 class, int[] 일수도 있음
            int curr = stack.pop();

            System.out.println(curr);
            
            for(int i = V; i > 0; i--) {
                if(!visited[i] && adj[curr][i] != 0) {
                    stack.push(i);
                    visited[i] = true;
                }
            }
        }
    }// /dfsStack
}