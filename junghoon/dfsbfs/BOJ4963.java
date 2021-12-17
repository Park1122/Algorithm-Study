package dfsbfs;

import java.util.Scanner;

public class BOJ4963 {
	/* 섬의 개수
	 * 
	 * 문제: 정사각형으로 이루어져 있는 섬과 바다 지도가 주어진다. 섬의 개수를 세는 프로그램을 작성하시오.
	 *      한 정사각형과 가로, 세로 또는 대각선으로 연결되어 있는 사각형은 걸어갈 수 있는 사각형이다. 
	 *      두 정사각형이 같은 섬에 있으려면, 한 정사각형에서 다른 정사각형으로 걸어서 갈 수 있는 경로가 있어야 한다. 
	 *      지도는 바다로 둘러싸여 있으며, 지도 밖으로 나갈 수 없다.
	 * 
	 * 입력: 입력은 여러 개의 테스트 케이스로 이루어져 있다. 
	 *      각 테스트 케이스의 첫째 줄에는 지도의 너비 w와 높이 h가 주어진다. w와 h는 50보다 작거나 같은 양의 정수이다.
	 *      둘째 줄부터 h개 줄에는 지도가 주어진다. 1은 땅, 0은 바다이다.
	 *      입력의 마지막 줄에는 0이 두 개 주어진다.
	 * 
	 * 출력: 각 테스트 케이스에 대해서, 섬의 개수를 출력한다.
	 * 
	 * 풀이: 입력 마지막 줄에는 0이 두 개 주어지므로 무한 루프문에서 두 입력값이 모두 0이면 프로그램을 종료한다.
	 *      섬들의 연결이 끊기면 하나의 거대한 섬이 되고, 이러한 섬들의 개수를 세는 것이므로 깊이 탐색 dfs를 사용한다.
	 *      동서남북, 그리고 네가지의 대각선 방향으로 이동이 가능하므로 dx와 dy배열에 이동 경로를 저장한다. 
	 *      for문을 돌려 하나씩 이동해보며, 맵의 범위를 벗어나지 않게 하기 위해 if문으로 이동할 지점이 주어진 사각형의 범위를 벗어나는 지 확인한다.
	 *      방문한 곳은 다시 방문하지 않도록 visit 배열로 처리한다.
	 * */
	
	static int[][] arr;
	static boolean[][] visit;
	static int w, h;
	
	private static int dfs(int x, int y) {
		int[] dx = {0, 0, 1, -1, -1, 1, -1, 1}, dy = {1, -1, 0, 0, -1, 1, 1, -1};
		
		visit[x][y] = true;
		for(int i = 0; i < 8; i++) {
			int xx = dx[i] + x, yy = dy[i] + y;
			if(xx > 0 && yy > 0 && xx <= h && yy <= w) {
				if(arr[xx][yy] == 1 && !visit[xx][yy]) dfs(xx, yy);
			}
		}		
		return 1;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			int cnt = 0;
			w = sc.nextInt(); h = sc.nextInt();
			if(w == 0 && h == 0) break;
			
			arr = new int[h+1][w+1]; visit = new boolean[h+1][w+1];
			for(int i = 1; i <= h; i++) {
				for(int j = 1; j <= w; j++) arr[i][j] = sc.nextInt(); 
			}
			
			for(int i = 1; i <= h; i++) {
				for(int j = 1; j <= w; j++) {
					if(arr[i][j] == 1 && !visit[i][j]) cnt += dfs(i, j);
				}
			}
			System.out.println(cnt);
		}
	}
}
