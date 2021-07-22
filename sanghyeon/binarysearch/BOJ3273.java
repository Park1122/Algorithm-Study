package year_2021.month_07.day_20;

import java.util.Arrays; 
import java.util.Scanner;

public class BOJ3273 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[] numbers = new int[scanner.nextInt()];
		for(int i=0; i<numbers.length; i++) numbers[i] = scanner.nextInt();
		Arrays.parallelSort(numbers);
		int sumTarget = scanner.nextInt();
		scanner.close();

		int count = 0;
		for(int i=0; i<numbers.length; i++) {
			int findNumber = sumTarget - numbers[i];
			int low = 0, high = numbers.length-1, mid = 0;
			while(low<=high) {
				mid = (low+high)/2;
				if(numbers[mid]==findNumber) { count++; break; }
				else if(numbers[mid] < findNumber) { low = mid+1; }
				else if(numbers[mid] > findNumber) { high = mid-1; }
			}
		}
		System.out.println(count/2);
	}

}
