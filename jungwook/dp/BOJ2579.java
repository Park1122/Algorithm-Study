package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2579 {
    static int N;
    static int[][] Dy; // i-1을 밟았는지 안밟았는지에 대한 조건이 필요하므로 2차원 배열 사용
    // Dy[i][0]: (i-2) -> i
    // Dy[i][1]: (i-1) -> i 이 경우, i-2는 자동으로 밟지 않음
    static int[] A;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        Dy = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
    }

    static void solve() {
        //initial
        Dy[1][0] = 0;
        Dy[1][1] = A[1];

        if (N >= 2) {
            Dy[2][0] = A[2];
            Dy[2][1] = A[1] + A[2];
        }

        //fill
        for (int i = 3; i <= N; i++) {
            Dy[i][0] = Math.max(Dy[i - 2][0] + A[i], Dy[i - 2][1] + A[i]);
            Dy[i][1] = Dy[i - 1][0] + A[i];
        }

        //solve
        int ans = Math.max(Dy[N][0], Dy[N][1]);

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
