package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1012 {
    static int T, M, N, K, cnt;
    static int[][] cabbage;
    static boolean[][] visit;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        cabbage = new int[M][N];
        for (int i = 0; i < M; i++) {
            cabbage[i] = new int[N];
        }
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cabbage[x][y] = 1;
        }
        visit = new boolean[M][N];
    }

    static void dfs(int x, int y) {
        visit[x][y] = true;

        for (int k = 0; k < 4; k++) {
            int nx = x + dir[k][0];
            int ny = y + dir[k][1];
            if(nx < 0 || ny < 0 || ny >= N || nx >= M) continue;
            if(cabbage[nx][ny] == 0) continue;
            if(visit[nx][ny]) continue;
            dfs(nx, ny);
        }
    }

    static void solve() {
        cnt = 0;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j] && cabbage[i][j] == 1) {
                    dfs(i, j);
                    cnt++;
                }
            }
        }

        sb.append(cnt).append('\n');
    }

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int tt = 1; tt <= T; tt++) {
            input();
            solve();
        }
        System.out.println(sb);
    }
}
