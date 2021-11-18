package codingstudy202107.w19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 19주차
 * 문제 출처 : https:/www.acmicpc.net/problem/16973
 * 이름 : 직사각형 탈출
 * 사용 알고리즘 : bfs
 * 으아아 계속 가로세로 좌표 헷갈려서 난리났었습니다. 이제 다시는 i j 안써...
 */
public class BOJ16973 {
    private static int count = 0;

    private static final int VAL = 0;
    private static final int ROW_NUM = 1;
    private static final int COL_NUM = 2;
    private static final int VISITED = 3;

    private static final int VAL_SPACE = 0;
    private static final int VAL_WALL = 1;
    private static final int VISITED_FALSE = -2;

    private static final int CANNOT_VISIT = -1;

    public static void main(String[] args) throws IOException {
        BOJ16973 main = new BOJ16973();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int rowLen = Integer.parseInt(st.nextToken()), colLen = Integer.parseInt(st.nextToken());

        Queue<int[]> queue = new ArrayDeque<>();

        int[][][] arr = new int[rowLen][colLen][4];
        for (int row = 0; row < rowLen; row++) {
            st = new StringTokenizer(reader.readLine());
            for (int col = 0; col < colLen; col++) {
                count++;
                arr[row][col][ROW_NUM] = row;
                arr[row][col][COL_NUM] = col;
                int val = Integer.parseInt(st.nextToken());
                if (val == VAL_SPACE) {
                    arr[row][col][VAL] = VAL_SPACE;
                    arr[row][col][VISITED] = VISITED_FALSE;
                } else {
                    arr[row][col][VAL] = VAL_WALL;
                    arr[row][col][VISITED] = CANNOT_VISIT;
                    queue.add(arr[row][col]);
                }
            }
        }


        st = new StringTokenizer(reader.readLine());
        int squareH = Integer.parseInt(st.nextToken());
        int squareW = Integer.parseInt(st.nextToken());

        int startRow = Integer.parseInt(st.nextToken()) - 1;
        int startCol = Integer.parseInt(st.nextToken()) - 1;

        int destinyRow = Integer.parseInt(st.nextToken()) - 1;
        int destinyCol = Integer.parseInt(st.nextToken()) - 1;

        int result = main.solution(arr, queue, squareH, squareW, destinyRow, destinyCol, startRow, startCol);
        System.out.print(result >= 0 ? result : -1);
    }


    private int solution(int[][][] arr, Queue<int[]> queue, int squareH, int squareW, int destinyRow, int destinyCol, int startRow, int startCol) {

        if (squareH > 1) {
            for (int i = arr.length - 1; i > arr.length-1 - squareH+1; i--) {
                for (int[] node : arr[i]) {
                    count++;
                    node[VISITED] = CANNOT_VISIT;
                }
            }
        }

        if (squareW > 1) {
            for (int row = 0; row <= arr.length - squareH; row++) {
                for (int col = arr[0].length - 1; col > arr[0].length - squareW; col--) {
                    count++;
                    arr[row][col][VISITED] = CANNOT_VISIT;
                }
            }
        }

//        print(arr);


        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int nodeCol = node[COL_NUM];
            int nodeRow = node[ROW_NUM];
            for (int col = nodeCol; col >= Math.max(0, nodeCol - squareW + 1); col--) {
                for (int row = nodeRow; row >= Math.max(0, nodeRow - squareH + 1); row--) {
                    if (arr[row][col][VISITED] == VISITED_FALSE) {
                        count++;
                        arr[row][col][VISITED] = CANNOT_VISIT;
                    }
                }
            }
        }//불가능한 위치를 모두 저장한다.

//        print(arr);

        int[] start = arr[startRow][startCol];
        if (start[VISITED] != VISITED_FALSE) return start[VISITED];
        start[VISITED] = 0;
        queue.add(start);

        while (!queue.isEmpty()) {
            count++;
            int[] node = queue.poll();
            int nodeRow = node[ROW_NUM];
            int nodeCol = node[COL_NUM];
            int visited = node[VISITED];

            if (nodeRow ==destinyRow  && nodeCol == destinyCol) return visited;
            int nextVisited = visited + 1;

            if (nodeRow - 1 >= 0) {
                this.addNodeNotVisitedToQueue(arr[nodeRow - 1][nodeCol], nextVisited, queue);
            }
            if (nodeRow + 1 < arr.length) {
                this.addNodeNotVisitedToQueue(arr[nodeRow + 1][nodeCol], nextVisited, queue);
            }
            if (nodeCol - 1 >= 0) {
                this.addNodeNotVisitedToQueue(arr[nodeRow][nodeCol - 1], nextVisited, queue);
            }
            if (nodeCol + 1 < arr[0].length) {
                this.addNodeNotVisitedToQueue(arr[nodeRow][nodeCol + 1], nextVisited, queue);
            }
        }
//        System.out.println("답 "+arr[destinyRow][destinyCol][ROW_NUM] + ", " + arr[destinyRow][destinyCol][COL_NUM] + " = " + arr[destinyRow][destinyCol][VISITED]);
        return arr[destinyRow][destinyCol][VISITED];
    }

    private void addNodeNotVisitedToQueue(int[] node, int visited, Queue<int[]> queue) {
        if (node[VISITED] == VISITED_FALSE) {
            node[VISITED] = visited;
            queue.add(node);
        }
    }


    private void print(int[][][] arr) {
        System.out.println();
        for (int[][] line : arr) {
            for (int[] node : line) {
                System.out.print(node[VISITED] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
