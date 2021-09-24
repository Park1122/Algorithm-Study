package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class BOJ2667 {
    // https://www.acmicpc.net/problem/2667

    // 소요시간 >>
    // 약 40분 (23:42 ~ 00:25)

    // 아이디어 >>
    // * visited를 이용해 방문여부를 판단함.
    // * 방문한 적 없는 번지면, 해당 번지와 무리로 묶일 수 있는 집들을 모두 탐색(혹은 방문)
    // * 그 수를 count[]에 저장

    // 에러 로그 >>
    // X

    // Constants
    private static int[] xArr ={0,1,0,-1}, yArr ={1,0,-1,0};

    // Attribute
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static int n;
    private static int[][] town, visited;


    public static void main(String[] args) throws IOException{
        // Input & Initialize
        initialize();

        // Logic
        ArrayList<Integer> pocket = new ArrayList<Integer>();
        for(int x=0; x<n; x++){
            for(int y=0; y<n; y++){
                if(town[x][y]==1 && visited[x][y]==0){
                    pocket.add(dfsFunc(x,y));
                }
            }
        }
        // Output
        pocket.sort(Comparator.naturalOrder());
        int size= pocket.size();

        System.out.println(size);
        for(int i=0;i<size;i++) {
            System.out.println(pocket.get(i));
        }

    }

    // Initialize
    public static void initialize() throws IOException{
        // n=지도 x,y 넓이 (정사각형 마을)
        n = Integer.parseInt(reader.readLine());

        // 마을 지도 배열
        town = new int[n][n];
        for(int x=0; x<n; x++){
            String line = reader.readLine();
            for(int y=0; y<n; y++){
                town[x][y] = Integer.parseInt(line.substring(y,y+1));
            }
        }
        // 방문 기록 배열
        visited = new int[n][n];

    }

    // DFS Function
    public static int dfsFunc(int x, int y){
        // 방문했음 체크
        visited[x][y]=1;

        // 해당 집을 세어서 1개
        // (해당 위치에서 안가본 집을 dfs해서 count에 저장해 Logic부분에서 ArrayList에 넣을 예정
        int count=1;

        // 동서남북으로 이동준비
        for(int i=0;i<4;i++){
            // 이동할 번지 계산
            int xDiff=x+xArr[i];
            int yDiff=y+yArr[i];

            if(checkBound(xDiff) && checkBound(yDiff) && // 타운 내에 있는 번지인가?
                    town[xDiff][yDiff]==1 && visited[xDiff][yDiff]==0){ // 집이 있고 방문한 적 없는 곳이 맞나?
                count += dfsFunc(xDiff,yDiff);
            }
        }

        return count;
    }

    // Sub-Method
    private static boolean checkBound(int val) {return 0<=val && val<n;}
}
