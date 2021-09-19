package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1260 {
    static int N, M, V;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void dfs(int x) {
        visited[x] = true;
        sb.append(x).append(' ');

        for(int y: graph[x]) {
            if(visited[y]) continue;
            dfs(y);
        }
    }

    static void bfs(int x) {
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        visited[x] = true;

        while(!q.isEmpty()) {
            int node = q.poll();

            sb.append(node).append(' ');
            for(int y: graph[node]) {
                if(visited[y]) continue;
                q.add(y);
                visited[y] = true;
            }
        }
    }

    static void input() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        for(int i = 1; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i = 1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            graph[y].add(x);
        }
        for(int i = 1; i<=N; i++) {
            Collections.sort(graph[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        //dfs
        visited = new boolean[N+1];
        dfs(V);
        sb.append('\n');

        //bfs
        for(int i = 1; i<=N; i++) visited[i] = false;
        bfs(V);

        //print
        System.out.println(sb);
    }
}
