package codingstudy202107.w20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 20주차
 * 문제 출처 : https:/www.acmicpc.net/problem/16954
 * 이름 : 벽 부수고 이동하기
 * 사용 알고리즘 : bfs
 */
public class BOJ2206 {
    public static int TYPE = 0;
    public static int LINE = 1;
    public static int COL = 2;
    public static int VISITED_WALLBROKEN = 3;
    public static int VISITED_WALLNOTBROKEN = 4;

    public static int TYPE_SPACE = 0;
    public static int TYPE_WALL = 1;
    public static int VISITED_FALSE = Integer.MAX_VALUE;

    public static int[][] coordinates = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};


    public static void main(String[] args) throws IOException {
        BOJ2206 main = new BOJ2206();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        int lineLen = Integer.parseInt(st.nextToken());
        int colLen = Integer.parseInt(st.nextToken());

        int[][][] arr = new int[lineLen][colLen][5];

        for (int line = 0; line < lineLen; line++) {
            String str = reader.readLine();
            for (int col = 0; col < colLen; ) {
                int[] node = arr[line][col];
                node[LINE] = line;
                node[COL] = col;
                node[TYPE] = Integer.parseInt(str.substring(col, ++col));
                node[VISITED_WALLNOTBROKEN] = VISITED_FALSE;
                node[VISITED_WALLBROKEN] = VISITED_FALSE;
            }
        }

        Queue<Pair<int[], Boolean>> queue = new ArrayDeque<>();
        int[] start = arr[0][0];
        start[VISITED_WALLNOTBROKEN] = 1;
        queue.add(new Pair<>(start, false));

        main.solution(arr, queue);

        int resultCount=Math.min(arr[lineLen - 1][colLen - 1][VISITED_WALLNOTBROKEN], arr[lineLen - 1][colLen - 1][VISITED_WALLBROKEN]);
        System.out.print(resultCount!=VISITED_FALSE? resultCount: -1);
    }

    private void solution(int[][][] arr, Queue<Pair<int[], Boolean>> queue) {
        while (!queue.isEmpty()) {
            Pair<int[], Boolean> pair = queue.poll();
            int[] node = pair.key;
            int line = node[LINE];
            int col = node[COL];
            if (line == arr.length - 1 && col == arr[0].length - 1) break;

            boolean wallBroken = pair.value;
            int targetVit = wallBroken ? VISITED_WALLBROKEN : VISITED_WALLNOTBROKEN;
            int nextCount = node[targetVit] + 1;

            for (int[] coord : coordinates) {
                try {
                    int[] next = arr[line + coord[0]][col + coord[1]];
                    if (next[TYPE] == TYPE_SPACE && next[targetVit] == VISITED_FALSE) {//빈공간이고, 들른 적 없음.
                        next[targetVit] = nextCount;
                        queue.add(new Pair<>(next, wallBroken));
                    } else if (!wallBroken && next[TYPE] == TYPE_WALL && next[targetVit] == VISITED_FALSE) {//벽 부순 적 없고, 벽이고, 들른 적 없음.->  벽 부수고 진행.
                        next[VISITED_WALLBROKEN] = nextCount;
                        queue.add(new Pair<>(next, true));
                    }
                } catch (ArrayIndexOutOfBoundsException ignore) {}
            }


        }

    }

    public static class Pair<T,K> {
        T key;
        K value;

        public Pair(T key, K value){
            this.key=key;
            this.value=value;
        }
    }


}
