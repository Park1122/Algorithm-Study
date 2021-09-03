package codingstudy202107.w09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 9주차
 * 문제 출처 : https://www.acmicpc.net/problem/1149
 * 이름 : RGB거리
 * 사용 알고리즘 : 다이나믹 프로그래밍
 */
public class BOJ1149 {
//    private static final int red = 0, green = 1, blue = 2;

    public static void main(String[] args) throws IOException {
        BOJ1149 main = new BOJ1149();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < 3; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        System.out.print(main.solution(arr));
    }

    private int solution(int[][] arr) {
        int[][] memoi = new int[arr.length][3];
        for (int[] line : memoi) Arrays.fill(line, -1);

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 3; j++) {
                int lastSum = 0;
                if (i > 0) {
                    lastSum = Math.min(memoi[i - 1][this.toColorCode(j + 1)], memoi[i - 1][this.toColorCode(j + 2)]);
                }
                memoi[i][j] = lastSum + arr[i][j];
//                System.out.println(j + "," + i + "으로 가는 최소값=" + (lastSum + arr[i][j]));
            }
        }
        return Math.min(Math.min(memoi[arr.length - 1][0], memoi[arr.length - 1][1]), memoi[arr.length - 1][2]);
    }

    private int toColorCode(int line) {
        return line % 3;
    }


}
