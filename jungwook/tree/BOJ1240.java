package jungwook.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 해당 문제는 3가지 풀이가 존재
 * 1. 트리도 일종의 그래프이므로 최단거리 알고리즘인 다익스트라 사용
 * 2. 트리에서 두 노드 사이의 경로는 유일한 점을 활용해서 BFS 사용
 * 3. 트리를 Rooted 트리로 변환해서 구현이 짧은 DFS를 통해 거리 계산
 *  마찬가지로, 두 노드 사이의 경로가 유일함을 확인
 *
 * -> 3번 풀이 이용
 */
public class BOJ1240 {
    static class Edge {
        int y;
        int dist;
        public Edge(int y, int dist) {
            this.y = y;
            this.dist = dist;
        }
    }

    static int N, M, ans;
    static ArrayList<Edge>[] con;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        con = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            con[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            con[x].add(new Edge(y, dist));
            con[y].add(new Edge(x, dist));
        }
    }

    // 현재 x 에 있으며, 부모 노드는 prev 이며, 목표 지점은 goal,
    // 그리고 root부터 지금까지 이동 거리가 dist 이다.
    static void dfs(int x, int prev, int goal, int dist) {
        if (x == goal) {
            ans = dist;
            return;
        }

        for (Edge e : con[x]) {
            if(e.y == prev) continue;
            dfs(e.y, x, goal, dist + e.dist);
        }
    }

    static void solve() throws IOException {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        dfs(x, -1, y, 0);
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        while (M-- > 0) {
            solve();
        }
    }
}
