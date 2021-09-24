package codingstudy202107.w12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 12주차
 * 문제 출처 : https://www.acmicpc.net/problem/1260
 * 이름 : DFS와 BFS
 * 사용 알고리즘 : dfs bfs
 */
public class BOJ1260 {

    public static void main(String[] args) throws IOException {
        BOJ1260 main = new BOJ1260();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        Node[] arr = new Node[n+1];
        arr[v]= new Node(v);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            if (arr[first] == null) arr[first] = new Node(first);
            if (arr[second] == null) arr[second] = new Node(second);

            arr[first].connect(arr[second]);
            arr[second].connect(arr[first]);
        }

        main.solution(arr[v],arr);
    }

    private void solution(Node start,Node[] arr) {
        StringBuilder sb = new StringBuilder();

        this.dfs(start,sb);

        sb.append('\n');
        for(Node node: arr){//한번 초기화.
            if(node!=null)node.eraseVisit();
        }

       this.bfs(start,sb);

        System.out.println(sb.toString());
    }

    private void dfs(Node start,StringBuilder sb) {
        start.visit(sb);
        Node[] nextNodes = start.getConnected();
        for (int i = 0; i < start.getConnectCount(); i++) {
            Node next = nextNodes[i];
            if (!next.isVisited()) {
                this.dfs(next,sb);
            }
        }
    }

    private String bfs(Node start,StringBuilder sb) {
        Deque<Node> queue = new ArrayDeque<>();

        start.visit(sb);
        queue.add(start);

        while (!queue.isEmpty()) {
            Node target = queue.poll();
            Node[] nextNodes = target.getConnected();
            for (int i = 0; i < target.getConnectCount(); i++) {
                Node next = nextNodes[i];
                if (!next.isVisited()) {
                    next.visit(sb);
                    queue.add(next);
                }
            }
        }
        return sb.toString();
    }


    private static class Node {
        private boolean visited;
        private final int val;
        private final Vector<Node> connected;
        private int connectCount;

        public Node(int val) {
            this.visited = false;
            this.connectCount = 0;
            this.val = val;
            this.connected = new Vector<>();
        }

        public void connect(Node node) {
            this.connected.add(node);
            this.connectCount++;
        }

        public boolean isVisited() {
            return this.visited;
        }

        public Node[] getConnected() {
            Node[] connectedArray= this.connected.toArray(new Node[connectCount]);
            Arrays.sort(connectedArray, (o1, o2) -> {
                if(o1.getVal()>o2.getVal()) return 1;
                else if (o1.getVal()<o2.getVal()) return -1;
                else return 0;
            });
            return connectedArray;
        }

        public int getConnectCount() {
            return this.connectCount;
        }

        public void visit(StringBuilder sb) {
            sb.append(this.val).append(" ");
            this.visited = true;
        }

        public int getVal() {
            return this.val;
        }

        public void eraseVisit() {
            this.visited=false;
        }
    }

}
