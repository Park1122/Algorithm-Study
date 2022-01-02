package jungwook.two_pointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1806 {
    static int n, S;
    static int[] a;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        a = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        int R = 0, sum = 0, ans = n + 1;
        for (int L = 1; L <= n; L++) {
            // L - 1 을 구간에서 제외하기
            sum -= a[L - 1];
            // R 을 옮길 수 있을 때 까지 옮기기
            while (R + 1 <= n && sum < S) {
                R++;
                sum += a[R];
            }
            // [L ... R] 의 합, 즉 sum이 조건을 만족하면 정답 갱신하기
            if (sum >= S) {
                ans = Math.min(ans, R - L + 1);
            }
        }

        // ans 값을 보고 불가능 판단하기
        if(ans == n+1) ans = 0;
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
