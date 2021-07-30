package year_2021.month_07.day_29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11399 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int waitingCount = Integer.parseInt(br.readLine());
		int[] waitingTimes = new int[waitingCount];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<waitingCount; i++) waitingTimes[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(waitingTimes);
		int sum = 0;
		for(int i=0; i<waitingCount; i++) sum += (waitingCount-i)*waitingTimes[i];
		System.out.println(sum);
	}
}
