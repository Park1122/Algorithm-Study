package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1003 {
    static int T, N;
    static int[] zeroCount, oneCount;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void preprocess() {
        zeroCount = new int[41];
        oneCount = new int[41];

        zeroCount[0] = 1;
        zeroCount[1] = 0;
        oneCount[0] = 0;
        oneCount[1] = 1;

        for (int i = 2; i < 41; i++) {
            zeroCount[i] = zeroCount[i - 1] + zeroCount[i - 2];
            oneCount[i] = oneCount[i - 1] + oneCount[i - 2];
        }
    }

    public static void main(String[] args) throws IOException {
        preprocess();

        T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            N = Integer.parseInt(br.readLine());
            sb.append(zeroCount[N]).append(' ').append(oneCount[N]).append('\n');
        }
        System.out.println(sb);
    }
}
