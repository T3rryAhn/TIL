package algorithm.graph.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS_02_배열_dist {
    static int N; // NxN
    static int[][] map;
    static int[][] dist; // 거리 저장 && 방문 체크

    // 4 방향 탐색 상하 좌우
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    // input
    static String input = "8\r\n"
            + "0 0 1 1 1 1 1 1\r\n"
            + "1 0 0 0 0 0 0 1\r\n"
            + "1 1 1 0 1 1 1 1\r\n"
            + "1 1 1 0 1 1 1 1\r\n"
            + "1 0 0 0 0 0 0 1\r\n"
            + "1 0 1 1 1 1 1 1\r\n"
            + "1 0 0 0 0 0 0 0\r\n"
            + "1 1 1 1 1 1 1 0\r\n"
            + "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(input);
        N = sc.nextInt();
        map = new int[N][N];
        dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 출발지 0,0; 도착지 N-1, N-1;
        bfs(0, 0);

        for(int[] row : dist) {
            for(int col : row) {
                System.out.printf("%3d", col);
            }
            System.out.println();
        }

        System.out.println("dist: " + dist[N-1][N-1]);
    }

    static void bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[] { r, c });

        dist[r][c] = 1;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            // 도착지 확인
            if (curr[0] == N - 1 && curr[1] == N - 1) {
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = curr[0] + dr[i];
                int nc = curr[1] + dc[i];

                // check bound
                if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                // check wall
                if(map[nr][nc] == 1) continue;
                // check distance
                if(dist[nr][nc] != 0) continue;

                dist[nr][nc] = dist[curr[0]][curr[1]] + 1;
                q.add(new int[] {nr, nc});
            }
        }
    }
}
