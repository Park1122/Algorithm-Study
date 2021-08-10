package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11726 {
    // https://www.acmicpc.net/problem/11726

    // 소요시간 >>
    // 1시간

    // 아이디어 >>
    // DP를 써서 n-1에서 앞뒤로 1x2혹은 2x2가 붙는걸 표현하자!
    // ex. 2x8이면 2x7에 1x2짜리를 붙이는 경우 + 2x6에 2x2을 붙이는 경우

    // 배운 것 >>
    // 1) DP는 반복된 것들의 합이 필요할 때 많이 쓴다. (5=4의 경우+1 같은 것)
    // 피보나치도 DP쓰면 빠르게 될 것 같단 생각이 듦
    // (반복된 값을 들어가지 않게하여 재귀의 속도를 개선해줄 것 같다.)

    // 에러 로그 >>
    // 시간초과 - func에서 깊게 파고드는게 시간이 많이 걸린 듯 싶다. (숫자가 커지면 내려가기 오래 걸림)
    //         if (dpline[indexNum] != 0) return dpline[indexNum];
    //        else return func(indexNum - 1) + func(indexNum - 2);
    //  => dpline에 값 저장하는 부분 추가함. (값을 넣지 않았던게 시간초과 요인이었다.)
    //
    // 1007로 나누는거 안함 - 문제를 제대로 읽어야겠다..
    //

    private static int[] dpline;

    private static int func(int indexNum) {
        if (dpline[indexNum] != 0) return dpline[indexNum];
        else {
            dpline[indexNum]= (func(indexNum - 1) + func(indexNum - 2))%10007;
            return dpline[indexNum];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(reader.readLine());

        dpline = new int[n+1]; //숫자로 인덱스를 쓰려고 함.
        dpline[1] = 1;
        if(n>=2) dpline[2] = 2;

        System.out.println(func(n)%10007);
    }

}
