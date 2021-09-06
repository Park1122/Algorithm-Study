package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11057 {
    static int N;
    static int[][] Dy;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        Dy = new int[N + 1][10];
    }

    static void solve() {
        //Dy[N][k] -> N자리의 수 중, 1의 자리가 k인 경우의 수
        for (int i = 1; i <= N; i++) {
            Dy[i][0] = 1;
            for (int j = 1; j < 10; j++) {
                if(i == 1) Dy[i][j] = 1;
                else{
                    Dy[i][j] = (Dy[i - 1][j] + Dy[i][j - 1]) % 10007;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 10; i++) {
            ans += Dy[N][i];
        }
        System.out.println(ans % 10007);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
