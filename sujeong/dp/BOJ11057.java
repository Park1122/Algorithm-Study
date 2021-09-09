package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11057 {
    // https://www.acmicpc.net/problem/11057

    // 소요시간 >>
    // 1시간

    // 아이디어 >>
    // i자리 수의 맨앞자리가 j인 경우의 개수 = i-1자리 수의 맨 앞자리가 0인 경우부터 j인 경우까지의 합

    // 에러 로그 >>
    // 틀렸습니다 : 10007로 나누기를 깜빡함.
    // 틀렸습니다 : 숫자 범위 오류 (dp를 int에서 long으로 변경)
    // 틀렸습니다 : 빼나가는 방식에서 더해나가는 방식으로 변경

    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(reader.readLine());

        // initialize
        dp = new long[n+1][10];
        for (int i=0;i<=9;i++) dp[1][i] = 1; // n=1일때의 값을 1로 미리 초기화해둠.

        // logic
        for(int i=2;i<=n;i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k=0;k<=j;k++) {
                    // i자리 수의 맨앞자리가 j인 경우의 개수 = i-1자리 수의 맨 앞자리가 0인 경우부터 j인 경우까지의 합
                    dp[i][j] = calcSum(i-1,k)%10007;
                }
            }
        }

        // output
        System.out.println(calcSum(n,9)%10007); // dp[n][0]~dp[n][9]의 합
    }

    private static long calcSum(int i,int loop){
        int ans = 0;
        for(int k=0;k<=loop;k++) ans+=dp[i][k];
        return ans;
    }
}
