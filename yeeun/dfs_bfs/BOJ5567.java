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
 * 문제 출처 : https://www.acmicpc.net/problem/5567
 * 이름 : 결혼식
 * 사용 알고리즘 : bfs
 * 이곳은 현재 백석역 카페이다.. 그리고 나는 마우스를 두고 왔다..마우스의 소중함을 오늘 다시 한 번 깨닫는다..
 * 마우스는 작고 소듕하고 꼭 있어야 하는 우리 모두의 친구 귀염둥이 찍찍이란 것을 우리 모두 기억하도록 하자..
 * 상근이는 아무래도 나처럼 아싸인 것 같다.. 친구의 친구까지 불러야 하객이 차는 건가.. 뭔가 남일같지 않네..
 */
public class BOJ5567 {

    public static void main(String[] args) throws IOException {
        BOJ5567 main = new BOJ5567();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        StringTokenizer st;
        int[][] arr = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.print(main.solution(n, arr));
    }

    private int solution(int n, int[][] arr) {
        Node[] nodes = new Node[n + 1];
        for (int i = 0; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        for (int[] line : arr) {
            Node p1 = nodes[line[0]], p2 = nodes[line[1]];
            if (!p1.friends.contains(p2)) p1.friends.add(p2);
            if (!p2.friends.contains(p1)) p2.friends.add(p1);
        }

        return this.bfs(nodes[1]);
    }

    private int bfs(Node start) {
        int result = -1;
        Queue<Node> queue = new ArrayDeque<>();
        this.addAndVisitAndSetCount(queue, start, 0);

        while (!queue.isEmpty()) {
            Node target = queue.poll();
            result++;
//            System.out.println(target.id + " " + target.count + " 초대 ->" + result);
            if (target.friends != null)
                for (Node node : target.friends) {
                    if (target.count >= 2) {
                        node.friends = null;//친구의 친구를 아싸로 만듬(ㅋㅋㅋ)
//                        System.out.println(node.id);
                    } else if (!node.visited)
                        this.addAndVisitAndSetCount(queue, node, target.count + 1);
                }
        }

        return result;
    }

    private void addAndVisitAndSetCount(Queue<Node> queue, Node node, int count) {
        node.visit();
        queue.add(node);
        node.count = count;
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
