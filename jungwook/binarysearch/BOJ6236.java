package jungwook.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6236 {
    static int N, M;
    static int[] A;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
    }

    static boolean determine(int money) {
        int cnt = 1;
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            if (sum + A[i] > money) {
                cnt++;
                sum = A[i];
            } else {
                sum += A[i];
            }
        }
        return cnt <= M;
    }

    static void solve() {
        int L = A[1];
        int R = 1000000000;
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            L = Math.max(L, A[i]);
        }

        while (L <= R) {
            int mid = (L + R) / 2;
            if (determine(mid)) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
