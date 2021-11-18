package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {
    static class Node {
        int x;
        int y;
        int dist;
        int breakCnt;

        public Node(int x, int y, int dist, int breakCnt) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.breakCnt = breakCnt;
        }
    }

    static int N, M;
    static String[] a;
    static boolean[][][] visit;
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
        visit = new boolean[N][M][2];
    }

    static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, 1, 0));
        visit[0][0][0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int dist = cur.dist;
            int breakCnt = cur.breakCnt;

            if (x == N - 1 && y == M - 1) {
                return dist;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dir[d][0];
                int ny = y + dir[d][1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visit[nx][ny][breakCnt]) continue;

                if (a[nx].charAt(ny) == '0') {
                    q.add(new Node(nx, ny, dist + 1, breakCnt));
                    visit[nx][ny][breakCnt] = true;
                } else {
                    if (breakCnt == 0) {
                        q.add(new Node(nx, ny, dist + 1, breakCnt + 1));
                        visit[nx][ny][breakCnt + 1] = true;
                    }
                }
            }
        }
        return -1;
    }

    static void solve() {
        System.out.println(bfs());
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
