package codingstudy202107.w10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 회차 : 코딩 스터디 10주차
 * 문제 출처 : https://www.acmicpc.net/problem/11057
 * 이름 : 오르막 수
 * 사용 알고리즘 : 다이나믹 프로그래밍
 * 0~9로 끝나는 수가 존재. 끝나는 수와 같거나 더 큰 숫자를 뒤에 붙일 수 있음.
 * 9로 끝나는 숫자는 9만 붙일 수 있음. 0으로 끝나는 숫자는 모두 붙일 수 있음.
 */
public class BOJ11057 {

    public static void main(String[] args) throws IOException {
        BOJ11057 main = new BOJ11057();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        System.out.print(main.solution(n));
    }

    private long solution(int val) {
        long result = 0L;
        long[][] memoi = new long[val + 1][10];

        memoi[1] = new long[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        for (int i = 2; i <= val; i++) {

            int sum = 0;
            for (int j = 9; j >= 0; j--) {
                sum += (memoi[i - 1][j]) % 10007;
                memoi[i][j] = sum;
            }

        }

        for (long l : memoi[val]) {
            result = (result + l) % 10007;
        }
        return result;
    }

}
