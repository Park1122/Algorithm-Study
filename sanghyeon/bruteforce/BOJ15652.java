package year_2021.month_07.day_08;

import java.util.Scanner;

public class P75_BOJ_15652_N°ú_M_4 { 

	static int N, M;
	static StringBuilder stringBuilder;

	public static void main(String[] args) {
		initialize();
		createPermutationString(1, 0, "");
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

	private static void createPermutationString(int start, int depth, String parentNodes) {
		if (depth == M) {
			stringBuilder.append(parentNodes);
			stringBuilder.append("\n");
			return;
		}
		for(int i=start; i<=N; i++) createPermutationString(i, depth+1, parentNodes+i+" ");
	}
	
}
