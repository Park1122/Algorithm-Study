package year_2021.month_07.day_16;

import java.util.Scanner;

public class BOJ9663 {

	static int N;
	static int[] QueenPlacedX;
	static int placedQueenCount, possibleCaseCount;
	
	public static void main(String[] args) {
		initialize();
		getAnswer(0);
		System.out.println(possibleCaseCount);
	}

	private static void initialize() {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		scanner.close();
		QueenPlacedX = new int[N];
		placedQueenCount = possibleCaseCount = 0;
	}

	private static void getAnswer(int y) {
		if(placedQueenCount == N) { possibleCaseCount++; return; }
			
		for(int x=0; x<N; x++) {
			if(isQueenPlaceable(x, y)) {
				QueenPlacedX[y] = x;
				placedQueenCount++;
				getAnswer(y+1);
				QueenPlacedX[y] = 0;
				placedQueenCount--;
			}
		}
	}

	private static boolean isQueenPlaceable(int targetX, int targetY) {
		for(int y=0; y<placedQueenCount; y++) {
			boolean canKillHorizontally = QueenPlacedX[y]==targetX;
			boolean canKillDiagonally = Math.abs(QueenPlacedX[y]-targetX)==Math.abs(y-targetY);
			if(canKillHorizontally || canKillDiagonally) return false;
		}
		return true;
	}
}
