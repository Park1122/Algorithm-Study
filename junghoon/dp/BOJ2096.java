package DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ2096 {
	
	/* 내려가기
	 * 
	 * 문제: N줄에 0 이상 9 이하의 숫자가 세 개씩 적혀 있다. 내려가기 게임을 하고 있는데, 
	 *      이 게임은 첫 줄에서 시작해서 마지막 줄에서 끝나게 되는 놀이이다.
	 *      먼저 처음에 적혀 있는 세 개의 숫자 중에서 하나를 골라서 시작하게 된다. 
	 *      그리고 다음 줄로 내려가는데, 다음 줄로 내려갈 때에는 다음과 같은 제약 조건이 있다.
	 *      바로 아래의 수로 넘어가거나, 아니면 바로 아래의 수와 붙어 있는 수로만 이동할 수 있다는 것이다. 
	 *      이 제약 조건을 그림으로 나타내어 보면 다음과 같다.
	 *      별표는 현재 위치이고, 그 아랫 줄의 파란 동그라미는 원룡이가 다음 줄로 내려갈 수 있는 위치이며, 
	 *      빨간 가위표는 원룡이가 내려갈 수 없는 위치가 된다. 
	 *      숫자표가 주어져 있을 때, 얻을 수 있는 최대 점수, 최소 점수를 구하는 프로그램을 작성하시오. 점수는 원룡이가 위치한 곳의 수의 합이다.
	 * 
	 * 입력: 첫째 줄에 N(1 ≤ N ≤ 100,000)이 주어진다. 다음 N개의 줄에는 숫자가 세 개씩 주어진다. 숫자는 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 중의 하나가 된다.
	 * 
	 * 출력: 첫째 줄에 얻을 수 있는 최대 점수와 최소 점수를 띄어서 출력한다.
	 * 
	 * 풀이: 다이나믹 프로그래밍? 슬라이딩 윈도우 기법?
	 *     
	 * */
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		
		int[] maxDp = new int[3];
		int[] minDp = new int[3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int x3 = Integer.parseInt(st.nextToken());

			if (i == 0) {
				
				maxDp[0] = minDp[0] = x1;
				maxDp[1] = minDp[1] = x2;
				maxDp[2] = minDp[2] = x3;
				
			} else {
				
				int beforeMaxDp_0 = maxDp[0], beforeMaxDp_2 = maxDp[2];
				maxDp[0] = Math.max(maxDp[0], maxDp[1]) + x1;
				maxDp[2] = Math.max(maxDp[1], maxDp[2]) + x3;
				maxDp[1] = Math.max(Math.max(beforeMaxDp_0, maxDp[1]), beforeMaxDp_2) + x2;

				int beforeMinDp_0 = minDp[0], beforeMinDp_2 = minDp[2];
				minDp[0] = Math.min(minDp[0], minDp[1]) + x1;
				minDp[2] = Math.min(minDp[1], minDp[2]) + x3;
				minDp[1] = Math.min(Math.min(beforeMinDp_0, minDp[1]), beforeMinDp_2) + x2;
			}
		}

		bw.write(Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2])) + " ");
		bw.write(Math.min(minDp[0], Math.min(minDp[1], minDp[2])) + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

}
