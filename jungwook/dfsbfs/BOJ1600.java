package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * bfs는 현재 상태를 나타낼 수 있는 것이라면 사용가능한 테크닉이다.
 * 큐에 넣는 것 뿐만 아니라 방문 체크를 할 때도 모든 "상태"에 대해 전부 하면 된다.
 */
public class BOJ1600 {
    static class Monkey {
        int x;
        int y;
        int dist;
        int k;

        public Monkey(int x, int y, int dist, int k) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.k = k;
        }
    }

    static int K, H, W;
    static int[][] a;
    static boolean[][][] visit;
    static int[][] dirHorse = {{2, 1}, {1, 2}, {2, -1}, {1, -2}, {-1, 2}, {-2, 1}, {-1, -2}, {-2, -1}};
    static int[][] dirMonkey = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        a = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visit = new boolean[H][W][K + 1];
    }

    static int bfs(Monkey monkey) {
        Queue<Monkey> q = new ArrayDeque<>();
        q.add(monkey);
        visit[0][0][0] = true;

        while (!q.isEmpty()) {
            Monkey m = q.poll();

            if (m.x == H - 1 && m.y == W - 1) {
                return m.dist;
            }

            for (int d = 0; d < 4; d++) {
                int nx = m.x + dirMonkey[d][0];
                int ny = m.y + dirMonkey[d][1];
                if(nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                if(a[nx][ny] == 1) continue;
                if(visit[nx][ny][m.k]) continue;

                q.add(new Monkey(nx, ny, m.dist + 1, m.k));
                visit[nx][ny][m.k] = true;
            }

            if(m.k == K) continue;

            for (int d = 0; d < 8; d++) {
                int nx = m.x + dirHorse[d][0];
                int ny = m.y + dirHorse[d][1];
                if(nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                if(a[nx][ny] == 1) continue;
                if(visit[nx][ny][m.k + 1]) continue;

                q.add(new Monkey(nx, ny, m.dist + 1, m.k + 1));
                visit[nx][ny][m.k + 1] = true;
            }
        }
        return -1;
    }


    static void solve() {
        System.out.println(bfs(new Monkey(0, 0, 0, 0)));
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
