package year_2021.month_07.day_29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11047 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numberOfCoinType = Integer.parseInt(st.nextToken());
		int wantSum = Integer.parseInt(st.nextToken());
		int[] coinTypes = new int[numberOfCoinType];
		for(int i=0; i<numberOfCoinType; i++) coinTypes[i] = Integer.parseInt(br.readLine());
		
		int needCoinCount = 0;
		for(int i=numberOfCoinType-1; i>=0; i--) {
			if(wantSum >= coinTypes[i]) {
				needCoinCount += wantSum/coinTypes[i];
				wantSum = wantSum%coinTypes[i];
			}
		}
		System.out.println(needCoinCount);
	}
}
