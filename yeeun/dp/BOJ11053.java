package codingstudy202107.w08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 8주차
 * 문제 출처 : https://www.acmicpc.net/problem/11053
 * 이름 : 가장 긴 증가하는 부분수열
 * 사용 알고리즘 : 다이나믹 프로그래밍
 */
public class BOJ11053 {

    public static void main(String[] args) throws IOException {
        BOJ11053 main = new BOJ11053();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.print(main.solution(arr));
    }

    private int solution(int[] arr) {
        int[] memoi = new int[arr.length];
        Arrays.fill(memoi, -1);
        memoi[0] = 1;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int result = this.iteration(memoi, arr, i);
            if (result > max) max = result;
        }
        return max;
    }

    private int iteration(int[] memoi, int[] arr, int position) {
        if (memoi[position] != -1) {
            return memoi[position];
        }

        int max = 0, target = arr[position];
        for (int i = 0; i < position; i++) {
            int last = arr[i];
            int length = memoi[i];
            if (last < target && length > max) {//만약 i가 타겟보다 작고, 지금까지 제일 긴 수열일 때.
                max = length;
            }
        }

        int result = max + 1;
        memoi[position] = result;
        return result;
    }

}
