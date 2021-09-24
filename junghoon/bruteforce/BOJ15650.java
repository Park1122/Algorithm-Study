package bruteforce;

import java.util.Scanner;

public class BOJ15650 {
	
	/*
	 * 
	 * 문제: 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
	 *  - 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
	 *  - 고른 수열은 오름차순이어야 한다.
	 * 
	 * 입력: 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
	 * 
	 * 출력: 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
	 *      수열은 사전 순으로 증가하는 순서로 출력해야 한다.
	 * 
	 * */
	
	//15649랑 비슷한데 at변수를 추가해줫따고함
	//int at는 현재 위치를 뜻함 
	
	public static int[] arr;
	public static int N, M;
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		N = scanner.nextInt();
		M = scanner.nextInt();
		
		arr = new int[M];
		
		dfs(1, 0);
	}
	
	public static void dfs(int at, int depth) { // at가 현재 위치
		if(depth == M) { //이게 깊이가 M이랑 같을때만 출력한데
			for(int val : arr) {
				System.out.print(val + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = at; i <= N; i++) { // 그래서 현재 위치인 at부터 보게함
			arr[depth] = i;
			dfs(i + 1, depth + 1);//++쓰면 고정되서 안쓰는거임 --> 재귀호출때 값을 증가시켜 호출해줘야하기때문인데 여기서 +1 해서 증가된 상태로 호출되는데 ++하면 증가된 값이 그대로 남게됨
		}
	}

}
