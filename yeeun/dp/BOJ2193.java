package codingstudy202107.w08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 8주차
 * 문제 출처 : https://www.acmicpc.net/problem/2193
 * 이름 : 이친수
 * 사용 알고리즘 : 다이나믹 프로그래밍
 * 1로 끝나는 수-> 0을 붙일 수 있음
 * 0로 끝나는 수-> 0,1을 붙일 수 있음
 * n 자리수의 이친수의 개수: (n-1자리수의 0으로 끝나는 이친수의 개수 * 2) + (n-1 자리수의 1로 끝나는 이친수의 개수)
 */
public class BOJ2193 {

    public static void main(String[] args) throws IOException {
        BOJ2193 main = new BOJ2193();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        System.out.print(main.solution(n));
    }

    private long solution(int val) {
        long[][] memoi = new long[2][val + 1];
        memoi[0][1] = 0;
        memoi[1][1] = 1;

        for (int i = 2; i < val + 1; i++) {
            memoi[0][i] = memoi[0][i - 1] + memoi[1][i - 1];
            memoi[1][i] = memoi[0][i - 1];
//            System.out.println(i+": ("+memoi[0][i]+"/"+memoi[1][i]+") - "+(memoi[0][i]+memoi[1][i]));
        }
        return memoi[0][val] + memoi[1][val];
    }

}
