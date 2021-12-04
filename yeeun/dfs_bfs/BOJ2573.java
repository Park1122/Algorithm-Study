package codingstudy202107.w21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 21주차
 * 문제 출처 : https:/www.acmicpc.net/problem/2573
 * 이름 : 빙산
 * 사용 알고리즘 : bfs
 */
public class BOJ2573 {
    public static final int[][] COORDINATES = new int[][]{{-1, -0}, {0, -1}, {0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BOJ2573 main = new BOJ2573();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int lineLen = Integer.parseInt(st.nextToken());
        int colLen = Integer.parseInt(st.nextToken());
        Node[][] arr = new Node[lineLen][colLen];

        for (int line = 0; line < lineLen; line++) {
            st = new StringTokenizer(reader.readLine());
            for (int col = 0; col < colLen; col++) {
                int val = Integer.parseInt(st.nextToken());
                if (val > 0) {
                    Node node = new Node(line, col, val);
                    arr[line][col] = node;
                }
            }
        }
        System.out.print(main.solution(arr));
    }


    private int solution(Node[][] arr) {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited;
        Node[][] nextArr;

        int sepCount = 1;
        int timePassed = 0;
        while (sepCount > 0) {
            sepCount = 0;
            visited = new boolean[arr.length][arr[0].length];
            nextArr = new Node[arr.length][arr[0].length];

            for (int line = 1; line < arr.length - 1; line++) {
                for (int col = 1; col < arr[0].length - 1; col++) {
                    if (!visited[line][col] && arr[line][col] != null) {
                        sepCount++;
                        if (sepCount > 1) return timePassed;
                        Node node = arr[line][col];
                        visited[line][col] = true;
                        queue.add(node);
                        this.bfs(queue, arr, visited, nextArr);
                    }
                }
            }
            timePassed++;
            arr = nextArr;
        }
        return 0;
    }

    private void printArr(int timePassed, int sepCount, Node[][] arr) {
        System.out.println("----- " + timePassed + "->" + sepCount + " -----");
        for (Node[] line : arr) {
            for (Node node : line) System.out.print(node == null ? 0 + " " : node.val + " ");
            System.out.println();
        }
        System.out.println();
    }


    private void bfs(Queue<Node> queue, Node[][] arr, boolean[][] visited, Node[][] nextArr) {
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int line = node.line, col = node.col;
            int meltedHeight = node.val - 4;

            for (int[] coord : COORDINATES) {
                try {
                    int nextLine = line + coord[0], nextCol = col + coord[1];
                    if (arr[nextLine][nextCol] != null) {
                        meltedHeight += 1;//인접한 얼음칸이 있으므로 녹인거 1만큼 취소.
                        if (!visited[nextLine][nextCol]) {
                            visited[nextLine][nextCol] = true;
                            queue.add(arr[nextLine][nextCol]);
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }

            if (meltedHeight <= 0) node.val = 0;
            else {
                node.val = meltedHeight;
                nextArr[node.line][node.col] = node;
            }
        }
    }

    public static class Node {
        int line, col, val;

        public Node(int line, int col, int val) {
            this.line = line;
            this.col = col;
            this.val = val;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

}