package codingstudy202107.w11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 11주차
 * 문제 출처 : https://www.acmicpc.net/problem/11066
 * 이름 : 파일 합치기
 * 사용 알고리즘 : 다이나믹 프로그래밍
 * 병실에서 보고 음 버그만 잘 해결하면 되겟군 하고 잤는데 퇴원해서 버그 해결해보니까 안풀리지 뭡니까..
 * 한번 더 보자!! 잊지말고 별표...보고 하는데도 어려웠다..
 */
public class BOJ11066 {

    public static void main(String[] args) throws IOException {
        BOJ11066 main = new BOJ11066();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        StringBuilder builder = new StringBuilder();

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            if (i > 0) builder.append('\n');

            int t = Integer.parseInt(reader.readLine());
            st = new StringTokenizer(reader.readLine());
            int[] sum = new int[t + 1];
            for (int j = 1; j <= t; j++) {
                int val = Integer.parseInt(st.nextToken());
                sum[j]= sum[j-1]+val;
            }
            builder.append(main.solution(t, sum));
        }

        System.out.print(builder.toString());
    }

    private int solution(int k, int[] sum) {
        int[][] dp = new int[501][501];// 이부분 인터넷 코드에서 보통 502던데 502일 필요가 아무리 봐도 없음. 501이면 될거같음. 500페이지 안넘잖아여..

        for (int n = 1; n <= k; n++) {
            for (int j = 1; j + n <= k; j++) {
                int h = j + n;
                dp[j][h] = Integer.MAX_VALUE;
                for (int divide = j; divide < h; divide++) {
                    dp[j][h] = Math.min(dp[j][h], dp[j][divide] + dp[divide + 1][h] + sum[h] - sum[j - 1]);
                }
            }
        }

        return dp[1][k];
    }


}
