package codingstudy202107.w04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 4주차
 * 문제 출처 : https://www.acmicpc.net/problem/11047
 * 이름 : 동전 0
 * 사용 알고리즘 : 그리디 알고리즘
 */
public class BOJ11047 {

    public static void main(String[] args) throws IOException {
        BOJ11047 main = new BOJ11047();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }
        System.out.print(main.solution(arr,k));
    }

    private int solution(int[] arr, int k) {
        int sum=0;

        for(int i=arr.length-1; i>=0; i--){
            int val= arr[i];
            if(k>=val) {
                int amount=k/val;
                sum+=amount;
                k-=amount*val;

                if(k==0) return sum;
            }
        }

        return sum;
    }


}
