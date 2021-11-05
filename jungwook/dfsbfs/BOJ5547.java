package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5547 {
    static int W, H;
    static int[][] a;
    static boolean[][] visit;
    static int[][] dirEven= {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, -1}, {-1, -1}};
    static int[][] dirOdd= {{-1, 0}, {0, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        a = new int[H + 2][W + 2];
        for (int i = 1; i < H + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < W + 1; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    // 건물의 외곽인지 체크
    static void bfs(int x, int y) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(x);
        q.add(y);
        visit[x][y] = true;

        while (!q.isEmpty()) {
            x = q.poll();
            y = q.poll();
            for (int k = 0; k < 6; k++) {
                int nx;
                int ny;
                if (x % 2 == 0) {
                    nx = x + dirEven[k][0];
                    ny = y + dirEven[k][1];
                } else {
                    nx = x + dirOdd[k][0];
                    ny = y + dirOdd[k][1];
                }
                if(nx < 0 || ny < 0 || nx >= H+2 || ny >= W+2) continue;
                if(a[nx][ny] == 1) continue;
                if(visit[nx][ny]) continue;

                visit[nx][ny] = true;
                q.add(nx);
                q.add(ny);
            }
        }
    }

    static void solve() {
        visit = new boolean[H + 2][W + 2];
        // 건물의 외곽인지 여부를 체크
        bfs(0, 0);

        int ans = 0;
        // 건물인 좌표에서 6방향으로 이동했을 때의 visit값이 true면 건물 외벽이라는 뜻
        for (int i = 1; i < H + 1; i++) {
            for (int j = 1; j < W + 1; j++) {
                if(a[i][j] == 0) continue;
                for (int k = 0; k < 6; k++) {
                    int nx;
                    int ny;
                    if (i % 2 == 0) {
                        nx = i + dirEven[k][0];
                        ny = j + dirEven[k][1];
                    } else {
                        nx = i + dirOdd[k][0];
                        ny = j + dirOdd[k][1];
                    }
                    if(visit[nx][ny]) ans++;
                }
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
