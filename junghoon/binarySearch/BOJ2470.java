package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2470 {

	/* 21�� 10�� 2���� ( 09.10.21 )
	 * 
	 * ����: KOI �μ� ���п����ҿ����� ���� ������ �꼺 ��װ� ��Į���� ����� �����ϰ� �ִ�.
	 *      �� ��׿��� �� ����� Ư���� ��Ÿ���� �ϳ��� ������ �־����ִ�.  
	 *      �꼺 ����� Ư������ 1���� 1,000,000,000������ ���� ������ ��Ÿ����, 
	 *      ��Į���� ����� Ư������ -1���� -1,000,000,000������ ���� ������ ��Ÿ����. 
	 *      ���� ���� �� ����� ȥ���� ����� Ư������ ȥ�տ� ���� �� ����� Ư������ ������ �����Ѵ�. 
	 *      �� �����ҿ����� ���� ���� �� ����� ȥ���Ͽ� Ư������ 0�� ���� ����� ����� ������� �Ѵ�. 
	 *      ���� ���, �־��� ��׵��� Ư������ [-2, 4, -99, -1, 98]�� ��쿡�� Ư������ -99�� ��װ� Ư������ 98�� ����� ȥ���ϸ� Ư������ -1�� ����� ���� �� �ְ�, 
	 *      �� ����� Ư������ 0�� ���� ����� ����̴�. ������, �� ������ ��Į���� ��׸����γ� Ȥ�� �� ������ �꼺 ��׸����� Ư������ 0�� ���� ����� ȥ�� ����� ����� ��쵵 ������ �� �ִ�. 
	 *      �꼺 ��װ� ��Į���� ����� Ư������ �־����� ��, �� �� �� ���� ���� �ٸ� ����� ȥ���Ͽ� Ư������ 0�� ���� ����� ����� ������ �� ����� ã�� ���α׷��� �ۼ��Ͻÿ�.
	 * 
	 * �Է�: ù° �ٿ��� ��ü ����� �� N�� �Էµȴ�. N�� 2 �̻� 100,000 �����̴�. ��° �ٿ��� ����� Ư������ ��Ÿ���� N���� ������ ��ĭ�� ���̿� �ΰ� �־�����. 
	 *      �� ������ ��� -1,000,000,000 �̻� 1,000,000,000 �����̴�. 
	 *      N���� ��׵��� Ư������ ��� �ٸ���, �꼺 ��׸����γ� ��Į���� ��׸����� �Է��� �־����� ��쵵 ���� �� �ִ�.
	 * 
	 * ���: ù° �ٿ� Ư������ 0�� ���� ����� ����� ������ �� ����� Ư������ ����Ѵ�. 
	 *      ����ؾ� �ϴ� �� ����� Ư������ ������������ ����Ѵ�. 
	 *      Ư������ 0�� ���� ����� ����� ������ ��찡 �� �� �̻��� ��쿡�� �� �� �ƹ����̳� �ϳ��� ����Ѵ�.
	 * 
	 * Ǯ��: �ΰ��� ���� 0�� ����� ���ڸ� ã�� ��, N�� �ִ� 10���̰� ���ѽð��� 1����. �׷��� o(n)���� Ǯ��� �Ѵ�. �迭�� �����ؼ� ���� ���ڿ� ū���ڸ� ���� �� max���� ���ϰ� 
	 *      �� ���� 0���� ũ�ٸ� �ε����� ���̰� �ƴϸ� �պ��� �۴ٸ� �ε����� ������Ų��.
	 *     
	 * */
	static int pick1 = -1;
	static int pick2 = -1;

	 static int stoi(String s) {
	        return Integer.parseInt(s);
	    }
	 
	 static void solution(int n, int[] arr) {
	        int left = 0;
	        int right = n-1;
	        int max = 2000000000;
	 
	        while(left < right) {
	            int sum = arr[left] + arr[right];
	 
	            if(Math.abs(sum) < max) {
	                pick1 = arr[left];
	                pick2 = arr[right];
	                max = Math.abs(sum);
	            }
	 
	            if(sum > 0)
	                right--;
	            else
	                left++;
	        }
	 }
	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st;
	 
	        int n = stoi(br.readLine());
	        int[] arr = new int[n];
	 
	        st = new StringTokenizer(br.readLine());
	        for(int i=0; i<n; i++)
	            arr[i] = stoi(st.nextToken());
	 
	        Arrays.sort(arr);
	 
	        solution(n, arr);
	 
	        System.out.println(pick1 + " " + pick2);
	    }

}