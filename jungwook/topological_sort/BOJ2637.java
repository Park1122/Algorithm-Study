package jungwook.topological_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ2637 {
    static class Node {
        int y, v;
        public Node(int y, int v) {
            this.y = y;
            this.v = v;
        }
    }
    static int N, M;
    static int[] indeg;
    static int[][] cnt; // cnt[i][j] : i번 부품을 만들때, j번 부품이 필요한 개수
    static ArrayList<Node>[] adj;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        indeg = new int[N + 1];
        cnt = new int[N + 1][N + 1];
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[y].add(new Node(x, v));
            indeg[x]++;
        }
    }

    static void solve() {
        Deque<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indeg[i] == 0) {
                q.add(i);
                cnt[i][i] = 1;
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            for (Node node : adj[x]) {
                int y = node.y;
                int v = node.v;
                indeg[y]--;
                if(indeg[y] == 0) q.add(y);
                // 제품 y에 제품 x 가 v 개 필요하므로 기본 부품 개수에 v 를 곱해서 누적
                for (int i = 1; i <= N; i++) {
                    cnt[y][i] += cnt[x][i] * v;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (cnt[N][i] != 0) {
                System.out.println(i + " " + cnt[N][i]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
