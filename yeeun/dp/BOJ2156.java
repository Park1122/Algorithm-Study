package codingstudy202107.w09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 9주차
 * 문제 출처 : https://www.acmicpc.net/problem/2156
 * 이름 : 포도주 시식
 * 사용 알고리즘 : 다이나믹 프로그래밍
 */
public class BOJ2156 {

    public static void main(String[] args) throws IOException {
        BOJ2156 main = new BOJ2156();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }

        System.out.print(main.solution(arr));
    }

    private int solution(int[] arr) {
        int [] memoi= new int[arr.length+1];
        memoi[1]= arr[0];
        if(arr.length>1){
            memoi[2]= arr[0]+arr[1];
        }

        for(int i= 3; i<arr.length+1; i++){
            memoi[i]= Math.max(memoi[i-1], Math.max(memoi[i-2]+arr[i-1], memoi[i-3]+arr[i-2]+arr[i-1]));
        }
        return memoi[arr.length];
    }



}
