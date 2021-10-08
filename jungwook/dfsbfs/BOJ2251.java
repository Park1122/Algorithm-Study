package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2251 {
    // 물통의 현재 상태 및 물을 붓는 행위를 관리하는 객체
    static class State {
        int[] X;
        State(int[] _X) {
            X = new int[3];
            for (int i = 0; i < 3; i++) X[i] = _X[i];
        }

        // from 물통에서 to 물통으로 물을 옮김
        State move(int from, int to, int[] Limit) {
            int[] nX = new int[]{X[0], X[1], X[2]};
            if (X[from] + X[to] >= Limit[to]) { // 옮긴 후에 from에 물이 남는 경우
                nX[from] -= Limit[to] - X[to];
                nX[to] = Limit[to];
            } else { // 남지 않는 경우
                nX[to] += nX[from];
                nX[from] = 0;
            }
            return new State(nX);
        }
    }

    static int[] Limit; // 물통들의 크기
    static boolean[][][] visit; // 방문했던 물의 양인지 체크하는 용도
    static boolean[] possible; // C에 남을 수 있는 물의 양 인덱스는 true

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        Limit = new int[3];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) Limit[i] = Integer.parseInt(st.nextToken());
        visit = new boolean[205][205][205];
        possible = new boolean[205];
    }

    static void bfs(int x1, int x2, int x3) {
        Queue<State> q = new LinkedList<>();
        visit[x1][x2][x3] = true;
        q.add(new State(new int[]{x1, x2, x3}));

        while (!q.isEmpty()) {
            State state = q.poll();
            //정답 조건에 맞는 경우 possible 배열에 체크
            if(state.X[0] == 0) possible[state.X[2]] = true;

            // state에서 A, B, C 물병의 물을 옮기는 모든 경우 탐색
            for (int from = 0; from < 3; from++) {
                for (int to = 0; to < 3; to++) {
                    // 안옮겨도 되는 경우
                    if(from == to) continue;
                    // 옮긴 후의 상태
                    State nxt = state.move(from, to, Limit);

                    // 옮긴 후의 상태가 방문 했던 상태인지 체크
                    if (!visit[nxt.X[0]][nxt.X[1]][nxt.X[2]]) {
                        visit[nxt.X[0]][nxt.X[1]][nxt.X[2]] = true;
                        q.add(nxt);
                    }
                }
            }
        }
    }

    static void solve() {
        bfs(0, 0, Limit[2]);

        for (int i = 0; i <= Limit[2]; i++) {
            if (possible[i]) {
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
