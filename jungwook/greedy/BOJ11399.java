package jungwook.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11399 {
    static int n, sum, answer;
    static int[] arr;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            answer += sum;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.println(answer);
    }
}
