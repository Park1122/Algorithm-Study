package codingstudy202107.w14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 14주차
 * 문제 출처 : https://www.acmicpc.net/problem/14502
 * 이름 : 연구소
 * 사용 알고리즘 : dfs bfs
 */
public class BOJ14502 {

    public static void main(String[] args) throws IOException {
        BOJ14502 main = new BOJ14502();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Node[][] arr = new Node[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = new Node(Integer.parseInt(st.nextToken()));
                if (i > 0) {
                    arr[i][j].addConnect(arr[i - 1][j]);
                    arr[i - 1][j].addConnect(arr[i][j]);
                }

                if (j > 0) {
                    arr[i][j].addConnect(arr[i][j - 1]);
                    arr[i][j - 1].addConnect(arr[i][j]);
                }
            }
        }
        System.out.print(main.solution(arr));
    }


    private int solution(Node[][] arr) {
        return this.createWalls(arr, 0, 0, 0, new int[3][2]);
    }

    private int createWalls(Node[][] arr, int row, int col, int count, int[][] temp) {
        if (col >= arr[0].length) {
            row++; col = 0;
        }

        int result = 0;
        if (count == 3) {
            return this.bfs(arr, temp);
        }

        for (int i = row; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if ((i > row || j >= col) && arr[i][j].getValue() == 0) {//다음 빈 칸을 찾는다.
//                    System.out.println(count + "번째" + i + "+" + j);
                    temp[count] = new int[]{i, j};//빈 칸에 벽을 세우기로 정함.

                    result = Math.max(result,this.createWalls(arr, i, j + 1, count + 1, temp));//다음 벽을 세우게 보냄.
                }
            }
        }
        return result;
    }

    private int bfs(Node[][] arr, int[][] temp) {
        Node[][] copy = new Node[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                copy[i][j] = new Node(arr[i][j].getValue());

                if (i > 0) {
                    copy[i][j].addConnect(copy[i - 1][j]);
                    copy[i - 1][j].addConnect(copy[i][j]);
                }

                if (j > 0) {
                    copy[i][j].addConnect(copy[i][j - 1]);
                    copy[i][j - 1].addConnect(copy[i][j]);
                }
            }
        }



//        System.out.println("bfs시작");
        for (int[] line : temp) {
//            System.out.print(Arrays.toString(line));
            copy[line[0]][line[1]].setValue(1);
        }

        Queue<Node> queue = new ArrayDeque<>();
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[0].length; j++) {
                if (copy[i][j].getValue() == 2) {
                    queue.add(copy[i][j]);
                }
            }
        }

        while (!queue.isEmpty()) {
            Node target = queue.poll();
            target.setValue(2);
            Node[] nextNodes = target.getConnectedNodes();
            for (Node next : nextNodes) {
                if (next != null && !next.isVisited()) {
                    if (next.getValue() != 1){
                        next.setValue(2);
                        queue.add(next);}
                    next.visit();
                }
            }
        }

        int count = 0;
        for (Node[] line : copy) {
//            System.out.println(Arrays.toString(line));
            for (Node node : line) {
                if (node.getValue() == 0) count++;
            }
        }
//        System.out.println(count);

        return count;
    }


    private static class Node {
        private int value;
        private boolean visited;
        private Node parentNode;
        private final Node[] connectedNodes;
        private int connectedCount;

        public Node(int value) {
            this.value = value;
            this.visited = false;
            this.connectedNodes = new Node[4];
            this.connectedCount = 0;
        }

        public String toString() {
            return this.value + "";
        }

        public void setParentNode(Node parentNode) {
            this.parentNode = parentNode;
        }

        public int getValue() {
            return this.value;
        }

        public void visit() {
            this.visited = true;
        }

        public void addConnect(Node node) {
            this.connectedNodes[connectedCount++] = node;
        }

        public boolean isVisited() {
            return this.visited;
        }

        public Node[] getConnectedNodes() {
            return this.connectedNodes;
        }

        public Node getParentNode() {
            return this.parentNode;
        }

        public void setValue(int i) {
//            System.out.print("밸류 바뀜"+i);
            this.value = i;
        }

        public void cancelVisit() {
            this.visited = false;
        }
    }


}
