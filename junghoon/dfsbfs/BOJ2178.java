package dfsbfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178 {

	/* 미로 탐색
	 * 
	 * 문제: N×M크기의 배열로 표현되는 미로가 있다.
	 *      1	0	1	1	1	1
	 *      1	0	1	0	1	0
	 *      1	0	1	0	1	1
	 *      1	1	1	0	1	1
	 *      미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 
	 *      이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오. 
	 *      한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.
	 *      위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 
	 *      칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.
	 * 
	 * 입력: 첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.
	 * 
	 * 출력: 첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.
	 * 
	 * 풀이: 최단 거리를 구하는 문제는 BFS 필요함 visited값이 0인경우 방문안한 것
	 *     
	 * */

	static int[][] map;
	static int[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int n,m;
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new int[n][m];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for(int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		bfs(0,0);
		System.out.println(visited[n-1][m-1]);
	}

	static void bfs(int x, int y) {
		Queue<Node> queue = new LinkedList<>();
		visited[x][y] = 1;
		queue.add(new Node(x, y));
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
					if (visited[nx][ny] == 0 && map[nx][ny] == 1) {
						queue.add(new Node(nx, ny));
						visited[nx][ny] = visited[node.x][node.y] + 1;
					}
				}
			}
		}
	}
}

class Node {
	int x;
	int y;
	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}	

}
