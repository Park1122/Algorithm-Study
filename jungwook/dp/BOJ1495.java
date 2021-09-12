package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1495 {
    static int N, S, M;
    static int[] volumes;
    static int[][] Dy;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        volumes = new int[N + 1];
        for (int i = 1; i <= N; i++) volumes[i] = Integer.parseInt(st.nextToken());
        Dy = new int[N + 1][M + 1];
    }

    static void solve() {
        Dy[0][S] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if(Dy[i - 1][j] == 1) {
                    if (j + volumes[i] <= M) {
                        Dy[i][j + volumes[i]] = 1;
                    }

                    if (j - volumes[i] >= 0) {
                        Dy[i][j - volumes[i]] = 1;
                    }
                }
            }
        }

        //answer
        int ans = -1;
        for (int i = 0; i <= M; i++) {
            if (Dy[N][i] == 1) {
                ans = Math.max(ans, i);
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
