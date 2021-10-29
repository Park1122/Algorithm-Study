package greedy;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ1715 {
	
	/* 21년 10월 4주차 ( 23.10.21 )
	 * 
	 * 문제: 정렬된 두 묶음의 숫자 카드가 있다고 하자. 
	 *      각 묶음의 카드의 수를 A, B라 하면 보통 두 묶음을 합쳐서 하나로 만드는 데에는 A+B 번의 비교를 해야 한다. 
	 *      이를테면, 20장의 숫자 카드 묶음과 30장의 숫자 카드 묶음을 합치려면 50번의 비교가 필요하다.
	 *      매우 많은 숫자 카드 묶음이 책상 위에 놓여 있다. 
	 *      이들을 두 묶음씩 골라 서로 합쳐나간다면, 고르는 순서에 따라서 비교 횟수가 매우 달라진다. 
	 *      예를 들어 10장, 20장, 40장의 묶음이 있다면 10장과 20장을 합친 뒤, 합친 30장 묶음과 40장을 합친다면 (10 + 20) + (30 + 40) = 100번의 비교가 필요하다. 
	 *      그러나 10장과 40장을 합친 뒤, 합친 50장 묶음과 20장을 합친다면 (10 + 40) + (50 + 20) = 120 번의 비교가 필요하므로 덜 효율적인 방법이다.
	 *      N개의 숫자 카드 묶음의 각각의 크기가 주어질 때, 최소한 몇 번의 비교가 필요한지를 구하는 프로그램을 작성하시오.
	 * 
	 * 입력: 첫째 줄에 식이 주어진다. 식은 ‘0’~‘9’, ‘+’, 그리고 ‘-’만으로 이루어져 있고, 가장 처음과 마지막 문자는 숫자이다. 
	 *      그리고 연속해서 두 개 이상의 연산자가 나타나지 않고, 5자리보다 많이 연속되는 숫자는 없다. 
	 *      수는 0으로 시작할 수 있다. 입력으로 주어지는 식의 길이는 50보다 작거나 같다.
	 * 
	 * 출력: 첫째 줄에 정답을 출력한다.
	 * 
	 * 풀이: 큐를 사용해서 작은 것들부터 빼주라, n 이 1이면 비교를 하지 않기에 0 이 나와야한다.
	 *     
	 * */
	
	static int solve(PriorityQueue<Integer> pq, int n) {
		if(n == 1) {
			return 0;
		}
		
		int result = 0;
		
		while(true) {
			int sum = 0;
			sum+=pq.poll();
			sum+=pq.poll();
			result+=sum;
			if(pq.isEmpty()) break;
			pq.add(sum);
		}
		return result;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int result = 0;
		Scanner sc = new Scanner(System.in);
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		int N = sc.nextInt();
		
		for(int i = 0; i < N; i++) {
			pq.add(sc.nextInt());
		}
		
		result = solve(pq,N);
		System.out.println(result);
	}

}
