package year_2021.month_08.day_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ1931 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numberOfMeeting = Integer.parseInt(br.readLine());
		int[][] meetings = new int[numberOfMeeting][2];
		for(int i=0; i<numberOfMeeting; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			meetings[i][0] = Integer.parseInt(st.nextToken());
			meetings[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(meetings, new Comparator<int[]>(){
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] != o2[1]) return o1[1]-o2[1]; 
				else return o1[0]-o2[0];
			}
		});
		
		ArrayList<int[]> answer = new ArrayList<>();
		int beforeEndTime = 0;
		for(int i=0; i<numberOfMeeting; i++) {
			if(beforeEndTime <= meetings[i][0]) {
				answer.add(meetings[i]);
				beforeEndTime = meetings[i][1];
			}
		}
		
		System.out.println(answer.size());
	}
}
