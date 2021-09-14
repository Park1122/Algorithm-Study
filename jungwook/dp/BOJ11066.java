package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11066 {
    static int K;
    static int[] nums;
    static int[][] Dy, sum;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        K = Integer.parseInt(br.readLine());
        nums = new int[K + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= K; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        sum = new int[K + 1][K + 1];
    }

    static void preprocess() {
        for (int i = 1; i <= K; i++) {
            for (int j = i; j <= K; j++) {
                sum[i][j] = sum[i][j - 1] + nums[j];
            }
        }
    }

    static void solve() {
        preprocess();
        Dy = new int[K + 1][K + 1];

        for (int len = 2; len <= K; len++) { // 구간의 길이
            for (int i = 1; i <= K - len + 1; i++) {
                int j = i + len - 1;
                Dy[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    Dy[i][j] = Math.min(Dy[i][j], Dy[i][k] + Dy[k + 1][j] + sum[i][j]);
                }
            }
        }

        sb.append(Dy[1][K]).append('\n');
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tt = 1; tt <= T; tt++) {
            input();
            solve();
        }
        System.out.println(sb);
    }
}
