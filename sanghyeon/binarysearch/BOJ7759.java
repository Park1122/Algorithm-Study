package year_2021.month_07.day_20;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ7759 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int testCaseCount = scanner.nextInt();
		for(int i=0; i<testCaseCount; i++) {
			int[] aSet = new int[scanner.nextInt()];
			int[] bSet = new int[scanner.nextInt()];
			for(int j=0; j<aSet.length; j++) aSet[j] = scanner.nextInt();
			for(int j=0; j<bSet.length; j++) bSet[j] = scanner.nextInt();
			Arrays.sort(bSet);
			
			int count = 0;
			for(int a : aSet) {
				for(int b : bSet) {
					if(a>b) count++; else break;
				}
			}
			System.out.println(count);
		}
		scanner.close();
	}

}
