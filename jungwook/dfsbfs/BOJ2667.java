package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ2667 {
    static int N, cnt;
    static String[] A;
    static boolean[][] visit;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static ArrayList<Integer> group;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        A = new String[N];
        for (int i = 0; i < N; i++) {
            A[i] = br.readLine();
        }
        visit = new boolean[N][N];
    }

    static void dfs(int x, int y) {
        cnt++;
        visit[x][y] = true;

        for (int k = 0; k < 4; k++) { // 인접 4개
            int nx = x + dir[k][0];
            int ny = y + dir[k][1];
            if (nx < 0 || ny < 0 || ny >= N || nx >= N) continue; // 옳지 못한 격자의 경우 패스
            if(A[nx].charAt(ny) == '0') continue; // 집 없음
            if(visit[nx][ny]) continue; // 이미 방문
            dfs(nx, ny);
        }
    }

    static void solve() {
        group = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j] && A[i].charAt(j) == '1') { // 방문한 적이 없는 새로운 단지
                    cnt  = 0;
                    dfs(i, j);
                    group.add(cnt);
                }
            }
        }

        Collections.sort(group);
        sb.append(group.size()).append('\n');
        for (int c : group) {
            sb.append(c).append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
