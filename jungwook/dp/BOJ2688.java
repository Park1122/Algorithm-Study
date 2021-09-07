package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2688 {
    static int T, N;
    static long[][] Dy;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static void preprocess() {
        Dy = new long[65][10];

        //Dy[N][k] -> N자리의 수 중, 1의 자리가 k인 경우의 수
        for (int i = 1; i <= 64; i++) {
            Dy[i][0] = 1;
            for (int j = 1; j < 10; j++) {
                if(i == 1) Dy[i][j] = 1;
                else{
                    Dy[i][j] = Dy[i - 1][j] + Dy[i][j - 1];
                }
            }
        }
    }

    static void solve() {
        long ans = 0;
        for (int i = 0; i < 10; i++) {
            ans += Dy[N][i];
        }
        sb.append(ans).append('\n');
    }

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        preprocess();
        for (int tt = 1; tt <= T; tt++) {
            N = Integer.parseInt(br.readLine());
            solve();
        }
        System.out.println(sb);
    }
}
