package codingstudy202107.w23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * 회차 : w23
 * 문제 출처 : <a href =https://www.acmicpc.net/problem/1068>링크 </a>
 * 이름 : 트리
 * 사용 알고리즘 : <사용 알고리즘>
 * 설명
 * ==========================================================================
 */
public class BOJ1068 {
    public static void main(String[] args) throws IOException {
        BOJ1068 main = new BOJ1068();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(reader.readLine());
        Node[] arr = new Node[count];

        StringTokenizer st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < count; i++) {
            //i번째 노드.
            if (arr[i] == null) arr[i] = new Node(i);

            //그의 부모
            int parentIdx = Integer.parseInt(st.nextToken());
            if (parentIdx == -1) continue;
            if (arr[parentIdx] == null) {
                arr[parentIdx] = new Node(parentIdx);
            }
            Node parent = arr[parentIdx];

            arr[i].parent = parent;
            parent.children.add(arr[i]);
        }

        int removeNodeIdx = Integer.parseInt(reader.readLine());

        Node removeNode = arr[removeNodeIdx];
        main.removeNode(removeNode);

        Node rootNode = main.getRootNode(removeNode);
        if(rootNode.equals(removeNode))System.out.println(0);
        else System.out.print(main.countLeaf(rootNode));
    }

    private int countLeaf(Node rootNode) {
        int leafCount = 0;

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(rootNode);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.children.isEmpty()) leafCount++;
            else {
                queue.addAll(node.children);
            }

        }
        return leafCount;
    }

    private Node getRootNode(Node removeNode) {
        Node node = removeNode;
        while (node.parent != null) {
            node = node.parent;
        }
        System.out.println("root: " + node.val);
        return node;
    }

    private void removeNode(Node removeNode) {
        if (removeNode.parent != null)
            removeNode.parent.children.remove(removeNode);//부모는 이제 얘를 몰라.
        else removeNode.children.clear();
    }

    public static class Node {
        Node parent;
        Vector<Node> children = new Vector<>();
        int val;

        public Node(int val) {
            this.val = val;
        }
    }
}
