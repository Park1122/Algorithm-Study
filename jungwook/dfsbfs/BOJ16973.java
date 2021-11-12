package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16973 {
    static int N, M, H, W, sR, sC, fR, fC;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int[][] a, dist;
    static boolean[][] visit;
    static ArrayList<int[]> wall; // 벽을 모아서 사용
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        a = new int[N][M];
        wall = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
                if (a[i][j] == 1) {
                    wall.add(new int[]{i, j});
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        sR = Integer.parseInt(st.nextToken());
        sC = Integer.parseInt(st.nextToken());
        fR = Integer.parseInt(st.nextToken());
        fC = Integer.parseInt(st.nextToken());
        dist = new int[N][M];
        visit = new boolean[N][M];
    }

    static boolean hasWall(int x, int y) {
        for (int[] w : wall) {
            if (x <= w[0] && w[0] < x + H && y <= w[1] && w[1] < y + W) {
                return true;
            }
        }
        return false;
    }

    static void bfs(int x, int y) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(x);
        q.add(y);
        visit[x][y] = true;
        dist[x][y] = 0;

        while (!q.isEmpty()) {
            x = q.poll();
            y = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                // 격자판 안에 존재하는지 확인
                if(nx < 0 || ny < 0 || nx + H > N || ny + W > M) continue;
                // 벽이 있는지 확인
                if(hasWall(nx, ny)) continue;
                // 이미 방문한 점
                if(visit[nx][ny]) continue;

                q.add(nx);
                q.add(ny);
                visit[nx][ny] = true;
                dist[nx][ny] = dist[x][y] + 1;
            }
        }
    }

    static void solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dist[i][j] = -1;
            }
        }
        bfs(sR - 1, sC - 1);
        System.out.println(dist[fR - 1][fC - 1]);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
