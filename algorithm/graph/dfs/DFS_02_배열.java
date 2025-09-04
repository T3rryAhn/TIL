package algorithm.graph.dfs;

import java.util.Scanner;

public class DFS_02_배열 {
    static int N;
    static String[][] map;
    static boolean[][] visited;
    static boolean ans; // 탈출가능 여부

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};    // 상하좌우

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
        map = new String[N][N]; // 테두리에 벽을 한줄 세우는 기법이라면 -> N+2 N+2
        visited = new boolean[N][N]; // 원본을 훼손하는 코드라면, 필요없을수도있음.
        ans = false;

        // 입력 시작
        // 입구, 출구 정보등 중요한 좌표 정보를 입력단계에서 미리 기록을 해둘 수 도 있다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int tmp = sc.nextInt();
                if(tmp == 1) {
                    map[i][j] = "❎";
                } else {
                    map[i][j] = " ";
                }
            }
        }
        map[N-1][N-1] = "🚪";

        dfs(0, 0);

        System.out.println(ans);


    }// /main

    static void dfs(int r, int c) {
        visited[r][c] = true;
        map[r][c] = "🦔";

        for(String[] row : map) {
            for(String col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------");


        //end
        if(r == N - 1 && c == N - 1) {
            ans = true;
            return;
        }

        //dfs

        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            // is in bound?
            if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
            // is not wall?
            if(map[nr][nc] != " ") continue;
            // visited?
            if(visited[nr][nc]) continue;   // 만약 visited 안쓰고 방문한곳을 벽으로 처리할수도있음
            
            dfs(nr, nc);
        }
    }
}
