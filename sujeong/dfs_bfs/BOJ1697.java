package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1697 {
    // https://www.acmicpc.net/problem/1697

    // 소요시간 >>
    // 16:12 ~ 16:55 (약 40분)

    // 아이디어 >>
    // 나이트 문제(7562,18404)들과 유사하다.
    // 나이트가 2차원 좌표평면 상에서 일어나고 이동방식을 constant로 빼낼 수 있던것과 달리
    // 해당문제는 직선 위에서 이동이 이뤄지고,
    // 해당 문제는 현재 수빈이의 위치*2를 하거나 수빈이의 위치+-1로 계산으로 찾도록  이동 방식이 면경되었다.

    // 음수로 다녀올 경우는 제외해도 된다.
    // >> -1을 타야지만 갈 수 있는데, -가 되면 동생이 있는 +방향으로 가기위해선 +1밖에 방법이 없다.
    // >> 그렇기 때문에 -1 -> +1로 되돌아오는 방식은 불필요한 걸음만되기에 제외하고 봐도 된다.

    // 에러로그
    // 틀렸습니다.
    // ->   수빈이와 동생이 같은 위치에 있다면 0인데 +-1을 하여 결과가 2가 나왔다.
    //      이를 수빈이의 첫 위치를 큐에 넣을 때 visited[n]=true로 하여 해결하였다.

    // Constants
    private static final int MAX_STEP = 100001; // 자주 사용하는 상수값 따로 빼두기

    // Main
    public static void main(String[] args){
        // Input -숫자만 두개 읽어오는 것이니 간단+효율을 위해 scanner 사용
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 수빈이의 위치
        int k = sc.nextInt(); // 동생의 위치


        // Initialize
        // 방문 여부 저장
        boolean[] visited = new boolean[MAX_STEP];
        // 걸음 수 저장
        int[] dp = new int[MAX_STEP];
        // 수빈이가 갈 수 있는 방식들 저장하는 큐
        Queue<Integer> subinPosQ = new LinkedList<>();
        visited[n]=true;
        subinPosQ.add(n);

        // Logic
        while(!subinPosQ.isEmpty()){
            // get one
            int nowPos = subinPosQ.poll();

            // set moving array
            int[] moves = {nowPos+1, nowPos-1, nowPos*2};

            // moving
            for(int step : moves){
                // 범위 체크
                if(step<0 || MAX_STEP<=step) continue;

                // 방문 체크
                if (visited[step]) continue;
                visited[step] = true;

                // 걸음수 설정
                dp[step] = dp[nowPos]+1;

                // 발견하면 나오도록 셋팅 (불필요한 부분까지 체크하는 것 방지)
                if(k==step) break;

                // 큐에 추가
                subinPosQ.add(step);
            }
        }

        // Output
        System.out.println(dp[k]);


    }

}
