package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15681 {
    static int N, R, Q;
    static int[] Dy;
    static ArrayList<Integer>[] con;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        con = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            con[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            con[x].add(y);
            con[y].add(x);
        }
    }

    static void dfs(int x, int prev) {
        Dy[x] = 1; //자기 자신 포함
        for (int y : con[x]) {
            if(y == prev) continue; // 부모인 경우에는 넘김
            dfs(y, x);
            Dy[x] += Dy[y];
        }
    }

    static void solve() throws IOException {
        Dy = new int[N + 1];

        dfs(R, -1);

        for (int i = 1; i <= Q; i++) {
            int q = Integer.parseInt(br.readLine());
            sb.append(Dy[q]).append('\n');
        }
        System.out.println(sb);
    }
    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
