package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2156 {
    // https://www.acmicpc.net/problem/2156

    // 소요시간 >>
    // 1시간 (보자마자 계단오르기 생각나서 어떻게 변형을 시키면 좋을지 고민하는데 대부분의 시간을 사용)

    // 아이디어 >>
    // 계단오르기랑 매우 유사함.
    // 계단오르기가 2개 연속이 안된다는 문제였다면, 얘는 3개연속이 안되는 문제 정도의 차이.
    // + 맨 끝을 안밟아도 됨. (오히려 이 점 덕에 더 쉬워짐.)
    // 규칙을 준수할 경우, f(n)은 두가지의 경우가 가능하다.
    // 1. dp[n]=f(n-2)+orgArr[n] --> 끊어졌다가 n이 연속의 1번인 경우
    // 2. dp[n]=f(n-3)+orgArr[n-1]+orgArr[n] (2개가 연속으로 이어지면 안된다를 n-3과 n-1의 경우로만 가능하다는 방식!
    // --> n-1이 연속1번, n이 연속 2번으로 연속의 마지막이 n인 경우.
    // 1과 2중 더 큰 값을 dp에 저장해나가는 방식으로 진행.

    // 에러 로그 >>
    // 틀렸습니다 -> n은 3인경우에서 1과 2의 포도주를 마시고 3번째 포도주를 안마시는 경우를 고려해야함을 까먹었음.
    // ArrayIndexOutOfBound -> dp[n+1]에서 최대 포도주 잔의 개수에 맞춰 dp[10001]로 변경 ==> 문제 없었음.
    // ArrayIndexOutOfBound ->  func(int index)에서 if(dp[index]!=0)이 값이 있다면 넣어라! 라는 의미로 쓰려고 한건데
    // 포도주가 담길수 있는 양 자체가 0인걸 수도 있다.
    // 죽, dp[index]의 값이 0일때의 원인이 비어있어서인지 실제 값이 0인지 구분할 수 없어 에러 발생.
    //
    // for 문으로 bottom-up 방식을 통해 값을 채울 때 사용했던 func 함수 >>
    //    private static int func(int index){
    //        if(dp[index]!=0) return dp[index]; // 이게 문제같음.
    //        dp[index]=Math.max(Math.max(func(index-2)+ orgArr[index], func(index-3)+orgArr[index-1]+orgArr[index]),dp[index-1]);
    //        return dp[index];
    //    }


    public static void main(String[] args) throws IOException {
        // input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(reader.readLine());
        int[] orgArr = new int[n + 1];
        for(int i=1;i<=n;i++) orgArr[i] = Integer.parseInt(reader.readLine());

        // setting - n-3까지 커버해야하니 3까지의 경우는 채워줘야함.
        // 하지만, 3의 경우가 실제 점화식과 별 다를게 없고, dp[i-3]은 dp[0]이므로 그 값 또한 비어있어 0이므로 문제되지 않음.
        int[] dp = new int[10001];
        dp[1]= orgArr[1];
        if(n>=2) dp[2]= orgArr[1]+ orgArr[2];
        // if(n>=3) dp[3]=Math.max(Math.max(orgArr[1]+ orgArr[3], orgArr[2]+ orgArr[3]),dp[2]);

        // func
        for(int i=3;i<=n;i++) {
            dp[i]=Math.max(Math.max(dp[i-2]+ orgArr[i], dp[i-3]+ orgArr[i-1]+ orgArr[i]), dp[i-1]);
        }

        // output
        System.out.println(dp[n]);
    }
}