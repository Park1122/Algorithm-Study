package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7569 {
    static int N, M, H;
    static int[][][] tomato, dist;
    static boolean[][][] visit;
    static int[][] dir = {{1, 0, 0}, {0, 1, 0}, {-1, 0, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
    static boolean isAllTomato = true; // 처음부터 토마토가 다 익어있는 상태인지 체크
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        tomato = new int[H][N][M];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    tomato[i][j][k] = Integer.parseInt(st.nextToken());
                    if (tomato[i][j][k] == 0) {
                        isAllTomato = false;
                    }
                }
            }
        }
        dist = new int[H][N][M];
        visit = new boolean[H][N][M];
    }

    // 토마토가 다 익었는지 확인
    static boolean checkNotAllTomato() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (tomato[i][j][k] == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // 토마토 익히기
    static void bfs() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    visit[i][j][k] = false;
                    if (tomato[i][j][k] == -1) {
                        dist[i][j][k] = -1;
                    } else {
                        dist[i][j][k] = 0;
                    }

                    if (tomato[i][j][k] == 1) {
                        q.add(i);
                        q.add(j);
                        q.add(k);
                        visit[i][j][k] = true;
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            int z = q.poll();
            int x = q.poll();
            int y = q.poll();

            for (int k = 0; k < 6; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                int nz = z + dir[k][2];

                if(nx < 0 || ny < 0 || nz < 0 || nx >= N || ny >= M || nz >= H) continue;
                if(tomato[nz][nx][ny] == -1) continue;
                if(visit[nz][nx][ny]) continue;

                // 토마토 익히기
                tomato[nz][nx][ny] = 1;
                // 방문체크
                visit[nz][nx][ny] = true;
                // 시간 갱신
                dist[nz][nx][ny] = dist[z][x][y] + 1;
                q.add(nz);
                q.add(nx);
                q.add(ny);
            }
        }
    }

    static void solve() {
        // 토마토 익히기
        bfs();
        // 출력 및 계산
        if (checkNotAllTomato()) {
            System.out.println(-1); // 토마토가 다 안 익은 경우
        } else { // 토마토가 다 익은 경우
            int ans = -1;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < M; k++) {
                        if (tomato[i][j][k] == 1) {
                            ans = Math.max(ans, dist[i][j][k]);
                        }
                    }
                }
            }
            System.out.println(ans);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        if (isAllTomato) { // 이미 토마토가 들어올 때부터, 다 익어 있는 경우
            System.out.println(0);
        } else { // 아닌 경우
            solve();
        }
    }
}
