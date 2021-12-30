package jungwook.topological_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ1516 {
    static int N;
    static int[] indeg, tDone, t;
    static ArrayList<Integer>[] adj;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N + 1];
        indeg = new int[N + 1];
        tDone = new int[N + 1];
        t = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int x = Integer.parseInt(st.nextToken());
                if(x == -1) break;
                adj[x].add(i);
                indeg[i]++;
            }
        }
    }

    static void solve() {
        Deque<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indeg[i] == 0) {
                q.add(i);
                tDone[i] = t[i];
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();

            for (int y : adj[x]) {
                indeg[y]--;
                if(indeg[y] == 0) q.add(y);
                tDone[y] = Math.max(tDone[y], tDone[x] + t[y]);
            }
        }

        for (int i = 1; i <= N; i++) {
            sb.append(tDone[i]).append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
