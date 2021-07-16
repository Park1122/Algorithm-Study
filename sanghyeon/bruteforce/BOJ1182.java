package year_2021.month_07.day_14;

import java.util.Scanner;

public class BOJ1182 {

	static int N, S, Count, Temp;
	static int[] Numbers;

	public static void main(String[] args) {
		initialize();
		for(int i=1; i<=N; i++) check(0, 0, i);
		System.out.println(Count);
	}
	
	private static void initialize() {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		S = scanner.nextInt();
		Numbers = new int[N];
		for(int i=0; i<N; i++) Numbers[i] = scanner.nextInt();
		scanner.close();
	}

	private static void check(int index, int depth, int depthLimit) {
		if(depth==depthLimit) {
			if(Temp==S) Count++;
			return;
		} else {
			for(int i=index; i<N; i++) {
				Temp+=Numbers[i];
				check(i+1, depth+1, depthLimit);
				Temp-=Numbers[i];
			}
		}
	}
}
