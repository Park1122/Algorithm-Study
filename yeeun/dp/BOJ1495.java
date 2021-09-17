package codingstudy202107.w11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 11주차
 * 문제 출처 : https://www.acmicpc.net/problem/1495
 * 이름 : 기타리스트
 * 사용 알고리즘 : 다이나믹 프로그래밍
 * 몇 가지 경우의 수인지는 체크하지 않으므로, 이 숫자가 나올 수 있다 없다만 저장하는 boolean으로 된 이차원 배열을 사용, 기억
 */
public class BOJ1495 {

    public static void main(String[] args) throws IOException {
        BOJ1495 main = new BOJ1495();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(reader.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.print(main.solution(n, s, m, arr));
    }

    private int solution(int n, int s, int m, int[] arr) {
        boolean dp[][] = new boolean[n][m + 1];

        if (this.addIfAvailable(dp, m, 0, s, arr[0])) {
            return -1;
        }

        for (int i = 1; i < n; i++) {
            int target = arr[i];
            boolean cannot = true;
            for (int j = 0; j <= m; j++) {
                if (dp[i - 1][j]) {
                    boolean flag = this.addIfAvailable(dp, m, i, j, target);
                    if (cannot) cannot = flag;
                }
            }
            if (cannot) return -1;
        }

        for (int i = m; i >= 0; i--) {
            if (dp[n - 1][i]) return i;
        }
        return -1;
    }


    private boolean addIfAvailable(boolean[][] dp, int max, int row, int startVol, int difVol) {
        boolean cannot = true;
        if (startVol + difVol >= 0 && startVol + difVol <= max) {
            dp[row][startVol + difVol] = true;
            if (cannot) cannot = false;
        }
        if (startVol - difVol >= 0 && startVol - difVol <= max) {
            dp[row][startVol - difVol] = true;
            if (cannot) cannot = false;
        }
        return cannot;
    }

}
