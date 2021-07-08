package year_2021.month_07.day_06;

import java.util.Scanner;

public class P73_BOJ_15650_N°ú_M_2 {

	static int N, M;
	static String stack;

	public static void main(String[] args) {
		initialize();
		printPermutation(1);
	}

	private static void initialize() {
		readNAndM();
		stack = "";
	}
	private static void readNAndM() {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		M = scanner.nextInt();
		scanner.close();
	}

	private static void printPermutation(int start) {
		if(stack.length()==M*2) { System.out.println(stack); return; }
		for(int i=start; i<=N; i++) {
			stack += i+" ";
			printPermutation(i+1);
			stack = stack.substring(0, stack.length()-2);
		}
	}

}
