package codingstudy202107.w14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 14주차
 * 문제 출처 : https://www.acmicpc.net/problem/11725
 * 이름 : 트리의 부모 찾기
 * 사용 알고리즘 : dfs bfs
 */
public class BOJ11725 {

    public static void main(String[] args) throws IOException {
        BOJ11725 main = new BOJ11725();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        StringTokenizer st;
        Node[] arr = new Node[n + 1];

        for (int i = 1; i <= n; i++)
            arr[i] = new Node(i);

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(reader.readLine());
            int firstNode = Integer.parseInt(st.nextToken());
            int secondNode = Integer.parseInt(st.nextToken());
            arr[firstNode].addConnect(arr[secondNode]);
            arr[secondNode].addConnect(arr[firstNode]);
        }
        System.out.print(main.solution(arr));
    }


    private String solution(Node[] arr) {
        StringBuilder builder = new StringBuilder();
        this.bfs(arr[1]);

        for (Node val : arr) {
            if (val != null && val.getParentNode() != null)
                builder.append(val.getParentNode().getValue()).append('\n');
        }
        builder.setLength(builder.length() - 1);

        return builder.toString();
    }

    private void bfs(Node start) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Node target = queue.poll();
            target.visit();
            Node[] nextNodes = target.getConnectedNodes();
            for (Node next : nextNodes) {
                if (next != null && !next.isVisited()) {
                    queue.add(next);
                    next.setParentNode(target);
                }
            }
        }
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
    }


}
