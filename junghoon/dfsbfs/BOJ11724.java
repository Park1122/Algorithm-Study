package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11724 {
	/* 연결 요소의 개수
	 * 
	 * 문제: 방향 없는 그래프가 주어졌을 때, 연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.
	 * 
	 * 입력: 첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다. 
	 *      (1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2) 둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다. 
	 *      (1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.
	 * 
	 * 출력: 첫째 줄에 연결 요소의 개수를 출력한다.
	 * 
	 * 풀이: 장점 1에 방문해서 연결된 정점을 찾아낸다. (인접리스트) -> 그리고 그 중 하나의 정점에 방문 -> 그래도 아니면 다음 정점에 방문 -> 모두 방문해서 그룹이 끝난 것을 암
	 *     
	 * */

	static ArrayList<Integer>[] adjList;
	static boolean visited[];
	
	static void dfs(int v){
        if(visited[v]) {
            return;
        }
        visited[v]=true;
        for(int i : adjList[v]){
            if(!visited[i]) {
                dfs(i);
            }
        }
    }

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[n+1];
		visited = new boolean[n+1];
		
		int vertex1,vertex2,answer = 0;
		
		for(int i = 1; i < n+1; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			vertex1 = Integer.parseInt(st.nextToken());
			vertex2 = Integer.parseInt(st.nextToken());
			adjList[vertex1].add(vertex2);
			adjList[vertex2].add(vertex1);
		}
		
		for(int i = 1; i < n+1; i++) {
			if(!visited[i]) {
				dfs(i);
				answer++;
			}
		}
		System.out.println(answer);
	}
	

}
