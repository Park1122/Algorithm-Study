package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2579 {
    // https://www.acmicpc.net/problem/2579

    // 소요시간 >>


    // 아이디어 >>
    // 규칙을 준수할 경우, f(n)은 두가지의 경우가 가능하다.
    // 1. dp[n]=f(n-2)+orgArr[n]
    // 2. dp[n]=f(n-3)+orgArr[n-1]+orgArr[n] (2개가 연속으로 이어지면 안된다를 n-3과 n-1의 경우로만 가능하다는 방식!
    // 1과 2중 더 큰 값을 dp에 저장해나가는 방식으로 진행.

    // 에러 로그 >>
    // ArrayIndexOutOfBounds : n이 3보다 작을경우 setting 부분에서 dp[3]을 설정하며 오류가 생길 수 있음을 간과함.

    private static int[] dp;
    private static int[] orgArr;

    private static int func(int index){
        if(dp[index]!=0) return dp[index];
        dp[index]=Math.max(func(index-2)+ orgArr[index], func(index-3)+orgArr[index-1]+orgArr[index]);
        return dp[index];
    }

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(reader.readLine());
        orgArr =new int[n+1];
        for(int i=1;i<=n;i++) orgArr[i] = Integer.parseInt(reader.readLine());

        // setting
        dp = new int[n+1];
        dp[1]= orgArr[1];
        if(n>=2) dp[2]= orgArr[1]+ orgArr[2];
        if(n>=3) dp[3]=Math.max(orgArr[1]+ orgArr[3], orgArr[2]+ orgArr[3]);

        // func
        for(int i=4;i<=n;i++) func(i);

        // output
        System.out.println(dp[n]);
    }
}