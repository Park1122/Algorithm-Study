package codingstudy202107.w21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 21주차
 * 문제 출처 : https:/www.acmicpc.net/problem/1967
 * 이름 : 트리의 지름
 * 사용 알고리즘 : bfs
 */
public class BOJ1967 {
    public static Node[] tree;

    public static void main(String[] args) throws IOException {
        BOJ1967 main = new BOJ1967();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodeCount = Integer.parseInt(reader.readLine());
        int lineCount = nodeCount - 1;
        StringTokenizer st;
        tree= new Node[nodeCount+1];

        for (int i = 0; i < lineCount; i++) {
            st = new StringTokenizer(reader.readLine());
            int parent = Integer.parseInt(st.nextToken());
            Node node = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            node.nextNode=tree[parent];
            tree[parent]=node;
        }
        System.out.print(main.solution());
    }

    private int solution() {
        int max = 0;
        for (Node node: tree) {//이 노드가 요가하는거처럼 다리찢기함.
            int max1 = 0, max2 = 0;
            while (node != null) {
                int result = this.bfs(node.child)+node.length;
                if (max1 >= max2) {
                    if (result > max2) max2 = result;
                } else {
                    if (result > max1) max1 = result;
                }
                node = node.nextNode;
            }
            int maxSum = max1 + max2;
            if (maxSum > max) max = maxSum;
        }
        return max;
    }

    private int bfs(int index) {
        int max = 0;
        Node node = tree[index];
        while (node != null) {
            int result = bfs(node.child)+node.length;
            if (result > max) max = result;
            node = node.nextNode;
        }
        return max;
    }

    public static class Node {
        int child, length;
        Node nextNode = null;

        public Node(int child, int length) {
            this.child = child;
            this.length = length;
        }
    }
}