package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1463 {
    static int N;
    static int[] Dy;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Dy = new int[1000001];
    }

    static void solve() {
        Dy[0] = 0;
        Dy[1] = 0;

        for (int i = 2; i <= 1000000; i++) {
            Dy[i] = Dy[i - 1] + 1;
            if (i % 2 == 0) {
                Dy[i] = Math.min(Dy[i], Dy[i / 2] + 1);
            }
            if (i % 3 == 0) {
                Dy[i] = Math.min(Dy[i], Dy[i / 3] + 1);
            }
        }

        System.out.println(Dy[N]);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
