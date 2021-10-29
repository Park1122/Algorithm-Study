package DP;

import java.util.Scanner;

public class BOJ11726 {
	
	/* 21년 10월 4주차 ( 23.10.21 )
	 * 
	 * 문제: 2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
	 *      아래 그림은 2×5 크기의 직사각형을 채운 한 가지 방법의 예이다.(https://www.acmicpc.net/problem/11726)
	 * 
	 * 입력: 첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)
	 * 
	 * 출력: 첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.
	 * 
	 * 풀이: 2xn 타일링 -> dp[n] = 2 x N 크기의 직사각형을 채우는 방법의 갯수 
	 *      피보나치랑 비슷하다고 하는데 난 피보나치도 헷갈림 -> 피보나치 공부 ##
	 *     
	 * */
	
	static int recur(int[] dp, int n) {
		for(int i = 3; i <= n; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 10007;
		}
		return dp[n];
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] dp = new int[n+2];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		
		recur(dp, n);
		System.out.println(recur(dp, n));
	}

}
