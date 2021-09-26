package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11724 {
    static int N, M;
    static ArrayList<Integer>[] con;
    static boolean[] visit;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        con = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            con[i] = new ArrayList<>();
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            con[u].add(v);
            con[v].add(u);
        }
    }

    static void dfs(int x) {
        visit[x] = true;

        for (int y : con[x]) {
            if(visit[y]) continue;
            dfs(y);
        }
    }

    static void solve() {
        int ans = 0;
        visit = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if(visit[i]) continue;
            dfs(i);
            ans++;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
