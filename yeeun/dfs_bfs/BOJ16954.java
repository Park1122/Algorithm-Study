package codingstudy202107.w20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 20주차
 * 문제 출처 : https:/www.acmicpc.net/problem/16954
 * 이름 : 움직이는 미로 탈출
 * 사용 알고리즘 : dfs
 */
public class BOJ16954 {
    //idx static val
    public static final int TYPE = 0;
    public static final int LINE = 1;
    public static final int COL = 2;
    public static final int NO_WALL_VISITED = 3;

    //val static val
    public static final int TYPE_SPACE = 0;
    public static final int TYPE_WALL = 1;
    public static final int NO_WALL_VISITED_FALSE = 0;
    public static final int NO_WALL_VISITED_TRUE = 1;

    //val movable coordinate dx dy
    public static final int[][] COORDINATES = new int[][]{
            {-1, -1}, {-1, -0}, {-1, 1},
            {0, -1}, {0, 0}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };

    public static void main(String[] args) throws IOException {
        BOJ16954 main = new BOJ16954();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[][][] arr = new int[8][8][4];
        int wallCount = 0;

        for (int line = 0; line < 8; line++) {
            String st = reader.readLine();
            for (int col = 0; col < 8; col++) {
                boolean isWall = st.charAt(col) == '#';
                if (isWall) wallCount++;
                arr[col][line][TYPE] = isWall ? TYPE_WALL : TYPE_SPACE;
                arr[col][line][COL] = col;
                arr[col][line][LINE] = line;
//                System.out.println(arr[col][line][LINE] + ", " + arr[col][line][COL] + " = " + arr[col][line][TYPE]);
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(arr[0][7]);

        System.out.print(main.solution(arr, queue, wallCount));
    }

    private int solution(int[][][] arr, Queue<int[]> queue, int wallCount) {

        Vector<int[]> nextMoves = new Vector<>();

        while (wallCount != 0) {
            nextMoves.clear();
            this.moveOnce(arr, queue,nextMoves);
            wallCount = this.wallMove(arr, wallCount);
            queue.addAll(nextMoves);
        }

        return this.moveAfterNoWall(arr, queue);
    }

    private int moveAfterNoWall(int[][][] arr, Queue<int[]> queue) {
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int line = node[LINE];
            int col = node[COL];
            if (line == 0 && col == 7) return 1;
            node[NO_WALL_VISITED] = NO_WALL_VISITED_TRUE;
            for (int[] dcoord : COORDINATES) {
                try {
                    int[] nextNode = arr[col + dcoord[1]][line + dcoord[0]];
                    if (nextNode[TYPE] == TYPE_SPACE && nextNode[NO_WALL_VISITED] == NO_WALL_VISITED_FALSE) {
                        nextNode[NO_WALL_VISITED] = NO_WALL_VISITED_TRUE;
                        queue.add(nextNode);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    //
                }
            }
        }
        return 0;
    }

    private int wallMove(int[][][] arr, int wallCount) {
        int count = 0;
        for (int col = 0; col < 8; col++) {
            int nextType = TYPE_SPACE;
            for (int[] node : arr[col]) {
                int tempType = node[TYPE];
                node[TYPE] = nextType;
                nextType = tempType;
            }
            if (nextType == TYPE_WALL) count++;
        }
        return wallCount - count;
    }

    private void moveOnce(int[][][] arr, Queue<int[]> queue, Vector<int[]> nextMoves) {
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int nodeType = node[TYPE];
            if (nodeType == TYPE_SPACE) {
                int line = node[LINE];
                int col = node[COL];
                for (int[] dcoord : COORDINATES) {
                    try {
                        int[] nextNode = arr[col + dcoord[1]][line + dcoord[0]];
                        if (nextNode[TYPE] == TYPE_SPACE) {
                            nextMoves.add(nextNode);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //
                    }
                }
            }
        }
    }

}