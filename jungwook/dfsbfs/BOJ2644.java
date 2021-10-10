package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2644 {
    static int N, A, B, M;
    static ArrayList<Integer>[] family;
    static boolean[] visit;
    static int[] dist;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        family = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            family[i] = new ArrayList<>();
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            family[x].add(y);
            family[y].add(x);
        }
        dist = new int[N + 1];
        visit = new boolean[N + 1];
    }

    static void bfs(int start) {
        for (int i = 1; i <= N; i++) {
            dist[i] = -1;
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;
        dist[start] = 0;

        while (!q.isEmpty()) {
            int x = q.poll();
            for (int y : family[x]) {
                if(visit[y]) continue;
                q.add(y);
                visit[y] = true;
                dist[y] = dist[x] + 1;
            }
        }
    }

    static void solve() {
        bfs(A);

        System.out.println(dist[B]);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
