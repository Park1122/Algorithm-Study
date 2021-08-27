package codingstudy202107.w08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 회차 : 코딩 스터디 8주차
 * 문제 출처 : https://www.acmicpc.net/problem/1463
 * 이름 : 1로 만들기
 * 사용 알고리즘 : 다이나믹 프로그래밍
 */
public class BOJ1463 {

    public static void main(String[] args) throws IOException {
        BOJ1463 main = new BOJ1463();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        System.out.print(main.solution(n));
    }

    private int solution(int val) {
        int[] memoi = new int[1000001];
        Arrays.fill(memoi, -1);
        memoi[1] = 0;
        memoi[2] = 1;
        memoi[3] = 1;
        for (int i = 1; i < val + 1; i++) {
            this.recursion(memoi, i);
        }
        return memoi[val];
    }


    private void recursion(int[] memoi, int val) {
        if (memoi[val] != -1) return;

        int result = 1;
        if (val % 6 == 0) {
            int any = memoi[val - 1];
            int even = memoi[val / 2];
            int divThree = memoi[val / 3];
            result += Math.min(any, Math.min(even, divThree));
        } else if (val % 3 == 0) {
            int any = memoi[val - 1];
            int divThree = memoi[val / 3];
            result += Math.min(any, divThree);
        } else if (val % 2 == 0) {
            int any = memoi[val - 1];
            int even = memoi[val / 2];
            result += Math.min(any, even);
        } else {
            int any = memoi[val - 1];
            result += any;
        }

        memoi[val] = result;
    }

}
