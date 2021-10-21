package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3055 {
    static int N, M;
    static String[] a;
    static int[][] distWater, distHedgehog;
    static boolean[][] visit;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        a = new String[N];
        for (int i = 0; i < N; i++) {
            a[i] = br.readLine();
        }
        distWater = new int[N][M];
        distHedgehog = new int[N][M];
        visit = new boolean[N][M];
    }

    // 모든 물 동시에 bfs 시작
    static void bfsWater() {
        Queue<Integer> q = new LinkedList<>();
        // 모든 물의 위치 q에 삽입
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // distWater, visit 초기화
                distWater[i][j] = -1;
                visit[i][j] = false;
                // 물 시작일 경우
                if (a[i].charAt(j) == '*') {
                    q.add(i);
                    q.add(j);
                    distWater[i][j] = 0; // 시작이기 때문에 0초
                    visit[i][j] = true;
                }
            }
        }

        //bfs
        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(a[nx].charAt(ny) != '.') continue;
                if(visit[nx][ny]) continue;
                visit[nx][ny] = true;
                distWater[nx][ny] = distWater[x][y] + 1;
                q.add(nx);
                q.add(ny);
            }
        }
    }

    static void bfsHedgehog() {
        Queue<Integer> q = new LinkedList<>();

        // 고슴도치 위치 q에 삽입
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // distHedgehog, visit 초기화
                distHedgehog[i][j] = -1;
                visit[i][j] = false;
                if (a[i].charAt(j) == 'S') {
                    q.add(i);
                    q.add(j);
                    visit[i][j] = true;
                    distHedgehog[i][j] = 0;
                }
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(a[nx].charAt(ny) != '.' && a[nx].charAt(ny) != 'D') continue;
                // 물 잠김 여부 체크 -> 1. 물이 찰 수 있으면서, 2. 고슴도치가 1초뒤 이동하면 물인 곳은 패스
                if(distWater[nx][ny] != -1 && distWater[nx][ny] <= distHedgehog[x][y] + 1) continue;
                if(visit[nx][ny]) continue;
                q.add(nx);
                q.add(ny);
                visit[nx][ny] = true;
                distHedgehog[nx][ny] = distHedgehog[x][y] + 1;
            }
        }
    }

    static void solve() {
        // 칸마다 물이 차는 시간 계산
        bfsWater();
        // 고슴도치가 물을 피해 탐색할 수 있는 공간 찾기
        bfsHedgehog();
        // 탈출구 'D'에 대한 결과를 통해 답 도출
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (a[i].charAt(j) == 'D') {
                    if (distHedgehog[i][j] == -1) {
                        System.out.println("KAKTUS");
                    } else {
                        System.out.println(distHedgehog[i][j]);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
