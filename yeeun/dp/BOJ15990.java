package codingstudy202107.w07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 회차 : 코딩 스터디 7주차
 * 문제 출처 : https://www.acmicpc.net/problem/15990
 * 이름 : 1, 2, 3 더하기 5
 * 사용 알고리즘 : 다이나믹 프로그래밍
 */
public class BOJ15990 {

    public static void main(String[] args) throws IOException {
        BOJ15990 main = new BOJ15990();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }

        System.out.print(main.solution(arr));
    }


    private String solution(int[] arr) {
        StringBuilder builder = new StringBuilder();

        int[][] memoi = new int[100001][4];

        for(int[] line: memoi){
            Arrays.fill(line,-1);
        }

        for (int val : arr) {
            builder.append(this.recursion(memoi, val, 0) % 1000000009).append('\n');
        }

        return builder.toString();
    }

    private long recursion(int[][] memoi, int val, int add) {
        if (val == 0) return 1;
        if(memoi[val][add]!=-1){
            return memoi[val][add];
        }

        long result;
        if (add == 1) {
            long twos = val >= 2 ? recursion(memoi, val - 2, 2) : 0;
            long threes = val >= 3 ? recursion(memoi, val - 3, 3) : 0;
            result= twos + threes;
        } else if (add == 2) {
            long ones = val >= 1 ? recursion(memoi, val - 1, 1) : 0;
            long threes = val >= 3 ? recursion(memoi, val - 3, 3) : 0;
            result= ones + threes;
        } else if (add == 3) {
            long ones = val >= 1 ? recursion(memoi, val - 1, 1) : 0;
            long twos = val >= 2 ? recursion(memoi, val - 2, 2) : 0;
            result= ones + twos;
        } else {
            long ones = val >= 1 ? recursion(memoi, val - 1, 1) : 0;
            long twos = val >= 2 ? recursion(memoi, val - 2, 2) : 0;
            long threes = val >= 3 ? recursion(memoi, val - 3, 3) : 0;
            result= ones + twos + threes;
        }
        memoi[val][add]= (int) (result%1000000009);
        return result;
    }

}
