package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1920 {
	
	/* 21년 10월 1주차 ( 02.10.21 )
	 * 
	 * 문제: N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때, 이 안에 X라는 정수가 존재하는지 알아내는 프로그램을 작성하시오.
	 * 
	 * 입력: 첫째 줄에 자연수 N(1 ≤ N ≤ 100,000)이 주어진다. 다음 줄에는 N개의 정수 A[1], A[2], …, A[N]이 주어진다. 다음 줄에는 M(1 ≤ M ≤ 100,000)이 주어진다. 
	 *      다음 줄에는 M개의 수들이 주어지는데, 이 수들이 A안에 존재하는지 알아내면 된다. 모든 정수의 범위는 -231 보다 크거나 같고 231보다 작다.
	 * 
	 * 출력: M개의 줄에 답을 출력한다. 존재하면 1을, 존재하지 않으면 0을 출력한다.
	 * 
	 * 풀이: binary search 에서 수 찾기 --> 중간에부터 시작해서 업 다운으로 나아가면서 찾아내가면됨 --> array.sort 무조건 필요 정렬이 되어있어야 중간부터 짤라서 찾기 가능
	 *     
	 * */
	
	public static int[] arr;
	
	static int binarySearch(int key) {
		int left = 0; // index[0]
		int right = arr.length -1; //index[마지막]
		
		while(left <= right) { //끝까지 다돔? 아님? 확인하는것
			
			int mid = (left + right) / 2;
			
			if(key < arr[mid]) {
				right = mid -1;
			} else if(key > arr[mid]) {
				left = mid +1;
			} else {
				return mid;
			}			
		}
		//값이 존재하지 않으면 음수를 반환해서 결과 확인
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int m = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < m; i++) {
			if(binarySearch(Integer.parseInt(st.nextToken())) >= 0) {
				sb.append(1).append("\n");
			} else {
				sb.append(0).append("\n");
			}
		}
		System.out.println(sb);
	}

}
