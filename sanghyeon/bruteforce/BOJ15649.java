package year_2021.month_07.day_05;

import java.util.Scanner;

public class P72_BOJ_15649_N°ú_M {

	static int N, M;
	static boolean[] inStack;
	static String stack;

	public static void main(String[] args) {
		initialize();
		printPermutation();
	}

	private static void initialize() {
		readNAndM();
		inStack = new boolean[N+1]; // 0~N
		stack = "";
	}
	private static void readNAndM() {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		M = scanner.nextInt();
		scanner.close();
	}

	private static void printPermutation() {
		if(stack.length()==M*2) { System.out.println(stack); return; }
		for(int i=1; i<=N; i++) {
			if(!inStack[i]) {
				stack += i+" ";
				inStack[i] = true;
				printPermutation();
				inStack[i] = false;
				stack = stack.substring(0, stack.length()-2);
			}
		}
	}

}
