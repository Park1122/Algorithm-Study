package codingstudy202107.w03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 3주차
 * 문제 출처 : https://www.acmicpc.net/problem/7795
 * 이름 : 두 수의 합
 * 사용 알고리즘 : 정렬, 두 포인터
 */
public class BOJ3273 {

    public static void main(String[] args) throws IOException {
        BOJ3273 main = new BOJ3273();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(reader.readLine());
        int x = Integer.parseInt(st.nextToken());

        main.solution(arr, x);
    }

    private void solution(int[] arr, int x) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            count += this.findCoupleNumber(x, arr[i], i + 1, arr);
        }
        System.out.print(count);
    }

    private int findCoupleNumber(int x, int target, int start, int[] arr) {
        for (int i = start; i < arr.length; i++) {
            if (arr[i] + target == x) {
                return 1;
            }
        }
        return 0;
    }
}
