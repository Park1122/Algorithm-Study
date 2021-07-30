package codingstudy202107.w04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 4주차
 * 문제 출처 : https://www.acmicpc.net/problem/2805
 * 이름 : 나무 자르기
 * 사용 알고리즘 : 이분 탐색
 */
public class BOJ2805 {

    public static void main(String[] args) throws IOException {
        BOJ2805 main = new BOJ2805();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(reader.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.print(main.solution(arr, m));
    }

    private int solution(int[] arr, int m) {
        Arrays.sort(arr);

        int end = arr[arr.length - 1], start = end - m;//아예 안 자르는경우~제일 긴 나무를 필요한만큼 자른 경우 사이에 답이 있을 것.

        while (start <= end) {
            int mid = (start + end) / 2;
            long sum = this.cutTree(arr, mid);

            if (sum == m) {
                return mid;
            } else if (sum > m) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }

    private long cutTree(int[] arr, int length) {
        long sum = 0;
        for (int val: arr) {
            if (val > length) sum += val - length;
        }
        return sum;
    }

}
