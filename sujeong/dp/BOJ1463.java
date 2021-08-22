package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1463 {
    // https://www.acmicpc.net/problem/1463

    // 소요시간 >>
    // 50분

    // 아이디어 >>
    // - 최소 연산이 목표기 때문에 3으로 나누고 2로 나누고 1을 빼는 방식을 사용
    // - 각 3,2,1방식의 연산을 한 결과가 1이될때까지 func를 호출하면서 더해나가자
    // - 3,2,1방식중 가장 작은값을 dp에 저장해서 n을 키워가면 될 듯하다!

    // 에러 로그 >>
    // 런타임에러 (ArrayIndexOutOfBounds) -> n의 범위 잘못 설정함.

    private static int[] dp;

    private static int func(int n){
        if(n==1||dp[n]!=0) return dp[n];

        int threeVal=n;
        if(n%3==0) threeVal = 1+func(n/3);

        int twoVal=n;
        if(n%2==0) twoVal = 1+func(n/2);

        int oneVal=1+func(n-1);

        dp[n]=Math.min(Math.min(threeVal,twoVal),oneVal);
        return dp[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(reader.readLine());

        dp = new int[n+1];
        dp[1]=0;

        System.out.println(func(n));
    }


}
