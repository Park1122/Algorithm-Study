package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ4963 {
    // https://www.acmicpc.net/problem/4963

    // 소요시간 >>
    // 1시간 (그렇게 어렵진 않은 편이었다.)

    // 아이디어 >>
    // 1. 종료 조건을 체크하기 위해 width, height를 먼저 받는다.
    // 2. while을 통해 섬의 지도를 만들고
    // 3. 지도를 탐색하며 섬을 탐색(func)한 뒤
        // 탐색(func) ->
        // 이미 방문한 적 있거나 좌표평면 이외의 공간일 경우 return을 시킴.
        // 아닐경우, 현재 좌표를 기준으로 시계방향으로 func를 호출시키며 섬의 크기를 count에 담음
        // 탐색 후 섬의 크기(count)를 리턴함.
    // 4. 섬의 크기가 0이 아니라면 섬이 있음을 의미하기에 func의 return값이 0이 아닐 경우만 retArr에 담음.
    // 5. retArr의 크기(섬의 개수)를 출력.
    // 6. 다음 룹의 width와 height를 먼저 받음.
    // 7. width와 height가 0과 0일 경우, 다음 루프 없이 종료하고, 아니라면 2로 돌아가 위의 과정을 반복한다.

    // 에러 로그 >>
    // X

    // Constants
    private static final int[] xDiff = {0,1,1,1,0,-1,-1,-1};
    private static final int[] yDiff = {1,1,0,-1,-1,-1,0,1};

    // Attribute
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int width, height;
    private static int[][] orgArr;
    private static boolean[][] visited;


    public static void main(String[] args) throws IOException{
        // Input
        String[] coords = reader.readLine().split(" ");
        width = Integer.parseInt(coords[0]);
        height = Integer.parseInt(coords[1]);

        while(!(width==0&&height==0)) {

            orgArr = new int[width][height];
            for (int y = 0; y < height; y++) {
                String[] line = reader.readLine().split(" ");
                for (int x = 0; x < width; x++) {
                    orgArr[x][y] = Integer.parseInt(line[x]);
                }
            }

            // Initialize
            visited = new boolean[width][height]; // 방문 여부 기록

            // Logic
            ArrayList<Integer> retArr = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int count = func(x, y);
                    if (count != 0) retArr.add(count);
                }
            }

            // Output
            System.out.println(retArr.size());

            // Next Loop Input (for check end condition, put duplicates at the bottom one more time)
            // 다음 루프를 위해 마지막에 다음 루프의 w,h를 받음.
            coords = reader.readLine().split(" ");
            width = Integer.parseInt(coords[0]);
            height = Integer.parseInt(coords[1]);

        }
    }

    // Function
    private static int func(int x, int y){
        // 좌표 이외의 구역이거나 방문한 적 없을 경우 그 수를 0으로 하여 return시킴.
        if(!checkBound(x,y)||visited[x][y]) return 0;
        // 방문하였음을 체크
        visited[x][y]=true;

        // 섬의 크기도 재보면 좋을 것 같아서 count를 이용해 그 크기를 담음.
        int count = 0;
        if(orgArr[x][y]==1) {
            count++;
            // 연결된 다른 육지를 탐색함.
            for(int i=0;i<8;i++){
                int newX = x+xDiff[i];
                int newY = y+yDiff[i];
                count+=func(newX, newY);
            }
        }
        return count;
    }

    // Method
    private static boolean checkBound(int x, int y){ return 0<=x&&x<width&&0<=y&&y<height;}
}
