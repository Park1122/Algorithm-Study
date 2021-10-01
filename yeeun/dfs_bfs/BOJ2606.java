package codingstudy202107.w13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * 회차 : 코딩 스터디 13주차
 * 문제 출처 : https://www.acmicpc.net/problem/2606
 * 이름 : 바이러스
 * 사용 알고리즘 : dfs bfs
 * 1번이랑 연결된 컴퓨터의 수를 세면 끝난다. 빠르게 완료. 근데 코드 더 깨끗하게 못하려나?
 */
public class BOJ2606 {

    public static void main(String[] args) throws IOException {
        BOJ2606 main = new BOJ2606();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        Node[] nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++)
            nodes[i] = new Node(i);

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            nodes[u].addConnect(nodes[v]);
            nodes[v].addConnect(nodes[u]);
        }

        System.out.print(main.solution(nodes[1]));
    }

    private int solution(Node start) {
        return this.bfs(start);
    }

    private int bfs(Node start) {
        int result =0;
        Deque<Node> queue = new ArrayDeque<>();
        this.visitNode(start, queue);
//        result++; 1번 컴퓨터는 안 세도 된다!

        while (!queue.isEmpty()) {
            Node target = queue.poll();
            Vector<Node> nextNodes = target.getConnectedNodes();
            for (int i = 0; i < nextNodes.size(); i++) {
                Node next = nextNodes.get(i);
                if (!next.isVisited()) {
                    this.visitNode(next, queue);
                    result++;
                }
            }
        }
        return result;
    }

    private void visitNode(Node target, Deque<Node> queue) {
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
