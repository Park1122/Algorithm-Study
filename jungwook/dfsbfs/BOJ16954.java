package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ16954 {
    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int ans;
    static char[][] a;
    static boolean[][] visit;
    static int[][] dir = {{0, 0}, {1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void input() throws IOException {
        a = new char[8][8];
        for (int i = 0; i < 8; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                a[i][j] = temp[j];
            }
        }
        visit = new boolean[8][8];
        ans = 0;
    }

    static void bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(7, 0));

        while (!q.isEmpty()) {
            int size = q.size();
            visit = new boolean[8][8];

            // 초단위로 모든 점 동시에 체크
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                int x = node.x;
                int y = node.y;

                if(a[x][y] == '#') continue;
                if (x == 0 && y == 7) {
                    ans = 1;
                    return;
                }

                for (int k = 0; k < 9; k++) {
                    int nx = x + dir[k][0];
                    int ny = y + dir[k][1];

                    if(nx < 0 || ny < 0 || nx >= 8 || ny >= 8) continue;
                    if(a[nx][ny] == '#') continue;
                    if(visit[nx][ny]) continue;

                    q.add(new Node(nx, ny));
                    visit[nx][ny] = true;
                }
            }
            moveWall();
        }
    }

    static void moveWall() {
        for (int x = 7; x >= 0; x--) {
            for (int y = 0; y < 8; y++) {
                if (x == 0) { // 끝 넘어가면 빈공간으로 변환
                    a[x][y] = '.';
                } else {
                    a[x][y] = a[x - 1][y];
                }
            }
        }
    }

    static void solve() {
        bfs();

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
