package jungwook.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11047 {
    static int N, K;
    static int[] money;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        money = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            money[i] = Integer.parseInt(br.readLine());
        }
    }

    static void solve() {
        int ans = 0;

        for (int i = N; i >= 1; i--) {
            if (money[i] <= K) {
                ans += K / money[i];
                K = K % money[i];
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
