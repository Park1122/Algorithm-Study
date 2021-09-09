package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BOJ2688 {
    // https://www.acmicpc.net/problem/2688

    // 소요시간 >>
    // 20분 (11057과 매우 유사하여 조금만 변경해 해결)

    // 아이디어 >>
    // 11057과 매우 비슷함.
    // 11057에 반복가능하게 조정하는 것 + 10007의 나머지 부분 빼기 + 10007나누는거 제외하면서 필요해진 범위 변환(long으로 타입 변환)을 수행하면 되었음.

    // 에러 로그 >>
    // 틀렸습니다 : n에 최대값인 64를 넣으면 int로는 감당 못함. long으로 계산하는 곳과 dp의 타입을 바꿔서 해결


    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(reader.readLine());
        for(int cycle=0;cycle<T;cycle++) {
            int n = Integer.parseInt(reader.readLine());

            // initialize
            dp = new long[n + 1][10];
            for (int i = 0; i <= 9; i++) dp[1][i] = 1; // n=1일때의 값을 1로 미리 초기화해둠.

            // logic
            for (int i = 2; i <= n; i++) {
                for (int j = 0; j <= 9; j++) {
                    for (int k = 0; k <= j; k++) {
                        // i자리 수의 맨앞자리가 j인 경우의 개수 = i-1자리 수의 맨 앞자리가 0인 경우부터 j인 경우까지의 합
                        dp[i][j] = calcSum(i - 1, k);
                    }
                }
            }

            // output
            System.out.println(calcSum(n, 9)); // dp[n][0]~dp[n][9]의 합
        }
    }

    private static long calcSum(int i,int loop){
        long ans = 0;
        for(int k=0;k<=loop;k++) ans+=dp[i][k];
        return ans;
    }
}
