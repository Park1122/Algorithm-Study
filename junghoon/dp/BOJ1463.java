package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1463 {
	/* 21년 11월 1주차 ( 06.11.21 ) 1로 만들기
	 * 
	 * 문제: 정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
	 *      X가 3으로 나누어 떨어지면, 3으로 나눈다.
	 *      X가 2로 나누어 떨어지면, 2로 나눈다.
	 *      1을 뺀다.
	 *      정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다.
	 *      연산을 사용하는 횟수의 최솟값을 출력하시오.
	 * 
	 * 입력: 첫째 줄에 1보다 크거나 같고, 106보다 작거나 같은 정수 N이 주어진다.
	 * 
	 * 출력: 첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.
	 * 
	 * 풀이: 재귀호출을 하면서 DP를 최솟값으로 갱신해주어야 한다.
	 *     
	 *     
	 * */
	
	static Integer[] dp;
	
	static int recur(int N) {
		 
		if (dp[N] == null) {
			if (N % 6 == 0) {
				dp[N] = Math.min(recur(N - 1), Math.min(recur(N / 3), recur(N / 2))) + 1;
			}
			else if (N % 3 == 0) {
				dp[N] = Math.min(recur(N / 3), recur(N - 1)) + 1;
			}
			else if (N % 2 == 0) {
				dp[N] = Math.min(recur(N / 2), recur(N - 1)) + 1;
			}
			else {
				dp[N] = recur(N - 1) + 1;
			}
		}
		return dp[N];
	}
	 
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		int N = Integer.parseInt(br.readLine());
 
		dp = new Integer[N + 1];
		dp[0] = dp[1] = 0;
 
		System.out.print(recur(N));
 
	}
 
	

}
