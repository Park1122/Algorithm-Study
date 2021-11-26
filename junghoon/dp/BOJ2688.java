package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ2688 {
	
	/* 21년 11월 3주차 ( 19.11.21 ) : 줄어들지 않아
	 * 
	 * 문제: 어떤 숫자가 줄어들지 않는다는 것은 그 숫자의 각 자리 수보다 그 왼쪽 자리 수가 작거나 같을 때 이다.
	 *      예를 들어, 1234는 줄어들지 않는다. 
	 *      줄어들지 않는 4자리 수를 예를 들어 보면 0011, 1111, 1112, 1122, 2223이 있다.
	 *      줄어들지 않는 4자리수는 총 715개가 있다.
	 *      이 문제에서는 숫자의 앞에 0(leading zero)이 있어도 된다. 
	 *      0000, 0001, 0002는 올바른 줄어들지 않는 4자리수이다.
	 *      n이 주어졌을 때, 줄어들지 않는 n자리 수의 개수를 구하는 프로그램을 작성하시오.

	 * 입력: 첫째 줄에 테스트 케이스의 개수 T(1 <= T <= 1,000)이 주어진다. 
	 *      각 테스트 케이스는 숫자 하나 n으로 이루어져 있다. (1 <= n <= 64)
	 * 
	 * 출력: 각 테스트 케이스에 대해 한 줄에 하나씩 줄어들지 않는 n자리 수의 개수를 출력한다.
	 * 
	 * 풀이: 다이나믹 프로그래밍? 자리 수가 커질 때마다 이전 자리 수의 값보다 크거나
	 *      같도록 설정해주는 수는 총 몇개가 있는지 구하는 것이 핵심
	 *     
	 * */
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		long[][] dp = new long[65][10]; 
		
		for (int i = 0; i <= 9; i++) {
			dp[1][i] = 1;
		}

		for (int i = 2; i <= 64; i++) {
			for (int j = 0; j <= 9; j++) {
				for (int k = j; k <= 9; k++) {
					dp[i][j] += dp[i - 1][k];
				}
			}
		}

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			long ans = 0;

			for (int i = 0; i <= 9; i++) {
				ans += dp[N][i];
			}

			sb.append(ans + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
