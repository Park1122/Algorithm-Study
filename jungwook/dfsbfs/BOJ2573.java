package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573 {
    static int N, M, time;
    static int[][] a;
    static boolean[][] visit;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        a = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        time = 0;
    }

    static void meltIce() {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (a[i][j] != 0) {
                    q.add(i);
                    q.add(j);
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();
            int seaCnt = 0;

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(!visited[nx][ny] && a[nx][ny] == 0) seaCnt++;
            }

            if (a[x][y] - seaCnt < 0) {
                a[x][y] = 0;
            } else {
                a[x][y] -= seaCnt;
            }
        }
    }

    static void dfs(int x, int y) {
        visit[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dir[d][0];
            int ny = y + dir[d][1];
            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if(a[nx][ny] == 0) continue;
            if(visit[nx][ny]) continue;

            dfs(nx, ny);
        }
    }

    static int countIceBerg() {
        visit = new boolean[N][M];
        int temp = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visit[i][j] && a[i][j] != 0) {
                    dfs(i, j);
                    temp++;
                }
            }
        }
        return temp;
    }

    static void solve() {
        int cnt = 0;
        while ((cnt = countIceBerg()) < 2) {
            if (cnt == 0) {
                time = 0;
                break;
            }

            // 녹이기
            meltIce();
            time++;
        }

        System.out.println(time);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
