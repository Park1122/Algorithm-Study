package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15652 {
	
	/*
	 * 
	 * 문제: 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
	 *  - 1부터 N까지 자연수 중에서 M개를 고른 수열
	 *  - 같은 수를 여러 번 골라도 된다.
	 *  - 고른 수열은 비내림차순이어야 한다.
	 *     -> 길이가 K인 수열 A가 A1 ≤ A2 ≤ ... ≤ AK-1 ≤ AK를 만족하면, 비내림차순이라고 한다. ---> 일단 이게 뭔말인지 모르겟음
	 *    !!!@@!@!@!@!@ 일단 비 내림차순이라는게 그냥 중복을 허용한다고 이해하면된다고함 즉 15650이랑 똑같은데 그냥 재귀할때 i를 넘겨주면 됨
	 * 
	 * 입력: 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
	 * 
	 * 출력: 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
	 *      수열은 사전 순으로 증가하는 순서로 출력해야 한다.
	 * 
	 * */

	//이제부턴 scanner최대한 안쓰도록,, 성능에 좋진 않음 --> 다른 방법으로 푸는 것도 이해하고 넘어가야함
	
	public static int[] arr;
	public static int N, M;
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		
		dfs(1, 0);
		System.out.println(sb);
	}
	
	public static void dfs(int at, int depth) {
		if(depth == M) {
			for(int val: arr) {
				sb.append(val).append(' ' );
			}
			sb.append('\n');
			return;
		}
		
		for(int i = at; i <= N; i++) {
			arr[depth] = i;
			dfs(i, depth +1); // 그냥 기존거에 i만 넣어주면 됬음
		}
	}
	
}
