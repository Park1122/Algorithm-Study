package jungwook.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1541 {
    static String expression;
    static int ans;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        expression = br.readLine();
        ans = Integer.MAX_VALUE;
    }

    static void solve() {
        StringTokenizer subtraction = new StringTokenizer(expression, "-");
        while (subtraction.hasMoreTokens()) {
            StringTokenizer addition = new StringTokenizer(subtraction.nextToken(), "+");
            int temp = 0;
            while (addition.hasMoreTokens()) {
                temp += Integer.parseInt(addition.nextToken());
            }

            if (ans == Integer.MAX_VALUE) {
                ans = temp;
            } else {
                ans -= temp;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
