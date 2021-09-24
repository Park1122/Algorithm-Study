package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15651 {
	
	/*
	 * 
	 * 문제: 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
	 *  - 1부터 N까지 자연수 중에서 M개를 고른 수열
	 *  - 같은 수를 여러 번 골라도 된다.
	 * 
	 * 입력: 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 7)
	 * 
	 * 출력: 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
	 *      수열은 사전 순으로 증가하는 순서로 출력해야 한다.
	 * 
	 * */
	
	//그렇다면 조건은 3개임 1~N까지 수 조합, M개 선택함 == depth, 중복가능
	// 여기선 scanner랑 print 쓰면 시간 오바됨
	
	public static int[] arr; //기존과쌤쌤하게 만듬
	public static int N, M; //이것도 똑같이
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()); //스트링 토큰화하기위해서
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[M];
		dfs(0);
		System.out.println(sb);
	}
	
	public static void dfs(int depth) {
		if(depth == M) { //깊이를 최대로 드갓으면 자식없으니 return
			for(int i = 0; i < M; i++) {
				sb.append(arr[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			arr[depth] = i;
			dfs(depth + 1); //중복가능, 그리고 1씩 늘려가면서 재귀호출
		}
	}
	

}
