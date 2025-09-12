package algorithm.graph.topological_sorting;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 위상정렬_Queue {
    public static String[] cook = { "", "재료구매", "양념장만들기", "고기재우기", "고기손질", "제육볶음만들기", "식사", "뒷정리", "채소손질", "밥하기" };

    static String input = "9 9\r\n" + //
            "1 4\r\n" + //
            "1 8\r\n" + //
            "2 3\r\n" + //
            "4 3\r\n" + //
            "8 5\r\n" + //
            "3 5\r\n" + //
            "5 6\r\n" + //
            "9 6\r\n" + //
            "6 7";

    public static void main(String[] args) {
        Scanner sc = new Scanner(input);

        int V = sc.nextInt(); // 1번 인덱싱
        int E = sc.nextInt();

        int[][] adjArr = new int[V + 1][V + 1]; // 인접행렬
        int[] inDegree = new int[V + 1]; // 진입차수를 저장할 배열(선행 과제의 개수)

        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            adjArr[from][to] = 1;
            inDegree[to]++; // 진입차수 1 증가

        }

        // 위상정렬 시작
        Queue<Integer> q = new LinkedList<>();

        // 1. 진입 차수가 0인 정점 전부 넣기
        for (int i = 1; i < V + 1; i++) {
            if (inDegree[i] == 0)
                q.add(i);
        }

        // 2. 큐가 공백이 될때까지 반복
        while(!q.isEmpty()) {
            int curr = q.poll();

            // 작업
            System.out.println(cook[curr]);

            //curr 인접한 정점을 순회하면서 간선 제거
            for(int i = 1; i < V + 1; i++) {
                if(adjArr[curr][i] == 1) {
                    inDegree[i]--;

                    if(inDegree[i] == 0) {
                        q.add(i);
                    }
                }
            }

        }
    }

}