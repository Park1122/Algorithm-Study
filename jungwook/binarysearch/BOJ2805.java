package jungwook.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2805 {
    static int N, M;
    static int[] trees;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trees = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        Arrays.sort(trees, 1, N + 1);

        int result = 0;
        int start = 0;
        int end = trees[N];

        while (start <= end) {
            long total = 0;
            int mid = (start + end) / 2;

            for (int i = 1; i <= N; i++) {
                if (trees[i] > mid) {
                    total += trees[i] - mid;
                }
            }

            if (total < M) {
                end = mid - 1;
            } else {
                start = mid + 1;
                result = mid;
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
