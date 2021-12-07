package jungwook.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9489 {
    static int N, K;
    static int[] a, par;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        a = new int[N + 1];
        par = new int[N + 1];
        // NPE 방지를 위한 조건문 추가
        if (N != 0 && K != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        // 각 노드의 부모 노드 인덱스를 계산하자. 루트 노드가 1번 노드임을 주의
        // 편의상 0번 정점의 부모를 -1 로 하자.
        par[0] = -1;
        par[1] = 0;

        // 각 노드들의 부모 인덱스 구하기
        // last := 이번에 자식 정점들을 찾을 노드의 인덱스
        int last = 1;
        for (int i = 2; i <= N; i++) {
            for (; i <= N; i++) { // i번부터 연속한 수를 가진 정점을 모두 last 의 자식으로 묶는다.
                par[i] = last;
                if (i < N && a[i] + 1 != a[i + 1]) {
                    break;
                }
            }
            last++;
        }

        // K인 정점의 인덱스 값 구하기
        int kIdx = 0; // K인 정점의 인덱스
        for (int i = 1; i <= N; i++) {
            if(a[i] == K) kIdx = i;
        }

        // 사촌구하기
        // 사촌 -> 부모는 다르나 부모의 부모는 같은 것
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (par[kIdx] != par[i] && par[par[kIdx]] == par[par[i]]) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            input();
            if(N == 0 && K == 0) break;
            solve();
        }
    }
}
