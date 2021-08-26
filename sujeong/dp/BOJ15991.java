package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15991 {
    // https://www.acmicpc.net/problem/15991

    // 소요시간 >>
    // 1시간

    // 아이디어 >>
    // 대칭이라는 건 f(x)=1+f(x-2)+1, 2+f(x-4)+2, 3+f(x-6)+3 으로 f를 진행해나가면 무조건 성립한다.
    // 단, 1~6사이면 이 식에서 음수가 나옴 -> 6까지 값을 계산해주자!

    // 에러 로그 >>
    // 1) 1000000009 나누는 걸 깜빡함
    // 2) 나누는 값이 10억이니 애매한 int보단 long으로 바꾸자.


    private static long[] dpline;
    private static int targetNum;

    private static long func(int indexNum){
        if (dpline[indexNum] != 0) return dpline[indexNum];
        else {
            // f(n) = f(n-2)+f(n-4)+f(n-6) (단, n>=7)
            dpline[indexNum]=(func(indexNum-2)+func(indexNum-4)+func(indexNum-6))%1000000009;
            return dpline[indexNum];
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(reader.readLine());

        dpline = new long[100001]; //숫자로 인덱스를 쓰려고 함.
        dpline[1]=1; // 1
        dpline[2]=2; // 1+1 , 2
        dpline[3]=2; // 1+1+1, 3
        dpline[4]=3; // 1+1+1+1, 1+2+1, 2+2
        dpline[5]=3; // 1+1+1+1+1, 1+3+1, 2+1+2
        dpline[6]=6; // 1+1+1+1+1+1, 1+1+2+1+1, 1+4+1, 2+1+1+2, 2+2+2, 3+3

        for (int i=0;i<n;i++){
            targetNum=Integer.parseInt(reader.readLine());
            System.out.println(func(targetNum));
        }
    }


}
