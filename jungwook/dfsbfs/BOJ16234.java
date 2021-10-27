package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16234 {
    static int R, L, N, ans;
    static int[][] map;
    static boolean[][] visit;
    static boolean isUpdate = true;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void bfs(int x, int y) {
        // 국경이 열린 국가 리스트
        ArrayList<int[]> unites = new ArrayList<>();
        // 국경이 열린 국가들의 인구 합을 위한 변수
        int sum = map[x][y];

        // bfs를 위한 queue
        Queue<Integer> q = new ArrayDeque<>();
        q.add(x);
        q.add(y);
        visit[x][y] = true;
        unites.add(new int[]{x, y});

        // bfs
        while (!q.isEmpty()) {
            x = q.poll();
            y = q.poll();
            // 이동
            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                // 국가가 아닌 좌표인가?
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                // 국경을 열수 있는가?
                if (Math.abs(map[nx][ny] - map[x][y]) < L || Math.abs(map[nx][ny] - map[x][y]) > R) continue;
                // 이미 확인해본 곳인가?
                if (visit[nx][ny]) continue;

                // 가능
                visit[nx][ny] = true;
                q.add(nx);
                q.add(ny);
                // 인구합 추가
                sum += map[nx][ny];
                // 국경열린 국가 리스트에 추가
                unites.add(new int[]{nx, ny});
            }
        }

        /**
         * 현재상태: 국경을 열 수 있는지 확인하여 가능한 국가들만 모아두고 인구합을 구해둔 상태
         */
        if (unites.size() > 1) { // 국경이 1개라도 열려있음 -> 인원수에 update 발생
            int avg = sum / unites.size();
            for (int[] item : unites) {
                map[item[0]][item[1]] = avg;
            }
            isUpdate = true;
        }
    }

    static void solve() {
        while (isUpdate) {
            isUpdate = false;
            visit = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visit[i][j]) {
                        bfs(i, j);
                    }
                }
            }
            if (isUpdate) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
