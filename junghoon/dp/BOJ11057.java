package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ11057 {
	
	/* 21년 11월 3주차 ( 19.11.21 ) : 오르막수
	 * 
	 * 문제: 오르막 수는 수의 자리가 오름차순을 이루는 수를 말한다. 
	 *      이때, 인접한 수가 같아도 오름차순으로 친다.
	 *      예를 들어, 2234와 3678, 11119는 오르막 수이지만, 
	 *      2232, 3676, 91111은 오르막 수가 아니다.
	 *      수의 길이 N이 주어졌을 때, 오르막 수의 개수를 구하는 프로그램을 작성하시오. 
	 *      수는 0으로 시작할 수 있다.

	 * 입력: 첫째 줄에 N (1 ≤ N ≤ 1,000)이 주어진다.
	 * 
	 * 출력: 첫째 줄에 길이가 N인 오르막 수의 개수를 10,007로 나눈 나머지를 출력한다.
	 * 
	 * 풀이: N-1의 K와 같거나 작은수를 선택해서 모든 경우의 수를다 더한다.
	 *     각 값에 대해서 10007로 나눈다, 마지막에는 모든 값을 더한 다음 10007로 나눈다.
	 *     
	 *     
	 * */
	
	static int dp[][];
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new int[n+1][10];
		for(int i = 0; i < 10; i++) {
			dp[1][i] = 1;
		}
		for(int i = 2; i <= n; i++) {
			for(int j = 0; j < 10; j++) {
				for(int k = 0; k<= j; k++) {
					dp[i][j] += dp[i-1][k];
					dp[i][j] %= 10007;
				}
			}
		}
		int sum = 0;
		for(int i = 0; i < 10; i++) {
			sum += dp[n][i];
		}
		System.out.println(sum%10007);
	}

}
