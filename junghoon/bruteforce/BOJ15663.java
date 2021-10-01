package bruteforce;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class BOJ15663 {

	

	/*
	 * 9월 마지막주차                                                           @@@@@@@@@@이해 못함
	 * 
	 * 문제: N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
	 *  - N개의 자연수 중에서 M개를 고른 수열
	 * 
	 * 입력: 첫째 줄에 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
	 *      둘째 줄에 N개의 수가 주어진다. 입력으로 주어지는 수는 10,000보다 작거나 같은 자연수이다.
	 * 
	 * 출력: 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
	 *      수열은 사전 순으로 증가하는 순서로 출력해야 한다.
	 * 
	 * 풀이: permutation활용해서 수열을 구하는 문제 (permutation: 순열 약간 바이너리 같은데  1에서 2로 2에서 3으로 점점 퍼저감 hierarchy같음)
	 * 
	 * 궁금: 근데 왜 첫번재는 무시가 되는거지 한줄에 하나씩 조건에 만족하는 수열을 출력하는건데 ?
	 * */
	
	static int N, M;
	static int[] nums, perm;
	static boolean[] visit;
	static LinkedHashSet<String> ans;
	//Treeset이 아닌 LinkedHashset사용하는 이유
	//둘 다 정렬이 가능한 Set이라는 점은 동일!
	//LinkedHashSet은 입력순으로 정렬
	//TreeSet은 생성시 인자로 Comparator를 넘겨주지 않는다면 기본적으로 오름차순 정렬
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		M = scanner.nextInt();
		
		nums = new int[N];
		perm = new int[M];
		visit = new boolean[N];
		ans = new LinkedHashSet<>();
		
		 for (int i = 0; i < N; i++)
	            nums[i] = scanner.nextInt();

	        Arrays.sort(nums);
	        permutation(0);
	        ans.forEach(System.out::println);
	}
	
	 static void permutation(int cnt) {
	        if (cnt == M) {
	            StringBuilder sb = new StringBuilder();
	            for (int p : perm)
	                sb.append(p).append(' ');
	            ans.add(sb.toString());
	            return;
	        }

	        for (int i = 0; i < N; i++) {
	            if (visit[i])
	                continue;
	            visit[i] = true;
	            perm[cnt] = nums[i];
	            permutation(cnt + 1);
	            visit[i] = false;
	        }
	    }

	
	
}
