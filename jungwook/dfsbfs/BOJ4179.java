package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4179 {
    static int N, M, ans;
    static char[][] map;
    static boolean[][] visit;
    static int[][] distHuman, distFire;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            char[] row = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = row[j];
            }
        }
        ans = -1;
    }

    static void bfsFire() {
        visit = new boolean[N][M];
        distFire = new int[N][M];
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = false;
                distFire[i][j] = -1;

                if (map[i][j] == 'F') {
                    q.add(i);
                    q.add(j);
                    visit[i][j] = true;
                    distFire[i][j] = 0;
                }
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = x + dir[d][0];
                int ny = y + dir[d][1];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(map[nx][ny] == '#') continue;
                if(visit[nx][ny]) continue;

                q.add(nx);
                q.add(ny);
                visit[nx][ny] = true;
                distFire[nx][ny] = distFire[x][y] + 1;
            }
        }
    }

    static void bfsHuman() {
        distHuman = new int[N][M];
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = false;
                distHuman[i][j] = -1;

                if (map[i][j] == 'J') {
                    q.add(i);
                    q.add(j);
                    visit[i][j] = true;
                    distHuman[i][j] = 0;
                }
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();

            if (x == 0 || y == 0 || x == N - 1 || y == M - 1) {
                ans = distHuman[x][y] + 1;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dir[d][0];
                int ny = y + dir[d][1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(distFire[nx][ny] != -1 && distFire[nx][ny] <= distHuman[x][y] + 1) continue;
                if(visit[nx][ny] || map[nx][ny] == '#') continue;

                q.add(nx);
                q.add(ny);
                visit[nx][ny] = true;
                distHuman[nx][ny] = distHuman[x][y] + 1;
            }
        }
    }

    static void solve() {
        // 불의 이동 시간 계산
        bfsFire();
        // 지훈이의 이동 시간 계산
        bfsHuman();
        // 출력
        if (ans == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(ans);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
