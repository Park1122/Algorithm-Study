package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502 {
    static int N, M, B, ans;
    static int[][] A, blank;
    static boolean[][] visit;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N + 1][M + 1];
        visit = new boolean[N + 1][M + 1];
        blank = new int[N * M + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    // 바이러스 퍼뜨리는 과정
    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        //모든 바이러스를 시작점으로 하기 위해, 큐에 삽입
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                visit[i][j] = false;
                if (A[i][j] == 2) {
                    q.add(i); // 행 삽입
                    q.add(j); // 열 삽입
                    visit[i][j] = true;
                }
            }
        }

        //bfs
        while (!q.isEmpty()) {
            int x = q.poll(); // 행
            int y = q.poll(); // 열

            for (int k = 0; k < 4; k++) { // 4방향 체크
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if (nx < 1 || ny < 1 || nx > N || ny > M) continue;
                if(A[nx][ny] != 0) continue;
                if(visit[nx][ny]) continue;
                visit[nx][ny] = true;
                q.add(nx);
                q.add(ny);
            }
        }

        //탐색 종료 -> 안전 영역 계산 및 정답 갱신
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (A[i][j] == 0 && !visit[i][j]) {
                    cnt++;
                }
            }
        }
        ans = Math.max(ans, cnt);
    }

    //idx 번째 빈 칸에 벽을 세울지 말지 결정, 이전까지 selectedCnt 개의 벽을 세웠다
    static void dfs(int idx, int selectedCnt) {
        if (selectedCnt == 3) { // 3 개의 벽을 모두 세운 상태
            bfs(); // 3개의 벽이 세워진 후, 바이러스를 퍼뜨려보자
            return;
        }
        if(idx > B) return; // 더 이상 세울 수 있는 벽이 없는 상태

        // 벽을 세울 경우
        A[blank[idx][0]][blank[idx][1]] = 1;
        dfs(idx + 1, selectedCnt + 1);

        // 벽을 안 세울 경우
        A[blank[idx][0]][blank[idx][1]] = 0;
        dfs(idx + 1, selectedCnt);
    }

    static void solve() {
        // 모든 벽을 세울 수 있는 위치 모아보기
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (A[i][j] == 0) {
                    B++;
                    blank[B][0] = i;
                    blank[B][1] = j;
                }
            }
        }

        // 벽을 3개 세우는 모든 방법 확인 -> B 개 중에서 3개 선택하기
        dfs(1, 0);
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
