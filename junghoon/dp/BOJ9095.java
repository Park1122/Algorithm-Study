package DP;

import java.util.Scanner;

public class BOJ9095 {
	
	/* 21년 10월 4주차 ( 23.10.21 )
	 * 
	 * 문제: 정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 7가지가 있다. 합을 나타낼 때는 수를 1개 이상 사용해야 한다.
	 *      1+1+1+1
	 *      1+1+2
	 *      1+2+1
	 *      2+1+1
	 *      2+2
	 *      1+3
	 *      3+1
	 *      정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.
	 * 
	 * 입력: 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있고, 정수 n이 주어진다. n은 양수이며 11보다 작다.
	 * 
	 * 출력: 각 테스트 케이스마다, n을 1, 2, 3의 합으로 나타내는 방법의 수를 출력한다.
	 * 
	 * 풀이: DP 는 뭘까 좀 어려움 설명을 더 봐야함
	 *     
	 * */
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] nums = new int[11];
		//n이 양수, 11보다 작기에 11선언
		nums[1] = 1;
		nums[2] = 2;
		nums[3] = 4;
		for(int i = 0; i < T; i++) {
			int n = sc.nextInt();
			for(int j = 4; j <= n; j++) {
				nums[j] = nums[j-1] + nums[j-2] + nums[j-3];
			}
			System.out.println(nums[n]);
		}
	}

}
