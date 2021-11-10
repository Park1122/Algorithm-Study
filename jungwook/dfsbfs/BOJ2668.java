package jungwook.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 풀이: https://moonsbeen.tistory.com/176
 * 참고: https://yongku.tistory.com/entry/%EB%B0%B1%EC%A4%80-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EB%B0%B1%EC%A4%80-2668%EB%B2%88-%EC%88%AB%EC%9E%90%EA%B3%A0%EB%A5%B4%EA%B8%B0-%EC%9E%90%EB%B0%94Java
 */
public class BOJ2668 {
    static int N, temp;
    static int[] a;
    static boolean[] visit;
    static ArrayList<Integer> nums;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        a = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }
        nums = new ArrayList<>();
        visit = new boolean[N + 1];
    }

    static void dfs(int k) {
        if(a[k] == temp) nums.add(temp);

        if (!visit[a[k]]) {
            visit[a[k]] = true;
            dfs(a[k]);
            visit[a[k]] = false;
        }
    }

    static void solve() {
        for (int i = 1; i <= N; i++) {
            visit[i] = true;
            temp = i;
            dfs(i);
            visit[i] = false;
        }

        Collections.sort(nums);
        System.out.println(nums.size());
        for (int n : nums) {
            sb.append(n).append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
