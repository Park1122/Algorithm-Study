package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ7795 {
	
	/* 21년 10월 1주차 ( 02.10.21 )
	 * 
	 * 문제: 심해에는 두 종류의 생명체 A와 B가 존재한다. A는 B를 먹는다. A는 자기보다 크기가 작은 먹이만 먹을 수 있다. 
	 *     예를 들어, A의 크기가 {8, 1, 7, 3, 1}이고, B의 크기가 {3, 6, 1}인 경우에 A가 B를 먹을 수 있는 쌍의 개수는 7가지가 있다. 
	 *     8-3, 8-6, 8-1, 7-3, 7-6, 7-1, 3-1.
	 *     두 생명체 A와 B의 크기가 주어졌을 때, A의 크기가 B보다 큰 쌍이 몇 개나 있는지 구하는 프로그램을 작성하시오.
	 * 
	 * 입력: 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스의 첫째 줄에는 A의 수 N과 B의 수 M이 주어진다. 
	 *      둘째 줄에는 A의 크기가 모두 주어지며, 셋째 줄에는 B의 크기가 모두 주어진다. 크기는 양의 정수이다. (1 ≤ N, M ≤ 20,000) 
	 * 
	 * 출력: 각 테스트 케이스마다, A가 B보다 큰 쌍의 개수를 출력한다.
	 * 
	 * 풀이: binarysearch로 들어오는거 하나씩 확인한다음에 풀었음
	 *      문제가 이해안됬는데 그냥 작은애들 하나씩 먹는다는 거로 상현이한테 물어봐서 이해했음 (자세 설명 카톡 일자 확인)
	 * */
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T --> 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer:: parseInt).sorted().toArray();
			int[] b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
			
			int count = 0;
			for(int i : a) {
				for(int j : b) {
					if(i <= j) {
						break;
					} else {
						count++;
					}
					sb.append(count+"\n");
				}
				System.out.print(sb);
			}
		}
	}
	
}
