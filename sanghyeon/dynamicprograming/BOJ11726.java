package year_2021.month_08.day_12;

import java.io.*;
import java.util.*;

public class BOJ11726 {

	// 문제에서 %10007나누라는 곳이 어딘지 애매하네
	
	private static Map<Integer, Integer> memory = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		System.out.println(getCase(n));
	}
	
	private static int getCase(int n) {
		if(n<0) return 0;
		else if(n==0) return 1;
		else if(memory.containsKey(n)) return memory.get(n);
		else {
			memory.put(n, (getCase(n-1) + getCase(n-2))%10007);
			return memory.get(n);
		}
	}
}
