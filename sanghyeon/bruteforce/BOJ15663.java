package year_2021.month_07.day_15;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class BOJ15663 {

	static int N, M;
	static String stack;
	static int[] Numbers;
	static boolean[] NumberUsages;
	static HashSet<String> History;

	public static void main(String[] args) {
		initialize();
		printPermutation(0);
	}

	private static void initialize() {
		stack = "";
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		M = scanner.nextInt();
		Numbers = new int[N];
		for(int i=0; i<N; i++) Numbers[i] = scanner.nextInt();
		Arrays.sort(Numbers);
		scanner.close();
		NumberUsages = new boolean[N];
		Arrays.fill(NumberUsages, false);
		History = new HashSet<>();
	}

	private static void printPermutation(int depth) {
		if (depth == M) {
			if(!History.contains(stack)) {
				System.out.println(stack);
				History.add(stack);
			}
			return;
		}
		for(int i=0; i<N; i++) {
			if(!NumberUsages[i]) {
				stack += Numbers[i]+" ";
				NumberUsages[i] = true;
				printPermutation(depth+1);
				stack = stack.substring(0, stack.length()-2);
				NumberUsages[i] = false;
			}
		}
	}
}
