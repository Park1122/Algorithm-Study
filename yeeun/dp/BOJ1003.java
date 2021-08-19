package codingstudy202107.w07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 회차 : 코딩 스터디 7주차
 * 문제 출처 : https://www.acmicpc.net/problem/1003
 * 이름 : 피보나치 함수
 * 사용 알고리즘 : 다이나믹 프로그래밍
 */
public class BOJ1003 {

    public static void main(String[] args) throws IOException {
        BOJ1003 main = new BOJ1003();
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
        int[][] memoi = new int[41][2];
        for (int[] line : memoi) {
            Arrays.fill(line, -1);
        }
        memoi[0][0] = 1;
        memoi[0][1] = 0;
        memoi[1][0] = 0;
        memoi[1][1] = 1;

        for (int val : arr) {
            this.recursion(builder, memoi, val);
            builder.append(memoi[val][0]).append(" ").append(memoi[val][1]).append('\n');
        }
        return builder.toString();
    }

    private void recursion(StringBuilder builder, int[][] memoi, int val) {
        if (memoi[val][0] == -1) {
            this.recursion(builder, memoi, val - 1);
            this.recursion(builder, memoi, val - 2);
            memoi[val][0] = memoi[val - 1][0] + memoi[val - 2][0];
            memoi[val][1] = memoi[val - 1][1] + memoi[val - 2][1];
        }
    }

}
