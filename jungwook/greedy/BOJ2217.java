package jungwook.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2217 {
    static int N;
    static int[] ropes;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ropes = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }
    }

    static void solve() {
        Arrays.sort(ropes, 1, N + 1);
        int ans = 0;

        for (int i = N; i >= 1; i--) {
            ans = Math.max(ans, ropes[i] * (N - i + 1));
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
