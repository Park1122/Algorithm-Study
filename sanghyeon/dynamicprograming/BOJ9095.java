package year_2021.month_08.day_11;

import java.io.*;
import java.util.*;

public class BOJ9095 {

	private static Map<Integer, Integer> Memory = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numberOfTestcase = Integer.parseInt(br.readLine());
		
		for(int i=0; i<numberOfTestcase; i++) {
			System.out.println(getNumberOfCase(Integer.parseInt(br.readLine())));
		}
	}

	public static int getNumberOfCase(int target) {
		if(target<0) return 0;
		else if(target==0) return 1;
		else if(Memory.containsKey(target)) return Memory.get(target);
		else return getNumberOfCase(target-1) + getNumberOfCase(target-2) + getNumberOfCase(target-3);
	}
}
