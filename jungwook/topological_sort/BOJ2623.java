package jungwook.topological_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ2623 {
    static int N, M;
    static int[] indeg;
    static ArrayList<Integer>[] adj;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N + 1];
        indeg = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        /*
         * A1, A2, ... Acnt 순서로 나가야 한다면,
         * A1 -> A2
         * A2 -> A3
         *  ...
         * A(cnt-1) -> Acnt
         * 의 간선을 만들어주면 된다.
         */
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y;
            for (int j = 2; j <= cnt; j++) {
                y = Integer.parseInt(st.nextToken());
                adj[x].add(y);
                indeg[y]++;
                x = y;
            }
        }
    }

    static void solve() {
        // 우선순위에 대한 조건을 간선으로 표현했으므로 위상정렬을 수행
        Deque<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indeg[i] == 0) {
                q.add(i);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            int x = q.poll();
            ans.add(x);
            for (int y : adj[x]) {
                indeg[y]--;
                if (indeg[y] == 0) {
                    q.add(y);
                }
            }
        }

        //출력
        if (ans.size() == N) {
            for (int x : ans) {
                sb.append(x).append('\n');
            }
        } else {
            sb.append(0);
        }
        System.out.println(sb);
    }



    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
