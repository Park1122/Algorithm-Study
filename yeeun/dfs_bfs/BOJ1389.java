package codingstudy202107.w16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 15주차
 * 문제 출처 : https://www.acmicpc.net/problem/1389
 * 이름 : 케빈 베이컨의 6단계 법칙
 * 사용 알고리즘 : bfs
 * 설명을 보아하니 케빈 베이컨은 본인도 인싸이고, 사귄 친구들도 다 인싸인가 보다..인싸의 삶은 어떤걸까 조금 궁금해지는 문제였던 것 같다.
 * bfs로 큐에 넣으면서 몇차 친구인지 노드의 애트리뷰트에 저장하고, 큐에서 빼면서 합계 계산을 했다.
 * 노드 클래스의 encapsulation 포기하니 코드 짧아져서 좋다..
 * 그렇지만 visit() 메소드는 포기 못하겠다. 그냥 간지난다(^0^)v. init()도 편의상 하나 만들었다.
 */
public class BOJ1389 {

    public static void main(String[] args) throws IOException {
        BOJ1389 main = new BOJ1389();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[m][2];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.print(main.solution(n, arr));
    }

    private int solution(int n, int[][] arr) {
        Node[] nodes = new Node[n+1];
        for (int i = 0; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        for (int[] line : arr) {
            Node p1 = nodes[line[0]], p2 = nodes[line[1]];
            if (!p1.friends.contains(p2)) p1.friends.add(p2);
            if (!p2.friends.contains(p1)) p2.friends.add(p1);
        }

        int count = n*n, id = -1;

        for (int i=1; i<nodes.length; i++) {
            Node start = nodes[i];
            int result = this.bfs(start);
            if (result < count) {
                count = result;
                id = start.id;
            }
            this.initNodes(nodes);
        }

        return id;
    }

    private int bfs(Node start) {
        int result = 0;
        Queue<Node> queue = new ArrayDeque<>();
        this.addAndVisitAndSetCount(queue, start, 0);

        while (!queue.isEmpty()) {
            Node target = queue.poll();
            result += target.count;
//            System.out.println(start.id+"->"+target.id+"카운트 "+target.count);
            for (Node node : target.friends) {
                if (!node.visited)
                    this.addAndVisitAndSetCount(queue, node, target.count + 1);
            }
        }

//        System.out.println(start.id+"사람 "+result);
        return result;
    }

    private void addAndVisitAndSetCount(Queue<Node> queue, Node node, int count) {
        node.visit();
        queue.add(node);
        node.count = count;
    }

    private void initNodes(Node[] nodes) {
        for (Node node : nodes) {
            node.init();
        }
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
