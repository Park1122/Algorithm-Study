package dfsbfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ2667 {
	/* 단지번호붙이기
	 * 
	 * 문제: <그림 1>과 같이 정사각형 모양의 지도가 있다. 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다. 
	 *      철수는 이 지도를 가지고 연결된 집의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다. 
	 *      여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다. 
	 *      대각선상에 집이 있는 경우는 연결된 것이 아니다. 
	 *      <그림 2>는 <그림 1>을 단지별로 번호를 붙인 것이다. 
	 *      지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.
	 * 
	 * 입력: 첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고, 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.
	 * 
	 * 출력: 첫 번째 줄에는 총 단지수를 출력하시오. 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.
	 * 
	 * 풀이: 숫자붙어있어서, String형으로 받아서 charAt해서 떼어야함, 배열에서 1이 발견되면 1증가, 그리고 arrayList에 넣어줌 그 크기가 단지의 개수가 됨
	 *     
	 * */
	
	static int N;
	static int[][] Map;
	static int count;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static ArrayList<Integer> result;
	static boolean[][] check;
	
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		N = sc.nextInt();
		Map = new int[N][N];
		check = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			String input = sc.next();
			for(int j = 0; j < N; j++) {
				Map[i][j] = input.charAt(j)-'0';
			}
		}
		count = 0;
		result = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(Map[i][j]==1 && !check[i][j]) {
					count = 1;
					Search(i, j);
					result.add(count);
				}
			}
		}
		Collections.sort(result);
		System.out.println(result.size());
		for(int c : result) System.out.println(c);
	}
	
	public static int Search(int x, int y) {
		check[x][y] = true;
		for(int i =0; i < 4; i++) {
			int nx = x +dr[i];
			int ny = y +dc[i];
			
			if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
				if(Map[nx][ny] ==1 && !check[nx][ny]) {
					Search(nx, ny);
					count++;
				}
			}
		}
		return count;
	}

}