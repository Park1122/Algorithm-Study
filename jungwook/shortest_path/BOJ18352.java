package jungwook.shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ18352 {
    static class Node {
        int to, weight;
        public Node(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static class Info {
        int idx, dist;
        public Info(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }

    static int N, M, K, X;
    static int[] dist;
    static ArrayList<Node>[] adj;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        dist = new int[N+1];
        adj = new ArrayList[N+1];
        for(int i = 1; i<=N; i++) adj[i] = new ArrayList<>();
        for(int i = 0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adj[from].add(new Node(to, 1));
        }
    }

    static void dijkstra(int start) {
        for(int i = 1; i<=N; i++) dist[i] = Integer.MAX_VALUE;
        PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        pq.add(new Info(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Info info = pq.poll();

            if(dist[info.idx] < info.dist) continue;
            for(Node n : adj[info.idx]) {
                if(dist[info.idx] + n.weight >= dist[n.to]) continue;

                dist[n.to] = dist[info.idx] + n.weight;
                pq.add(new Info(n.to, dist[n.to]));
            }
        }
    }

    static void solve() {
        dijkstra(X);

        ArrayList<Integer> temp = new ArrayList<>();
        for(int i = 1; i<=N; i++) {
            if(dist[i] == K) temp.add(i);
        }

        if(temp.size() > 0) {
            for(int n : temp) sb.append(n).append('\n');
        }else {
            sb.append(-1);
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
