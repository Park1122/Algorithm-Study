package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15991 {
    static long[] Dy;
    static StringBuilder sb = new StringBuilder();

    static void preprocess() {
        Dy = new long[100001];

        Dy[1] = 1;
        Dy[2] = 2;
        Dy[3] = 2;
        Dy[4] = 3;
        Dy[5] = 3;
        Dy[6] = 6;
        for (int i = 7; i <= 100000; i++) {
            Dy[i] = (Dy[i - 2] + Dy[i - 4] + Dy[i - 6]) % 1000000009;
        }
    }

    static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(Dy[N]).append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        preprocess();
        solve();
    }
}
