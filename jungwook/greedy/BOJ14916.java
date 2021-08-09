package jungwook.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ14916 {
    static int n;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
    }

    static void solve() {
        int ans = 0;
        while (n > 0) {
            if (n % 5 == 0) {
                ans += n/5;
                break;
            }
            n-=2;
            ans++;
        }

        if (n < 0) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
