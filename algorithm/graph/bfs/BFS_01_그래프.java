package algorithm.graph.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BFS_01_그래프 {
	static int V, E;
	static List<Integer>[] adjList;// 인접리스트
	static boolean[] visited; // 방문쳌
	static Queue<Integer> q;

	// 정점 7개, 간선 9개
	// 두개의 정점이 주어진다.
	// 무향!
	static String input = "7 9\r\n"
			+ "1 2\r\n"
			+ "1 3\r\n"
			+ "1 6\r\n"
			+ "2 4\r\n"
			+ "2 7\r\n"
			+ "3 4\r\n"
			+ "4 7\r\n"
			+ "5 6\r\n"
			+ "5 7\r\n"
			+ "";

	public static void main(String[] args) {
		// Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(input);

		V = sc.nextInt();
		E = sc.nextInt();

		adjList = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}

		visited = new boolean[V + 1];

		for (int i = 0; i < E; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();

			adjList[A].add(B);
			adjList[B].add(A);
			// 유향이 라면
			// adjList[sc.nextInt()].add(sc.nextInt());
		}

		bfs(1);

	}

	// v : 시작정점
	static void bfs(int v) {
		q = new LinkedList<>();

		q.add(v); // 시작정점 넣기
		visited[v] = true;

		while (!q.isEmpty()) {
			int curr = q.poll();
			System.out.println(curr);

			for (int w : adjList[curr]) {
				if (!visited[w]) {
					q.add(w);
					visited[w] = true;
				}
			}
		} // BFS

	}

}
