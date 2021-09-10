package codingstudy202107.w10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 회차 : 코딩 스터디 10주차
 * 문제 출처 : https://www.acmicpc.net/problem/2688
 * 이름 : 줄어들지 않아
 * 사용 알고리즘 : 다이나믹 프로그래밍
 */
public class BOJ2688 {

    public static void main(String[] args) throws IOException {
        BOJ2688 main = new BOJ2688();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i]=Integer.parseInt(reader.readLine());
        }
        System.out.print(main.solution(arr));
    }

    private String solution(int [] arr) {

        StringBuilder builder = new StringBuilder();
        boolean first=true;
        for(int val: arr){
            if(first) first=false;
            else builder.append('\n');
            builder.append(this.iteration(val));
        }
        return builder.toString();
    }

    private long iteration(int val) {
        long result = 0L;
        long[][] memoi = new long[val + 1][10];

        memoi[1] = new long[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        for (int i = 2; i <= val; i++) {
            int sum = 0;
            for (int j = 9; j >= 0; j--) {
                sum += memoi[i - 1][j];
                memoi[i][j] = sum;
            }
        }

        for (long l : memoi[val]) {
            result +=  l;
        }
        
        return result;
    }

}
