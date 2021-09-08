package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5557 {
    static int N;
    static int[] nums;
    // Dy[i][j]: i -> i번째 수까지 이용, j -> 0~20 사이의 수를 만들 수 있는가
    // ex. 3번째 수까지 이용, 10을 만들 수 있다. => Dy[3][10] = 1;
    static long[][] Dy;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        nums = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Dy = new long[N + 1][21];
    }

    static void solve() {
        Dy[1][nums[1]] = 1;

        for (int i = 2; i < N; i++) {
            for (int j = 0; j <= 20; j++) {
                if (Dy[i - 1][j] != 0) {
                    if (j + nums[i] <= 20) {
                        Dy[i][j + nums[i]] += Dy[i - 1][j];
                    }
                    if (j - nums[i] >= 0) {
                        Dy[i][j - nums[i]] += Dy[i - 1][j];
                    }
                }
            }
        }

        System.out.println(Dy[N - 1][nums[N]]);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
