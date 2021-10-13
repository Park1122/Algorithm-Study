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
    private static final int[] xDiff = {1,2,2,1,-1,-2,-2,-1}; // 시계방향으로 나이트가 이동할 수 있는 x값
    private static final int[] yDiff = {2,1,-1,-2,-2,-1,1,2}; // 시계방향으로 나이트가 이동할 수 있는 y값

    // Variable
    private static int n, m; // 한 변의 길이, 적의 수
    private static Node knight; // 나이트의 위치
    private static Node[] enemies; // 적의 위치를 담은 배열

    // Main
    public static void main(String[] args) throws IOException{
        // Input
        // 기본정보인 체스판의 한 변 길이와 적의 수를 n,m에 저장
        String[] basicInfo = reader.readLine().split(" ");
        n = Integer.parseInt(basicInfo[0]);
        m = Integer.parseInt(basicInfo[1]);

        // knight의 위치를 받아와 knight 변수에 할당
        String[] knightInfo = reader.readLine().split(" ");
        knight = new Node(Integer.parseInt(knightInfo[0]),Integer.parseInt(knightInfo[1]));

        // enemy의 위치를 받아와 enemies 배열에 할당
        enemies = new Node[m];
        for(int i = 0; i<m;i++){
            String[] enemyInfo = reader.readLine().split(" ");
            Node enemy = new Node(Integer.parseInt(enemyInfo[0]),Integer.parseInt(enemyInfo[1]));
            enemies[i] = enemy;
        }

        // Initialize
        // knight의 이동하는 거리를 계산
        int[][] dp = new int[n+1][n+1];

        // 방문한 적 있는지 여부 저장 (knight의 시작위치 방문 체크)
        boolean[][] visited = new boolean[n+1][n+1];
        visited[knight.x][knight.y] = true;

        // knight가 움직일 케이스들을 담은 큐 생성 + 큐에 현재 나이트 위치 추가
        Queue<Node> knightQueue = new LinkedList<>();
        knightQueue.add(knight);

        // Logic
        while(!knightQueue.isEmpty()){
            // 나이트 하나를 가져옴.
            Node tmpKnight = knightQueue.poll();

            for(int i=0;i<8;i++){
                // 나이트가 갈 수 있는 좌표를 가져온다.
                Node tmp = tmpKnight.add(xDiff[i], yDiff[i]);

                // 범위 체크 (범위를 벗어나는지 체크)
                if(!checkBound(tmp)) continue; 

                // 방문 여부 체크 (방문했던 곳인지 체크)
                if(visited[tmp.x][tmp.y]) continue;
                visited[tmp.x][tmp.y] = true;

                // dp값(해당 좌표까지의 최소 이동횟수) 넣기.(bfs + visited이기 때문에 현재 저장값과 새로운 값의 비교는 필요없다.)
                dp[tmp.x][tmp.y] = dp[tmpKnight.x][tmpKnight.y]+1;

                // 큐에 넣어 더이상 탐색할 수 없을 때까지 탐색 진행.
                knightQueue.add(tmp);
            }
        }

        // Output
        String sb = "";
        for(int i=0;i<m;i++){
            // 띄어쓰기로 각 적들까지의 최소 이동 수를 담는다. 
            Node enemy = enemies[i];
            sb+=dp[enemy.x][enemy.y]+" ";
        }
        System.out.println(sb.stripTrailing());
    }

    // Method
    // x와 y좌표가 0~n으로 체스판 위에 있는지 체크하는 함수
    private static boolean checkBound(Node node){return 0<node.x && node.x<=n && 0<node.y && node.y<=n;}

    // Inner Class
    // 나이트의 좌표(x,y)를 담음.
    private static class Node{
        int x,y;
        public Node(int x,int y){
            this.x= x;
            this.y= y;
        }

        // 현재 노드의 x,y에 파라미터로 받은 x,y를 더한 위치의 새 노드를 리턴한다.
        public Node add(int x, int y){
            int tmpX = this.x+x;
            int tmpY = this.y+y;
            return new Node(tmpX,tmpY);
        }

    }

}
