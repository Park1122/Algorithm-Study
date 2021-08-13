package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9095 {
    static int[] Dy;
    static StringBuilder sb = new StringBuilder();

    static void preprocess() {
        Dy = new int[15];

        Dy[1] = 1;
        Dy[2] = 2;
        Dy[3] = 4;

        for (int i = 4; i <= 11; i++) {
            Dy[i] = Dy[i - 1] + Dy[i - 2] + Dy[i - 3];
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
