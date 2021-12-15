package jungwook.topological_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ9470 {
    static int K, M, P;
    static int[] indeg, order, maxCnt;
    static ArrayList<Integer>[] adj;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        adj = new ArrayList[M + 1];
        indeg = new int[M + 1];
        order = new int[M + 1];
        maxCnt = new int[M + 1];
        for (int i = 1; i <= M; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adj[x].add(y);
            indeg[y]++;
        }
    }

    static void solve() {
        Deque<Integer> q = new LinkedList<>();
        // 제일 앞에 "정렬될 수 있는" 정점 찾기
        for (int i = 1; i <= M; i++) {
            if (indeg[i] == 0) {
                q.add(i);
                order[i] = 1;
                maxCnt[i] = 1;
            }
        }

        // Strahler 순서를 고려해서 위상정렬을 수행하자.
        int ans = 0;
        while (!q.isEmpty()) {
            int x = q.poll();
            // 만약 들어오는 순서 중 가장 큰 순서가 2개 이상이면 자신의 순서를 1 증가
            if (maxCnt[x] >= 2) {
                order[x]++;
            }
            ans = Math.max(ans, order[x]);

            for (int y : adj[x]) {
                indeg[y]--;
                if (indeg[y] == 0) q.add(y);
                // Strahler 순서 계산을 위해서 y번 정점에 들어오는 최대 순서를 갱신
                if (order[y] == order[x]) maxCnt[y]++;
                else if (order[y] < order[x]) {
                    order[y] = order[x];
                    maxCnt[y] = 1;
                }
            }
        }

        // 출력
        System.out.println(K + " " + ans);
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            input();
            solve();
        }
    }
}
