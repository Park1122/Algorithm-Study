package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11049 {
	
	/* 행렬 곱셈 순서
	 * 
	 * 문제: 크기가 N×M인 행렬 A와 M×K인 B를 곱할 때 필요한 곱셈 연산의 수는 총 N×M×K번이다. 
	 *      행렬 N개를 곱하는데 필요한 곱셈 연산의 수는 행렬을 곱하는 순서에 따라 달라지게 된다.
	 *      예를 들어, A의 크기가 5×3이고, B의 크기가 3×2, C의 크기가 2×6인 경우에 행렬의 곱 ABC를 구하는 경우를 생각해보자.
	 *      AB를 먼저 곱하고 C를 곱하는 경우 (AB)C에 필요한 곱셈 연산의 수는 5×3×2 + 5×2×6 = 30 + 60 = 90번이다.
	 *      BC를 먼저 곱하고 A를 곱하는 경우 A(BC)에 필요한 곱셈 연산의 수는 3×2×6 + 5×3×6 = 36 + 90 = 126번이다.
	 *      같은 곱셈이지만, 곱셈을 하는 순서에 따라서 곱셈 연산의 수가 달라진다.
	 *      행렬 N개의 크기가 주어졌을 때, 모든 행렬을 곱하는데 필요한 곱셈 연산 횟수의 최솟값을 구하는 프로그램을 작성하시오. 
	 *      입력으로 주어진 행렬의 순서를 바꾸면 안 된다.
	 * 
	 * 입력: 첫째 줄에 행렬의 개수 N(1 ≤ N ≤ 500)이 주어진다.
	 * 둘째 줄부터 N개 줄에는 행렬의 크기 r과 c가 주어진다. (1 ≤ r, c ≤ 500)
	 * 항상 순서대로 곱셈을 할 수 있는 크기만 입력으로 주어진다.
	 * 
	 * 출력: 첫째 줄에 입력으로 주어진 행렬을 곱하는데 필요한 곱셈 연산의 최솟값을 출력한다. 
	 *      정답은 231-1 보다 작거나 같은 자연수이다. 또한, 최악의 순서로 연산해도 연산 횟수가 231-1보다 작거나 같다.
	 * 
	 * 풀이: 행렬 곱셈 : A행렬 * B행렬 = A행의 수 * A열의 수 * B의 열의 수 = 행렬곱셈횟수
	 *     
	 * */
	
	public static void main(String [] args) throws IOException{
		
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(reader.readLine());
  
        int [][] a = new int[n][2];
        int [][] dp = new int[n][n];
        
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            a[i][0] = Integer.parseInt(st.nextToken());
            a[i][1] = Integer.parseInt(st.nextToken());
        }
        
        for(int i=1; i<n; i++) {
            for(int j=0; j<n-i; j++) {
                dp[j][j+i] = Integer.MAX_VALUE;
                for(int k=0; k<i; k++) {
                    int cost = dp[j][j+k] + dp[j+k+1][j+i] + a[j][0] * a[j+k][1] * a[j+i][1];
                    dp[j][j+i] = Math.min(dp[j][j+i], cost);
                }
            }
        }
        System.out.println(dp[0][n-1]);
    }


}
