package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class BOJ1912 {
    // https://www.acmicpc.net/problem/1912

    // 소요시간 >>
    // 1시간 (max와 dp[i]의 구분을 찾느라 대부분의 시간을 씀. 점화식엔 얼마 시간이 안들었다.)

    // 아이디어 >>
    // dp[n]은 arr[n]이 들어간 경우중 가장 큰 경우를 의미함.
    // dp[n]에 들어갈 수 있는건 연속으로 dp[n-1]+arr[n]이 될 수도 있고,
    // 단순히 arr[n]이 연속된 경우를 이길만큼 크다면 arr[n]이 될 수도 있다.
    // dp[n]의 점화식 =>  Math.max( dp[n-1]+arr[n], arr[n] )
    // max는 이전까지의 최대값과 새로운 최대값을 비교하는게 기본 방식인데,
    // 여기서는 dp[n]이 새것이라면 max는 이전의 max를 의미함.
    // 그렇다면 max의 초기값은? dp[1]이 arr[1]밖에 안되듯, max도 1일때에는 arr[1]밖에 안됨.
    // max의 점화식 => Math.max(max, dp[n])

    // 에러 로그 >>
    // 자체 테스트에서 fail => dp[i]의 값과 해당 배열의 max값이 다를 수 있음을 간과함.

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(reader.readLine());

        int[] arr=new int[n+1];
        String[] strArr = reader.readLine().split(" ");
        for(int i = 1;i<=n;i++) arr[i]= Integer.parseInt(strArr[i-1]);

        int[] dp = new int[n + 1];
        dp[1]=arr[1];
        int max= arr[1];

        for(int i=2;i<=n;i++){
            dp[i]=Math.max(dp[i-1]+arr[i],arr[i]); // dp[i]는 dp[i-1]에 자신을 더한 것이 되거나 자신만 들어가는 경우가 가능.
            max= Math.max(max, dp[i]); // 이전 최대값과 dp[i]를 비교해서 최대값을 산출
        }

        System.out.println(max);
    }


}
