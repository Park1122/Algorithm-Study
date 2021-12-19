package jungwook.topological_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ1005 {
    static int N, K, W;
    // tDone[X] : X번 건물이 완성되는 시간 -> 해당 배열을 채워야 함
    // t[x] : X번의 건설시간
    static int[] indeg, tDone, t;
    static ArrayList<Integer>[] adj;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        // Testcase 가 존재하는 문제이므로 "배열 초기화"에 유의하자
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N + 1];
        indeg = new int[N + 1];
        tDone = new int[N + 1];
        t = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            t[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adj[x].add(y);
            indeg[y]++;
        }
        W = Integer.parseInt(br.readLine());
    }

    static void solve() {
        Deque<Integer> q = new LinkedList<>();
        // 제일 앞에 "정렬될 수 있는" 정점 찾기
        for (int i = 1; i <= N; i++) {
            if (indeg[i] == 0) {
                q.add(i);
                tDone[i] = t[i];
            }
        }

        // 위상 정렬 순서대로 T_done 계산을 함께 해주기
        while (!q.isEmpty()) {
            int x = q.poll();
            for (int y : adj[x]) {
                indeg[y]--;
                if(indeg[y] == 0) q.add(y);
                tDone[y] = Math.max(tDone[y], tDone[x] + t[y]);
            }
        }

        // 출력
        System.out.println(tDone[W]);
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T > 0) {
            T--;
            input();
            solve();
        }
    }
}
