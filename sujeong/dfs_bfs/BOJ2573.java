package sujeong.dfs_bfs;
import java.util.*;
import java.io.*;

public class BOJ2573 {
    // https://www.acmicpc.net/problem/2573

    // 소요시간 >>
    // 2시간 - 어렵던 건 아닌데 코드가 길어서 오래걸렸다.

    // 아이디어 >>
    // 인풋을 받을 때 0이 아닌 부분(=섬)만 따로 큐에 담고,
    // 큐를 돌기전에 종료조건을 탐색한다.
    // 이때의 종료조건 탐색은 dfs의 방식으로 이뤄지는데,
    // 큐에 있는 값 하나를 가져와서 쭉 dfs시키고 dfs로 찾는 섬의 수와
    // 전체 섬을 담고있는 큐의 사이즈가 다르면 두덩이 이상, 아니면 한덩이를 의미하기 때문에
    // 한덩이일 경우에는 회전을 계속해주도록 넘어가고 아닐 경우에는 지금까지의 회전수(cnt)를 출력한다.
    // 큐는 돌 때,섬인 부분을 돌면서 주변에 0인 부분을 방문할 때마다
    // 새로운 맵(tmpArr)의 높이값(h)을 1씩 빼준다.
    // 그렇게 큐가 빌때까지 완성된 새로만든 맵(tmpArr)의 주소를 기존 맵(orgArr)에 담으며 회전수를 증가시킨다.
    // 큐가 비었음에도 두덩이가 되지 못했다면 0을 출력하며 프로그램을 종료한다.

    // 에러로그 >>
    // X

    // Constant
    private static final int[][] diff = {{0,1},{1,0},{0,-1},{-1,0}};

    // Variable
    private static int row, col;
    private static int[][] orgArr;
    private static boolean[][] checked;
    private static Queue<Point> q;

    public static void main(String[] args) throws IOException{
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        orgArr = new int[row][col];
        q = new ArrayDeque<>();
        for(int y=0;y<row;y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0;x<col;x++){
                orgArr[y][x] = Integer.parseInt(st.nextToken());
                if(orgArr[y][x]!=0) q.add(new Point(y,x));
            }
        }

        // Logic
        int cnt=0;
        while(!q.isEmpty()){
            // get this loop's queue size
            int size = q.size();

            // check end condition
            checked = new boolean[row][col];
            Point tmp = q.peek();
            if(dfs(tmp.y, tmp.x)-1!=size) {
                // Output - when success
                System.out.println(cnt);
                System.exit(0);
            }

            // clone orgArr
            int[][] tmpArr = new int[row][col];
            for(int y=0;y<row;y++){
                for(int x=0;x<col;x++) tmpArr[y][x] = orgArr[y][x];
            }

            // searching
            for(;0<size;size--){
                Point cur = q.poll();
                int x = cur.x;
                int y = cur.y;
                int h = orgArr[y][x];

                for(int i=0;i<4;i++){
                    int tmpX = cur.x + diff[i][0];
                    int tmpY = cur.y + diff[i][1];
                    // check Condition
                    if(!checkBound(tmpY, tmpX)) continue; // 범위체크
                    // for nextLoop
                    if(orgArr[tmpY][tmpX]==0) h--;
                }
                // next loop
                tmpArr[y][x] = h<=0? 0: h;
                if(tmpArr[y][x]!=0) q.add(new Point(y,x));
            }
            // for next loop
            orgArr = tmpArr;
            cnt++;
        }
        // Output - when failed
        System.out.println(0);
    }
    // Method - 한 섬에 연결된 섬이 몇개인지 체크하는 함수
    private static int dfs(int y,int x){
        int retVal = 1;
        for(int i = 0;i<4;i++){
            int tmpX = x + diff[i][0];
            int tmpY = y + diff[i][1];
            // check condition
            if(!checkBound(tmpY, tmpX)) continue; // 범위가 맞는지
            if(checked[tmpY][tmpX]) continue; // 방문했는지
            if(orgArr[tmpY][tmpX]==0) continue; // 섬인지
            // for next
            checked[tmpY][tmpX] = true;
            retVal+=dfs(tmpY, tmpX);
        }
        return retVal;
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