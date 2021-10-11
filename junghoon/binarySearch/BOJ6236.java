package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6236 {
	
	/* 21년 10월 1주차 ( 02.10.21 )                        ******문제 이해 필요*******
	 * 
	 * 문제: 현우는 용돈을 효율적으로 활용하기 위해 계획을 짜기로 하였다. 
	 *      현우는 앞으로 N일 동안 자신이 사용할 금액을 계산하였고, 돈을 펑펑 쓰지 않기 위해 정확히 M번만 통장에서 돈을 빼서 쓰기로 하였다. 
	 *      현우는 통장에서 K원을 인출하며, 통장에서 뺀 돈으로 하루를 보낼 수 있으면 그대로 사용하고, 모자라게 되면 남은 금액은 통장에 집어넣고 다시 K원을 인출한다. 
	 *      다만 현우는 M이라는 숫자를 좋아하기 때문에, 정확히 M번을 맞추기 위해서 남은 금액이 그날 사용할 금액보다 많더라도 남은 금액은 통장에 집어넣고 다시 K원을 인출할 수 있다. 
	 *      현우는 돈을 아끼기 위해 인출 금액 K를 최소화하기로 하였다. 현우가 필요한 최소 금액 K를 계산하는 프로그램을 작성하시오.
	 * 
	 * 입력: 1번째 줄에는 N과 M이 공백으로 주어진다. (1 ≤ N ≤ 100,000, 1 ≤ M ≤ N)
	 *      2번째 줄부터 총 N개의 줄에는 현우가 i번째 날에 이용할 금액이 주어진다. (1 ≤ 금액 ≤ 10000)
	 * 
	 * 출력: 첫 번째 줄에 현우가 통장에서 인출해야 할 최소 금액 K를 출력한다.
	 * 
	 * 풀이: 돈을 M번 이하로 채우면 동일하게 넣고 뺄 수 있음. 이게 핵심 
	 *           *****김무성 개발자님 코드 확인******
	 * */
	
	static int n, m;
	static int[] costs;
	
	static int getCount(int mid) {
		int remain = mid;
		int count = 1;
		for(int i = 0; i < n; i++) {
			if(remain - costs[i] < 0) {
				remain = mid;
				count++;
			}
			remain -= costs[i];
		}
		return count;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		costs = new int[n];
		int low = 1;
		int high = (int)1e9;
		int answer = 0;
		for (int i = 0; i < n; i++) {
            costs[i] = Integer.parseInt(br.readLine());
            low = Math.max(low, costs[i]);
        }

        while (low <= high) {
            int mid = (low + high) / 2;

            if (getCount(mid) > m) {
                low = mid + 1;
            } else {
                answer = mid;
                high = mid - 1;
            }
        }

        System.out.println(answer);
    }
	

}
