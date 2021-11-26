package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1912 {

	/* 21년 11월 1주차 ( 05.11.21 )
	 * 
	 * 문제: n개의 정수로 이루어진 임의의 수열이 주어진다. 
	 *      우리는 이 중 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하려고 한다.
	 *      단, 수는 한 개 이상 선택해야 한다.
	 *      예를 들어서 10, -4, 3, 1, 5, 6, -35, 12, 21, -1 이라는 수열이 주어졌다고 하자. 
	 *      여기서 정답은 12+21인 33이 정답이 된다.

	 * 입력: 첫째 줄에 정수 n(1 ≤ n ≤ 100,000)이 주어지고 둘째 줄에는 n개의 정수로 이루어진 수열이 주어진다.
	 *      수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수이다.
	 * 
	 * 출력: 첫째 줄에 답을 출력한다.
	 * 
	 * 풀이: Top down 방식으로 입력방법 조합하여 파악
	 *     
	 * */


	static int[] arr;		
	static Integer[] dp;	
	static int max;		

	static int recur(int N) {

		if(dp[N] == null) {
			dp[N] = Math.max(recur(N - 1) + arr[N], arr[N]);

			max = Math.max(dp[N], max);
		}

		return dp[N];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		arr = new int[N];
		dp = new Integer[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		dp[0] = arr[0];
		max = arr[0];

		recur(N - 1);

		System.out.println(max);
	}



}
