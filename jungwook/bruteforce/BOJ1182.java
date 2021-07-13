package jungwook.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1182 {
    static int N, S, ans;
    static int[] nums;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        nums = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void recFunc(int k, int value) {
        if (k == N + 1) {
            if(S == value) ans++;
        } else {
            // include
            recFunc(k + 1, value + nums[k]);
            // not include
            recFunc(k + 1, value);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        recFunc(1, 0);
        if (S == 0) {
            ans--;
        }
        System.out.println(ans);
    }
}
