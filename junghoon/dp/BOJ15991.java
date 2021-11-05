package DP;

import java.util.Scanner;

public class BOJ15991 {
	
	/* 21년 10월 5주차 ( 30.10.21 )
	 * 
	 * 문제: 정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 3가지가 있다. 합을 나타낼 때는 수를 1개 이상 사용해야 한다. 단, 합은 대칭을 이루어야 한다.
	 * 		1+1+1+1
	 * 		1+2+1
	 * 		2+2
	 * 		정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.
	 * 
	 * 입력: 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있고, 정수 n이 주어진다. n은 양수이며 100,000보다 작거나 같다.	 * 
	 * 
	 * 출력: 각 테스트 케이스마다, n을 1, 2, 3의 합으로 나타내는 방법의 수를 1,000,000,009로 나눈 나머지를 출력한다.
	 * 
	 * 풀이: 15990이랑 비슷한데 dp[0]을 어떻게 생각하느냐에 따라 다른 것 같음
	 *     
	 * */
	
	//**이거 HSK 시험 끝나고 다시 보기
	
	public static void main(String[] args) {
		
        Scanner sc = new Scanner(System.in);
 
        long dp[] = new long[100001];
        
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 2;
        dp[4] = 3;
        dp[5] = 3;
 
        for(int i = 6; i < dp.length; i++){
            dp[i] = (dp[i - 6] + dp[i - 4] + dp[i - 2]) % 1000000009;
        }
 
        int test = sc.nextInt();
 
        while(test-- > 0){
            int num = sc.nextInt();
 
            System.out.println(dp[num]);
        }
    }

}
