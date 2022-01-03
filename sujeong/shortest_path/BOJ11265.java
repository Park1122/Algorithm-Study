package sujeong.shortest_path;

import java.util.*;
import java.io.*;

public class BOJ11265 {
    // https://www.acmicpc.net/problem/11265

    // 소요시간 >>
    // 4시간 (플로이드와샬인걸 알기 전까지 다익스트라 개선만 하다 시간을 많이 써버렸다.)

    // 아이디어 >>
    // 한두개의 출발-목적지의 최단거리를 구하는게 아닌 다수의 경우의 최단거리를 구해야하기 때문에
    // 플로이드 와샬로 최단거리를 dp에 모두 저장한다.
    // 0) Input - 기본정보(n,m)를 입력받는다.
    // 1) initialize dp array - dp에 입력받은 i->j까지의 시간을 담는다.
    // 2) floyd warshall - 플로이드와샬 알고리즘으로 mid를 늘려가며 start에서 end까지의 dp(최단거리)값을 갱신해나간다.
    // 3) output - 입력받은 출발지부터 도착지까지의 최단거리가 입력받은 최단거리보다 크다면 ans[0], 아니면 ans[1]을 출력

    // 에러로그 >>
    // 시간초과 & 틀렸습니다 - 다익스트라 알고리즘을 써서 풀었는데 시간초과가 발생했다. 그래서 모든 지점에서의 최단거리르 구할땐
    // 플로이드워셜 알고리즘을 써야한다는 글을 발견하고 플로이드워셜 알고리즘으로 변경을 시도했다.
    // 틀렸습니다 - mid start end 순인데 start end mid순으로 for문을 돌렸다. (해결완)
    // (그러면 dp에 들어가는 순서가 엉망진창이라 값이 나올 수 없음 (간격(=mid)을 작은것부터 큰 방향으로 해야 알맞은 순서로 비교가 되어 젇답이 나옴.))
 
    // 개선과정 >>
    // * BufferedWriter를 사용해 출력 하도록 변경
    // => 메모리 28360 -> 26364 / 시간 668 -> 452
    // * 1~n단위로 배열을 사용하는 것에서 0~n-1단위로 바꿈
    // => 메모리 26364 -> 26688 / 시간 452 -> 448
    
    // Constant
    static String[] ans = {"Stay here\n","Enjoy other party\n"};
    // Variable
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    // Read Method 
    private static void readLine() throws IOException {st = new StringTokenizer(br.readLine()); }
    private static int nextInt() {return Integer.parseInt(st.nextToken());}
    // Main
    public static void main(String[] args) throws IOException {
        // Input
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        readLine();
        int n = nextInt(); // 파티장의 크기
        int m = nextInt(); // 서비스를 요청한 손님의 수
        // initialize dp array
        int[][] dp = new int[n][n]; // [i][j] 일때 i에서 j까지의 최단거리를 저장함.
        for(int i=0;i<n;i++){
            readLine();
            for(int j=0;j<n;j++) dp[i][j] = nextInt();
        }
        // floyd warshall 
        for(int mid=0; mid<n;mid++){ // mid가 for문의 가장 바깥에 있어야하고, 작은수부터 늘려나가야 올바른 값이 나온다.
            for(int start=0;start<n;start++){
                for(int end=0;end<n;end++){
                    if(end==mid) continue; // end==mid일때의 값은 갱신할 필요가 없음.
                    dp[start][end] = Math.min(dp[start][end],dp[start][mid]+dp[mid][end]);
                }
            }
        }
        // Output
        for(int i=0;i<m;i++){
            readLine();
            bw.append(ans[dp[nextInt()-1][nextInt()]>nextInt() ? 0: 1]); 
        } bw.flush();
    }
}
