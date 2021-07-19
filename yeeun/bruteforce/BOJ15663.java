package codingstudy202107.w02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 2주차
 * 문제 출처 : https://www.acmicpc.net/problem/15663
 * 이름 : N과 M (9)
 * 사용 알고리즘 : 백트래킹
 */
public class BOJ15663 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        BOJ15663 main = new BOJ15663();
        main.solution(n, m, arr);
    }

    public void solution(int n, int m, int[] arr) {
        int[] pick = new int[n];
        Arrays.fill(pick, -1);
        StringBuilder builder = new StringBuilder();
        Arrays.sort(arr);
        String result = this.makeSubset(0, m, pick, arr, builder).toString();
        System.out.print(result);
    }

    public StringBuilder makeSubset(int selected, int pickAmount, int[] pick, int[] arr, StringBuilder builder) {
        if (selected == pickAmount) {
            this.makeString(pick, arr, builder, pickAmount);
            return builder;
        }

        int prev = -1;
        for (int i = 0; i < pick.length; i++) {
            if (pick[i]==-1&&arr[i] != prev) {
                pick[i] = selected;
                builder = this.makeSubset(selected + 1, pickAmount, pick,  arr, builder);
                pick[i] = -1;
                prev = arr[i];
            }
        }

        return builder;
    }

    private void makeString(int[] pick, int[] arr, StringBuilder builder, int pickAmount) {
        int count = 0;
        for (int i = 0; i < pickAmount; i++) {
            for (int j = 0; j < pick.length; j++) {
                if (pick[j] == i) {
                    builder.append(arr[j]);
                    if (++count == pickAmount) {
                        builder.append('\n');
                    } else builder.append(" ");
                    break;
                }
            }
        }
    }

}

