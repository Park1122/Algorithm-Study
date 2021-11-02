package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2636 {
    // 소요시간 >>
    // 1시간 30분

    // 아이디어 >>
    // 맨위에서부터 빈곳으로 훑는데, 빈곳 기준 치즈인 곳이 자신과 맞닿아 있으면
    // 녹은 치즈로 체크하고 (+1), 자신이 옆으로 이동할 수 없는 공백일때는 그외로 넘어감.
    // 그렇게 치즈를 위에서부터 차례대로 녹여내고, 모든 치즈를 녹이고 난 뒤,
    // 걸린 날짜와 같은 값을 가진 칸의 수를 세어 2번쨰 줄에 출력한다.

    // +궁금+ 주석달면서 정리하다가 logic부분을 main으로 올렸는데 시간과 메모리 모두 증가하여서 다시 분리함.

    // 에러로그 >>
    // 틀렸습니다 -  치즈가 없는 경우도 고려해서 처음엔 문제를 체출했는데,
    //              한조각의 치즈가 판위에 주어져있다고 명시해뒀으므로 그럴 수 없단 걸 알고 지웠더니 해결
    // 메모리초과 - 막 녹은 치즈의 좌표만 다음 큐에서 사용하도록 arraylist에 담아둠으로써 메모리 사용량을 줄였다.

    // Constants
    private static final int[] xDiff = {0,1,0,-1};
    private static final int[] yDiff = {1,0,-1,0};

    // Variable
    private static int n,m; // 세로, 가로
    private static int hour = 0; // 치즈가 모두 녹는데 걸리는 시간
    private static int[][] orgArr; // 치즈의 상태를 저장하는 배열
    private static Queue<Point> queue; // 비어있는 공간을 담아 탐색을 하기 위한 큐
    private static boolean[][] visited; // 방문 여부를 저장하는 배열
    private static ArrayList<Point> next; // 다음 시간에 공기가 될 좌표를 미리 담아 후에 넘겨주는 배열

    // Main
    public static void main(String[] args) throws IOException{

        // input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // Initialize
        orgArr = new int[n][m];
        queue = new LinkedList<>();
        visited = new boolean[n][m];
        next = new ArrayList<>();

        for(int y=0;y<n;y++){
            st = new StringTokenizer(reader.readLine()," ");
            for(int x=0;x<m;x++){
                orgArr[y][x] = Integer.parseInt(st.nextToken());
                // 맨 외곽 테두리 중 빈 곳만 큐에 담아서 돌릴 수 있게 함.
                if(orgArr[y][x]==0 && (y==0||y==n-1||x==0||x==m-1)) {
                    queue.add(new Point(y,x));
                    visited[y][x] = true;
                }
            }
        }

        // Logic
        logic();

        // Output
        System.out.println(hour);
        System.out.println(howManyNum(-hour));

    }

    // Method - 로직부분을 따로 분리하여
    private static void logic(){
        for(hour = 1; ; hour++){
            while(!queue.isEmpty()){
                // looking for melted cheese
                Point p = queue.poll();
                for(int i=0;i<4;i++) {
                    int tmpX = p.x + xDiff[i];
                    int tmpY = p.y + yDiff[i];

                    // 방문했거나 범위 이외면 넘어감.
                    if(!checkBound(tmpY,tmpX) || visited[tmpY][tmpX])  continue;
                    visited[tmpY][tmpX] = true; // 방문 체크

                    // 공기와 맞닿는 부분 중에
                    // 위치한 녹지않은 치즈라면, 그 값을 -시간으로 변경하고, 다음 큐에서 사용할 수 있도록 next에 담는다.
                    if(orgArr[tmpY][tmpX]==1) {
                        orgArr[tmpY][tmpX] = -hour;
                        next.add(new Point(tmpY,tmpX));
                    // 공기거나 이미 녹은 치즈라면, 큐에다 넣어 현재 공기로 판단될 곳을 탐색하도록 큐에 담음.
                    } else if(orgArr[tmpY][tmpX] <= 0){
                        queue.add(new Point(tmpY,tmpX));
                    }

                }
            }
            // 남은 치즈가 없다면 for문을 나옴.
            if(howManyNum(1)==0) return;

            // reinitialize
            visited = new boolean[n][m];
            queue.addAll(next);
            next = new ArrayList<>();

        }
    }

    // Method - 남은 치즈(1)의 수를 세거나 언제 녹았는지(-hour) 찾기위해 사용하는 함수
    private static int howManyNum(int num){
        int cnt = 0;
        for(int y=0;y<n;y++){
            for(int x=0;x<m;x++){
                if(orgArr[y][x]==num) cnt++;
            }
        }
        return cnt;
    }

    // Method - 좌표가 배열을 벗어났는지 여부를 판단하기 위한 함수
    private static boolean checkBound(int tmpY, int tmpX){
        return 0<=tmpY && tmpY<n && 0<=tmpX && tmpX<m;
    }

    // Inner Class - x,y 좌표값을 함께 저장하기 위한 클래스
    private static class Point{
        int x,y;
        public Point(int y, int x){
            this.y=y;
            this.x=x;
        }
    }
}
