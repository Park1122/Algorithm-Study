package DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15681 {
	
	/* 트리와 쿼리
	 * 
	 * 문제: 간선에 가중치와 방향성이 없는 임의의 루트 있는 트리가 주어졌을 때, 아래의 쿼리에 답해보도록 하자.
	 *      정점 U를 루트로 하는 서브트리에 속한 정점의 수를 출력한다.
	 *      만약 이 문제를 해결하는 데에 어려움이 있다면, 하단의 힌트에 첨부한 문서를 참고하자.
	 * 
	 * 입력: 트리의 정점의 수 N과 루트의 번호 R, 쿼리의 수 Q가 주어진다. (2 ≤ N ≤ 105, 1 ≤ R ≤ N, 1 ≤ Q ≤ 105)
	 *      이어 N-1줄에 걸쳐, U V의 형태로 트리에 속한 간선의 정보가 주어진다. (1 ≤ U, V ≤ N, U ≠ V)
	 *      이는 U와 V를 양 끝점으로 하는 간선이 트리에 속함을 의미한다.
	 *      이어 Q줄에 걸쳐, 문제에 설명한 U가 하나씩 주어진다. (1 ≤ U ≤ N)
	 *      입력으로 주어지는 트리는 항상 올바른 트리임이 보장된다.
	 * 
	 * 출력: Q줄에 걸쳐 각 쿼리의 답을 정수 하나로 출력한다.
	 * 
	 * 풀이: 트리에서 DP 문제, 트리를 만들 때 루트를 지정하고 시작하는 것이 좋음. 트리를 만들면서 배열에 자신의 서브노드 개수를 기록하면 된다.
	 *      함수를 재귀적으로 호출하면 terminal node부터 함수가 종료되기 때문에 쉽게 구현할 수 있다.
	 *     
	 * */
	
	static int[] dp;
	static ArrayList<ArrayList<Integer>> graph;
	
	static int buildTree(int cur, int parent) {
		if(dp[cur] != 0) {
			return dp[cur];
		}
		dp[cur] = 1;
		
		for(int child : graph.get(cur)) {
			if(parent != child) {
				dp[cur] += buildTree(child, cur);
			}
		}
		return dp[cur];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		graph = new ArrayList<>();
		
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		dp = new int[n+1];
		
		for(int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		buildTree(r, -1);
		
		StringBuilder sb=  new StringBuilder();
		for(int i = 0; i < q; i++) {
			int u = Integer.parseInt(br.readLine());
			sb.append(dp[u]).append("\n");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
