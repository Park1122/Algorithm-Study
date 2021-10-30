package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 치즈를 상대로 bfs를 하게 되면 내부 빈 공간을 체크 할 방법이 없음
 * 공기를 상대로 bfs를 진행하면서 1을 만나면 가장 외곽 치즈라는 것을 체킹 후, 1시간 뒤 체킹한 부분만 녹여주면 됨
 */
public class BOJ2636 {
    static int N, M, ans;
    static int[][] board;
    static boolean[][] visit;
    static ArrayList<Integer> con; // bfs마다 남은 치즈 개수를 담아둘 리스트
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        con = new ArrayList<>();
    }

    static void bfs(int x, int y) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(x);
        q.add(y);
        visit[x][y] = true;

        while (!q.isEmpty()) {
            x = q.poll();
            y = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visit[nx][ny]) continue;
                if (board[nx][ny] == 0) {
                    visit[nx][ny] = true;
                    // 외곽의 치즈를 찾기 위해 q에 넣음
                    q.add(nx);
                    q.add(ny);
                }
                if (board[nx][ny] == 1) {
                    board[nx][ny] = 2;
                    visit[nx][ny] = true;
                    // q.add()를 하지 않는 이유
                    // 가장 외곽만 확인하면 되고, 치즈의 내부까지 확인할 필요는 없어서 q에 넣지 않음
                }
            }
        }
        removeCheese();
    }

    static void removeCheese() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                }
            }
        }
    }

    static int getCheeseCount() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    static void solve() {
        boolean isMelting = true;
        //처음부터 없거나, 1시간 뒤에 바로 다 사라질 수 있기에 초기값을 저장
        int initialCheeseCount = getCheeseCount();

        while (isMelting) {
            ans++;
            visit = new boolean[N][M];
            bfs(0, 0);

            // bfs 후에 치즈 개수 카운팅 후 다 녹은 상태인지 확인
            int cnt = getCheeseCount();
            if (cnt == 0) {
                isMelting = false;
            } else {
                con.add(cnt);
            }
        }

        // 치즈가 다 녹은 상태에 진입한 상태
        sb.append(ans).append('\n');

        // 치즈 녹는 시간 최소 2시간 이상인 경우
        if (con.size() > 1) {
            sb.append(con.get(con.size() - 1));
        } else { // 1시간 혹은 처음부터 없는 경우
            sb.append(initialCheeseCount);
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
