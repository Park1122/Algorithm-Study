package jungwook.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15900 {
    static int N, depth;
    static ArrayList<Integer>[] tree;
    static boolean[] visit;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
    }

    static void dfs(int n, int cnt) {
        visit[n] = true;

        for (int y : tree[n]) {
            if(visit[y]) continue;
            dfs(y, cnt + 1);
        }

        // root가 아니고, 인접 리스트 사이즈가 1이면 leaf node
        if (n != 1 && tree[n].size() == 1) {
            depth += cnt;
        }
    }

    static void solve() {
        visit = new boolean[N + 1];
        dfs(1, 0);

        if (depth % 2 == 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
