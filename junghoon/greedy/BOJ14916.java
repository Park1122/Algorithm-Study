package greedy;

import java.util.Scanner;

public class BOJ14916 {
	
	/* 21년 10월 3주차 ( 16.10.21 )
	 * 
	 * 문제: 춘향이는 편의점 카운터에서 일한다.
	 *      손님이 2원짜리와 5원짜리로만 거스름돈을 달라고 한다. 2원짜리 동전과 5원짜리 동전은 무한정 많이 가지고 있다. 
	 *      동전의 개수가 최소가 되도록 거슬러 주어야 한다. 
	 *      거스름돈이 n인 경우, 최소 동전의 개수가 몇 개인지 알려주는 프로그램을 작성하시오. 
	 *      예를 들어, 거스름돈이 15원이면 5원짜리 3개를, 거스름돈이 14원이면 5원짜리 2개와 2원짜리 2개로 총 4개를, 
	 *      거스름돈이 13원이면 5원짜리 1개와 2원짜리 4개로 총 5개를 주어야 동전의 개수가 최소가 된다.
	 * 
	 * 입력: 첫째 줄에 거스름돈 액수 n(1 ≤ n ≤ 100,000)이 주어진다.
	 * 
	 * 출력: 거스름돈 동전의 최소 개수를 출력한다. 만약 거슬러 줄 수 없으면 -1을 출력한다.
	 * 
	 * 풀이: 동전의 개수가 최소가 되어야함. 5원짜리가 최대한 많아야되서 금액이 5의 배수가 될때까지 2원을 빼주고, 5의 배수가 될 때 5원을 준다.
	 *     
	 * */
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int count = 0;
		while( n > 0) {
			if(n%5==0) {
				count = n/5 + count;
				break;
			}
			n -=2;
			count++;
		}
		if(n<0) {
			System.out.println(-1);
		} else {
			System.out.println(count);
		}
	}

}
