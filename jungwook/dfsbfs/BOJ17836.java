package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 검을 먹었을 때와 아닌 경우를 나눠서 생각해야 한다.
 * 참고: https://coder-in-war.tistory.com/entry/BOJ-JAVA17836-%EA%B3%B5%EC%A3%BC%EB%8B%98%EC%9D%84-%EA%B5%AC%ED%95%B4%EB%9D%BC
 */
public class BOJ17836 {
    static class Man {
        // 검이 없으면 0, 있으면 1
        int x, y, gram, time;
        public Man(int x, int y, int gram, int time) {
            this.x = x;
            this.y = y;
            this.gram = gram;
            this.time = time;
        }
    }

    static int N, M, T;
    static int[][] a;
    static boolean[][][] visit;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        a = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void bfs(int x, int y) {
        Queue<Man> q = new ArrayDeque<>();
        q.add(new Man(x, y, 0, 0));

        while (!q.isEmpty()) {
            Man man = q.poll();
            int gram = man.gram;

            if (man.time > T) {
                continue;
            }
            if (man.x == N - 1 && man.y == M - 1) {
                System.out.println(man.time);
                return;
            }

            for (int k = 0; k < 4; k++) {
                int nx = man.x + dir[k][0];
                int ny = man.y + dir[k][1];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visit[nx][ny][gram]) continue;

                if (gram == 0) { // 검이 없는 경우
                    if(a[nx][ny] == 1) continue; // 벽을 못뚫음

                    visit[nx][ny][gram] = true;
                    if (a[nx][ny] == 2) {
                        q.add(new Man(nx, ny, 1, man.time + 1));
                    } else {
                        q.add(new Man(nx, ny, 0, man.time + 1));
                    }
                } else { // 검이 있는 경우 --> 벽 유무 상관없음
                    visit[nx][ny][gram] = true;
                    q.add(new Man(nx, ny, 1, man.time + 1));
                }
            }
        }
        // 도착 못함
        System.out.println("Fail");
    }

    static void solve() {
        visit = new boolean[N][M][2];
        bfs(0, 0);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
