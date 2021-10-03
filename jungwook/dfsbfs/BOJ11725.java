package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11725 {
    static int N;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    static int[] parent;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        for (int i = 1; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            graph[y].add(x);
        }
        parent = new int[N + 1];
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();

        q.add(start);
        visit[start] = true;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int y : graph[node]) {
                if(visit[y]) continue;

                q.add(y);
                parent[y] = node;
                visit[y] = true;
            }
        }
    }

    static void solve() {
        visit = new boolean[N + 1];
        bfs(1);

        for (int i = 2; i <= N; i++) {
            sb.append(parent[i]).append('\n');
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
