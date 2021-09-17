package codingstudy202107.w11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 11주차
 * 문제 출처 : https://www.acmicpc.net/problem/11049
 * 이름 : 행렬 곱셈 순서
 * 사용 알고리즘 : 다이나믹 프로그래밍
 */
public class BOJ11049 {

    public static void main(String[] args) throws IOException {
        BOJ11049 main = new BOJ11049();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        StringTokenizer st;
        int[][] arr = new int[n+1][2];
        int[][] dp = new int [n+1][501];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(reader.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());//rows[i]
            arr[i][1] = Integer.parseInt(st.nextToken());//cols[i]
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }

        System.out.print(main.solution(arr,dp,1,n));
    }

    private int solution(int[][] arr, int[][] dp, int start, int end) {
        if (start == end)
            return 0;

        if (dp[start][end] != Integer.MAX_VALUE) {
            return dp[start][end];
        }

        for (int i = start; i < end; i++) {
            int cost = solution(arr,dp,start, i) + solution(arr,dp,i + 1, end) + arr[start][0] * arr[i][1] * arr[end][1];
            dp[start][end] = Math.min(dp[start][end], cost);
        }
        return dp[start][end];
    }

}
