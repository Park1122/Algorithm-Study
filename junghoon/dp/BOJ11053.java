package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11053 {

	/* 21년 11월 1주차 ( 06.11.21 ) 가장 긴 증가하는 부분 수열
	 * 
	 * 문제: 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
	 *      예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 
	 *      가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.
	 * 
	 * 입력: 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.
	 * 		둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)
	 * 
	 * 출력: 첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.
	 * 
	 * 풀이: 최장 증가 부분 수열 (LIS),
	 *     
	 * */

	static int[] seq;
	static Integer[] dp;

	static int LIS(int N) {
		if(dp[N] == null) {
			dp[N] = 1;				
			for(int i = N - 1; i >= 0; i--) {
				if(seq[i] < seq[N]) {
					dp[N] = Math.max(dp[N], LIS(i) + 1);
				}
			}
		}
		return dp[N];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		seq = new int[N];
		dp = new Integer[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0; i < N; i++) {
			LIS(i);
		}
		int max = dp[0];
		for(int i = 1; i < N; i++) {
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
}
