package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1094 {
    // https://www.acmicpc.net/problem/1094

    // 소요시간 >>
    // 1시간 (6까지 경우의 수를 그리느라 시간 소요가 컸다)

    // 아이디어 >>
    // f(n) = f(n-1) + f(n-2)와 같다.
    // f(1) = 1, f(2)=2, f(3)=3
    // 그래서, f(4) = f(2)+f(3) = 5
    // f(5) = f(3)+f(4) = 8 ...

    // 에러 로그 >>
    //  15746 나누는 걸 깜빡했다. (작은 수(6까지)에선 에러로 검출안되었어서 몰랐다)
    // 숫자가 엄청 커져서 int의 범위를 넘어설 거란 걸 생각 못했다 (자꾸 이를 간과하는 것 같다..)

    private static long[] dpline;

    private static long func(int indexNum) {
        if(dpline[indexNum]!=0) return dpline[indexNum];
        else {
            dpline[indexNum] = (func(indexNum-1)+func(indexNum-2))%15746;
            return dpline[indexNum];
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(reader.readLine());

        dpline = new long[n+1];
        if(n>=0) dpline[0]=0;
        if(n>=1) dpline[1]=1;
        if(n>=2) dpline[2]=2;
        if(n>=3) func(n);

        System.out.println(dpline[n]);
    }
}
