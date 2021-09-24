package codingstudy202107.w12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 12주차
 * 문제 출처 : https://www.acmicpc.net/problem/1012
 * 이름 : 유기농 배추
 * 사용 알고리즘 : dfs bfs
 */
public class BOJ1012 {

    public static void main(String[] args) throws IOException {
        BOJ1012 main = new BOJ1012();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        int t = Integer.parseInt(reader.readLine());//테스트 케이스 개수

        for (int p = 0; p < t; p++) {
            if (p > 0) builder.append('\n');

            StringTokenizer st = new StringTokenizer(reader.readLine());
            int m = Integer.parseInt(st.nextToken());//가로길이
            int n = Integer.parseInt(st.nextToken());//세로길이
            int k = Integer.parseInt(st.nextToken());//배추 개수

            Node[][] arr = new Node[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = new Node();
                    if (i > 0) {
                        //지금꺼와 윗줄을 연결, 윗줄과 지금꺼를 연결
                        arr[i][j].addConnect(arr[i - 1][j]);
                        arr[i - 1][j].addConnect(arr[i][j]);
                    }
                    if (j > 0) {
                        //지금꺼와 왼쪽을 연결, 왼쪽과 지금꺼를 연결
                        arr[i][j - 1].addConnect(arr[i][j]);
                        arr[i][j].addConnect(arr[i][j - 1]);
                    }
                }
            }

            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(reader.readLine());
                arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())].plantBachu();
            }
            builder.append(main.solution(arr));
        }


        System.out.print(builder.toString());
    }

    private int solution(Node[][] arr) {
        int count = 0;
        for (Node[] line : arr) {
            for (Node val : line) {
                if (val.getValue() && !val.isVisited()) {
                    count++;
                    this.bfs(val);
                }
            }
        }
        return count;
    }

    private void bfs(Node start) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Node target = queue.poll();
            boolean val = target.getValue();

            if (!val) {//배추 안 심어져 있음.
                target.visit();
            } else if (!target.isVisited()) {//배추가 심어져 있음.
                target.visit();
                Node[] nextNodes = target.getConnectedNodes();
                for (Node next : nextNodes) {
                    if (next != null && !next.isVisited()) queue.add(next); //다음 배추들을 넣는다.
                }
            }
        }
    }


    private static class Node {
        private boolean value;
        private boolean visited;
        private final Node[] connectedNodes;
        private int connectedCount;

        public Node() {
            this.visited = false;
            this.connectedNodes = new Node[4];
            this.connectedCount = 0;
        }

        public void plantBachu() {
            this.value = true;
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
