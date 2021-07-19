package jungwook.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3273 {
    static int n, x;
    static int[] nums;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        x = Integer.parseInt(br.readLine());
    }

    static boolean search(int[] A, int L, int R, int X) {
        while (L <= R) {
            int mid = (L + R) / 2;
            if (A[mid] == X) {
                return true;
            }

            if (A[mid] < X) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return false;
    }

    static void solve() {
        Arrays.sort(nums, 1, n + 1);

        int ans = 0;
        for (int i = 1; i <= n-1; i++) {
            //nums[i] 선택 -> i+1 ~ n에서 x-nums[i]가 있는지 탐색
            if (search(nums, i+1, n, x - nums[i])) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
