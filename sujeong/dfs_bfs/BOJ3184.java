package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ3184 {
    // https://www.acmicpc.net/problem/3184

    // 소요시간 >>
    // 2시간 (들어갈 내용은 그래도 빠르게 찾았는데 조금씩 어긋나서 오래 걸린 편..)

    // 아이디어 >>
    // 지도를 돌아다니며 좌표 하나하나를 방문하며
    // 방문X & 벽X 라면, 탐색을 다녀옴.
        // 탐색 ->
        // 방문X, 1(벽)이 아니고(0,2,3), 범위도 맞다면,
        // 2,3일경우 wolf, sheep의 수를 늘림.
        // 연결된 모든 노드를 살피며 그 수를 늘려나감.
    // 탐색을 마치고 양과 늑대의 수를 allSheep, allWolf에 더함.
    // allSheep과 allWolf를 출력하며 끝.

    // 에러 로그 >>
    // X

    // Attribute
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int width, height, wolf, sheep;
    private static int[][] orgArr;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException{
        // input
        String[] coords = reader.readLine().split(" ");
        height = Integer.parseInt(coords[0]);
        width = Integer.parseInt(coords[1]);

        // make String to OrgArr
        orgArr = new int[height][width];
        for (int y = 0 ; y<height ; y ++){
            String line = reader.readLine();
            for(int x= 0; x<width ; x++){
                switch (line.substring(x,x+1)){
                    case "#" : orgArr[y][x] = 1; break;
                    case "v" : orgArr[y][x] = 2; break;
                    case "o" : orgArr[y][x] = 3; break;
                }
            }
        }
        // initialize
        visited = new boolean[height][width];
        int allSheep = 0 ;
        int allWolf = 0;

        // Logic
        for (int y = 0 ; y<height ; y ++) {
            for (int x = 0; x < width; x++) {
                if(visited[y][x] || orgArr[y][x]==1) continue;

                // reset
                sheep = 0;
                wolf = 0;

                // Find
                func(y, x);

                // Add
                int[] ret = new int[2];
                if(sheep>wolf) allSheep+=sheep;
                else allWolf+=wolf;

            }
        }
        // Output
        System.out.println(allSheep+" "+allWolf);


    }

    private static final int[] yDiff ={1,0,-1,0};
    private static final int[] xDiff ={0,1,0,-1};
    private static boolean checkBound(int x,int y){return 0<=x&&x<width&&0<=y&&y<height;}

    private static void func(int y, int x){
        if(!checkBound(x,y) || orgArr[y][x]==1 || visited[y][x]) return;

        visited[y][x]=true;
        if(orgArr[y][x]==2) wolf++;
        else if(orgArr[y][x]==3) sheep++;

        for(int i=0;i<4;i++){
            func(y+yDiff[i],x+xDiff[i]);
        }

    }

}
