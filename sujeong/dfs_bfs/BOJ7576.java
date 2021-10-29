package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ7576 {
    // https://www.acmicpc.net/problem/7576

    // 소요시간 >>
    // 3시간

    // 아이디어 >>
    // 토마토를 상하좌우로 익혀나가며 더이상 익을 수 있는 토마토가 있는지 체크(모두 익었거나 or 가로막혀 익을 수 없거나)하여
    // 더 익혀나갈 수 있다면 while문을 통해 더 익혀나가고,
    // 아니라면 while문을 빠져나와 정답을 출력한다.

    // 에러로그 >>
    // 시간초과 - Stringtokenizer를 사용해봤지만 역부족이었다.
    // 시간초과 - SpreadTmt와 tmpTmt를 비교하는 부분을 for문을 다 돌고 체크하는 것에서 for문 내에서 체크하는 것으로 변경하여 해결

    // Constants
    private static final int[] xDiff = {0,1,0,-1};
    private static final int[] yDiff = {1,0,-1,0};

    // Variable
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int m,n; // 가로, 세로
    private static int tmpTmt = 0, spreadTmt = 0;
    private static int[][] orgArr;
    private static int ans;

    // Main
    public static void main(String[] args) throws IOException{
        // Input
        StringTokenizer st = new StringTokenizer(reader.readLine()," ");
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        // Initialize
        orgArr = new int[n][m]; // 세로 가로
        ArrayList<Point> riped = new ArrayList<>(); // 익은 토마토를 담을 배열
        for(int y=0;y<n;y++){
            st = new StringTokenizer(reader.readLine()," ");
            for (int x=0;x<m;x++){
                orgArr[y][x] = Integer.parseInt(st.nextToken());
                if(orgArr[y][x]==1) riped.add(new Point(x,y));
            }
        }

        // Logic
        while(true) {
            // Inner init
            Queue<Point> queue = new LinkedList<>(riped); // 막 익은 토마토를 담은 배열
            riped = new ArrayList<>(); // 이번 날짜에 익을 토마토를 담을 배열

            while (!queue.isEmpty()) {
                // get a riped tomato
                Point p = queue.poll();
                // spread
                for (int i = 0; i < 4; i++) {
                    int tmpX = p.x + xDiff[i];
                    int tmpY = p.y + yDiff[i];

                    if (!checkBound(tmpX, tmpY)) continue; // 범위 체크

                    if (orgArr[tmpY][tmpX] == 0) { // 아직 안익은 토마토라면,
                        orgArr[tmpY][tmpX] = orgArr[p.y][p.x] + 1; // 이전 토마토의 값(= 해당 토마토가 익는데 걸린 시간+1)에 1을 더해준다.
                        riped.add(new Point(tmpX, tmpY));
                        spreadTmt++; // 이전과 이후가 같은지 체크하기 위함.
                    }
                }
            }
            // check condition - 더이상 익을 토마토가 없다면 while문을 나가라.
            if (checkCondition()) break;

            // reinitialize
            tmpTmt = spreadTmt;
        }

        // output
        System.out.println(ans);
    }

    // Logic Method - 종료 조건은 모든 토마토가 익거나(=all zero) 더이상 익을 수 있는 토마토가 없을 때
    private static boolean checkCondition(){
        // all zero
        int maxVal = Integer.MIN_VALUE; // for count date
        boolean isnoriped = false; // for check there's no riped tomato

        // finding no riped tomato(= orgArr[y][x]==0) & maximal date(=maxVal)
        for(int y=0;y<n;y++){
            for(int x=0;x<m;x++){
                // if exist no riped tomato
                if(orgArr[y][x]==0) {
                    // 오늘도 익은 토마토가 있다면, false를 리턴
                    if (spreadTmt!=tmpTmt) return false;
                    isnoriped = true;
                    break;
                }
                maxVal = Math.max(maxVal,orgArr[y][x]);
            }
        }
        if(isnoriped) ans = -1; // 토마토가 다 익진 않았지만 더 익을 수 있는 토마토가 없다면 ans(=다 익는데 걸린 시간)=-1
        else ans = maxVal-1; // ans(=다 익는데 걸린 시간)은 가장 큰 기록된 값 -1 (maxVal의 시작은 첫날부터 0이 아닌 1이였기 때문에)
        return true;
    }

    // Method
    private static boolean checkBound(int tmpX, int tmpY){return 0<=tmpY && tmpY<n && 0<=tmpX && tmpX<m;}

    // Inner Class
    private static class Point{
        int x,y;
        public Point(int x,int y){
            this.x=x;
            this.y=y;
        }
    }

}
