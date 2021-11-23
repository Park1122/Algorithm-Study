package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ16932 {
    static int N, M, cnt, sectorNum;
    static int[][] a;
    static int[][] visit; // 좌표별 섹터 번호 기입
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int[] sizeMap; // 섹터번호 인덱스에 해당 섹터 번호의 영역 사이즈 기입
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        a = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        sizeMap = new int[N * M + 1];
    }

    static void dfs(int x, int y) {
        cnt++;
        visit[x][y] = sectorNum;
        sizeMap[sectorNum] = Math.max(sizeMap[sectorNum], cnt);

        for (int d = 0; d < 4; d++) {
            int nx = x + dir[d][0];
            int ny = y + dir[d][1];
            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if(a[nx][ny] == 0) continue;
            if(visit[nx][ny] != 0) continue;

            dfs(nx, ny);
        }
    }

    static void solve() {
        // 좌표마다 영역 번호 붙이기 및 영역의 사이즈 갱신
        visit = new int[N][M];
        sectorNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (a[i][j] == 1 && visit[i][j] == 0) {
                    cnt = 0;
                    dfs(i, j);
                    sectorNum++;
                }
            }
        }

        // 최대값 찾기
        Set<Integer> sectorNumSet = new HashSet<>(); // 중복 제거 -> ex. 좌우가 알고보니 같은 섹터인 경우에는 1개 빼야 함.
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 0인 경우에 상하좌우 체크 예정
                if (a[i][j] == 0) {
                    sectorNumSet.clear();
                    // 0을 포함한 영역 크기를 계산하여 size에 담기
                    int size = 1;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dir[d][0];
                        int ny = j + dir[d][1];
                        if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                        if(visit[nx][ny] == 0) continue;

                        sectorNumSet.add(visit[nx][ny]);
                    }

                    // 중복 제거된 영역의 sectorNum으로 영역크기 구하고, 크기 더하기
                    for (int k : sectorNumSet) {
                        size += sizeMap[k];
                    }

                    // 최대값 구하기
                    max = Math.max(max, size);
                }
            }
        }

        System.out.println(max);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
