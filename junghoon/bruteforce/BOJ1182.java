package bruteforce;

import java.util.Scanner;

public class BOJ1182 {
	
	
	/*                                                                !!!@@@@ 해결 및 이해 못한 문제 CHECK CHECK
	 * 9월 마지막주차
	 * 
	 * 문제: N개의 정수로 이루어진 수열이 있을 때, 크기가 양수인 부분수열 중에서 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오.
	 * 
	 * 입력: 첫째 줄에 정수의 개수를 나타내는 N과 정수 S가 주어진다. (1 ≤ N ≤ 20, |S| ≤ 1,000,000) 
	 *      둘째 줄에 N개의 정수가 빈 칸을 사이에 두고 주어진다. 주어지는 정수의 절댓값은 100,000을 넘지 않는다.
	 * 
	 * 출력: 첫째 줄에 합이 S가 되는 부분수열의 개수를 출력한다.
	 * 
	 * 풀이: 투포인트 알고리즘과 DFS 사용해야함
	 * */
	
	//개인 문제: 일단 문제가 무슨말인 지 모르겠음, 입력하는데 입력 방식이 대체 뭔지를 모르겠음 --> 수학공부해야함
	
	static int N, S, count = 0;
	static int[] arr;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		N = scanner.nextInt();
		S = scanner.nextInt();
		arr = new int[N]; //여기까지는 3주차에 사용한 기존 방법이랑 완전 동일
		
		for(int i = 0; i < N; i++) { //왜돌려야함?
			arr[i] = scanner.nextInt();
		}
		
		dfs(0, 0);
		
		if(S == 0) { //count 합이 0일때 공집합이 포함된다고 빼줘야한다고하는데 이부분만 이해감
			count--;
			System.out.println(count);
		} else {
			System.out.println(count);
		}
	}
	
	static void dfs(int value, int c) {
		if(value == N) { //dfs로 돌다가 value 마지막에 오면 원하는 값인지 확인하는 것
			if(c == S) {
				count++;
			}
			return;
		}
		//부분수열로 원소를 선택하거나 안하는거 라는데 여기 !@!@!@!@ CHECK CHECK
		dfs(value+1, c+arr[value]); // 선택
		dfs(value+1, c); // 선택안함
	}
	
	

}
