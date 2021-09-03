package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class BOJ1149 {
    // https://www.acmicpc.net/problem/1149

    // 소요시간 >>
    // 50분

    // 아이디어 >>
    // - n의 최소값 = n-1까지의 최소값 + n에 가능한 색 중 가장 비용이 작은 색의 값

    // 에러 로그 >>
    // X

    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(reader.readLine());

        dp = new int[1001][3];

        String[] colors = reader.readLine().split(" ");
        dp[1][0] = Integer.parseInt(colors[0]);
        dp[1][1] = Integer.parseInt(colors[1]);
        dp[1][2] = Integer.parseInt(colors[2]);

        for(int i=2;i<=n;i++){
            int[] ColorInts = Stream.of(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            dp[i][0] = Math.min(dp[i-1][1],dp[i-1][2])+ColorInts[0];
            dp[i][1] = Math.min(dp[i-1][0],dp[i-1][2])+ColorInts[1];
            dp[i][2] = Math.min(dp[i-1][0],dp[i-1][1])+ColorInts[2];
        }

        System.out.println(Math.min(Math.min(dp[n][0],dp[n][1]),dp[n][2]));
    }


}
