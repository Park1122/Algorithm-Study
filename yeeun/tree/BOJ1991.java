package codingstudy202107.w22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * 회차 : w22
 * 문제 출처 : <a href = https://www.acmicpc.net/problem/1991>링크</a>
 * 이름 : 트리 순회
 * 사용 알고리즘 : 재귀
 * 설명
 * ==========================================================================
 */
public class BOJ1991 {

    public static List<String> ALPHABETS = Arrays.asList(
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "N", "M", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y",
            "Z"
    );


    public static void main(String[] args) throws IOException {
        BOJ1991 main = new BOJ1991();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodeCount = Integer.parseInt(reader.readLine());

        StringTokenizer st;

        Node[] arr = new Node[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            st = new StringTokenizer(reader.readLine());
            int node = ALPHABETS.indexOf(st.nextToken());
            int leftChild = ALPHABETS.indexOf(st.nextToken());
            int rightChild = ALPHABETS.indexOf(st.nextToken());

            if (arr[node] == null) {
                arr[node] = new Node(node);
            }
            if (leftChild!=-1&&arr[leftChild] == null) {
                arr[leftChild] = new Node(leftChild);
                arr[node].leftChild = arr[leftChild];
            }
            if (rightChild!=-1&&arr[rightChild] == null) {
                arr[rightChild] = new Node(rightChild);
                arr[node].rightChild = arr[rightChild];
            }
        }

        System.out.print(main.traverse(arr[0]));
    }

    private String traverse(Node node) {
        StringBuilder builder= new StringBuilder();
        this.recursionPreorder(node,builder);
        builder.append('\n');
        this.recursionInorder(node,builder);
        builder.append('\n');
        this.recursionPostorder(node,builder);
        return builder.toString();
    }



    private void recursionPostorder(Node node, StringBuilder builder) {
        if(node==null)return;
        this.recursionPostorder(node.leftChild,builder);
        this.recursionPostorder(node.rightChild,builder);
        builder.append(ALPHABETS.get(node.id));
    }

    private void recursionInorder(Node node, StringBuilder builder) {
        if(node==null)return;
        this.recursionInorder(node.leftChild,builder);
        builder.append(ALPHABETS.get(node.id));
        this.recursionInorder(node.rightChild,builder);
    }

    private void recursionPreorder(Node node, StringBuilder builder) {
        if(node==null)return;
            builder.append(ALPHABETS.get(node.id));
            this.recursionPreorder(node.leftChild,builder);
            this.recursionPreorder(node.rightChild,builder);
    }


    public static class Node {
        int id;
        Node leftChild, rightChild;

        public Node(int id){
            this.id=id;
        }
    }


}
