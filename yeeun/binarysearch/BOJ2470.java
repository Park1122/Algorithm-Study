package codingstudy202107.w04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 4주차
 * 문제 출처 : https://www.acmicpc.net/problem/2470
 * 이름 : 두 용액
 * 사용 알고리즘 : -
 */
public class BOJ2470 {

    public static void main(String[] args) throws IOException {
        BOJ2470 main = new BOJ2470();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        main.solution(arr);
    }

    private void solution(int[] arr) {
        Arrays.sort(arr);
        int start=0, end=arr.length-1;

        int minFirst=arr[start], minSecond=arr[end];
        int min= this.getAbsoluteSum(minFirst,minSecond);

        while(start<end){
            int a=arr[start], b=arr[end];
            int sum =this.getAbsoluteSum(a,b);
            if(sum<min){
                minFirst=a;
                minSecond=b;
                min=sum;
            }

            if(a+b>0)end--;
            else start++;
        }
        this.write(minFirst,minSecond);
    }



    private int getAbsoluteSum(int a, int b) {
        return a + b > 0 ? a + b : (a + b) * -1;
    }


    private void write(int a, int b) {
        System.out.print(new StringBuilder().append(Math.min(a, b)).append(" ").append(Math.max(a, b)).toString());
    }
}
