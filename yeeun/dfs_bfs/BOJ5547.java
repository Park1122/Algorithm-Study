package codingstudy202107.w18;

import org.omg.Messaging.SyncScopeHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 18주차
 * 문제 출처 : https:/www.acmicpc.net/problem/5547
 * 이름 : 일루미네이션
 * 사용 알고리즘 : bfs
 */
public class BOJ5547 {
    private static int bfsCount = -1;
    private static final int VAL = 0;
    private static final int X = 1;
    private static final int Y = 2;
    private static final int VISITED = 3;

    private static final int VISITED_FALSE = 0;
    private static final int VISITED_TRUE = 1;
    private static final int VISITED_OUT_DOOR = 2;
    private static final int VISITED_FINISHED = 3;

    public static void main(String[] args) throws IOException {
        BOJ5547 main = new BOJ5547();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int colLen = Integer.parseInt(st.nextToken()), lineLen = Integer.parseInt(st.nextToken());


        Queue<int[]> queue = new ArrayDeque<>();

        int[][][] arr = new int[lineLen][colLen][4];
        for (int line = 0; line < lineLen; line++) {
            st = new StringTokenizer(reader.readLine());
            for (int col = 0; col < colLen; col++) {
                arr[line][col][VAL] = Integer.parseInt(st.nextToken());
                arr[line][col][X] = col;
                arr[line][col][Y] = line;
                if (line == 0 || col == 0 || line == lineLen - 1 || col == colLen - 1) {
                    arr[line][col][VISITED] = VISITED_TRUE;
                    queue.add(arr[line][col]);
                }
            }
        }

        int result = main.solution(arr, queue);

        System.out.print(result);
    }
    private void addNodesToCheck(int[][][] arr, Queue<int[]> queue, int x, int y, boolean isEvenLine) {
        if (!isEvenLine) {
            if (y > 0) {
                    queue.add(arr[y - 1][x]);
                if (x > 0) {
                    queue.add(arr[y - 1][x - 1]);
                }
            }
            if (y < arr.length - 1) {
                if (x > 0) {
                    queue.add(arr[y + 1][x - 1]);
                }
                    queue.add(arr[y + 1][x]);
            }
        } else {
            if (y > 0) {
                    queue.add(arr[y - 1][x]);
                if (x < arr[0].length - 1) {
                    queue.add(arr[y - 1][x + 1]);
                }
            }
            if (y < arr.length - 1) {
                if (x < arr[0].length - 1) {
                    queue.add(arr[y + 1][x + 1]);
                }
                    queue.add(arr[y + 1][x]);
            }
        }
        if (x > 0 ) {
            queue.add(arr[y][x - 1]);
        }
        if (x < arr[0].length - 1) {
            queue.add(arr[y][x + 1]);
        }
    }

    private void addNodes(int[][][] arr, Queue<int[]> queue, int x, int y, boolean isEvenLine) {
        if (!isEvenLine) {
            if (y > 0) {
                if (arr[y - 1][x][VISITED] == VISITED_FALSE) {
                    arr[y - 1][x][VISITED] = VISITED_TRUE;
                    queue.add(arr[y - 1][x]);
                }
                if (x > 0 && arr[y - 1][x - 1][VISITED] == VISITED_FALSE) {
                    arr[y - 1][x - 1][VISITED] = VISITED_TRUE;
                    queue.add(arr[y - 1][x - 1]);
                }
            }

            if (y < arr.length - 1) {
                if (x > 0 && arr[y + 1][x - 1][VISITED] == VISITED_FALSE) {
                    arr[y + 1][x - 1][VISITED] = VISITED_TRUE;
                    queue.add(arr[y + 1][x - 1]);
                }
                if (arr[y + 1][x][VISITED] == VISITED_FALSE) {
                    arr[y + 1][x][VISITED] = VISITED_TRUE;
                    queue.add(arr[y + 1][x]);
                }
            }
        } else {
            if (y > 0) {
                if (arr[y - 1][x][VISITED] == VISITED_FALSE) {
                    arr[y - 1][x][VISITED] = VISITED_TRUE;
                    queue.add(arr[y - 1][x]);
                }
                if (x < arr[0].length - 1 && arr[y - 1][x + 1][VISITED] == VISITED_FALSE) {
                    arr[y - 1][x + 1][VISITED] = VISITED_TRUE;
                    queue.add(arr[y - 1][x + 1]);
                }
            }

            if (y < arr.length - 1) {
                if (x < arr[0].length - 1 && arr[y + 1][x + 1][VISITED] == VISITED_FALSE) {
                    arr[y + 1][x + 1][VISITED] = VISITED_TRUE;
                    queue.add(arr[y + 1][x + 1]);
                }
                if (arr[y + 1][x][VISITED] == VISITED_FALSE) {
                    arr[y + 1][x][VISITED] = VISITED_TRUE;
                    queue.add(arr[y + 1][x]);
                }
            }


        }
        if (x > 0 && arr[y][x - 1][VISITED] == VISITED_FALSE) {
            arr[y][x - 1][VISITED] = VISITED_TRUE;
            queue.add(arr[y][x - 1]);
        }
        if (x < arr[0].length - 1 && arr[y][x + 1][VISITED] == VISITED_FALSE) {
            arr[y][x + 1][VISITED] = VISITED_TRUE;
            queue.add(arr[y][x + 1]);
        }

    }

    private void print(int[][][] arr) {
        for (int[][] line : arr) {
            for (int[] node : line) {
                System.out.print(node[VISITED]+" ");
            }
            System.out.println();
        }

    }

    private int solution(int[][][] arr, Queue<int[]> queue) {

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int x = node[X];
            int y = node[Y];
            int val = node[VAL];


            if (val == 0) {
                node[VISITED] = VISITED_OUT_DOOR;
                boolean isEvenLine = y % 2 == 0;
                this.addNodes(arr, queue, x, y, isEvenLine);
            }
        }

        int surroundLen=0;
        for (int[][] line : arr) {
            for (int[] node : line) {
                int val = node[VAL];
                if(val==1){
                    int count=6;
                    int x = node[X];
                    int y = node[Y];
                    boolean isEvenLine = y % 2 == 0;
                    this.addNodesToCheck(arr,queue,x,y,isEvenLine);
                    while(!queue.isEmpty()){
                        int[] next = queue.poll();
                        if(next[VISITED]!=VISITED_OUT_DOOR)count--;
                    }
                    surroundLen+=count;
                }
            }
        }
        return surroundLen;
    }




}
