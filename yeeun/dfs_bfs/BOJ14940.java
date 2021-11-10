package codingstudy202107.w19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 18주차
 * 문제 출처 : https:/www.acmicpc.net/problem/14940
 * 이름 : 쉬운 최단거리
 * 사용 알고리즘 : bfs
 */
public class BOJ14940 {
    public static final int LINE = 0;
    public static final int COL = 1;
    public static final int VAL = 2;
    public static final int DISTANCE = 3;

    public static final int NOT_VISITED = -1;
    public static final int CANNOT_VISIT = 0;


    public static void main(String[] args) throws IOException {
        BOJ14940 main = new BOJ14940();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int lineLen = Integer.parseInt(st.nextToken()), colLen = Integer.parseInt(st.nextToken());

        Queue<int[]> queue = new ArrayDeque<>();

        int[][][] arr = new int[lineLen][colLen][4];
        for (int line = 0; line < lineLen; line++) {
            st = new StringTokenizer(reader.readLine());
            for (int col = 0; col < colLen; col++) {
                int val = Integer.parseInt(st.nextToken());
                arr[line][col][LINE] = line;
                arr[line][col][COL] = col;
                arr[line][col][VAL] = val;
                arr[line][col][DISTANCE] = NOT_VISITED;
                if (val == 2) {
                    arr[line][col][DISTANCE] = 0;
                    queue.add(arr[line][col]);
                } else if(val==0){
                    arr[line][col][DISTANCE]=CANNOT_VISIT;
                }
            }
        }

        main.solution(arr, queue);

    }

    private void printResult(int[][][] arr) {
        StringBuilder builder = new StringBuilder();
        for (int[][] line : arr) {
            for (int[] node : line) {
                builder.append(node[DISTANCE]).append(" ");
            }
            builder.append('\n');
        }
        builder.setLength(builder.length()-1);
        System.out.print(builder);
    }

    private void solution(int[][][] arr, Queue<int[]> queue) {
        int lineLen = arr.length, colLen = arr[0].length;

        while (!queue.isEmpty()) {
            int[] target = queue.poll();
            if (target[VAL] == 0) {//갈 수 없는 땅.
                target[DISTANCE] = CANNOT_VISIT;
            } else {
                int targetLine = target[LINE];
                int targetCol = target[COL];
                int nextDistance = target[DISTANCE] + 1;
                if (targetLine > 0) {
                    int[] next = arr[targetLine - 1][targetCol];
                    if (next[DISTANCE] == NOT_VISITED) {
                        next[DISTANCE] = nextDistance;
                        queue.add(next);
                    }
                }
                if (targetLine < lineLen - 1) {
                    int[] next = arr[targetLine + 1][targetCol];
                    if (next[DISTANCE] == NOT_VISITED) {
                        next[DISTANCE] = nextDistance;
                        queue.add(next);
                    }
                }
                if (targetCol > 0) {
                    int[] next = arr[targetLine][targetCol - 1];
                    if (next[DISTANCE] == NOT_VISITED) {
                        next[DISTANCE] = nextDistance;
                        queue.add(next);
                    }
                }
                if (targetCol < colLen - 1) {
                    int[] next = arr[targetLine][targetCol + 1];
                    if (next[DISTANCE] == NOT_VISITED) {
                        next[DISTANCE] = nextDistance;
                        queue.add(next);
                    }
                }
            }
        }
        this.printResult(arr);
    }


}
