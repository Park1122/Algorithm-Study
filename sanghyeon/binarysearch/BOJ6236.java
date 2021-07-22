package year_2021.month_07.day_20;

import java.util.Scanner;

public class BOJ6236 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[] numbers = new int[scanner.nextInt()];
		int M = scanner.nextInt(), totalCost = 0, maxCost = 0;
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = scanner.nextInt();
			if(numbers[i] > maxCost) maxCost = numbers[i];
			totalCost+=numbers[i];
		}
		scanner.close();

		int low = maxCost, high=totalCost, answerCost = 0;
		while(low<=high) {
			answerCost = (low+high)/2;
			if(getMinM(numbers, answerCost) > M) { low = answerCost+1; }
			else { high = answerCost-1; }
		}
		System.out.println(answerCost);
	}

	private static int getMinM(int[] numbers, int answerCost) {
		int M = 0, cash = 0;
		for(int i=0; i<numbers.length; i++) {
			if(cash-numbers[i] < 0) {
				M++;
				cash=answerCost;
			}
			cash-=numbers[i];
		}
		return M;
	}
}
