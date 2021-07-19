package jungwook.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ7795 {
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[] A, B;

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N + 1];
        B = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
    }

    static int lowerBound(int[] A, int L, int R, int x) {
        int result = L - 1;

        while (L <= R) {
            int mid = (L + R) / 2;
            if (A[mid] < x) {
                result = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return result;
    }

    static void solve() {
        Arrays.sort(B, 1, M + 1);

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans += lowerBound(B, 1, M, A[i]);
        }
        sb.append(ans).append('\n');
    }

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {
            input();
            solve();
        }
        System.out.println(sb.toString());
    }
}
