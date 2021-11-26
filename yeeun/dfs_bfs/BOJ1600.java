package codingstudy202107.w20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 20주차
 * 문제 출처 : https:/www.acmicpc.net/problem/1600
 * 이름 : 말이 되고픈 원숭이
 * 사용 알고리즘 : bfs
 */
public class BOJ1600 {
    public static int TYPE = 0;
    public static int LINE = 1;
    public static int COL = 2;

    public static int VISITED = 0;
    public static int COUNT_ = 1;

    public static int VISITED_TRUE = 1;
    public static int VISITED_FALSE = 0;

    public static int TYPE_SPACE = 0;
    public static int TYPE_WALL = 1;

    public static int[][] coordinates = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static int[][] horseCoordinates = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
            {1, -2}, {1, 2}, {2, -1}, {2, 1}};

    public static void main(String[] args) throws IOException {
        BOJ1600 main = new BOJ1600();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int jumpLimit = Integer.parseInt(reader.readLine());

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int colLen = Integer.parseInt(st.nextToken());
        int lineLen = Integer.parseInt(st.nextToken());
        int[][][] arr = new int[lineLen][colLen][3];

        for (int line = 0; line < lineLen; line++) {
            st = new StringTokenizer(reader.readLine());
            for (int col = 0; col < colLen; col++) {
                int[] node = arr[line][col];
                node[LINE] = line;
                node[COL] = col;
                node[TYPE] = Integer.parseInt(st.nextToken());
            }
        }

        long[][][][] visited = new long[lineLen][colLen][jumpLimit + 1][2];
        Queue<Pair<int[], Integer>> queue = new ArrayDeque<>();
        main.addNextNode(visited, queue, 0, 0, arr, 0, 0);

        System.out.print(main.solution(visited, arr, queue, jumpLimit));
    }


    private long solution(long[][][][] visited, int[][][] arr, Queue<Pair<int[], Integer>> queue, int jumpLimit) {
        while (!queue.isEmpty()) {
            Pair<int[], Integer> pair = queue.poll();
            int[] node = pair.key;
            int nodeJumpCount = pair.value;

            int line = node[LINE];
            int col = node[COL];
            if (line == arr.length - 1 && col == arr[0].length - 1) return visited[line][col][nodeJumpCount][COUNT_];

            long nextCount = visited[line][col][nodeJumpCount][COUNT_] + 1;

            for (int[] coord : coordinates) {
                this.addNextNode(visited, queue, nodeJumpCount, nextCount, arr, line, col, coord);
            }

            if (nodeJumpCount < jumpLimit) {
                int nextJumpCount = nodeJumpCount + 1;
                for (int[] coord : horseCoordinates) {//점프 횟수 남음.
                    this.addNextNode(visited, queue, nextJumpCount, nextCount, arr, line, col, coord);
                }
            }
        }
        return -1;
    }

    public void addNextNode(long[][][][] visited, Queue<Pair<int[], Integer>> queue, int nextJumpCount, long nextCount, int[][][] arr, int line, int col, int[] coord) {
        int nextLine = line + coord[0], nextCol = col + coord[1];
        this.addNextNode(visited, queue, nextJumpCount, nextCount, arr, nextLine, nextCol);
    }
    public void addNextNode(long[][][][] visited, Queue<Pair<int[], Integer>> queue, int nextJumpCount, long nextCount, int[][][] arr, int nextLine, int nextCol) {
        try {
            int[] next = arr[nextLine][nextCol];
            if (next[TYPE] == TYPE_SPACE && visited[nextLine][nextCol][nextJumpCount][VISITED] == VISITED_FALSE) {//빈공간이고, 들른 적 없음.
                visited[nextLine][nextCol][nextJumpCount][VISITED] = VISITED_TRUE;
                visited[nextLine][nextCol][nextJumpCount][COUNT_] = nextCount;
                queue.add(new Pair<>(next, nextJumpCount));
            }
        } catch (ArrayIndexOutOfBoundsException ignore) {
        }
    }


    public static class Pair<T, K> {
        T key;
        K value;

        public Pair(T key, K value) {
            this.key = key;
            this.value = value;
        }
    }
}
