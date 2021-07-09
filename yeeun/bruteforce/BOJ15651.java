package codingstudy202107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 1주차
 * 문제 출처 : https://www.acmicpc.net/problem/15651
 * 이름 : N과 M (3)
 * 사용 알고리즘 : 백트래킹
 * 설명 : 백트래킹 재귀로 풀면 시간초과. 단순 반복문으로 풀어본 것.
 */
public class BOJ15651 {

    private StringBuilder sb;
    private int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        BOJ15651 main = new BOJ15651();
        main.solution(n, m);
    }

    public void solution(int n, int m) {
        this.sb= new StringBuilder();
        this.max=n;
        this.recursion(m);
    }

    public void recursion(int count) {
        int length= sb.length();
        if (length == count*2) {
            System.out.println(sb.toString());
        } else {
            for (int i= 1; i < this.max+1; i++) {
                sb.append(i);
                sb.append(" ");
                this.recursion(count);
                sb.delete(length,length+2);
            }
        }
    }
}
