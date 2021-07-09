package codingstudy202107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 1주차
 * 문제 출처 : https://www.acmicpc.net/problem/15652
 * 이름 : N과 M (4)
 * 사용 알고리즘 : 백트래킹
 */
public class BOJ15652 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        BOJ15652 main = new BOJ15652();
        main.solution(n, m);
    }

    public void solution(int n, int m) {
        this.recursion(new int[m], n, 0);
    }

    public void recursion(int[] result, int max, int count) {
        if (result.length == count) {
            this.printArray(result);
        } else {
            for (int i = count == 0 ? 0 : result[count - 1]-1; i < max; i++) {
                result[count] = i+1;
                this.recursion(result, max, count+1);
            }
        }
    }

    public void printArray(int[] arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arr.length; ) {
            stringBuilder.append(arr[i++]);
            if (i != arr.length) {
                stringBuilder.append(" ");
            }
        }
        System.out.println(stringBuilder.toString());
    }
}