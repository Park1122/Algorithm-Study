package year_2021.month_10.day_07;

import java.util.*;
import java.io.*;

class P187_BOJ14502_연구소 {
    static class Dot {
        int x, y;

        public Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static int m;
    static int[][] map;
    static int[][] copyed;
    static List<Dot> virusList = new ArrayList<Dot>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        copyed = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2)
                    virusList.add(new Dot(i, j));
            }
        }

        setWall(0, 0);
        System.out.println(max);
    }

    // 백트래킹을 이용하여 3개의 벽 세우기
    static void setWall(int start, int depth) {
        if (depth == 3) {
            // 맵 복사
            copyMap();

            // 바이러스 퍼트리기
            for (Dot dot : virusList) {
                spreadVirus(dot.x, dot.y);
            }

            // 안전영역 크기 구하기
            max = Math.max(max, getSafeArea());
            return;
        }

        for (int i = start; i < n * m; i++) {
            int x = i / m;
            int y = i % m;

            if (map[x][y] == 0) {
                map[x][y] = 1;
                setWall(i + 1, depth + 1);
                map[x][y] = 0;
            }
        }
    }

    // 기존 맵을 유지하기 위해 바이러스 퍼트릴 맵 복사하기
    static void copyMap() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copyed[i][j] = map[i][j];
            }
        }
    }

    // DFS 로 바이러스 퍼트리기
    static void spreadVirus(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                if (copyed[nx][ny] == 0) {
                    copyed[nx][ny] = 2;
                    spreadVirus(nx, ny);
                }
            }
        }
    }

    static int getSafeArea() {
        int safe = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyed[i][j] == 0)
                    safe++;
            }
        }
        return safe;
    }
}