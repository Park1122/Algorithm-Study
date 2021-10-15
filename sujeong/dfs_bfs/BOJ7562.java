package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ7562 {
    // https://www.acmicpc.net/problem/7562
    // 소요시간 >>
    // 21:05 ~ 22:10 (약 1시간)

    // 아이디어 >>
    // bfs방식을 사용하여, kQueue(위치 별 나이트를 담는 큐)에 한 나이트가 이동 가능한 위치들을 모두 담아내고
    // 다시 방문할 수 없도록 visited를 이용해 방문할 수 있는 위치를 줄여나가 종료가 가능하도록 함.
    // 코드 설명은 주석으로 미리 달아두었다.

    // 에러로그 >>
    // X

    // Attribute
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // Constants
    private static final int[] xDiff = {1,2,2,1,-1,-2,-2,-1};
    private static final int[] yDiff = {2,1,-1,-2,-2,-1,1,2};

    // Variable
    private static int size; // 체스 한 변의 크기

    // Main
    public static void main(String[] args) throws IOException{
        // Input
        int testcase = Integer.parseInt(reader.readLine()); // 테스트 케이스의 수
        for(int loop = 0 ; loop< testcase; loop++){
            size = Integer.parseInt(reader.readLine()); // 체스의 한 변의 크기

            String[] sLine = reader.readLine().split(" "); // 시작 위치
            Node start = new Node(Integer.parseInt(sLine[0]),Integer.parseInt(sLine[1]));

            String[] eLine = reader.readLine().split(" "); // 종료 위치
            Node end = new Node(Integer.parseInt(eLine[0]),Integer.parseInt(eLine[1]));

            // Initialize
            int[][] dp = new int[size][size]; // 최소 이동횟수를 담는다.

            boolean[][] visited = new boolean[size][size]; // 방문 여부를 담는다.

            Queue<Node> kQueue = new LinkedList<>(); // 각 나이트들의 위치를 담는다.
            visited[start.x][start.y] = true; // 시작 위치 방문 true로 셋팅(시작과 종료의 위치가 같을 때 다른 곳을 다녀오지 않도록 해야하기 때문에)
            kQueue.add(start); // 시작 위치 큐에 담기

            // Logic
            while(!kQueue.isEmpty()){
                // 큐에서 좌표 가져오기
                Node node = kQueue.poll();

                for(int i=0;i<8;i++){ // 나이트의 이동가능한 위치를 모두 탐색
                    // 임시 좌표 설정
                    int tmpX = node.x+xDiff[i];
                    int tmpY = node.y+yDiff[i];
                    Node tmp = new Node(tmpX, tmpY);

                    // 범위 체크(체스판을 벗어나는지 여부)
                    if(!checkBound(tmp)) continue;

                    // 방문 체크(이미 방문했던 위치인지 여부 - bfs이기 때문에 계속 커지는 count의 특성 상 더 작은 경우가 나올 수 없어 visited면 다시 방문하지 않도록 체크)
                    if(visited[tmpX][tmpY]) continue;
                    visited[tmpX][tmpY] = true;

                    // dp값(해당 좌표까지의 최소 이동횟수) 넣기.
                    dp[tmpX][tmpY] = dp[node.x][node.y]+1;

                    // 큐에 넣어 더이상 탐색할 수 없을 때까지 탐색 진행.
                    kQueue.add(tmp);
                }

            }

            // Output
            System.out.println(dp[end.x][end.y]);
        }
    }

    // Method
    private static boolean checkBound(Node node){return 0<=node.x && node.x<size && 0<=node.y && node.y<size;}

    // Inner Class
    private static class Node{
        int x, y;
        public Node(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
}
