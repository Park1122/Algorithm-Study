package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11066 {
    // https://www.acmicpc.net/problem/11066

    // 소요시간 >>
    // 4시간

    // 아이디어 >>
    // 구글 검색의 힘을 빌렸다.. 그래도 어려웠다....(https://gre-eny.tistory.com/55)
    // 핵심은 "i~j까지의 파일을 합치는 최소비용을 dp에 저장한다"이다. -> 이걸 생각해냈다면 풀 수 있다.
    // i==j (파일 1개) : 해당 파일의 크기를 넣는다.
    // j==i+1 (파일 2개) : i번째 파일과 j번째 파일을 합친다.
    // j>i+1 (파일 3개이상)
    // : dp[i][j] = dp[i][mid] + dp[mid+1][start+gap] + sum(i ~ start+gap)

    // 에러 로그 >>
    // X


    public static void main(String[] args) throws IOException{
        // input & initialize
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());
        for(int i=0;i<T;i++){
            int n = Integer.parseInt(reader.readLine());
            String[] strArr = reader.readLine().split(" ");

            int[] orgArr = new int[n];
            for(int j=0;j<n;j++) orgArr[j]=Integer.parseInt(strArr[j]);

            int[] sum = new int[n]; //sum[i]는 i까지의 파일을 합친 값
            sum[0]=orgArr[0];
            for(int j=1;j<n;j++) sum[j]=sum[j-1]+orgArr[j];

            int[][] dp = new int[n][n]; // a부터 b까지의 값을 dp[a]b]에 담음
            for (int j = 0; j < n-1; j++) { // 앞에서부터 인접한 두 파일을 합친 값
                dp[j][j+1] = orgArr[j] + orgArr[j+1];
            }

            // Logic
            for(int gap=2;gap<n;gap++){
                for(int start=0;start+gap<n;start++){

                    dp[start][start+gap] = Integer.MAX_VALUE;
                    for(int mid=start;mid<start+gap;mid++){
                        dp[start][start+gap] = Math.min(dp[start][start+gap], dp[start][mid]+dp[mid+1][start+gap]+sumDist(sum,start,start+gap));
                    }
                }

            }

            // output
            System.out.println(dp[0][n-1]); // 전체 범위에 대해서 최소값을 출력 = 정답
        }
    }

    // start~end의 총합을 구함.
    private static int sumDist(int[] sum, int start, int end) {
        return start == 0 ? sum[end] :  sum[end] - sum[start - 1];
    }

}
