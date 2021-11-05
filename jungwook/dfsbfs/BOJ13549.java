package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13549 {
    static int N, K;
    static int[] a, dist;
    static boolean[] visit;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        a = new int[100001];
        visit = new boolean[100001];
        dist = new int[100001];
    }

    static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visit[start] = true;
        dist[start] = 0;

        while (!q.isEmpty()) {
            int x = q.poll();

            int nx = x * 2;
            if (nx <= 100000 && !visit[nx]) {
                dist[nx] = dist[x];
                q.add(nx);
                visit[nx] = true;
            }

            nx = x - 1;
            if (0 <= nx && !visit[nx]) {
                q.add(nx);
                visit[nx] = true;
                dist[nx] = dist[x] + 1;
            }
            nx = x + 1;
            if (nx <= 100000 && !visit[nx]) {
                q.add(nx);
                visit[nx] = true;
                dist[nx] = dist[x] + 1;
            }
        }
    }

    static void solve() {
        bfs(N);

        System.out.println(dist[K]);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
