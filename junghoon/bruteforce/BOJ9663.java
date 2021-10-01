package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9663 {
	
	

	/*
	 * 9월 마지막주차          제일 신기함
	 * 
	 * 문제: N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.
	 *  - N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.
	 * 
	 * 입력: 첫째 줄에 N이 주어진다. (1 ≤ N < 15)
	 * 
	 * 출력: 첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.
	 * 
	 * 풀이: Backtracking
	 * */
	
	public static int[] arr;
	public static int N; //체스판 크기
	public static int count = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		nQueen(0);
		System.out.println(count);
	}
	
	
	//재귀 호출 부분 -> 1차원 배열로 생각해서 품, index = 열, 원소 = 행
	public static void nQueen(int depth) {
		//첫줄부터 확인하는데 행을 다 채우면 1증가하고 리턴
		if(depth == N) {
			count++;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			arr[depth] = i;
			//possibility에서 해당 열의 i 번째에 놓을 수 있는지 검사
			if(Possibility(depth)) {
				nQueen(depth + 1);
			}
		}
	}
	
	//놓이는 취이가 다른 퀸이랑 곂치는지 확인
	public static boolean Possibility(int collapse) {
		for(int i = 0; i < collapse; i++) {
			if(arr[collapse] == arr[i]) {
				return false;
			}
			//대각선에 있을 때 ==> 계산 법: 열의 차와 행의 차가 같으면 대각선이란다 @@@@@@ 이건 뭔말인지 모르겟음
			else if(Math.abs(collapse - i) == Math.abs(arr[collapse] - arr[i])){
				return false;
			}
		}
		return true;
	}

}
