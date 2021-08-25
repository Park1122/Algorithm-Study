package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1904 {
    static int N;
    static int[] Dy;

    static void preprocess() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Dy = new int[1000001];

        Dy[1] = 1;
        Dy[2] = 2;

        for (int i = 3; i <= 1000000; i++) {
            Dy[i] = (Dy[i - 1] + Dy[i - 2]) % 15746;
        }
    }

    public static void main(String[] args) throws IOException {
        preprocess();
        System.out.println(Dy[N]);
    }
}
