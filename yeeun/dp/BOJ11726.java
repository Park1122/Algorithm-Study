package codingstudy202107.w06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 회차 : 코딩 스터디 6주차
 * 문제 출처 : https://www.acmicpc.net/problem/11726
 * 이름 : 1,2,3 더하기
 * 사용 알고리즘 : 다이나믹 프로그래밍
 */
public class BOJ11726 {

    public static void main(String[] args) throws IOException {
        BOJ11726 main = new BOJ11726();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        System.out.print((int)(main.solution(n)/1));
    }


    private Long solution(int n) {
        Long[] memoi = new Long[1001];
        Arrays.fill(memoi, -1L);

        memoi[1] = 1L;
        memoi[2] = 2L;
        return this.recursion(memoi, n);
    }

    private Long recursion(Long[] memoi, int val) {
        if (memoi[val] > -1L) return memoi[val];

        Long sum = this.recursion(memoi, val - 1) + this.recursion(memoi, val - 2);
        sum %= 10007;

        memoi[val] = sum;
        return sum;
    }


}
