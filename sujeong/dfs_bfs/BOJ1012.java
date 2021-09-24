package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1012 {
    // https://www.acmicpc.net/problem/1012

    // 소요시간 >>
    // 3시간
    // 첫 dfs bfs라 어려웠다.....

    // 아이디어 >>
    // * visited를 이용해 방문여부를 판단함.
    // * 방문한 적 없고 배추가 있는 좌표면, 해당 배추와 무리로 묶일 수 있는 배추들을 모두 탐색(혹은 방문)
    // * 배추를 찾고(무더기까지 방문한번씩하기) -> count++을 해줌.
    // * 마지막에 count를 출력함으로써 정답을 낸다.

    // 에러 로그 >>
    // X

    // Attribute
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static int[] xDiff ={0,1,0,-1};
    private static int[] yDiff ={1,0,-1,0};

    private static int m,n,k;
    private static int[][] orgArr, visited;


    public static void main(String[] args) throws IOException{
        // input
        int testcase = Integer.parseInt(reader.readLine());
        for (int i = 0; i < testcase; i++) {
            String[] basicInfo = reader.readLine().split(" ");
            m = Integer.parseInt(basicInfo[0]); // 가로 크기
            n = Integer.parseInt(basicInfo[1]); // 세로 크기
            k = Integer.parseInt(basicInfo[2]); // 총 배추 수

            orgArr = new int[m][n];
            for(int j=0;j<k;j++){
                String[] line = reader.readLine().split(" ");
                int x = Integer.parseInt(line[0]);
                int y = Integer.parseInt(line[1]);

                orgArr[x][y] = 1;
            }

//// input test----------------------------------------------------
//            for(int x= 0; x<m ; x++){
//                for(int y=0;y<n; y++){
//                    System.out.print(orgArr[x][y]+" ");
//                }
//                System.out.println();
//            }
//// --------------------------------------------------------------

            // Logic
            int count = 0; // 배추 무리 수
            visited = new int[m][n]; // 흔적지도

            for(int x= 0; x<m ; x++){
                for(int y=0;y<n; y++){
                    if(orgArr[x][y]==1 && visited[x][y]==0){ // 배추가 있으면서 & 방문한적 없는 좌표일 경우
                        dfsFunc(x,y); // 해당 배추 주변을 모두 탐색
                        count++; // 배추 무리 수 증가
                    }
                }
            }

            // Output
            System.out.println(count);
        }
    }

    public static void dfsFunc(int x, int y){
        visited[x][y]=1;

        for(int i=0;i<4;i++){
            int tmpX = x-xDiff[i];
            int tmpY = y-yDiff[i];

            if(checkBoundX(tmpX) && checkBoundY(tmpY) // x가 0~m, y가 0~n인지 확인
                    && orgArr[tmpX][tmpY]==1 && visited[tmpX][tmpY]==0){ // 배추가 있고 방문한 적 없는지 확인
                dfsFunc(tmpX,tmpY);
            }
        }

    }


    private static boolean checkBoundX(int tmpX){ return 0<=tmpX && tmpX<m;}
    private static boolean checkBoundY(int tmpY){ return 0<=tmpY && tmpY<n;}
}
