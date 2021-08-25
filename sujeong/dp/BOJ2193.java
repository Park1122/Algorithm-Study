package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ2193 {
    // https://www.acmicpc.net/problem/2193

    // 소요시간 >>
    // 2시간 (규칙을 발견하는 과정이 오래 걸렸다.)

    // 아이디어 >>
    // n=1->1 , n=2->1, n=3->2, n=4->3, n=5->5, n=6->8
    // 여기서 알 수 있듯이 f(n)=f(n-1)+f(n-2)의 형태를 갖는다.
    // ex. n=5의 경우는 n=3의 경우(101, 100)에 01을 붙인 10101, 10001과,
    //     n=4의 경우(1001,1010,1000)에 0을 붙인 10010,10100,10000을 가진다.

    // 에러 로그 >>
    // 틀렸습니다. - int타입을 사용하여 값의 범위가 초과되 에러가 발생했다. -> long으로 변경하여 해결

    private static long[] dp;

    private static long func(int index){
        if(dp[index]!=0) return dp[index];

        dp[index]=func(index-2)+func(index-1);
        return dp[index];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(reader.readLine());

        dp = new long[n+1];
        dp[1]=1;
        if(n>1) dp[2]=1;
        System.out.println(func(n));
    }
}