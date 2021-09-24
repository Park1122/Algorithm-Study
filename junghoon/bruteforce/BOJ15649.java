package bruteforce;

import java.util.Scanner;

public class BOJ15649 {

	
	/*
	 * 
	 * 문제: 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
	 *  - 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
	 * 
	 * 입력: 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
	 * 
	 * 출력: 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
	 *      수열은 사전 순으로 증가하는 순서로 출력해야 한다.
	 * 
	 * 풀이: Backtracking (bruteforce는 모든 경우의 수 찾는거임 --> 자바알고리즘 290쪽에 사용법 있음)
	 * */
	
	
	public static int[] arr; //돌면서 값 저장
	public static boolean[] visit; // N크기로 생성, 이유는 방문했으면 넘어가게하기위해
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		int N = scanner.nextInt(); // N
		int M = scanner.nextInt(); // M
		
		arr = new int[M];
		visit = new boolean[N];
		dfs(N, M, 0);
	}
	
	public static void dfs(int N, int M, int depth) { //dfs는 백트래킹 방법 중 하나임 바이너리트리에서 왓다리갓다리함
		if(depth == M) {
			for(int val: arr) { //?
				System.out.print(val + " ");
			}
			System.out.println();
			return;
		}
		for(int i = 0; i < N; i++) {
			if(!visit[i]) { //여기 돌았는지
				visit[i] = true;
				arr[depth] = i + 1; //근데 애초에 0
				dfs(N, M, depth + 1); //아 그래서 여기서 +1해줌 ++하면안됨 그러면 --도해줘야함
				visit[i] = false;
			}
		}
	}
}
