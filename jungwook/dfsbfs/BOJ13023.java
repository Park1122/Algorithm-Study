package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * visit[x] = false 추가 이유 설명: https://loosie.tistory.com/509
 */
public class BOJ13023 {
    static int N, M, ans;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            graph[y].add(x);
        }
        visit = new boolean[N];
    }

    static void dfs(int x, int depth) {
        if (depth == 5) {
            ans = 1;
            return;
        }

        visit[x] = true;

        for (int y : graph[x]) {
            if(visit[y]) continue;

            dfs(y, depth + 1);
        }
        visit[x] = false;
    }

    static void solve() {
        for (int i = 0; i < N; i++) {
            if (ans == 0) {
                visit = new boolean[N];
                dfs(i, 1);
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
