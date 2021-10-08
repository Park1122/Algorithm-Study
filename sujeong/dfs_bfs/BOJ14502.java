package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ14502 {
    // https://www.acmicpc.net/problem/14502

    // 소요시간 >>
    // 5시간...(어려운 bfs와 문제해결방법 생각이 꽤 오래 오래 걸렸던 문제....)

    // 아이디어 >>
    // 빈공간에 벽 3개를 둘 수 있는 모든 경우를 만들고,
    // 각 경우마다 바이러스를 퍼뜨려 최대로 남은 빈공간 수를 ans에 저장한다.
    // ans를 출력하며 마무리한다.
    // 벽을 만드는 과정에서 bruteforce, virus를 퍼뜨리는 과정에선 bfs가 사용된다.

    // 에러로그 >>
    // X

    // Attribute
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int n,m;
    private static int ans = Integer.MIN_VALUE;
    private static int[][] map;
    private static Queue<Integer> queue;

    // Constants
    private static final int[] xDiff = {0,1,0,-1};
    private static final int[] yDiff = {1,0,-1,0};

    // Main
    public static void main(String[] args) throws IOException{
        // Input
        String[] basicInfo = reader.readLine().split(" ");
        n = Integer.parseInt(basicInfo[0]);
        m = Integer.parseInt(basicInfo[1]);

        map = new int[n][m];
        for(int y=0;y<n;y++){
            String[] line = reader.readLine().split(" ");
            for(int x=0; x<m;x++){
                map[y][x] = Integer.parseInt(line[x]);
            }
        }

        // Initialize

        // Logic
        makeWall(0);

        // Output
        System.out.println(ans);

    }

    // 벽 만들기
    private static void makeWall(int wallCount){
        if(wallCount==3){
            spread();
            return;
        }
        for(int y=0;y<n;y++){
            for(int x=0; x<m;x++){
                if(map[y][x]==0){
                    map[y][x] = 1; // 세우고
                    makeWall(wallCount+1); // 벽 추가 & 3개되면 스프레드까지 시도
                    map[y][x] = 0; // 다시 허물기
                }
            }
        }
    }

    // spread virus
    private static void spread(){
        // Initialize
        Queue<Node> queue = new LinkedList<>();

        // copy map
        int[][] clone = new int[n][m];
        for(int y=0;y<n;y++){
            if (m >= 0) System.arraycopy(map[y], 0, clone[y], 0, m);
        }

        // find virus
        for(int y=0;y<n;y++){
            for(int x=0; x<m;x++){
                if(clone[y][x]==2) queue.add(new Node(x,y));
            }
        }

        // spread virus
        while(!queue.isEmpty()){
            Node one = queue.poll();

            for(int i=0;i<4;i++){
                int tmpX = one.x+xDiff[i];
                int tmpY = one.y+yDiff[i];

                if(isPossible(tmpX,tmpY) && clone[tmpY][tmpX]==0){
                    clone[tmpY][tmpX] = 2;
                    queue.add(new Node(tmpX,tmpY));
                }

            }
        }

        // check zero count
        checkSafe(clone);
    }

    // Method
    private static boolean isPossible(int x, int y) {return 0<=x && x<m && 0<=y && y<n; }
    private static void checkSafe(int[][] clone){
        int count = 0;
        for(int y=0;y<n;y++){
            for(int x=0; x<m;x++){
                if(clone[y][x]==0) count++;
            }
        }
        ans = Math.max(ans,count);
    }

    // Inner Class
    private static class Node{
        private int x, y;
        public Node(int x, int y){
            this.x=x;
            this.y=y;
        }
    }


}
