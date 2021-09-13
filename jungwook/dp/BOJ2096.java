package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2096 {
    static int N;
    static int[][] nums, DyMax, DyMin;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        nums = new int[N+1][4];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        DyMax = new int[N + 1][4];
        DyMin = new int[N + 1][4];
    }

    static void solve() {
        DyMax[1][1] = nums[1][1];
        DyMax[1][2] = nums[1][2];
        DyMax[1][3] = nums[1][3];
        DyMin[1][1] = nums[1][1];
        DyMin[1][2] = nums[1][2];
        DyMin[1][3] = nums[1][3];

        for (int i = 2; i <= N; i++) {
            DyMax[i][1] = Math.max(DyMax[i - 1][1], DyMax[i - 1][2]) + nums[i][1];
            DyMax[i][2] = Math.max(DyMax[i - 1][1], Math.max(DyMax[i - 1][2], DyMax[i - 1][3])) + nums[i][2];
            DyMax[i][3] = Math.max(DyMax[i - 1][2], DyMax[i - 1][3]) + nums[i][3];
        }
        for (int i = 2; i <= N; i++) {
            DyMin[i][1] = Math.min(DyMin[i - 1][1], DyMin[i - 1][2]) + nums[i][1];
            DyMin[i][2] = Math.min(DyMin[i - 1][1], Math.min(DyMin[i - 1][2], DyMin[i - 1][3])) + nums[i][2];
            DyMin[i][3] = Math.min(DyMin[i - 1][2], DyMin[i - 1][3]) + nums[i][3];
        }

        System.out.println(Math.max(DyMax[N][1], Math.max(DyMax[N][2], DyMax[N][3])) + " " + Math.min(DyMin[N][1], Math.min(DyMin[N][2], DyMin[N][3])));
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
