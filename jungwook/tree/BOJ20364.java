package jungwook.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 이진 트리 -> 원하는 숫자에서 2씩 나누는 숫자들이 병아리의 이동 경로
 * 2씩 나누면서 해당 번호가 이미 점거한 땅인지 체크
 */
public class BOJ20364 {
    static int N, Q;
    static HashSet<Integer> landSet;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        landSet = new HashSet<>();
    }

    static void solve() throws IOException {
        for (int i = 1; i <= Q; i++) {
            int wantNum = Integer.parseInt(br.readLine());
            int temp = wantNum;
            int ans = 0;

            while (true) {
                if(temp == 0) break;

                if (landSet.contains(temp)) {
                    ans = temp;
                }
                temp/=2;
            }
            sb.append(ans).append('\n');

            if (ans == 0) {
                landSet.add(wantNum);
            }
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
