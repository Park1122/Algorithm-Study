package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7562 {
    static int L, A, B, N, M;
    static boolean[][] visit;
    static int[][] dist;
    static int[][] dir = {{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        L = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visit = new boolean[L][L];
        dist = new int[L][L];
    }

    static void bfs(int x, int y) {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                dist[i][j] = -1;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        q.add(y);
        visit[x][y] = true;
        dist[x][y] = 0;

        while (!q.isEmpty()) {
            int qx = q.poll();
            int qy = q.poll();

            for (int k = 0; k < 8; k++) {
                int nx = qx + dir[k][0];
                int ny = qy + dir[k][1];
                if(nx < 0 || ny < 0 || nx >= L || ny >= L) continue;
                if(visit[nx][ny]) continue;
                q.add(nx);
                q.add(ny);
                visit[nx][ny] = true;
                dist[nx][ny] = dist[qx][qy] + 1;
            }
        }
    }

    static void solve() {
        bfs(A, B);

        sb.append(dist[N][M]).append('\n');
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tt = 1; tt <= T; tt++) {
            input();
            solve();
        }
        System.out.println(sb);
    }
}
