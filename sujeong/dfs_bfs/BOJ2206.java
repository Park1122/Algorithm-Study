package sujeong.dfs_bfs;

import java.util.*;
import java.io.*;

public class BOJ2206 {
    // https://www.acmicpc.net/problem/2206

    // 소요시간 >>
    // 1시간 (적당히 무난하게 풀었던 것 같다)

    // 아이디어 >>
    // 시작점에서부터 사방으로 이동하며 1)범위가 맵을 넘어서는지, 2)방문을 했던 곳인지, 3)이미 벽을 부순 적 있는데 벽을 방문했는지 체크하여
    // 이동할 좌표들을 줄이고, 종료지점에 도착했는지 체크하여 도착했다면 출력, 아니라면 다음 탐색을 위해
    // visited 생신과 큐에 추가를 해준다.
    // 모든 탐색에도 종료지점에 못왔다면 도착할 수 없다는 뜻이기에 -1을 출력하며 프로그램을 종료한다.

    // 에러로그 >>
    // 틀렸습니다 - n,m모두 1일떄를 반영하지 못해서 그렇다. (정답=1,수정 전 답=-1) (https://www.acmicpc.net/board/view/63738)

    // Constant
    private static final int[][] diff = {{1,0},{0,1},{-1,0},{0,-1}};

    // Variable
    private static int[][] orgArr; // 입력받은 맵을 저장
    private static int n,m; // row, column

    // Main
    public static void main(String[] args) throws IOException{
        // Input
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(r.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        orgArr = new int[n][m];
        for(int y=0;y<n;y++) {
            String str = r.readLine();
            for(int x=0;x<m;x++) orgArr[y][x] = Integer.parseInt(str.substring(x,x+1));
        }

        // 종료지점 == 시작지점 -> 1을 출력하고 종료
        if(n==1 && m==1) {
            System.out.println(1);
            System.exit(0);
        }

        // Logic
        Queue<Situation> q = new ArrayDeque<>(); // 상황을 담을 큐
        q.add(new Situation(0,0,0,1));

        boolean[][][] visited = new boolean[2][n][m]; // 방문여부를 체크 [벽을 부순횟수][y좌표][x좌표]
        visited[0][0][0]=true;

        while(!q.isEmpty()){
            // get a situation
            Situation cur = q.poll();
            // moving
            for(int i=0;i<4;i++){
                // get tmp
                int tmpY = cur.y + diff[i][0];
                int tmpX = cur.x + diff[i][1];
                // check condition
                if(!checkBound(tmpY,tmpX)) continue; // 범위체크
                if(visited[cur.z][tmpY][tmpX]) continue; // 방문체크
                if(orgArr[tmpY][tmpX]==1 && cur.z == 1) continue; //벽통과 X라면
                // check end-Condition
                if(tmpY==n-1 && tmpX ==m-1) {
                    System.out.println(cur.moved+1); // 기존+도착
                    System.exit(0); //종료
                }
                // for next Situation
                int newZ = orgArr[tmpY][tmpX]==1?cur.z+1:cur.z;
                visited[newZ][tmpY][tmpX] = true;
                q.add(new Situation(newZ,tmpY,tmpX,cur.moved+1));
            }
        }
        // Output
        System.out.println(-1);
    }

    // Method - 범위체크
    private static boolean checkBound(int y, int x) {return 0<=y && y<n && 0<=x && x<m;}

    // Inner Class
    private static class Situation{
        int z,y,x,moved; // 벽 부순 횟수, y좌표, x좌표, 움직인 횟수
        public Situation(int z, int y, int x, int moved){
            this.x=x;
            this.y=y;
            this.z=z;
            this.moved=moved;
        }
    }
}