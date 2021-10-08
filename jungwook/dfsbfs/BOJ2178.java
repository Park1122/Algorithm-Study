package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178 {
    static int N, M;
    static String[] a;
    static int[][] dist;
    static boolean[][] visit;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static ArrayList<Integer> group;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        a = new String[N];
        for (int i = 0; i < N; i++) a[i] = br.readLine();
        dist = new int[N][M];
        visit = new boolean[N][M];
    }

    static void bfs(int x, int y) {
        // dist 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dist[i][j] = -1;
            }
        }
        // (x, y)를 Queue에 삽입, visit에 체크 후 dist 값 초기화
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        q.add(y);
        dist[x][y] = 1;
        visit[x][y] = true;
        // bfs
        while (!q.isEmpty()) {
            x = q.poll();
            y = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(a[nx].charAt(ny) == '0') continue;
                if(visit[nx][ny]) continue;
                q.add(nx);
                q.add(ny);
                visit[nx][ny] = true;
                dist[nx][ny] = dist[x][y] + 1;
            }
        }
    }

    static void solve() {
        // (0, 0) 탐색 시작
        bfs(0, 0);
        // (N-1, M-1) 필요한 최소 이동 횟수 출력
        System.out.println(dist[N - 1][M - 1]);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
