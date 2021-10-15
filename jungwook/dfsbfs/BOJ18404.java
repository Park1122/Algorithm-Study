package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ18404 {
    static int N, M, X, Y;
    static int[][] enemy, dist;
    static boolean[][] visit;
    static int[][] dir = {{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-2, 1}, {-2, -1}, {-1, 2}, {-1, -2}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        enemy = new int[M + 1][2];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            enemy[i] = new int[]{a, b};
        }
        visit = new boolean[N][N];
        dist = new int[N][N];
    }

    static void bfs(int x, int y) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
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
                if(nx < 0 || ny < 0 || nx >= N || ny >= N ) continue;
                if(visit[nx][ny]) continue;
                q.add(nx);
                q.add(ny);
                visit[nx][ny] = true;
                dist[nx][ny] = dist[qx][qy] + 1;
            }
        }
    }

    static void solve() {
        bfs(X - 1, Y - 1);

        for (int i = 1; i <= M; i++) {
            sb.append(dist[enemy[i][0] - 1][enemy[i][1] - 1]).append(' ');
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.println(sb);
    }
}
