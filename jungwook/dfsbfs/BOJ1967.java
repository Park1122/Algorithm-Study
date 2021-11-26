package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1967 {
    static class Node {
        int idx;
        int weight;

        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }

    static int N, maxIdx, max;
    static ArrayList<Node>[] graph;
    static boolean[] visit;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[parent].add(new Node(child, weight));
            graph[child].add(new Node(parent, weight));
        }
    }

    static void dfs(int nodeNum, int weight) {
        visit[nodeNum] = true;

        if (max < weight) {
            max = weight;
            maxIdx = nodeNum;
        }

        for (Node node : graph[nodeNum]) {
            if(visit[node.idx]) continue;
            dfs(node.idx, weight + node.weight);
        }
    }

    static void solve() {
        // n = 1인 경우, 0이 출력
        if (N > 1) {
            // 루트 노드에서 가장 누적 가중치가 큰 노드의 번호 구하기
            visit = new boolean[N + 1];
            dfs(1, 0);

            // 위에서 구한 노드에서 가장 누적 가중치가 큰 노드의 번호까지가 트리의 지름
            visit = new boolean[N + 1];
            dfs(maxIdx, 0);

            // 출력
            System.out.println(max);
        } else {
            System.out.println(0);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
