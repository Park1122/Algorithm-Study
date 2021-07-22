package year_2021.month_07.day_20;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ1920 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int[] a = new int[scanner.nextInt()];
		for(int i=0; i<a.length; i++) a[i] = scanner.nextInt();
		Arrays.parallelSort(a);
		
		int testCaseCount = scanner.nextInt();
		for(int i=0; i<testCaseCount; i++) {
			int testCase = scanner.nextInt();
			int low = 0, high = a.length-1, mid = 0;
			boolean isContain = false;
			while(low<=high) {
				mid = (low+high)/2;
				if(a[mid]==testCase) { isContain = true; break; }
				else if(a[mid] < testCase) { low = mid+1; }
				else if(a[mid] > testCase) { high = mid-1; }
			}
			System.out.println(isContain? 1:0);
		}
		
		scanner.close();
	}

}
