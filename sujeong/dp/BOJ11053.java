package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ11053 {
    // https://www.acmicpc.net/problem/11053

    // 소요시간 >>
    // 5시간 (안되는 경우부터 생각했더니 고려할 점이 많아져 시간을 많이 허비했다.

    // 아이디어 >>
    // * 최소 자신은 꼭 포함되니 모든 경우의 초기값은 1로 셋팅.
    // * 꼭 포함해야하는 원소의 값(=기준값)과
    //    맨처음 원소부터 꼭 포함해야하는 원소이자 해당 부분집합의 마지막 원소 바로 앞까지 중 한 값(=비교값)을 이용하여,
    //    증가해야하니 비교값 < 기준값이어야하고,
    //    (=>orgArr[i]<orgArr[index]에만 dp값에 변화를 만든다.)
    // * 비교값의 길이가 가장 클 수록 기준값의 길이가 더 커질 것이다
    //    = 비교값의 길이들 중 가장 큰값을 찾아야한다
    //    = 비교값 중 길이가 가장 큰 값의 경우 + orgArr[기준인덱스]가 가장 길이가 큰 경우의 모습이다.
    //    => dp[index]{기준값)보다 dp[i](비교값)의 길이가 더 크다면 dp[index]=dp[i]+1(기준값은 가장 큰 비교값의 길이 + 1)
    // * 마지막 원소를 포함한 경우가 꼭 제일 클거란 보장 없으니까 결과 출력 전에 sorting

    // 에러 로그 >>
    // 틀렸습니다.
    // - 부등호 방향, 식 실수
    // - 마지막 원소가 가장클거라고 착각함.

    public static void main(String[] args) throws IOException {
        // input & initialize
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(reader.readLine());

        String[] strArr = reader.readLine().split(" ");
        int[] orgArr = new int[n+1];
        for (int i = 1; i <= n ; i++) {orgArr[i]=Integer.parseInt(strArr[i-1]);}

        int[] dp = new int[n+1];

        // function
        for(int index=1;index<=n;index++){
            dp[index]=1;
            for(int i=1;i<index;i++){
                dp[index] = (orgArr[i]<orgArr[index] && dp[index]<=dp[i]) ? dp[i]+1:dp[index];
            }
        }

        // output
        Arrays.sort(dp);
        System.out.println(dp[n]);

    }
}