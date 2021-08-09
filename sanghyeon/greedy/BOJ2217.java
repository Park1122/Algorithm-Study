package year_2021.month_08.day_05;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2217 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numberOfLope = Integer.parseInt(br.readLine());
		int[] lopes = new int[numberOfLope];
		for(int i=0; i<numberOfLope; i++) lopes[i]=Integer.parseInt(br.readLine());
		Arrays.sort(lopes);
		
		int maxWeight = 0;
		for(int i=numberOfLope-1; i>=0; i--) {
			int torelantWeight = lopes[i]*(numberOfLope-i);
			if(maxWeight < torelantWeight) maxWeight = torelantWeight;
		}
		
		System.out.println(maxWeight);
	}
}
