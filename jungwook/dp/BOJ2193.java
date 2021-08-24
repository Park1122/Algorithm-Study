package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2193 {
    static int N;
    static long[] Dy;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void preprocess() {
        Dy = new long[91];

        Dy[1] = 1;
        Dy[2] = 1;

        for (int i = 3; i <= 90; i++) {
            Dy[i] = Dy[i - 1] + Dy[i - 2];
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        preprocess();
        System.out.println(Dy[N]);
    }
}
