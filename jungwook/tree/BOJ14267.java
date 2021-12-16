package jungwook.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ14267 {
    static int n, m;
    static int[] Dy;
    static ArrayList<Integer>[] children;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        Dy = new int[n + 1];
        children = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            children[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int par = Integer.parseInt(st.nextToken());
            if(i == 1) continue;
            children[par].add(i);
        }
        for (int j = 1; j <= m; j++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            Dy[i] += w;
        }
    }

    // 내가 받은 칭찬을 부하 직원들에게 누적시켜주는 작업
    static void dfs(int x) {
        for (int y : children[x]) {
            Dy[y] += Dy[x];
            dfs(y);
        }
    }

    static void solve() {
        dfs(1);
        for (int i = 1; i <= n; i++) {
            sb.append(Dy[i]).append(' ');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
