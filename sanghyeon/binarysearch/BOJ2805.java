package year_2021.month_07.day_27;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2805 {
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		int treeCount = Integer.parseInt(st1.nextToken());
		long needTreeLength = Long.parseLong(st1.nextToken());
		int[] treeLengths = new int[treeCount];
		int maxTreeLength = 0;
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for (int i = 0; i < treeCount; i++) {
			treeLengths[i] = Integer.parseInt(st2.nextToken());
			if(maxTreeLength < treeLengths[i]) maxTreeLength = treeLengths[i];
		}
		Arrays.sort(treeLengths);

		int lowTreeHeight = 0, highTreeHeight = maxTreeLength;
		int maxHeightToGetNeedTreeLength = -1;
		while (lowTreeHeight <= highTreeHeight) {
			int midHeight = (lowTreeHeight + highTreeHeight)/2;
			long getTreeLength = getTreeLength(treeLengths, midHeight);
			if (needTreeLength > getTreeLength) {
				highTreeHeight = midHeight-1;
			}else if(needTreeLength <= getTreeLength) {
				lowTreeHeight = midHeight+1;
				maxHeightToGetNeedTreeLength = midHeight;
			}
		}
		System.out.println(maxHeightToGetNeedTreeLength);
	}

	private static long getTreeLength(int[] lengths, int midHeight) {
		long sum=0;
		for(int length : lengths) sum += Math.max(0, length-midHeight);
		return sum;
	}
}
