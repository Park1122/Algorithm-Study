package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17836 {
    // https://www.acmicpc.net/problem/17836

    // 소요시간 >>
    // 8시간.. (진짜 될거같은데 안되고,,
    // 다른 분들 참고해서 봐도 빠지는게 없는데 왜 안될까 했더니 if문을 좀 더 구조적으로 걸러야됐다.)

    // 아이디어 >>
    // 그램을 갖고 있을 떄와 없을 떄/방문한 곳이 비어있는지, 벽인지, 그램이있는지에 따라 총 6가지의 경우가 가능하다.
    // 그램 없 + (벽 -> 별 조치없이 지나감 / 빈공간 ->  큐에 추가 / 그램 -> situation의 getGram 값 변경)
    // 그램 있 -> 큐에 추가하여 진행

    // 에러로그 >>
    // 틀렸습니다 - visited와 hour을 혼용해서 사용 / getGram에 따른 분류가 불명확

    // Constants
    private static final int[] xDiff = {0,1,0,-1};
    private static final int[] yDiff = {1,0,-1,0};

    // Variable
    private static int n,m,t; // 세로, 가로, 제한시간, 정답(걸린시간)
    private static int[][] orgArr;

    // Main
    public static void main(String[] args) throws IOException{
        // Input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        orgArr = new int[n+1][m+1];
        for(int y=1;y<=n;y++){
            st = new StringTokenizer(reader.readLine()," ");
            for(int x=1;x<=m;x++){
                orgArr[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // Logic
        int ans = logic();

        // Output
        System.out.println(ans==-1 ? "Fail" : ans);

    }

    private static int logic(){
        // Initialize
        Queue<Situation> q = new LinkedList<>(); // 상황을 담을 큐
        q.add(new Situation(0,1,1,0)); // 처음엔 hour=0, x=1,y=1에서 검이 없는 채로(getGram=0) 시작

        boolean[][][] visited= new boolean[2][n+1][m+1]; // 방문여부를 판단하는 배열 [0=검이 없을 때, 1=검이 있을 때][y좌표][x좌표]

        while(!q.isEmpty()){
            // get a situation
            Situation s = q.poll();

            // 종료조건 체크
            if(s.hour>t) break; // timeout

            // 탐색
            for(int i=0;i<4;i++){
                int tmpX = s.x + xDiff[i];
                int tmpY = s.y + yDiff[i];

                // 조건 체크
                if(!checkBound(tmpY,tmpX)) continue; // 범위 체크
                if(tmpX == m && tmpY==n) return s.hour+1; // 도착 체크
                if(visited[s.getGram][tmpY][tmpX]) continue; // 방문 체크

                // gram에 따라 분류
                if(s.getGram==0){ // gram을 갖고있지 않을 때,
                    if(orgArr[tmpY][tmpX]==0) {  // 빈공간(0)이라면
                        visited[s.getGram][tmpY][tmpX] = true; // vistied 체크하고
                        q.add(new Situation(s.hour+1, tmpY,tmpX,s.getGram)); // 큐에 새로운 상황을 추가하고,
                    }
                    else if(orgArr[tmpY][tmpX]==1) continue; // 벽(1)이라면 끝내고,
                    else if(orgArr[tmpY][tmpX]==2) { // 그램을 만났다면(2)
                        visited[0][tmpY][tmpX] = true; // 다시 검을 만나는 경우를 제외하도록 visited[0].. 일때도 true로
                        visited[1][tmpY][tmpX] = true; // 검을 만난 이후도 체크되도록 visited[1].. 일때도 true로 변경
                        q.add(new Situation(s.hour+1,tmpY,tmpX,1)); // 큐에 기본변경 외에도 getGram을 1로 바꿔 추가하기
                    }
                } else{ // gram을 갖고있다면
                    // visited 체크하고 큐에 담으면 끝.
                    visited[s.getGram][tmpY][tmpX] = true;
                    q.add(new Situation(s.hour+1,tmpY,tmpX,s.getGram));
                }
            }
        }
        // while문을 도는 동안에 return되지 못하면 시간초과 혹은 방문불가를 의미.
        // 출력시 필터로 사용하기 위해 -1을 리턴
        return -1;
    }

    // Method - 범위체크
    private static boolean checkBound(int y, int x){return 0<y && y<=n && 0<x && x<=m;}

    // Inner Class - 상황을 담는 클래스
    private static class Situation{
        // Variable
        int x,y,hour,getGram; // x좌표, y좌표, 걸린시간, 그램을 가졌는지(0=없음,1=있음)

        // Constructor
        public Situation(int hour, int y, int x, int getGram){
            this.x=x;
            this.y=y;
            this.hour = hour;
            this.getGram = getGram;
        }
    }
}
