package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9465 {
    static int T, N;
    static int[][] stk, Dy;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        stk = new int[N + 1][2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            stk[i][0] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            stk[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        Dy = new int[N + 1][2];

        Dy[1][0] = stk[1][0];
        Dy[1][1] = stk[1][1];

        for (int i = 2; i <= N; i++) {
            Dy[i][0] = Math.max(Dy[i - 1][1], Dy[i - 2][1]) + stk[i][0];
            Dy[i][1] = Math.max(Dy[i - 1][0], Dy[i - 2][0]) + stk[i][1];
        }
        sb.append(Math.max(Dy[N][0], Dy[N][1])).append('\n');
    }

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            input();
            solve();
        }
        System.out.println(sb);
    }
}
