package codingstudy202107.w13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 13주차
 * 문제 출처 : https://www.acmicpc.net/problem/4963
 * 이름 : 섬의 개수
 * 사용 알고리즘 : dfs bfs
 */
public class BOJ4963 {

    public static void main(String[] args) throws IOException {
        BOJ4963 main = new BOJ4963();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        int col, row;
        while (true) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            col = Integer.parseInt(st.nextToken());
            row = Integer.parseInt(st.nextToken());

            if(col==0&&row==0){
                System.out.print(builder.toString());
                break;
            }

            Node[][] arr = new Node[row][col];
            for (int i = 0; i < row; i++) {
                st = new StringTokenizer(reader.readLine());
                Node[] line = new Node[col];
                for (int j = 0; j < col; j++) {
                    line[j] = new Node(Integer.parseInt(st.nextToken()) == 1);

                    if (i > 0) {
                        //지금꺼와 윗줄을 연결, 윗줄과 지금꺼를 연결
                        line[j].addConnect(arr[i - 1][j]);
                        arr[i - 1][j].addConnect(line[j]);
                        if (j > 0) {
                            //윗줄 왼쪽 대각선 연결
                            line[j].addConnect(arr[i - 1][j - 1]);
                            arr[i - 1][j - 1].addConnect(line[j]);
                        }
                        if (j < col-1) {
                            //윗줄 오른쪽 대각선 연결
                            line[j].addConnect(arr[i - 1][j + 1]);
                            arr[i - 1][j + 1].addConnect(line[j]);
                        }
                    }
                    if (j > 0) {
                        //지금꺼와 왼쪽을 연결, 왼쪽과 지금꺼를 연결
                        line[j].addConnect(line[j - 1]);
                        line[j - 1].addConnect(line[j]);
                    }

                }
                arr[i] = line;
            }
           main.solution(arr, builder);
        }
    }


    private void solution(Node[][] arr, StringBuilder builder) {
        int count = 0;
        for (Node[] line : arr) {
            for (Node val : line) {
                if (val.getValue() && !val.isVisited()) {
                    count++;
                    this.bfs(val);
                }
            }
        }
        builder.append(count).append('\n');
    }

    private void bfs(Node start) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Node target = queue.poll();
            boolean val = target.getValue();

            if (!val) {
                target.visit();
            } else if (!target.isVisited()) {
                target.visit();
                Node[] nextNodes = target.getConnectedNodes();
                for (Node next : nextNodes) {
                    if (next != null && !next.isVisited()) queue.add(next);
                }
            }
        }
    }


    private static class Node {
        private boolean value;
        private boolean visited;
        private final Node[] connectedNodes;
        private int connectedCount;

        public Node(boolean value) {
            this.value=value;
            this.visited = false;
            this.connectedNodes = new Node[8];
            this.connectedCount = 0;
        }

        public boolean getValue() {
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
    }


}
