package codingstudy202107.w09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 9주차
 * 문제 출처 : https://www.acmicpc.net/problem/9465
 * 이름 : 스티커
 * 사용 알고리즘 : 다이나믹 프로그래밍
 */
public class BOJ9465 {

    public static void main(String[] args) throws IOException {
        BOJ9465 main = new BOJ9465();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int m = Integer.parseInt(reader.readLine());
            int[][] arr = new int[2][m];
            for (int j = 0; j < 2; j++) {
                StringTokenizer st = new StringTokenizer(reader.readLine());
                for (int k = 0; k < m; k++) arr[j][k] = Integer.parseInt(st.nextToken());
            }
            sb.append(main.solution(arr)).append('\n');
        }
        System.out.print(sb.toString());

    }

    private int solution(int[][] arr) {
        int[][] memoi = new int[2][arr[0].length];
        for (int[] line : memoi) Arrays.fill(line, -1);

        for (int i = 0; i < arr[0].length; i++) {
            for (int j = 0; j < 2; j++) {
                int lastSum = 0;
                if (i > 0) {
                    lastSum = memoi[this.toggle(j)][i-1];
                }
                if (i > 1) {
                    int llastSum=memoi[this.toggle(j)][i-2];
                    lastSum = Math.max(lastSum,llastSum);
                }
//                System.out.println(j+","+i+"으로 가는 최대값="+(lastSum+arr[j][i]));
                memoi[j][i]=lastSum+arr[j][i];
            }
        }

        return Math.max(memoi[0][arr[0].length-1],memoi[1][arr[0].length-1]);
    }


    private int toggle(int line) {
        if (line == 0) return 1;
        else return 0;
    }


}
