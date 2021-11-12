package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ18513 {
    static class House {
        int x;
        int dist;
        public House(int x, int dist) {
            this.x = x;
            this.dist = dist;
        }
    }

    static int N, K;
    static long ans;
    static HashSet<Integer> visit;
    static Queue<House> q;
    static int[] dir = {1, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visit = new HashSet<>();
        q = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            visit.add(x);
            q.add(new House(x, 0));
        }
    }

    static void bfs() {
        boolean isEnd = false;
        int cnt = 0;
        while (!q.isEmpty()) {
            House now = q.poll();
            for (int k = 0; k < 2; k++) {
                int nx = now.x + dir[k];
                // 이미 방문한 점
                if(visit.contains(nx)) continue;
                // 집 추가
                cnt++;
                ans += now.dist + 1;
                // 집을 다 지었으면 끝내기
                if (cnt == K) {
                    isEnd = true;
                    break;
                }

                visit.add(nx);
                q.add(new House(nx, now.dist + 1));
            }
            if (isEnd) {
                break;
            }
        }
    }

    static void solve() {
        // 불행도 계산
        bfs();
        //출력
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
