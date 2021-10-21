package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1389 {
    static int N, M;
    static boolean[] visit;
    static int[] dist;
    static int[][] kb;
    static ArrayList<Integer>[] graph;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            graph[y].add(x);
        }
        kb = new int[N + 1][2]; // kb[i][0]: i번의 번호, kb[i][1]: i번의 케빈베이컨 값
        for (int i = 1; i <= N; i++) {
            kb[i][0] = i;
            kb[i][1] = 0;
        }
    }

    // 본인 제외 다른 학생들과의 단계 구하기
    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;
        dist[start] = 0;

        while (!q.isEmpty()) {
            int x = q.poll();
            for (int y : graph[x]) {
                if(visit[y]) continue;
                q.add(y);
                visit[y] = true;
                dist[y] = dist[x] + 1;
            }
        }
    }

    // 케빈 베이컨 수 구하기
    static void calculateKB(int index) {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            if(i == index) continue;
            sum += dist[i];
        }
        kb[index][1] = sum;
    }

    static void solve() {
        for (int i = 1; i <= N; i++) {
            visit = new boolean[N + 1];
            dist = new int[N + 1];
            bfs(i);
            calculateKB(i);
        }

        // 구해진 케빈 베이컨 수 오름차순 정렬
        Arrays.sort(kb, 1, N + 1, (o1, o2) -> {
            if (o1[1] != o2[1]) {
                return o1[1] - o2[1];
            } else {
                return o1[0] - o2[0];
            }
        });

        // 케빈 베이컨 수가 가장 작은 학생의 기존 인덱스 번호 출력
        System.out.println(kb[1][0]);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
