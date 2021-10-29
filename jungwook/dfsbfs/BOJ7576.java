package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7576 {
    static int N, M;
    static int[][] tomato, dist;
    static boolean[][] visit;
    static boolean isAlreadyTomato = true;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        tomato = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                tomato[i][j] = Integer.parseInt(st.nextToken());
                if (tomato[i][j] == 0) {
                    isAlreadyTomato = false;
                }
            }
        }
        dist = new int[N][M];
        visit = new boolean[N][M];
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();

        // 익은 토마토 한번에 Queue에 삽입
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = false;

                if (tomato[i][j] == 1) {
                    q.add(i);
                    q.add(j);
                    visit[i][j] = true;
                }
            }
        }

        // bfs 시작
        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(tomato[nx][ny] != 0) continue; // 익지 않은 토마토가 아닌 이상 갈 수가 없음
                if(visit[nx][ny]) continue; // 이미 방문한 경우는 못감

                visit[nx][ny] = true;
                tomato[nx][ny] = 1; // 토마토 익히기
                dist[nx][ny] = dist[x][y] + 1;
                q.add(nx);
                q.add(ny);
            }
        }
    }

    // 익히기 과정 후에도 안익은 토마토가 있는지 확인
    static boolean checkAllNotTomato() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tomato[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    static void solve() {
        // 토마토 익히기
        bfs();

        int ans = Integer.MIN_VALUE;
        if (checkAllNotTomato()) { // 익힌 후에도 안 익은 경우가 존재
            System.out.println(-1);
        } else {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    ans = Math.max(ans, dist[i][j]);
                }
            }
            System.out.println(ans);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        // 처음부터 다 익은 상태인지 체크
        if (isAlreadyTomato) {
            System.out.println(0);
        } else {
            solve();
        }
    }
}
