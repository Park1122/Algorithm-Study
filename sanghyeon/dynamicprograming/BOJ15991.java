import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ15991 {

    public static void main(String[] args) throws Exception {
        long[] dp = new long[100_001];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i = 4; i <= 100_000; i++) dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % 1_000_000_009;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
            int t = Integer.parseInt(br.readLine());
            long sum=0;
            for(int j=0; j<4; j++) if((t-j)%2==0 && t-j>=0) sum += dp[(t-j)/2];
            System.out.println(sum % 1_000_000_009);
        }
    }
}
