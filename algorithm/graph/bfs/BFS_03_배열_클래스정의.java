package algorithm.graph.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS_03_배열_클래스정의 {
    static class Pos {
        int r, c, dist;

        public Pos(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

    }

    static int N; // NxN
    static int[][] map;
    static boolean[][] vistied;

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
        vistied = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 출발지 0,0; 도착지 N-1, N-1;
        int ans = bfs(0, 0);

        System.out.println(ans);
    }

    static int bfs(int r, int c) {
        Queue<Pos> q = new LinkedList<>();

        q.add(new Pos(r, c, 1));

        vistied[r][c] = true;

        while (!q.isEmpty()) {
            Pos curr = q.poll();

            // 도착지 확인
            if (curr.r == N - 1 && curr.c == N - 1) {
                return curr.dist;
            }

            for (int i = 0; i < 4; i++) {
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];

                // check bound
                if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                // check wall
                if(map[nr][nc] == 1) continue;
                // check distance
                if(vistied[nr][nc]) continue;

                q.add(new Pos(nr, nc, curr.dist + 1));
            }
        }

        return -1;
    }
}
