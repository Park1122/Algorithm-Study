package dfsbfs;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1260 {
	/* DFS와 BFS
	 * 
	 * 문제: 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 
	 *      단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 
	 *      정점 번호는 1번부터 N번까지이다.
	 * 
	 * 입력: 첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다. 
	 *     다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 
	 *     입력으로 주어지는 간선은 양방향이다.
	 * 
	 * 출력: 첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.
	 * 
	 * 풀이: 그래프를 dfs, bfs 이용하여 탐색, dfs bfs수행결과가 따로있네? 깊이랑 너비를  dfs를 하면서 bfs를 하나?
	 *     
	 * */
	
	 static int[][] check;
	  static boolean[] checked; 
	  static int n; 
	  static int m; 
	  static int start; 	 
	  
	  public static void dfs(int i) {
	    checked[i] = true;
	    System.out.print(i + " ");
	    
	    for(int j = 1; j <= n; j++) {
	      if(check[i][j] == 1 && checked[j] == false) {
	        dfs(j);
	      }
	    }
	  }
	  
	  public static void bfs() {
	    Queue<Integer> queue = new LinkedList<Integer>();
	    queue.offer(start); 
	    checked[start] = true;
	    System.out.print(start + " ");
	    
	    while(!queue.isEmpty()) {
	      int temp = queue.poll();
	      
	      for(int j = 1; j <= n; j++) {
	        if(check[temp][j] == 1 && checked[j] == false) {
	          queue.offer(j);
	          checked[j] = true;
	          System.out.print(j + " ");
	        }
	      }
	    }
	  }
	  
	  public static void main(String[] args) throws IOException {
		  Scanner sc = new Scanner(System.in);
		  n = sc.nextInt();
		  m = sc.nextInt();
		  start = sc.nextInt();
		  
		  check = new int[1001][1001];
		  checked = new boolean[1001];
		  
		  for(int i = 0; i < m; i++) {
		    int x = sc.nextInt();
		    int y = sc.nextInt();
		    
		    check[x][y] = check[y][x] = 1;
		  }
		  
		  dfs(start);
		  
		  checked = new boolean[1001];
		  System.out.println();
		  
		  bfs(); 
		  }

}
