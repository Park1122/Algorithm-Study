package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14940 {
    // https://www.acmicpc.net/problem/14940

    // 소요시간 >>
    // 1시간 30분

    // 아이디어 >>
    // 해당 문제는 BFS를 꽤 풀어봤다면 쉽게 아이디어를 생각해냈을 문제다.
    // 그 방식을 어떻게 풀어낼지에 대해서 고민한게 가장 컸다.
    // 1. 인풋을 받을 때, for문을 도는 겸 visited를 함께 셋팅해준다.
    // 2. 시작점을 미리 큐에 넣어두고, visited는 빈공간을 모두 -1로 바꿔 후에 못 방문한 곳을 표시하기 좋게 미리 값을 넣어준다.
    // 3. 로직으로 넘어와서 시작점부터 시작해 근처 좌표를 탐색해나간다.
    // 4. 탐색할 떄에는 범위체크, 방문체크, 벽체크를 통과했다면
    // 5. 탐색한점의 visited 값을 현재점의 visited값+1로 바꿔주며 탐색을 계속한다.
    // 6. 모든 탐색이 끝나면 visited를 출력한다.

    // 에러로그 >>
    // X

    // Constants
    private static final int[] xDiff = {0,1,0,-1};
    private static final int[] yDiff = {1,0,-1,0};

    // Variable
    private static int n,m;

    // Main
    public static void main(String[] args) throws IOException{
        // Input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] orgArr = new int[n][m]; // 기본 인풋값을 저장하는 배열
        int[][] visited = new int[n][m]; // 방문여부를 저장하는 배열
        Queue<int[]> q = new ArrayDeque<>(); // 탐색을 위한 큐
        for(int y=0;y<n;y++){
            st = new StringTokenizer(reader.readLine());
            for(int x=0;x<m;x++){
                orgArr[y][x] = Integer.parseInt(st.nextToken());
                if(orgArr[y][x]==1) visited[y][x]=-1;
                if(orgArr[y][x]==2) q.add(new int[]{y,x});
            }
        }

        // Logic
        while(!q.isEmpty()){
            // get a current point
            int[] cur = q.poll();
            // explore the surroundings
            for(int i=0;i<4;i++){
                // calculate tmp point
                int tmpY = cur[0] + yDiff[i];
                int tmpX = cur[1] + xDiff[i];
                // check status (범위, 방문여부, 벽여부)
                if(!checkBound(tmpY,tmpX) || visited[tmpY][tmpX]!=-1 || orgArr[tmpY][tmpX]==0) continue;
                // visited 값 갱신
                visited[tmpY][tmpX] = visited[cur[0]][cur[1]]+1;
                // 큐에 추가하여 탐색을 계속할 수 있게 함.
                q.add(new int[]{tmpY,tmpX});
            }
        }

        // Output
        for(int y=0;y<n;y++) {
            for (int x = 0; x < m; x++) {
                System.out.print(visited[y][x]+" ");
            } System.out.println();
        }
    }

    // Method - 범위체크 (깔끔한게 좋아서 분리해둠)
    private static boolean checkBound(int y, int x){return 0<=y && y<n && 0<=x && x<m;}
}