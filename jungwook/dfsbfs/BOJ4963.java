package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ4963 {
    static int w, h;
    static int[][] a;
    static boolean[][] visit;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        a = new int[h][w];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visit = new boolean[h][w];
    }

    static void dfs(int x, int y) {
        visit[x][y] = true;
        for (int d = 0; d < 8; d++) {
            int nx = x + dir[d][0];
            int ny = y + dir[d][1];
            if(nx<0 || ny<0 || nx>=h || ny>=w) continue;
            if(a[nx][ny] == 0) continue;
            if(visit[nx][ny]) continue;
            dfs(nx, ny);
        }
    }

    static void solve() {
        int ans = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (!visit[i][j] && a[i][j] == 1) {
                    ans++;
                    dfs(i, j);
                }
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            input();
            if(w == 0 && h == 0) break;
            solve();
        }
    }
}
