package sujeong.dp;

import java.io.*;

public class BOJ5557 {
    // https://www.acmicpc.net/problem/5557

    // 소요시간 >>
    // 2시간

    // 아이디어 >>
    // DFS의 방식이 먼저 떠올라서 해봤지만 시간초과가 발생하여 기록을 하고 이를 사용하는 걸 고려하여 dp를 만들었다.
    // row는 입력받은 숫자의 인덱스(예시1번인 8 3 2 4 8 7 2 4 0 8 8에서 각각을의 인덱스 ex. row가 2면 2를 말함)를 말하고,
    // col은 0~20 사이의 값으로 나올 계산되어 나올 수 있는 경우의 수를 말한다.
    //
    // 예시 1번은 다음과 같았다.
    // 11
    // 8 3 2 4 8 7 2 4 0 8 8
    //
    // 이해를 돕고자 이를 기준으로 설명하면 다음과 같다.
    // dp[1][orgArr[1]]은 첫번쨰 숫자가 나오는 경우는 자신 뿐이므로 1임.(예시 1번에서는 8 자신만 존재하기 때문에 dp setting에서 1로 만듦)
    // dp[2][]에서는 8에서 2번째 값인 3을 더하거나 뺐을 때인 dp[2][5]와 dp[2][11]에 dp[1][8]의 값인 1이 더해짐. --> dp[2][5]와 dp[2][11]은 각각 1이됨.
    // dp[3][]에서는
    //  // 5에서 3번째 값인 2를 더하거나 뺐을 때인 dp[3][3], dp[3][7]에 dp[2][5]의 값인 1이 더해지고,
    //  // 11에서 3번째 값인 2를 더하거나 뺐을 때인 dp[3][9], dp[3][13]에  dp[2][11]의 값인 1이 더해진다.
    // dp[4][]에서는
    //  // 3에서 4번째 값인 4를 더하거나 뻈을 떄인 dp[4][-1], dp[4][7]에 dp[3][3]의 값인 1이 더해져야 한다.
    //  //  // 하지만 -1은 범위를 벗어나므로 제외되고 dp[4][7]에만 dp[3][3]의 값인 1이 더해진다.
    //  // 7에서 4번째 값인 4를 더하거나 뻈을 떄인 dp[4][3], dp[4][11]에 dp[3][7]의 값인 1이 더해져야 한다.
    //  // 9에서 4번째 값인 4를 더하거나 뻈을 떄인 dp[4][5], dp[4][13]에 dp[3][9]의 값인 1이 더해져야 한다.
    //  // 13에서 4번째 값인 4를 더하거나 뻈을 떄인 dp[4][9], dp[4][17]에 dp[3][13]의 값인 1이 더해져야 한다.
    // 위과 같은 방식으로 계속하여 더해 답을 도출한다.

    // 에러 로그 >>
    // X

    private static long count=0;
    private static int n;
    public static void main(String[] args) throws IOException {
        // input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(reader.readLine());
        String[] strArr = reader.readLine().split(" ");

        int[] orgArr = new int[n+1];
        for (int i=1;i<=n;i++) orgArr[i]=Integer.parseInt(strArr[i-1]);

        // dp setting
        long[][] dp = new long[n+1][21]; // 인덱스에서의 값에 떄라 0~20에 그 경우의 수를 넣어줌.
        dp[1][orgArr[1]]=1;

        // logic
        for(int row=2;row<n;row++){
            for(int col=0;col<=20;col++){

                if(dp[row-1][col]!=0){
                    int plus = col+orgArr[row];
                    if(plus<=20) dp[row][plus]+=dp[row-1][col];

                    int minus = col-orgArr[row];
                    if(minus>=0) dp[row][minus]+=dp[row-1][col];
                }

            }
        }

        // output
        System.out.println(dp[n-1][orgArr[n]]);

    }
}
