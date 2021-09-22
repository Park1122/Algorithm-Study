package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2096 {
    // https://www.acmicpc.net/problem/2096

    // 소요시간 >>
    // 30분

    // 아이디어 >>
    // 다음표처럼 생각하면 된다.
    // index\col | 0 | 1 | 2 |
    //   0       |   |   |   |
    //   1       |   |   |   |
    //   2       |   |   |   |
    //   3       |   |   |   |
    //   4       |   |   |   |
    //
    // index번째에서 0번칸에 값이 들어오는 경우는 index-1번째에서 0번칸과 1번칸에 값이 있을 때이고,
    // index번째에서 1번칸에 값이 들어오는 경우는 index-1번째에서 0번칸과 1번칸, 2번칸에 값이 있을 때이고,
    // index번째에서 2번칸에 값이 들어오는 경우는 index-1번째에서 1번칸과 2번칸에 값이 있어야한다.
    // 이를 max와 min 관점으로 dp를 만들어 1부터(0은 orgArr의 0과 같음)만들어가면 된다.
    //
    // dp를 많이 풀다보니 바로 아이디어가 떠오른 케이스였다.

    // 에러 로그 >>
    // X


    private static int[][] maxDp, minDp;
    public static void main(String[] args) throws IOException{
        // input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[][] orgArr = new int[n][3];
        for(int i=0;i<n;i++) {
            String[] arr = reader.readLine().split(" ");
            for (int l=0;l<3;l++)
                orgArr[i][l] = Integer.parseInt(arr[l]);
        }
        // dp set
        maxDp =new int[n][3];
        minDp =new int[n][3];

        maxDp[0][0]=orgArr[0][0];
        maxDp[0][1]=orgArr[0][1];
        maxDp[0][2]=orgArr[0][2];
        minDp[0][0]=orgArr[0][0];
        minDp[0][1]=orgArr[0][1];
        minDp[0][2]=orgArr[0][2];


        for(int index = 1; index<n;index++){
            maxDp[index][0] = Math.max(maxDp[index-1][0], maxDp[index-1][1])+orgArr[index][0];
            maxDp[index][1] = Math.max(maxDp[index-1][0],Math.max(maxDp[index-1][1], maxDp[index-1][2]))+orgArr[index][1];
            maxDp[index][2] = Math.max(maxDp[index-1][1], maxDp[index-1][2])+orgArr[index][2];

            minDp[index][0] = Math.min(minDp[index-1][0], minDp[index-1][1])+orgArr[index][0];
            minDp[index][1] = Math.min(minDp[index-1][0],Math.min(minDp[index-1][1], minDp[index-1][2]))+orgArr[index][1];
            minDp[index][2] = Math.min(minDp[index-1][1], minDp[index-1][2])+orgArr[index][2];

        }

        int max = Math.max(Math.max(maxDp[n-1][0],maxDp[n-1][1]),maxDp[n-1][2]);
        int min = Math.min(Math.min(minDp[n-1][0],minDp[n-1][1]),minDp[n-1][2]);

        System.out.println(max+" "+min);
    }

}


