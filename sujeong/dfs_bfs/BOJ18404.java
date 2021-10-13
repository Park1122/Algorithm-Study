package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ18404 {
    // https://www.acmicpc.net/problem/18404

    // 소요시간 >>
    // 15:07 ~ 16:04 (약 1시간)

    // 아이디어 >>
    // 7562의 테스트케이스가 여러개로 늘어난것과 동일하다.

    // 에러로그 >>
    // X

    // Attribute
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // Constants
    private static final int[] xDiff = {1,2,2,1,-1,-2,-2,-1};
    private static final int[] yDiff = {2,1,-1,-2,-2,-1,1,2};

    // Variable
    private static int n, m; // 한 변의 길이, 적의 수
    private static Node knight;
    private static Node[] enemies;

    // Main
    public static void main(String[] args) throws IOException{
        // Input
        String[] basicInfo = reader.readLine().split(" ");
        n = Integer.parseInt(basicInfo[0]);
        m = Integer.parseInt(basicInfo[1]);

        String[] knightInfo = reader.readLine().split(" ");
        knight = new Node(Integer.parseInt(knightInfo[0]),Integer.parseInt(knightInfo[1]));

        enemies = new Node[m];
        for(int i = 0; i<m;i++){
            String[] enemyInfo = reader.readLine().split(" ");
            Node enemy = new Node(Integer.parseInt(enemyInfo[0]),Integer.parseInt(enemyInfo[1]));
            enemies[i] = enemy;
        }

        // Initialize
        int[][] dp = new int[n+1][n+1];

        boolean[][] visited = new boolean[n+1][n+1];
        visited[knight.x][knight.y] = true;

        Queue<Node> knightQueue = new LinkedList<>();
        knightQueue.add(knight);

        // Logic
        while(!knightQueue.isEmpty()){
            Node tmpKnight = knightQueue.poll();

            for(int i=0;i<8;i++){
                Node tmp = tmpKnight.add(xDiff[i], yDiff[i]);

                if(!checkBound(tmp)) continue;

                if(visited[tmp.x][tmp.y]) continue;
                visited[tmp.x][tmp.y] = true;

                // dp값(해당 좌표까지의 최소 이동횟수) 넣기.
                dp[tmp.x][tmp.y] = dp[tmpKnight.x][tmpKnight.y]+1;

                // 큐에 넣어 더이상 탐색할 수 없을 때까지 탐색 진행.
                knightQueue.add(tmp);
            }
        }

        // Output
        String sb = "";
        for(int i=0;i<m;i++){
            Node enemy = enemies[i];
            sb+=dp[enemy.x][enemy.y]+" ";
        }
        System.out.println(sb.stripTrailing());
    }

    // Method
    private static boolean checkBound(Node node){return 0<node.x && node.x<=n && 0<node.y && node.y<=n;}

    // Inner Class
    private static class Node{
        int x,y;
        public Node(int x,int y){
            this.x= x;
            this.y= y;
        }

        public Node add(int x, int y){
            int tmpX = this.x+x;
            int tmpY = this.y+y;
            return new Node(tmpX,tmpY);
        }

    }

}
