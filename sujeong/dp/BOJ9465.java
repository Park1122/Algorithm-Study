package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ9465 {
    // https://www.acmicpc.net/problem/9465

    // 소요시간 >>
    // 2시간 30분 (두줄의 배열을 한줄의 배열로 바꾸거나 90도 돌리는 등의 방식을 사용하다 시간을 지체함. -> 제대로된 값도 안나와서 에러로그도 없음....)

    // 아이디어 >>
    // top-down 방식을 사용.
    // dp[0][n]과 dp[1][n]중에 큰 값을 출력합니다.
    // i자리의 한 스티커를 뗌으로써 영향을 받는건 i-2~i+2까지입니다.
    // 만약 dp[0][i]를 뗸다면 이전에 뗄 수 있는 위치는 dp[1][i-1], dp[1][i-2], dp[0][i-2]정도가 됩니다.
    // 이때, dp[0][i-2]는 dp[1][i-1]을 뗸 경우에서 거쳐갈 수 있기에 이를 제외합니다.
    // 그러면 서로 대칭의 모습을 갖는 점화식을 만들어낼 수 있습니다.
    // dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2])+orgArr[0][i]; (내 대각선의 경우와 내 전값의 대각선의 경우에 내 값을 더함.)
    // dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2])+orgArr[1][i]; (내 대각선의 경우와 내 전값의 대각선의 경우에 내 값을 더함.)
    // 그렇게 가장 작은 경우까지 가면, 1인 경우를 최소한 지정을 해둬야합니다.
    // n이 1인 dp[0][1]과 dp[1][1]은 명확하게 orgArr[0][1]과 orgArr[1][1]과 같습니다.
    // n은 0인경우는 없지만 그 값 또한 없으므로 dp[0][0]과 dp[1][0]은 그대로 0을 유지하게 하면 됩니다.
    // 이를 코드화하여 구현한게 해당 문제 소스코드입니다.

    // 에러 로그 >>
    // X

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(reader.readLine());

        for(int loop=0;loop<T;loop++){
            // get N
            int N=Integer.parseInt(reader.readLine());

            // orgArr setting
            int[][] orgArr = new int[2][N+2];
            String[] strArr1 = reader.readLine().split(" ");
            for(int i=1;i<=N;i++) orgArr[0][i] = Integer.parseInt(strArr1[i-1]);
            String[] strArr2 = reader.readLine().split(" ");
            for(int i=1;i<=N;i++) orgArr[1][i] = Integer.parseInt(strArr2[i-1]);

            // dp setting
            int[][] dp = new int[2][N+1];
            dp[0][1]= orgArr[0][1];
            dp[1][1]= orgArr[1][1];
            for(int i=2;i<=N;i++){
                dp[0][i]=Math.max(dp[1][i-1],dp[1][i-2])+orgArr[0][i];
                dp[1][i]=Math.max(dp[0][i-1],dp[0][i-2])+orgArr[1][i];
            }

            // output
            System.out.println(Math.max(dp[0][N],dp[1][N]));

        }
    }
}
