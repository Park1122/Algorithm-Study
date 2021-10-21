package codingstudy202107.w16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * 회차 : 코딩 스터디 15주차
 * 문제 출처 : https://www.acmicpc.net/problem/7569
 * 이름 : 토마토
 * 사용 알고리즘 : bfs
 * 골드 문제가 너무 사악해서 네번째 문제로 다시 돌아왔다.. 살았다 휴
 * 삼차원 배열을 만들어야 할 거 같고, 노드에 연결된 다른 노드의 수가 6개인거 같긴 한데
 * 생각한 것처럼 별로 안어려웠다. 휴.. 근데 골드 어쩌지.
 */
public class BOJ7569 {

    public static void main(String[] args) throws IOException {
        BOJ7569 main = new BOJ7569();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken()),
                m = Integer.parseInt(st.nextToken()),
                h = Integer.parseInt(st.nextToken());

        int[][][] arr = new int[h][m][n];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(reader.readLine());
                for (int k = 0; k < n; k++) {
                    arr[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        System.out.print(main.solution(arr));
    }

    private int solution(int[][][] arr) {
        Queue<Node> queue = new ArrayDeque<>();

        Node[][][] nodes = new Node[arr.length][arr[0].length][arr[0][0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                for (int k = 0; k < arr[0][0].length; k++) {
                    nodes[i][j][k] = new Node(arr[i][j][k]);
                    if (i > 0) {
                        nodes[i][j][k].friends.add(nodes[i - 1][j][k]);
                        nodes[i - 1][j][k].friends.add(nodes[i][j][k]);
                    }

                    if (j > 0) {
                        nodes[i][j][k].friends.add(nodes[i][j - 1][k]);
                        nodes[i][j - 1][k].friends.add(nodes[i][j][k]);
                    }

                    if (k > 0) {
                        nodes[i][j][k].friends.add(nodes[i][j][k - 1]);
                        nodes[i][j][k - 1].friends.add(nodes[i][j][k]);
                    }
                    if (arr[i][j][k] == 1) {
                        queue.add(nodes[i][j][k]);
                        nodes[i][j][k].count = 0;
                    }
                }
            }
        }

        return this.bfs(nodes, queue);

    }

    private int bfs(Node[][][] nodes, Queue<Node> queue) {
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

        for (Node[][] bundle : nodes) {
            for (Node[] line : bundle) {
                for (Node node : line) {
                    if (node.id == 0) return -1;
                }
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
