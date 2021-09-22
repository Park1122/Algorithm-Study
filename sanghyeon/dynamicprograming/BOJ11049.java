package year_2021.month_09.day_17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11049 {

    static int N;
    static int r, c;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][2];
        dp = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            map[i][0] = r;
            map[i][1] = c;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j)
                    dp[i][j] = 0;
                else
                    dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int c = 1; c < N; c++) {
            for (int i = 0; i + c < N; i++) {
                calcul(i, i + c);
            }
        }

        System.out.println(dp[0][N - 1]);
    }

    private static void calcul(int start, int end) {
        for (int i = start; i < end; i++) {
            // A*B => a*b b*b b*c
            int cost = dp[start][i] + dp[i + 1][end] + map[start][0] * map[i][1] * map[end][1];
            dp[start][end] = Math.min(dp[start][end], cost);
        }

    }
}
