package jungwook.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * subtree로 쪼개가면서 역으로 단말노드의 개수를 더해 root까지 구할 것
 * dp와 매우 유사
 */
public class BOJ1068 {
    static int N, root, erased;
    static ArrayList<Integer>[] child;
    static int[] leaf;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        child = new ArrayList[N];
        leaf = new int[N];
        for (int i = 0; i < N; i++) {
            child[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int par = Integer.parseInt(st.nextToken());
            if (par == -1) {
                root = i;
                continue;
            }
            child[par].add(i);
        }
        erased = Integer.parseInt(br.readLine());
    }

    // dfs(x) := Subtree(x) 의 leaf 개수를 세주는 함수
    static void dfs(int x) {
        if (child[x].isEmpty()) {
            leaf[x] = 1;
        }
        for (int y : child[x]) {
            dfs(y);
            leaf[x] += leaf[y];
        }
    }

    static void solve() {
        // erased와 그의 부모 사이의 연결을 끊어주기
        for (int i = 0; i < N; i++) {
            if (child[i].contains(erased)) {
                child[i].remove(child[i].indexOf(erased));
            }
        }

        // erased 가 root 인 예외 처리하기
        if (root != erased) {
            dfs(root);
        }

        // 정답 출력하기
        System.out.println(leaf[root]);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
