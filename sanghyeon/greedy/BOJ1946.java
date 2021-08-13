package year_2021.month_08.day_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ1946 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numberOfTestcase = Integer.parseInt(br.readLine());
		for(int i=0; i<numberOfTestcase; i++) {
			int numberOfCandidate = Integer.parseInt(br.readLine());
			int[][] scores = new int[numberOfCandidate][2]; 
			for(int j=0; j<numberOfCandidate; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				scores[j][0] = Integer.parseInt(st.nextToken());
				scores[j][1] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(scores, Comparator.comparingInt(o1->o1[0]));
			int minInterviewScore = scores[0][1], maxPass = 1;
			for(int j=1; j<numberOfCandidate; j++) {
				if(scores[j][1] < minInterviewScore) {
					maxPass++;
					minInterviewScore = scores[j][1];
				}
			}
			System.out.println(maxPass);
		}
	}
}
