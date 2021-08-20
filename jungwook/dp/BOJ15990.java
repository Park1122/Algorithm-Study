package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15990 {
    static int T, N;
    static long[][] Dy;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void preprocess() throws IOException {
        T = Integer.parseInt(br.readLine());

        Dy = new long[100001][4];
        //Dy[i][1] : i가 만들어질 수 있는 1,2,3의 합 중에서 1로 끝나는 경우
        //Dy[i][2] : i가 만들어질 수 있는 1,2,3의 합 중에서 2로 끝나는 경우
        //Dy[i][3] : i가 만들어질 수 있는 1,2,3의 합 중에서 3로 끝나는 경우

        Dy[1][1] = 1;
        Dy[1][2] = 0;
        Dy[1][3] = 0;

        Dy[2][1] = 0;
        Dy[2][2] = 1;
        Dy[2][3] = 0;

        Dy[3][1] = 1;
        Dy[3][2] = 1;
        Dy[3][3] = 1;

        for (int i = 4; i <= 100000; i++) {
            Dy[i][1] = (Dy[i - 1][2] + Dy[i - 1][3]) % 1000000009; // 끝이 1로 끝난 경우에는 1을 뺀 i-1번의 경우에서 2, 3으로 끝나는 경우만 올 수 있음
            Dy[i][2] = (Dy[i - 2][1] + Dy[i - 2][3]) % 1000000009; // 끝이 2로 끝난 경우에는 2을 뺀 i-2번의 경우에서 1, 3으로 끝나는 경우만 올 수 있음
            Dy[i][3] = (Dy[i - 3][1] + Dy[i - 3][2]) % 1000000009; // 끝이 3로 끝난 경우에는 3을 뺀 i-3번의 경우에서 1, 2로 끝나는 경우만 올 수 있음
        }
    }

    static void solve() throws IOException {
        for (int i = 1; i <= T; i++) {
            N = Integer.parseInt(br.readLine());
            sb.append((Dy[N][1] + Dy[N][2] + Dy[N][3]) % 1000000009).append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        preprocess();
        solve();
    }
}
