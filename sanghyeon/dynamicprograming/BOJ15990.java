import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ15990 {

    public static void main(String[] args) throws Exception {
        long[][] dp = new long[100_001][4];
        dp[0] = new long[] {0, 0, 0, 0};
        dp[1] = new long[] {0, 1, 0, 0};
        dp[2] = new long[] {0, 0, 1, 0};
        dp[3] = new long[] {0, 1, 1, 1};
        for(int i = 4; i <= 100_000; i++) {
            dp[i][1] = (dp[i-1][2] + dp[i-1][3]) % 1_000_000_009;
            dp[i][2] = (dp[i-2][1] + dp[i-2][3]) % 1_000_000_009;
            dp[i][3] = (dp[i-3][1] + dp[i-3][2]) % 1_000_000_009;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
            int t = Integer.parseInt(br.readLine());
            System.out.println((dp[t][1] + dp[t][2] + dp[t][3]) % 1_000_000_009);
        }
    }
}
