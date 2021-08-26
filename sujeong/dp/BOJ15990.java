package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15990 {
    // https://www.acmicpc.net/problem/15990

    // 소요시간 >>
    // 4시간

    // 아이디어 >>
    // 14916과 매우 흡사해보임
    // 3,2,1 순으로 3의 개수를 줄이고 1의 개수를 늘려가는 방향으로 구성해봐야할 것 같음.
    // 위의 아이디어의 경우 dp를 사용하기 어려워져서 시간이 오래걸림
    // -> 어차피 작은 수에서 부터 쌓아가는 점은 같으니 미리 dp의 값을 다 구하자

    // 에러 로그 >>
    // 시간 초과 -> 미리 모든 값을 구하고 룹을 도는 형태로 변경


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(reader.readLine());

        long[][] dpline = new long[1000001][4]; //숫자로 인덱스를 쓰려고 함. 뒤는 123을 저장하려고 4로 함.

        // 기존 케이스 중 연속 제외한 경우의 가지수
        dpline[1][1] = 1; // 1
        dpline[1][2] = 0;
        dpline[1][3] = 0;

        dpline[2][1] = 0;
        dpline[2][2] = 1; // 2
        dpline[2][3] = 0;

        dpline[3][1] = 1; // 2+1
        dpline[3][2] = 1; // 1+2
        dpline[3][3] = 1; // 3

        for (int i=4;i<1000001;i++){
            // n-1의 경우 그 뒤로 2를 빼거나 3을 빼는 경우만 가능
            // 마찬가지로 n-2는 그뒤로 1,3이고, n-3은 그뒤로 1,2임.
            dpline[i][1]=(dpline[i-1][2]+dpline[i-1][3])%1000000009;
            dpline[i][2]=(dpline[i-2][1]+dpline[i-2][3])%1000000009;
            dpline[i][3]=(dpline[i-3][1]+dpline[i-3][2])%1000000009;
        }

        for (int i=0;i<T;i++){
            int n =Integer.parseInt(reader.readLine());
            System.out.println((dpline[n][1] + dpline[n][2] + dpline[n][3])%1000000009);
        }
    }

}
