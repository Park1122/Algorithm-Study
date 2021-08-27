package codingstudy202107.w08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 회차 : 코딩 스터디 8주차
 * 문제 출처 : https://www.acmicpc.net/problem/2579
 * 이름 : 계단 오르기
 * 사용 알고리즘 : 다이나믹 프로그래밍
 */
public class BOJ2579 {

    public static void main(String[] args) throws IOException {
        BOJ2579 main = new BOJ2579();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }
        System.out.print(main.solution(arr));
    }

    private long solution(int[] arr) {
        long[][] memoi = new long[3][arr.length];
        for (long[] line : memoi) Arrays.fill(line, -1L);

        return this.recursion(memoi, arr, arr.length - 1, 1);
    }


    private long recursion(long[][] memoi, int[] arr, int position, int mode) {
        if (position < 0) return 0;
        long sum = arr[position];

        if (mode == 1) {//끝이 01
            if (position > 1 && memoi[1][position - 2] == -1)
                memoi[1][position - 2] = this.recursion(memoi, arr, position - 2, 1);

            if (position > 0 && memoi[2][position - 1] == -1)
                memoi[2][position - 1] = this.recursion(memoi, arr, position - 1, 2);

            if (position > 1) sum += Math.max(memoi[1][position - 2], memoi[2][position - 1]);
            else if (position > 0) sum += memoi[2][position - 1];
        } else if (position > 1) {//끝이 11
            if (memoi[1][position - 2] == -1)
                memoi[1][position - 2] = this.recursion(memoi, arr, position - 2, 1);
            sum += memoi[1][position - 2];
        }

        memoi[mode][position] = sum;
        return sum;
    }

}
