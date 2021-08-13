package codingstudy202107.w05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

/**
 * 회차 : 코딩 스터디 5주차
 * 문제 출처 : https://www.acmicpc.net/problem/2217
 * 이름 : 로프
 * 사용 알고리즘 : 수학, 그리디, 정렬, 물리학
 */
public class BOJ2217 {

    public static void main(String[] args) throws IOException {
        BOJ2217 main = new BOJ2217();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }

        int result = main.solution(n,arr);
        System.out.print(result);
    }

    private int solution(int n, int[] arr){
       Arrays.sort(arr);

       int max=0;
       for(int i=arr.length-1; i>=0; i--){
           int val= arr[i]*(n-i);
           if(val>max)max=val;
       }
       return max;
    }
}
