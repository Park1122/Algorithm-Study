package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1912 {
    static int N, max;
    static int[] nums, Dy;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        nums = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void preprocess() {
        Dy = new int[N + 1];

        Dy[1] = nums[1];
        max = nums[1];

        for (int i = 2; i <= N; i++) {
            Dy[i] = Math.max(Dy[i - 1] + nums[i], nums[i]);
            max = Math.max(max, Dy[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        preprocess();
        System.out.println(max);
    }
}
