package codingstudy202107.w05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 5주차
 * 문제 출처 : https://www.acmicpc.net/problem/14916
 * 이름 : 거스름돈
 * 사용 알고리즘 : 수학, 다이나믹 프로그래밍
 */
public class BOJ14916 {

    public static void main(String[] args) throws IOException {
        BOJ14916 main = new BOJ14916();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int result = main.solution(n);
        System.out.println(result);
    }

    private int solution(int n) {
        if (n < 5) {
            if (n % 2 != 0) return -1;
            else return n / 2;
        } else {
            int fives = n / 5, twos = (n % 5) / 2, answer = fives + twos;
            if ((n % 5) % 2 != 0) {// 5로 나눈 나머지가 짝수가 아님.
                fives--;
                twos = (n - (fives * 5)) / 2;
                answer = fives + twos;
            }
            return answer;
        }
    }
}
