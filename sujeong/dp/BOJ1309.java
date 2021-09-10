package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1309 {
    // https://www.acmicpc.net/problem/1309

    // 소요시간 >>
    // 4시간 30분
    // (그림그리고 규칙을 찾아 나누느라 시간의 80퍼센트 사용하였다.
    // 비록 이는 너무 거시적이라서 i번째 줄만의 초점을 두는 것과 거리가 멀어서 사용하지 못함.)

    // 아이디어 >>
    // 2개의 col에 n개의 row의 형태인 우리가 있다.
    // 1개의 row만 떼어서 봤을떈, x x / x o / o x로 존재할 수 있음.(o o는 붙어있는거라 성립 불가)
    // x x를 dp[][0] o x를 dp[][1] x o를 dp[][2]로 설정함.
    // 줄이 1개일 때는 x x, x o, o x로 dp[1][0],dp[1][1],dp[1][2]는 각각 1이다.
    // i번째 줄에 사자가 없다면 이전 줄은 x x, o x, x o
    // i번째 줄에 사자가 왼쪽에만 있다면(o x라면) 이전 줄은 x x, x o
    // i번째 줄에 사자가 오른쪽에만 있다면(x o라면) 이전 줄은 x x, o x


    // 에러 로그 >>
    // X

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // get N
        int N=Integer.parseInt(reader.readLine());

        // dp setting
        int[][] dp = new int[N+1][3]; // dp[n줄][경우의 수]
        dp[1][0]=1;
        dp[1][1]=1;
        dp[1][2]=1;

        // logic
        for(int i=2;i<=N;i++){
            dp[i][0]=(dp[i-1][0]+dp[i-1][1] + dp[i-1][2])%9901; // i번째 줄에 사자가 없다면 이전 줄은 x x, o x, x o
            dp[i][1]=(dp[i-1][0] + dp[i-1][2])%9901; // i번째 줄에 사자가 왼쪽에만 있다면(o x라면) 이전 줄은 x x, x o
            dp[i][2]=(dp[i-1][0] + dp[i-1][1])%9901; // i번째 줄에 사자가 오른쪽에만 있다면(x o라면) 이전 줄은 x x, o x
        }

        // output
        // n번째 줄까지 가능한 경우의 수 = n번쨰 줄이 o o인 경우 o x인 경우 x o인 경우를 모두 더함.
        System.out.println((dp[N][0]+dp[N][1]+dp[N][2])%9901);
    }
}
