package year_2021.month_07.day_16;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ1759 {

	static int L, C;
	static String stack;
	static String[] Chars;

	public static void main(String[] args) {
		initialize();
		printPermutation(0);
	}

	private static void initialize() {
		Scanner scanner = new Scanner(System.in);
		L = scanner.nextInt();
		C = scanner.nextInt();
		Chars = new String[C];
		for(int i=0; i<C; i++) Chars[i] = scanner.next();
		scanner.close();
		Arrays.sort(Chars);
		stack = "";
	}

	private static void printPermutation(int start) {
		if (stack.length() == L) {
			int aeiouCount = 0;
			for(int i=0; i<L; i++) {
				if("aeiou".contains(stack.charAt(i)+"")) aeiouCount++;
			}
			if(aeiouCount>0 && L-aeiouCount>=2) System.out.println(stack);
			return;
		}
		for(int i=start; i<C; i++) {
			stack += Chars[i];
			printPermutation(i+1);
			stack = stack.substring(0, stack.length()-1);
		}
	}
}
