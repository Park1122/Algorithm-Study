package codingstudy202107.w17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * 회차 : 코딩 스터디 15주차
 * 문제 출처 : https://www.acmicpc.net/problem/7576
 * 이름 : 토마토
 * 사용 알고리즘 : bfs
 * 저번에 푼 문제의 하위호환. 금방 끝냅시다.
 */
public class BOJ7576 {

    public static void main(String[] args) throws IOException {
        BOJ7576 main = new BOJ7576();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken()),
                m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[m][n];

            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(reader.readLine());
                for (int k = 0; k < n; k++) {
                    arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }


        System.out.print(main.solution(arr));
    }

    private int solution(int[][] arr) {
        Queue<Node> queue = new ArrayDeque<>();

        Node[][] nodes = new Node[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                    nodes[i][j] = new Node(arr[i][j]);
                    if (i > 0) {
                        nodes[i][j].friends.add(nodes[i - 1][j]);
                        nodes[i - 1][j].friends.add(nodes[i][j]);
                    }

                    if (j > 0) {
                        nodes[i][j].friends.add(nodes[i][j - 1]);
                        nodes[i][j - 1].friends.add(nodes[i][j]);
                    }


                    if (arr[i][j] == 1) {
                        queue.add(nodes[i][j]);
                        nodes[i][j].count = 0;
                    }

            }
        }

        return this.bfs(nodes, queue);

    }

    private int bfs(Node[][] nodes, Queue<Node> queue) {
        int last=0;
        while (!queue.isEmpty()) {
            Node target = queue.poll();
            last=target.count;
            for (Node next : target.friends) {
                if (next.id == 0) {
                    next.id = 1;
                    next.count = last + 1;
                    queue.add(next);
                }
            }
        }

            for (Node[] line : nodes) {
                for (Node node : line) {
                    if (node.id == 0) return -1;
                }
            }

        return last;
    }



    private class Node {
        int count, id;
        boolean visited;
        Vector<Node> friends;

        public Node(int id) {
            this.id = id;
            this.friends = new Vector<>();
            this.init();
        }

        public void init() {
            this.visited = false;
            this.count = -1;
        }

        public void visit() {
            this.visited = true;
        }
    }


}
