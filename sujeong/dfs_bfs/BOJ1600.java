package sujeong.dfs_bfs;

import java.util.*;
import java.io.*;

public class BOJ1600 {
    // https://www.acmicpc.net/problem/1600

    // 소요시간 >>
    // 4시간 (이제 아이디어랑 방식은 금방 생각나는데 코드 줄의 위치나 메모리, 시간을 고려하는 부분이 부족한 것 같다.)

    // 아이디어 >>
    // 이건 BFS문제이다.
    // 유의할 점은 말의 방식으로 가는게 먼저오도록 코드를 위치시켜야한다.
    // (더 멀리간게 더 빨리 도착할 확률이 높기 때문에 visited를 사용하는 상황상 앞쪽에 위치시키는게 맞다.)
    // horse를 먼저 위치시킴으로써 초반에 단거리 이동을 한 경우가 멀리 뻗어나가지 못함.

    // 에러로그 >>
    // 틀렸습니다 - 해당 링크의 반례를 통과 못함.(https://www.acmicpc.net/board/view/40044)
    // -> 느낌상 horse무빙이 앞에와서 그런것 같은데, 바꿔도 달라지는것 없이 오히려 시간만 오래 걸림.
    // -> horse무빙을 한 횟수에 따라 층을 나눠서 visited를 봐야할 것 같다. (맞았다)

    // Constant
    private static final int[][][] diff = {
            {{0,1},{1,0},{0,-1},{-1,0}},  // 기본 걸음
            {{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1},{-2,1},{-1,2}}, // 말 걸음
    };

    // Variable
    private static int K,W,H; // 말걸음의 비용, 너비, 높이
    private static int[][] orgArr; // 입력받은 맵

    // Main
    public static void main(String[] args) throws IOException {
        // Initialize
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(reader.readLine());

        StringTokenizer st = new StringTokenizer(reader.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        orgArr = new int[H][W];
        for(int y=0;y<H;y++){
            st = new StringTokenizer(reader.readLine());
            for(int x=0;x<W;x++) orgArr[y][x] =  Integer.parseInt(st.nextToken());
        }

        // 상황을 담을 큐
        Queue<Situation> q = new LinkedList<>();
        q.add(new Situation(0,0,0,0));

        // 방문했는지 여부를 담는 배열 [높이][너비][말걸음횟수]
        boolean[][][] visited = new boolean[H][W][K+1];
        visited[0][0][0]=true;

        // Logic
        while(!q.isEmpty()){
            // get a Situation
            Situation cur = q.poll();

            // End Condition
            if(cur.x == W-1 && cur.y == H-1){
                System.out.println(cur.time);
                System.exit(0);
            }
            // moving
            for(int i=0;i<2;i++){
                // 이미 최대 말무빙 횟수에 도달했는데 말무빙 방식을 해야한다면 넘어감.
                if(i==1 && cur.hMoveCnt>=K) continue;

                for(int j=0;j<diff[i].length;j++){
                    // calc Tmp Value
                    int tmpX = cur.x + diff[i][j][0];
                    int tmpY = cur.y + diff[i][j][1];

                    // check condition
                    if(!checkBound(tmpY,tmpX)) continue; // 범위 체크
                    if(orgArr[tmpY][tmpX]==1) continue; // 벽

                    // for next step
                    int tmpHM = i==1? cur.hMoveCnt+1:cur.hMoveCnt;
                    if(!visited[tmpY][tmpX][tmpHM]){
                        visited[tmpY][tmpX][tmpHM] = true;
                        q.add(new Situation(tmpY,tmpX,tmpHM,cur.time+1));
                    }
                }
            }
        }
        // If the end point cannot be reached
        System.out.println(-1);
    }

    // Method - check boundary
    private static boolean checkBound(int y, int x){return 0<=y && y<H && 0<=x && x<W;}

    // Inner Class
    private static class Situation {
        // Variable
        int y,x,hMoveCnt,time;
        // Constructor
        public Situation(int y, int x, int hMoveCnt, int time){
            this.y=y;
            this.x=x;
            this.hMoveCnt=hMoveCnt;
            this.time = time;
        }
    }
}