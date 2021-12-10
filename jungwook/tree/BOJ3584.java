package jungwook.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3584 {
    static int N;
    static int[] par, check;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        par = new int[N + 1];
        check = new int[N + 1];
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            par[b] = a;
        }
    }

    static void solve() throws IOException {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        // x에서 루트까지 이동하면서 조상에 체크
        while (x > 0) {
            check[x] = 1;
            x = par[x];
        }

        // y에서 루트로 이동하면서 처음으로 만나는 체크된 정점 찾기
        while (y > 0 && check[y] == 0) {
            y = par[y];
        }

        // 출력
        System.out.println(y);
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tt = 0; tt < T; tt++) {
            input();
            solve();
        }
    }
}
