package jungwook.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1991 {
    static int N;
    static int[][] child;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        child = new int[30][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char parChar = st.nextToken().charAt(0);
            int par = (int) (parChar - 'A');
            for (int k = 0; k < 2; k++) {
                char childChar = st.nextToken().charAt(0);
                if (childChar != '.') {
                    child[par][k] = (int) (childChar - 'A');
                } else {
                    child[par][k] = -1;
                }
            }
        }
    }

    static void preOrder(int x) {
        if(x == -1) return;

        sb.append((char) (x + 'A'));
        preOrder(child[x][0]);
        preOrder(child[x][1]);
    }

    static void inOrder(int x) {
        if(x == -1) return;

        inOrder(child[x][0]);
        sb.append((char) (x + 'A'));
        inOrder(child[x][1]);
    }

    static void postOrder(int x) {
        if(x == -1) return;

        postOrder(child[x][0]);
        postOrder(child[x][1]);
        sb.append((char) (x + 'A'));
    }

    static void solve() {
        preOrder(0);
        sb.append('\n');
        inOrder(0);
        sb.append('\n');
        postOrder(0);

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
