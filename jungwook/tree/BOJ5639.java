package jungwook.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5639 {
    static class Node {
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
        }

        void insert(int n) {
            if (n < this.value) {
                if(this.left == null) this.left = new Node(n);
                else this.left.insert(n);
            } else {
                if(this.right == null) this.right = new Node(n);
                else this.right.insert(n);
            }
        }
    }

    static Node root;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        root = new Node(Integer.parseInt(br.readLine()));
        String str;
        while ((str = br.readLine()) != null) {
            root.insert(Integer.parseInt(str));
        }
    }

    static void postOrder(Node n) {
        if(n == null) return;

        postOrder(n.left);
        postOrder(n.right);
        sb.append(n.value).append('\n');
    }

    static void solve() {
        postOrder(root);
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
