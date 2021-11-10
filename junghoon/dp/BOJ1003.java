package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1003 {
	
	/* 21년 10월 5주차 ( 30.10.21 )
	 * 
	 * 문제: 다음 소스는 N번째 피보나치 수를 구하는 C++ 함수이다.
	 *     int fibonacci(int n) {
	 *         if (n == 0) {
	 *            printf("0");
	 *            return 0;
	 *          } else if (n == 1) {
	 *          printf("1");
	 *          return 1;
	 *          } else {
	 *      return fibonacci(n‐1) + fibonacci(n‐2);
	 *          }
	 *       }
	 *      fibonacci(3)을 호출하면 다음과 같은 일이 일어난다.
	 *      fibonacci(3)은 fibonacci(2)와 fibonacci(1) (첫 번째 호출)을 호출한다.
	 *      fibonacci(2)는 fibonacci(1) (두 번째 호출)과 fibonacci(0)을 호출한다.
	 *      두 번째 호출한 fibonacci(1)은 1을 출력하고 1을 리턴한다.
	 *      fibonacci(0)은 0을 출력하고, 0을 리턴한다.
	 *      fibonacci(2)는 fibonacci(1)과 fibonacci(0)의 결과를 얻고, 1을 리턴한다.
	 *      첫 번째 호출한 fibonacci(1)은 1을 출력하고, 1을 리턴한다.
	 *      fibonacci(3)은 fibonacci(2)와 fibonacci(1)의 결과를 얻고, 2를 리턴한다.
	 *      1은 2번 출력되고, 0은 1번 출력된다. N이 주어졌을 때, fibonacci(N)을 호출했을 때, 0과 1이 각각 몇 번 출력되는지 구하는 프로그램을 작성하시오.
	 * 
	 * 입력: 첫째 줄에 테스트 케이스의 개수 T가 주어진다.
	 * 		각 테스트 케이스는 한 줄로 이루어져 있고, N이 주어진다. N은 40보다 작거나 같은 자연수 또는 0이다.
	 * 
	 * 출력: 각 테스트 케이스마다 0이 출력되는 횟수와 1이 출력되는 횟수를 공백으로 구분해서 출력한다.
	 * 
	 * 풀이: 피보나치 수에서 0 과 1이 호출되는 횟수를 구하는 것
	 *     
	 * */
	

	///1번 재귀로 푸는 법
	static Integer[][] dp = new Integer[41][2];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		dp[0][0] = 1;
		dp[0][1] = 0;
		dp[1][0] = 0;
		dp[1][1] = 1;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T --> 0){
			int N = Integer.parseInt(br.readLine());
			fibonacci(N);
			sb.append(dp[N][0] + " " + dp[N][1]).append('\n');
		}
		System.out.println(sb);
	}
	
	static Integer[] fibonacci(int N) {
		if(dp[N][0] == null || dp[N][1] == null) {
			dp[N][0] = fibonacci(N - 1)[0] + fibonacci(N - 2)[0];
			dp[N][1] = fibonacci(N - 1)[1] + fibonacci(N - 2)[1];
		}
		return dp[N];
 
	}
	
	
	//**2번째 방법인 반복문으로 푸는 것 1번이 재귀
	
//	static int zero;
//	static int one;
//	static int zero_plus_one;
//	public static void main(String[] args) throws IOException {
// 
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 
//		int T = Integer.parseInt(br.readLine());
//		
//		StringBuilder sb = new StringBuilder();
//		
//		for (int i = 0; i < T; i++) {
//			int N = Integer.parseInt(br.readLine());
//			fibonacci(N);			
//			sb.append(zero).append(' ').append(one).append('\n');
//		}
//		System.out.println(sb);
// 
//	}
// 
//	public static void fibonacci(int N) {
//		zero = 1;
//		one = 0;
//		zero_plus_one = 1;
// 
//		for (int i = 0; i < N; i++) {
//			zero = one;
//			one = zero_plus_one;
//			zero_plus_one = zero + one;
//		}
// 
//	}

}
