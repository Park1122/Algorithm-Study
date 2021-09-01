package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2156 {
    static int N;
    static int[] wine, Dy;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        wine = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }
    }

    static void solve() {
        Dy = new int[N + 1];

        Dy[1] = wine[1];
        if (N > 1) {
            Dy[2] = wine[1] + wine[2];
        }

        //N번째 포도주를 0번째 연속 마시는 경우 -> N-1번째 포도주를 마시거나 말거나 : Dy[N] = Dy[N-1]
        //N번째 포도주를 1번째 연속 마시는 경우 -> N번째는 마심. N-1번째는 안됨. N-2번째는 마시거나 말거나 : Dy[N] = Dy[N-2] + wine[i]
        //N번째 포도주를 2번째 연속 마시는 경우 -> N번째 마심. N-1번째 마심. N-2번째 안됨. N-3번째 마시거나 말거나 : Dy[N] = Dy[N-3] + wine[i-1] + wine[i]
        //셋중 최대값 선택
        for (int i = 3; i <= N; i++) {
            Dy[i] = Math.max(Dy[i - 1], Math.max(Dy[i - 2] + wine[i], Dy[i - 3] + wine[i - 1] + wine[i]));
        }

        System.out.println(Dy[N]);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
