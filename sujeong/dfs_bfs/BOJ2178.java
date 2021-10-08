package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2178 {
    // https://www.acmicpc.net/problem/2178

    // 소요시간 >>
    // 4시간 (아직은 bfs가 어렵다.. dfs는 할만한데 bfs는 아직은 감이 잘 안온다..)

    // 아이디어 >>
    // dfs로 가면 끝쯤에서 2개가 만날 떄 일을 다시 처리해야하기 떄문에 시간도 길고 값도 옳지 않을 수 있다.
    //

    // 에러로그 >>
    // X

    // Attribute
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int n,m; // x축 크기, y축 크기
    private static int[][] map, dp; // 원래 미로 지도, 해당 좌표까지 이동하기 위한 최소의 칸 수
    private static boolean[][] visited; // 방문여부 체크 (이미 지나온 값으로 되돌아가지 않도록 함.)
    private static Queue<Node> queue; // bfs방식으로 Node를 add, poll하기 위한 queue

    // direction
    private static final int[] xDiff={0,1,0,-1};
    private static final int[] yDiff={1,0,-1,0};

    // Main
    public static void main(String[] args) throws IOException{
        // Input - read basicInfo
        String[] basicInfo = reader.readLine().split(" ");
        n = Integer.parseInt(basicInfo[0]);
        m = Integer.parseInt(basicInfo[1]);

        // Input - read load
        map = new int[n][m];
        for(int x = 0 ; x<n;x++){
            String line = reader.readLine();
            for(int y = 0;y<m; y++){
                map[x][y]=Integer.parseInt(line.substring(y,y+1));
            }
        }

        // Initialize
        visited = new boolean[n][m];
        queue = new LinkedList<>();
        dp = new int[n][m];

        // Logic
        bfsFunc(0,0);

        // Output
        System.out.println(dp[n-1][m-1]);

    }
    // Logic
    private static void bfsFunc(int x, int y){
        // set first node
        dp[x][y] = 1;
        visited[x][y]= true;
        queue.add(new Node(x,y));

        // fill dp values
        while(!queue.isEmpty()){
            Node one = queue.poll();

            for(int i=0;i<4; i++){
                // 주변 좌표 가져오기
                int tmpX = one.x + xDiff[i];
                int tmpY = one.y + yDiff[i];

                // 갈 수 있는 길이라면 dp값, 방문여부를 변경해주고 queue에 넣어 다음 loop에서 bfs방식으로 확인해나갈 수 있도록 함.
                if(checkPossible(tmpX,tmpY)){
                    // set next node
                    dp[tmpX][tmpY] = dp[one.x][one.y]+1;
                    visited[tmpX][tmpY] = true;
                    queue.add(new Node(tmpX,tmpY));
                }
            }
        }

    }

    // Method
    private static boolean checkPossible(int x, int y){
        return 0<=x && x<n && 0<=y && y<m && map[x][y]==1 && dp[x][y]==0;
    }

    // Inner Class
    private static class Node{
        int x,y;
        public Node(int x, int y){
            this.x=x;
            this.y=y;
        }
    }

}
