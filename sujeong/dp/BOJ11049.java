package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11049 {
    // https://www.acmicpc.net/problem/11049

    // 소요시간 >>
    // 4시간

    // 아이디어 >>
    // 구글의 힘을 빌렸다. 서너번 읽고나니 이해가 되었다.
    // 가장 큰 힘을 주신 곳은 이곳. (https://where-change-begins.tistory.com/2)
    // 나중에 개인적으로 다시 풀어봐야겠다.
    // 코드에 대한 설명은 코드에 그대로 녹여둠.

    // 에러 로그 >>
    //  X


    private static int[][] dp, orgArr;
    public static void main(String[] args) throws IOException{
        // input & initialize
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        orgArr = new int[n+1][2];
        for(int i=1;i<=n;i++) {
            String[] strArr = reader.readLine().split(" ");
            orgArr[i][0] = Integer.parseInt(strArr[0]);
            orgArr[i][1] = Integer.parseInt(strArr[1]);
        }

        // dp setting
        dp = new int[n+1][n+1];
        for(int i=1; i<n ; i++)
            // i번째 행렬과 바로 다음 행렬인 i+1의 곱은, i{a,b} i+1{b,c}에서 a*b*c 와 같다.
            dp[i][i+1] = orgArr[i][0] * orgArr[i][1] * orgArr[i+1][1];

        // Logic
        for(int gap=2;gap<n;gap++){ // 곱하는 행렬의 수
            for(int start=0;start+gap<=n;start++){ // 시작 인덱스
                int end=start+gap; // 끝 행렬 = 시작인덱스 + 곱하는 행렬 수
                for(int mid = start ; mid<end ; mid++){
                    // res는 새로운 dp[start][end]의 후보로, start~mid까지 곱하고 mid~end를 곱한 후 이 둘을 곱했을 때의 식임.
                    int res = dp[start][mid] + dp[mid+1][end] + orgArr[start][0]*orgArr[mid][1]*orgArr[end][1];
                    if(dp[start][end]==0 || dp[start][end] > res) { // 비어있거나 새로운 후보가 더 작으면,
                        dp[start][end] = res; // 후보를 dp값으로 셋팅.
                    }
                }
            }
        }

        // output
        System.out.println(dp[1][n]);

    }
}
