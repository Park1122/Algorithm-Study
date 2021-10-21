package sujeong.dfs_bfs;

import java.util.Scanner;

public class BOJ1389 {
    // https://www.acmicpc.net/problem/1389

    // 소요시간 >>
    // 22:37 ~ 24: 10 (약 1시간 30분)

    // 아이디어 >>
    // 문자를 읽어오는게 아니면 Scanner가 더 편하고 간소할 것 같아서 변경해봄.
    // 사람간의 거리를 나타낼 배열 dp에 나 자신은 0, 그외는 INITIAL을 넣어준다. (Integer.Max 쓰면 더하기 시 음수로 바뀜)
    // 그 뒤 input으로 받은 관계에 1을 넣어주고 (대칭구조 + 출력를 위해 dp[i][j]와 dp[j][i] 둘다에 1을 넣음)
    // 그리고 나선 k->i->j (경유지->출발지->도착지)순으로 for문을 돌려
    // 기존의 값과 새로운 값을 계속해서 비교해나감.
    // k가 가장 밖에 있는 것은 k가 가장 안에 있을 경우 i->k->j를 탐색 시 i->k나 k->j가
    // 아직 완성되지 못할 수도 있어 정확한 값을 못 불러오기 때문이다.
    // (사진으로 후에 설명)
    // 그렇게 dp 테이블을 완성하면 n부터 1로 가며 가장 작은 합을 가진 유저를 탐색해나간다.
    // sum < minV가 아닌 sum <= minV이기 때문에 가장 작은 번호의 유저를 ans에 저장할 수 있다.

    // 에러로그 >>
    // 틀렸습니다 -> 경유지가 for문의 밖에 있었다.


    // Constants
    private static final int INITIAL = 5001;

    // Variable
    private static final Scanner sc = new Scanner(System.in);

    // Main
    public static void main(String[] args){
        // Input
        int n = sc.nextInt(); // 유저 수
        int m = sc.nextInt(); // 입력받을 관계의 수

        // make initial dp table (자기자신은 0, 그 외는 INITIAL로 초기화)
        int[][] dp = new int[n + 1][n + 1];
        for(int i = 1; i<= n; i++){
            for(int j = 1; j<= n; j++){
                if(i==j) dp[i][j] = 0;
                else dp[i][j] = INITIAL;
            }
        }

        // set initial relationship
        for(int i = 0; i< m; i++){
            int one = sc.nextInt();
            int other = sc.nextInt();
            dp[one][other] = 1;
            dp[other][one] = 1;
        }


        // Logic
        // i~k, k~j를 돌아다님.
        // 경유지를 잡고 i~j를 여러개로 나눠서 살펴봄.
        // 왜 k부터인지는 아이디어 + 풀이 ppt-2 12 (후에 블로그에 가공해 올림)에 있음.
        for (int k = 1; k<= n; k++){
            for(int i = 1; i<= n; i++){
                for(int j = i+1; j<= n; j++){ // 대칭구조라 시간 조금이라도 줄이고자 반만 돌 수 있도록 함.
                    // i~j와 i~k + k~j 중에 더 작은 값을 tmp에 저장
                    int tmp =  Math.min(dp[i][j], dp[i][k]+ dp[k][j]);

                    // dp가 i,i(같은 수 조합)를 기준으로 대칭인 점을 생각해 시간을 줄이고자함.
                    dp[i][j] = tmp;
                    dp[j][i] = tmp;
                }
            }
        }

        // Output
        int minV  = INITIAL; // 가장 작은 값을 비교해나갈 때 사용할 변수
        int ans = 0 ; // 가장 작은 인덱스(유저번호)를 저장할 변수
        for(int i = n; i>0; i--) {
            // add i's kevin-bacon value
            int sum = 0;
            for (int j = 1; j <= n; j++) sum += dp[i][j];

            // compare old value & new value
            if (sum <= minV) {
                minV=sum;
                ans = i;
            }
        }
        // print answer
        System.out.println(ans);


    }
}
