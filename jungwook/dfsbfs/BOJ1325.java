package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * dfs: 시간초과
 * bfs: 간신히 시간 통과
 * 어느 노드가 가장 해킹을 많이 하는가? -> 방문 횟수가 가장 많은 노드 찾기
 * 선의 방향을, 처음 생각한 y -> x가 아닌 x -> y인 단방향 그래프로 생각
 * 풀이 참고: https://c-king.tistory.com/122
 */
public class BOJ1325 {
    static int N, M;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    static int[] rank;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y); // x -> y 방향의 그래프로 생성
        }
        visit = new boolean[N + 1];
        rank = new int[N + 1];
    }

    static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visit[start] = true;

        while (!q.isEmpty()) {
            int x = q.poll();
            for (int y : graph[x]) {
                if(visit[y]) continue;
                visit[y] = true;
                rank[y]++;
                q.add(y);
            }
        }
    }

    static void solve() {
        // bfs
        for (int i = 1; i <= N; i++) {
            visit = new boolean[N + 1];
            bfs(i);
        }

        // 최대값 계산
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, rank[i]);
        }

        // 출력
        for (int i = 1; i <= N; i++) {
            if (rank[i] == max) {
                sb.append(i).append(' ');
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
