package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16973 {
    // https://www.acmicpc.net/problem/16973

    // 소요시간 >>
    // 2시간 30분 (w,h,n,m등 변수의 사용 형태와 그 값의 매칭이 헷갈리는 경우가 많아 이를 수정하는데 꽤 시간이 걸렸다.)

    // 아이디어 >>
    // 1) 시작 위치(sX,sY)에 직사각형을 먼저 위치시키고
    // 2) tmpX,tmpY를 계산하여 범위(checkBound), 방문여부(visited), 직사각형이 이동할 수 있는지 여부(isPossibleMoving)으로 필터링을 해준다.
    // 3) visited에 이동횟수를 저장한 뒤
    // 4) 도착위치에 x,y가 왔다면 logic함수에서 빠져나와 output으로 값을 출력해주고
    // 5) 아니라면 큐에 tmpX,tmpY를 넣어 다음 이동이 가능하도록 한다.
    // 6) 만약 while문을 모두 돌았음에도 도착하지 못했다면 기본값인 0에서 -1되어 문제에서 미도착시 원했던 값인 -1을 출력한다.

    // 에러로그 >>
    // 시간초과 - Rect라는 클래스를 만들어 직사각형의 x,y좌표를 기억하는 방식에서 int[]로 바꾸었다.
    // 시간초과 - visited와 isPossibleMoving의 순서를 바꿔 시간 복잡도가 더 큰 isPossibleMoving을 방문하는 횟수를 줄여 문제 해결함.

    // 기타배움 >>
    // 최단 경로를 구하는데에는 BFS를 사용해야한다.

    // Constants
    private static final int[] xDiff = {0,1,0,-1};
    private static final int[] yDiff = {1,0,-1,0};

    // Variable
    private static int n,m,h,w,sX,sY,fX,fY; // 가로, 세로, 직사각형 높이, 직사각형 너비, 시작x, 시작y, 종료x, 종료y
    private static int[][] orgArr; // 입력받은 원래 배열
    private static Queue<int[]> q; // 직사각형의 위치를 받아 bfs에 사용된 큐
    private static int[][] visited; // 직사각형의 첫 점의 위치를 통해 방문했는지 여부를 체크하는 배열

    // Main
    public static void main(String[] args) throws IOException{
        // Input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        orgArr = new int[n+1][m+1];
        for(int y=1;y<=n;y++){
            st = new StringTokenizer(reader.readLine()," ");
            for(int x=1;x<=m;x++){
                orgArr[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(reader.readLine()," ");
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        sY = Integer.parseInt(st.nextToken());
        sX = Integer.parseInt(st.nextToken());
        fY = Integer.parseInt(st.nextToken());
        fX = Integer.parseInt(st.nextToken());

        // Logic
        logic();

        // Output
        System.out.println(visited[fY][fX]-1);
    }

    private static void logic(){
        // Initialize
        q = new LinkedList<>();
        q.add(new int[]{sY,sX}); // 시작위치를 담아준다.

        visited = new int[n+1][m+1];
        visited[sY][sX] = 1; // 초기값으로 visited 분리하기 위해 넣어줌. (후에 출력에서 -1해줘야한다.)

        while(!q.isEmpty()){
            // get a Rect
            int[] cur = q.poll();
            int curY = cur[0];
            int curX = cur[1];

            for(int i=0;i<4;i++){
                // calc tmpX, tmpY
                int tmpX = curX + xDiff[i];
                int tmpY = curY + yDiff[i];

                // filtering (범위, 방문여부, 직사각형의 이동가능 여부)
                if(!checkBound(tmpY,tmpX)||visited[tmpY][tmpX]!=0||!isPossibleMoving(tmpY,tmpX)) continue;

                // set visited value (moving count)
                visited[tmpY][tmpX] = visited[curY][curX]+1;
                if(isFinished(tmpY,tmpX)) return;
                // for next
                q.add(new int[]{tmpY,tmpX});

            }
        }
    }

    // Method - 범위를 벗어나지 않는지 판단
    private static boolean checkBound(int y, int x){return 1<=x && x<=m && 1<=y && y<=n;}

    // Method - 움직일 수 있는지 여부를 판단
    private static boolean isPossibleMoving(int inputY,int inputX){
        int tmpY = inputY+h-1; // 직사각형의 끝 y좌표
        int tmpX = inputX+w-1; // 직사각형의 끝 x좌표
        if(!checkBound(tmpY,tmpX)) return false;

        for(int y=inputY;y<=tmpY;y++){
            for(int x=inputX;x<=tmpX;x++){if(orgArr[y][x]==1) return false;}
        } return true;
    }

    // Method - 도착 여부를 판단
    private static boolean isFinished(int y, int x) {return y==fY && x==fX;}

}