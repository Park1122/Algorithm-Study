package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1149 {
    static int N;
    static int[][] Dy, cost;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N + 1][4]; // [i][1]: R [i][2]: G [i][3]: B
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
            cost[i][3] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        Dy = new int[N + 1][4]; // [i][1]: R [i][2]: G [i][3]: B

        Dy[1][1] = cost[1][1];
        Dy[1][2] = cost[1][2];
        Dy[1][3] = cost[1][3];

        for (int i = 2; i <= N; i++) {
            Dy[i][1] = Math.min(Dy[i - 1][2], Dy[i - 1][3]) + cost[i][1];
            Dy[i][2] = Math.min(Dy[i - 1][1], Dy[i - 1][3]) + cost[i][2];
            Dy[i][3] = Math.min(Dy[i - 1][1], Dy[i - 1][2]) + cost[i][3];
        }

        System.out.println(Math.min(Math.min(Dy[N][1], Dy[N][2]), Dy[N][3]));
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
