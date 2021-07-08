package year_2021.month_07.day_07;

import java.util.Scanner;

public class P74_BOJ_15651_N°ú_M_3 { 

	static int N, M;
	static StringBuilder stringBuilder;

	public static void main(String[] args) {
		initialize();
		createPermutationString(0, "");
		System.out.print(stringBuilder.toString());
	}

	private static void initialize() {
		readNAndM();
		stringBuilder = new StringBuilder();
	}
	private static void readNAndM() {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		M = scanner.nextInt();
		scanner.close();
	}

	private static void createPermutationString(int depth, String parentNodes) {
		if (depth == M) {
			stringBuilder.append(parentNodes);
			stringBuilder.append("\n");
			return;
		}
		for(int i=1; i<=N; i++) createPermutationString(depth+1, parentNodes+i+" ");
	}
	
}
