package codingstudy202107.w17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 17주차
 * 문제 출처 : https://www.acmicpc.net/problem/1325
 * 이름 : 효율적인 해킹 - 원본
 * 사용 알고리즘 : bfs
 * 인터넷의 다른 사람 코드를 보고 차이를 확인해보았다.
 * 다른점 1. 하나의 메소드에서 일을 다 한다.(main 메소드 안에서 그냥 다한다. 전혀 oop가 아닌 모습)
 * 다른점 2. Node 클래스를 안쓴다.(그냥 Integer로 해서 Queue에 넣더라)
 * 다른검 3. static 값으로 고정해놓고 사용한다.(argument로 넘기면서 메모리도 차지하고 낭비하는건가?)
 *
 * 첫번째 아이디어: node 클래스의 객체로 만들지 말고, 그냥 int와 Set의 배열로 사용할까?
 * -> 이럴수가 바로 성공이라니...근데 이거 차이가 있는게 맞는건가?
 */
public class BOJ1325 {

    public static void main(String[] args) throws IOException {
        BOJ1325 main = new BOJ1325();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken()),
                m = Integer.parseInt(st.nextToken());

        List<Integer>[] sets= new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            sets[i]=new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int val0 = Integer.parseInt(st.nextToken());
            int val1 = Integer.parseInt(st.nextToken());
            sets[val0].add(val1);
        }

        main.solution(n,sets);
    }

    private void solution(int n, List<Integer>[] sets) {
        StringBuilder builder = new StringBuilder();
        int[] count = new int[n+1];

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i=1; i<=n; i++) {
            this.bfs(i,sets, count,queue);
        }


        int max = Integer.MIN_VALUE;
        for (int i = 1; i < count.length; i++) {
            max = Integer.max(max, count[i]);
        }

        for (int i = 1; i <=n; i++) {
            if (count[i] == max) builder.append(i).append(" ");
        }

        builder.setLength(builder.length() - 1);
        System.out.print(builder.toString());

    }

    private void bfs(int n, List<Integer>[] sets, int[] count, Queue<Integer> queue) {
        boolean[] visited = new boolean[count.length];
        this.addNode(queue, visited, count, n);

        while (!queue.isEmpty()) {
            int target = queue.poll();
            for (int next : sets[target]) {
                if (!visited[next])
                    this.addNode(queue, visited, count, next);
            }
        }


    }

    private void addNode(Queue<Integer> queue, boolean[] visited, int[] count, int node) {
        visited[node] = true;
        count[node]++;
        queue.add(node);
    }


}

//고치기 전 버전.
//public class BOJ1325 {
//
//    public static void main(String[] args) throws IOException {
//        BOJ1325 main = new BOJ1325();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//        StringTokenizer st = new StringTokenizer(reader.readLine());
//        int n = Integer.parseInt(st.nextToken()),
//                m = Integer.parseInt(st.nextToken());
//
//        main.solution(main.readNodes(n, m, reader));
//    }
//
//    private Node[] readNodes(int n, int m, BufferedReader reader) throws IOException {
//        StringTokenizer st;
//        Node[] nodes = new Node[n + 1];
//        for (int i = 0; i <= n; i++) {
//            nodes[i] = new Node(i);
//        }
//
//        for (int i = 0; i < m; i++) {
//            st = new StringTokenizer(reader.readLine());
//            int val0 = Integer.parseInt(st.nextToken());
//            int val1 = Integer.parseInt(st.nextToken());
//            nodes[val0].iTrustList.add(nodes[val1]);
//        }
//        return nodes;
//    }
//
//    private void solution(Node[] nodes) {
//        StringBuilder builder = new StringBuilder();
//        int[] count = new int[nodes.length];
//
//        for (Node node : nodes) {
//            if (node.id == 0) continue;
//            this.bfs(node, count);
//        }
//
//        int max = Integer.MIN_VALUE;
//        for (int i = 1; i < count.length; i++) {
//            max = Integer.max(max, count[i]);
//        }
//
//        for (int i = 1; i < nodes.length; i++) {
//            if (count[i] == max) builder.append(i).append(" ");
//        }
//
//
//        builder.setLength(builder.length() - 1);
//        System.out.print(builder.toString());
//    }
//
//    private void bfs(Node node, int[] count) {
//        boolean[] visited = new boolean[count.length];
//        Queue<Node> queue = new ArrayDeque<>();
//        this.addNode(queue, visited, count, node);
//
//        while (!queue.isEmpty()) {
//            Node target = queue.poll();
//            for (Node next : target.iTrustList) {
//                if (!visited[next.id])
//                    this.addNode(queue, visited, count, next);
//            }
//        }
//
//    }
//
//    private void addNode(Queue<Node> queue, boolean[] visited, int[] count, Node node) {
//        visited[node.id] = true;
//        count[node.id]++;
//        queue.add(node);
//    }
//
//
//    private class Node {
//        int id;
//        Set<Node> iTrustList;
//        boolean finished = false;
//
//        public Node(int id) {
//            this.id = id;
//            this.iTrustList = new HashSet<>();
//        }
//
//
//        @Override
//        public String toString() {
//            return id + "";
//        }
//    }
//
//
//}
