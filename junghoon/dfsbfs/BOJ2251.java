package dfsbfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2251 {
	/* 물통
	 * 
	 * 문제: 각각 부피가 A, B, C(1≤A, B, C≤200) 리터인 세 개의 물통이 있다. 
	 *      처음에는 앞의 두 물통은 비어 있고, 세 번째 물통은 가득(C 리터) 차 있다. 
	 *      이제 어떤 물통에 들어있는 물을 다른 물통으로 쏟아 부을 수 있는데, 이때에는 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 물을 부을 수 있다. 
	 *      이 과정에서 손실되는 물은 없다고 가정한다.
	 *      이와 같은 과정을 거치다보면 세 번째 물통(용량이 C인)에 담겨있는 물의 양이 변할 수도 있다. 
	 *      첫 번째 물통(용량이 A인)이 비어 있을 때, 세 번째 물통(용량이 C인)에 담겨있을 수 있는 물의 양을 모두 구해내는 프로그램을 작성하시오.
	 * 
	 * 입력: 첫째 줄에 세 정수 A, B, C가 주어진다.
	 * 
	 * 출력: 첫째 줄에 공백으로 구분하여 답을 출력한다. 각 용량은 오름차순으로 정렬한다.
	 * 
	 * 풀이: 물통이 3개 -> 물이 이동 가능한 경우는 6가지
	 *     
	 * */
	
	static int a, b, c;
	static boolean[][][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();
		visited = new boolean[a + 1][b + 1][c + 1];

		ArrayList<int[]> list = new ArrayList<>();
		ArrayList<Integer> ans = new ArrayList<>();
		Queue<int[]> q = new LinkedList<>();

		q.add(new int[] { 0, 0, c });

		while (!q.isEmpty()) {
			int[] temp = q.poll();

			if (visited[temp[0]][temp[1]][temp[2]])
				continue;
			visited[temp[0]][temp[1]][temp[2]] = true;

			if (temp[0] == 0)
				ans.add(temp[2]);

			if (temp[0] + temp[1] > a) { 
				q.add(new int[] { a, temp[0] + temp[1] - a, temp[2] });
			} else { 
				q.add(new int[] { temp[0] + temp[1], 0, temp[2] });
			}

			if (temp[0] + temp[1] > b) { 
				q.add(new int[] { temp[0] + temp[1] - b, b, temp[2] });
			} else { 
				q.add(new int[] { 0, temp[0] + temp[1], temp[2] });
			}

			if (temp[0] + temp[2] > a) {
				q.add(new int[] { a, temp[1], temp[0] + temp[2] - a });
			} else { 
				q.add(new int[] { temp[0] + temp[2], temp[1], 0 });
			}

			if (temp[0] + temp[2] > c) {
				q.add(new int[] { temp[0] + temp[2] - c, temp[1], c });
			} else { 
				q.add(new int[] { 0, temp[1], temp[0] + temp[2] });
			}

			if (temp[1] + temp[2] > b) {
				q.add(new int[] { temp[0], b, temp[1] + temp[2] - b });
			} else { 
				q.add(new int[] { temp[0], temp[1] + temp[2], 0 });
			}

			if (temp[1] + temp[2] > c) { 
				q.add(new int[] { temp[0], temp[1] + temp[2] - c, c });
			} else {
				q.add(new int[] { temp[0], 0, temp[1] + temp[2] });
			}
		}

		Collections.sort(ans);
		for (int i = 0; i < ans.size(); i++)
			System.out.print(ans.get(i) + " ");
	}
	

}
