package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;

public class BOJ1495 {
    // https://www.acmicpc.net/problem/1495

    // 소요시간 >>
    // 1시간

    // 아이디어 >>
    // 전에 풀었던 0~20까지의 수의 범위만 필요한 경우의 문제와 비슷했다.
    // i번째 곡에서 일어날 수 있는 볼륨의 가짓수를 dp로 표현함.
    // dp[i][v] = i번째 곡에서 켜지는 볼륨의 수를 의미.
    // 예시로는 곡의 개수는 3, 시작 볼륨은 5, 최대 볼륨은 10이 주어진 다음과 같은 input이 들어왔다.
    // 3 5 10
    // 5 3 7
    // 이 경우 dp에 저장되는건 다음과 같다.
    // n \ m | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |
    //   0   |   |   |   |   |   | 1 |   |   |   |   |    |
    //   1   | 1 |   |   |   |   |   |   |   |   |   |  1 |
    //   2   |   |   |   | 1 |   |   |   | 1 |   |   |    |
    //   3   | 1 |   |   |   |   | 1 |   |   |   |   |  1 |
    // 이때 dp[n][for문으로 10부터 대입하며 가장 먼저 발견한 1의 인덱스]가 구하고자 하는 답이 된다.

    // 에러 로그 >>
    // X

    private static int n, startVol, maxVol;
    private static int[][] dp;
    public static void main(String[] args) throws IOException{
        // input & initialize
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] strArr = reader.readLine().split(" ");
        n = Integer.parseInt(strArr[0]);
        startVol = Integer.parseInt(strArr[1]);
        maxVol = Integer.parseInt(strArr[2]);

        int[] orgArr = new int[n+1];
        String[] songStrArr = reader.readLine().split(" ");
        for(int i=1;i<=n;i++){
            orgArr[i]=Integer.parseInt(songStrArr[i-1]);
        }

        // dp setting
        dp=new int[n+1][maxVol+1];
        dp[0][startVol] = 1;

        // Logic
        for(int i=1;i<=n;i++){
            for(int vol = 0; vol<=maxVol;vol++){
                if(dp[i-1][vol]!=0){

                    int max = vol+orgArr[i];
                    if(max<=maxVol) dp[i][max]=1;

                    int min = vol-orgArr[i];
                    if(min>=0) dp[i][min]=1;
                }
            }
        }

        // output
        System.out.println(getLast());

    }

    private static int getLast(){
        for(int i=maxVol;i>=0;i--)
            if(dp[n][i]==1)
                return i;
        return -1;
    }
}
