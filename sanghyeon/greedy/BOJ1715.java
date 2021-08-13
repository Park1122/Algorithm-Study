package year_2021.month_08.day_10;

import java.io.*;
import java.util.*;

public class BOJ1715 {

	// PriorityQueue<Integer> ±≤¿Â«ÿ!
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numberOfPack = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> packs = new PriorityQueue<>();
		for(int i=0; i<numberOfPack; i++) packs.add(Integer.parseInt(br.readLine()));
		 
		int sum = 0;
		while(packs.size()!=1) {
			int minSum = packs.poll()+packs.poll();
			sum+= minSum;
			packs.add(minSum);
		}
		System.out.println(sum);
	}
}
