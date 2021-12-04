package sujeong.dfs_bfs;

import java.util.*;
import java.io.*;

public class BOJ4179 {
    // https://www.acmicpc.net/problem/4179

    // 소요시간 >>
    // 4시간 - 큐 두개를 같이 써야해서 어떤 구성으로 가야할지 고민하는 과정이 길었다.

    // 아이디어 >>
    // 같은 지점에 불과 사람을 이동시켜야 하는데,
    // 같은 시점에 불과 사람이 같은 지점에서 만나야할 경우
    // 불이 그 지점을 잠식하여 사람은 갈 수 없다는 점을 이용하여
    // 불을 먼저 이동시키고 지훈이를 이동시키며 후에 갈 곳이 바운드 이외면 ans(걸린시간)를 출력하고 끝.
    // 아니라면 지훈이를 큐(jq)에 넣어 빌때까지 bfs탐색을 계속함.
    // 큐가 비게 되어 탐색이 더이상 이뤄질 수 없다면 IMPOSSIBLE을 출력하고 종료한다.

    // 에러로그 >>
    // 틀렸습니다 - 도착조건을 checkbound를 하기전에 두어서,
    //              해당 링크(https://www.acmicpc.net/board/view/31494)의
    //              2번째 케이스에서 2가 나오며 실패했다.
    // 틀렸습니다(7%) - checkBound가 true로 나오면 정답에 도착했단 건데, 이를 따로 분리하고 0부분이 비어있게 되면서 오류가 났던 것 같다.

    // Constant
    private static final int[][] diff = {{0,1},{1,0},{0,-1},{-1,0}};

    // Variable
    private static int row,col;
    private static char[][] orgArr; // 맵정보를 담는 배열
    private static Queue<Point> jq, fq; // 지훈이 위치를 담을 큐, 불의 위치를 담을 큐

    public static void main(String[] args) throws IOException{
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        orgArr = new char[row][col];
        jq = new ArrayDeque<>();
        fq = new ArrayDeque<>();

        // draw map(input orgArr)
        for(int y=0;y<row;y++){
            // input value with char array
            orgArr[y] = br.readLine().toCharArray();
            for (int x=0;x<col;x++) {
                char c = orgArr[y][x];
                // adding queues
                if(c=='J') jq.add(new Point(y,x));
                else if(c=='F') fq.add(new Point(y,x));
            }
        }

        // Logic
        for(int ans=1;!jq.isEmpty();ans++){
            // spread fire
            for(int fs=fq.size(); 0<fs; fs--){
                // get a fire
                Point p = fq.poll();
                // explore the surroundings
                for(int i=0;i<4;i++){
                    // get tmp point
                    int tmpX = p.x+diff[i][0];
                    int tmpY = p.y+diff[i][1];
                    // check condition
                    if(!checkBound(tmpY,tmpX)) continue;
                    if(orgArr[tmpY][tmpX]=='F' || orgArr[tmpY][tmpX]=='#') continue;
                    // set value for next search
                    orgArr[tmpY][tmpX] = 'F';
                    fq.add(new Point(tmpY,tmpX));
                }
            }
            // move jh
            for(int js=jq.size(); js>0; js--){
                // get a man
                Point cur = jq.poll();
                // explore the surroundings
                for(int i=0;i<4;i++){
                    // get tmp point
                    int tmpX = cur.x+diff[i][0];
                    int tmpY = cur.y+diff[i][1];
                    // check condition (contain end condition)
                    if(!checkBound(tmpY,tmpX)) {
                        // Output - success
                        System.out.println(ans);
                        return;
                    }
                    if(orgArr[tmpY][tmpX]!='.') continue;
                    // set value for next search
                    orgArr[tmpY][tmpX] = 'J';
                    jq.add(new Point(tmpY,tmpX));
                }
            }
        }
        // Output - fail
        System.out.println("IMPOSSIBLE");

    }
    // Method - 범위를 체크하는 함수
    private static boolean checkBound(int tmpY, int tmpX) {return 0<=tmpX && tmpX<col && 0<=tmpY && tmpY<row; }
    // Inner Class - 섬의 좌표를 표현하기 위해 사용하는 클래스
    private static class Point{
        int x,y;
        public Point(int y,int x){
            this.y=y;
            this.x=x;
        }
    }
}