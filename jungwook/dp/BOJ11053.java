package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11053 {
    static int N;
    static int[] nums, Dy;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Dy = new int[N + 1];
    }

    static void solve() {
        for (int i = 1; i <= N; i++) {
            Dy[i] = 1;
            for (int j = 1; j < i; j++) {
                if (nums[j] < nums[i] && Dy[i] < Dy[j] + 1) {
                    Dy[i] = Dy[j] + 1;
                }
            }
        }
        Arrays.sort(Dy, 1, N + 1);

        System.out.println(Dy[N]);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
