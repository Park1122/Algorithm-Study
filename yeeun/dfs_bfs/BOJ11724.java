package codingstudy202107.w13;

import codingstudy202107.w12.BOJ1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 13주차
 * 문제 출처 : https://www.acmicpc.net/problem/11724
 * 이름 : 연결 요소의 개수
 * 사용 알고리즘 : dfs bfs
 */
public class BOJ11724 {

    public static void main(String[] args) throws IOException {
        BOJ11724 main = new BOJ11724();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Node[] nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++)
            nodes[i] = new Node(i);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            nodes[u].addConnect(nodes[v]);
            nodes[v].addConnect(nodes[u]);
        }


        System.out.print(main.solution(nodes));
    }

    private int solution(Node[] arr) {
        int count = 0;
            for (Node val : arr) {
                if (val!=null&&!val.isVisited()) {
                    count++;
                    this.bfs(val);
                }
        }
        return count;
    }

    private void bfs(Node start) {
        Deque<Node> queue = new ArrayDeque<>();
        this.visitNode(start,queue);

        while (!queue.isEmpty()) {
            Node target = queue.poll();
            Vector<Node> nextNodes = target.getConnectedNodes();
            for (int i = 0; i < nextNodes.size(); i++) {
                Node next = nextNodes.get(i);
                if (!next.isVisited()) {
                    this.visitNode(next,queue);
                }
            }
        }
    }

    private void visitNode(Node target, Deque<Node> queue){
        target.visit();
        queue.add(target);
    }


    private static class Node {
        private int value;
        private boolean visited;
        private Vector<Node> connectedNodes;

        public Node(int value) {
            this.value = value;
            this.visited = false;
            this.connectedNodes = new Vector<>();
        }

        public void visit() {
            this.visited = true;
        }

        public void addConnect(Node node) {
            this.connectedNodes.add(node);
        }

        public boolean isVisited() {
            return this.visited;
        }

        public Vector<Node> getConnectedNodes() {
            return this.connectedNodes;
        }
    }

}
