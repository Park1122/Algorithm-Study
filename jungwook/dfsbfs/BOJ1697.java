package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697 {
    static int N, K;
    static int[] dist;
    static boolean[] visit;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visit = new boolean[100005];
        dist = new int[100005];
    }

    // 숨바꼭질 시작
    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        visit[N] = true;
        dist[N] = 0;

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = x - 1;
            if (0 <= y && !visit[y]) {
                visit[y] = true;
                dist[y] = dist[x] + 1;
                q.add(y);
            }
            y = x + 1;
            if (y <= 100000 && !visit[y]) {
                visit[y] = true;
                dist[y] = dist[x] + 1;
                q.add(y);
            }
            y = x * 2;
            if (y <= 100000 && !visit[y]) {
                visit[y] = true;
                dist[y] = dist[x] + 1;
                q.add(y);
            }
        }
    }

    static void solve() {
        bfs();
        System.out.println(dist[K]);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
