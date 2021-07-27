package jungwook.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2470 {
    static int N;
    static int[] A;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static int lowerBound(int[] A, int L, int R, int X) {
        int res = R + 1;

        while (L <= R) {
            int mid = (L + R) / 2;
            if (A[mid] >= X) {
                res = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return res;
    }

    static void solve() {
        Arrays.sort(A, 1, N + 1);

        int bestSum = Integer.MAX_VALUE;
        int value1 = 0;
        int value2 = 0;
        for (int left = 1; left <= N - 1; left++) {
            int result = lowerBound(A, left + 1, N, -A[left]);

            if (left + 1 <= result - 1 && result - 1 <= N && Math.abs(A[result - 1] + A[left]) < bestSum) {
                bestSum = Math.abs(A[left] + A[result - 1]);
                value1 = A[left];
                value2 = A[result - 1];
            }

            if (left + 1 <= result && result <= N && Math.abs(A[result] + A[left]) < bestSum) {
                bestSum = Math.abs(A[left] + A[result]);
                value1 = A[left];
                value2 = A[result];
            }
        }
        sb.append(value1).append(' ').append(value2);
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
