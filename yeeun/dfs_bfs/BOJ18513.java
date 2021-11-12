package codingstudy202107.w19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 18주차
 * 문제 출처 : https:/www.acmicpc.net/problem/18513
 * 이름 : 샘터
 * 사용 알고리즘 : bfs
 */
public class BOJ18513 {


    public static void main(String[] args) throws IOException {
        BOJ18513 main = new BOJ18513();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int springAmount = Integer.parseInt(st.nextToken()), houseAmount = Integer.parseInt(st.nextToken());

        Queue<Node> queue = new ArrayDeque<>();

        int[] springLocations = new int[springAmount];

        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < springAmount; i++) {
            springLocations[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(springLocations);

        Node prev = null;
        for (int loc : springLocations) {
            Node node = new Node(loc);
            node.happiness = 0;
            if (prev != null) {
                node.left = prev;
                prev.right = node;
            }
            queue.add(node);
            prev = node;
        }


        main.solution(prev, queue, houseAmount);
    }

    private void solution(Node root, Queue<Node> queue, int houseAmount) {
        if (houseAmount == 0) this.calcAllHappiness(root);
        else while (!queue.isEmpty()) {
                Node target = queue.poll();
                int targetLocation = target.LOCATION;
                int nextHappiness = target.happiness + 1;
                if (target.left == null || target.left.LOCATION < targetLocation - 1) {
                    Node targetLeft = target.left;
                    Node left = new Node(targetLocation - 1);

                    left.happiness = nextHappiness;
                    if (targetLeft != null) targetLeft.right = left;
                    target.left = left;
                    left.left = targetLeft;
                    left.right = target;

                    queue.add(left);
                    houseAmount--;
                    if (houseAmount == 0) {
                        this.calcAllHappiness(root);
                        break;
                    }
                }
                if (target.right == null || target.right.LOCATION > targetLocation + 1) {
                    Node targetRight = target.right;
                    Node right = new Node(targetLocation + 1);

                    //root 찾기 (맨 오른쪽)
                    if (root.LOCATION < right.LOCATION) root = right;

                    right.happiness = nextHappiness;
                    if (targetRight != null) targetRight.left = right;
                    right.left = target;
                    right.right = targetRight;
                    target.right = right;

                    queue.add(right);
                    houseAmount--;
                    if (houseAmount == 0) {
                        this.calcAllHappiness(root);
                        break;
                    }
                }
            }
    }

    private void calcAllHappiness(Node root) {
        long sum = 0;
        while (root != null) {
//            System.out.println(root.LOCATION + ": " + root.happiness);
            sum += root.happiness;
            root = root.left;
        }
        System.out.print(sum);
    }


    public static class Node {
        public Node left, right;
        public final int LOCATION;
        public int happiness;

        public Node(int location) {
            this.LOCATION = location;
        }
    }


}
