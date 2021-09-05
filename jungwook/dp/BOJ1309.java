package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1309 {
    static int N;
    static int[][] Dy;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        Dy = new int[N + 1][3];
    }

    static void solve() {
        Dy[1][0] = 1;
        Dy[1][1] = 1;
        Dy[1][2] = 1;

        for (int i = 2; i <= N; i++) {
            // i번째 줄에서 양쪽 다 안들어 가는 경우
            Dy[i][0] = (Dy[i - 1][0] + Dy[i - 1][1] + Dy[i - 1][2]) % 9901;

            // i번째 줄에서 왼쪽에 들어가는 경우
            Dy[i][1] = (Dy[i - 1][0] + Dy[i - 1][2]) % 9901;

            // i번째 줄에서 오른쪽에 들어가는 경우
            Dy[i][2] = (Dy[i - 1][0] + Dy[i - 1][1]) % 9901;
        }

        System.out.println((Dy[N][0] + Dy[N][1] + Dy[N][2]) % 9901);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
