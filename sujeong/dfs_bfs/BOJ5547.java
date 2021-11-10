package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ5547 {
    // https://www.acmicpc.net/problem/5547

    // 소요시간 >>
    // 2시간 (육각형은 처음만나서 당황스러웠다)

    // 아이디어 >>
    // 주어진 맵 이외에 빈공간 테두리를 만들어서 테두리는 모두 연결되어있으니까
    // 테두리를 이용해 건물 안의 빈공간을 제외한 빈공간 즉, 바깥 부분만을 따로 체크함.
    // 체크된 바깥부분들만 탐색하면서 바깥부분에서 벽을 만나면 ans(맞닿는 면)을 1 증가시켜준다.
    // 그결과 ans에는 건물의 외부 면의 수가 담겨짐.

    // 에러로그 >>
    // X

    // Constants
    private static final int[][][] diff = {
        {{0,-1}, {1,0}, {0,1}, {-1,1}, {-1,0}, {-1, -1}}, // y가 짝수일 경우
        {{1,-1}, {1,0}, {1,1}, {0,1}, {-1,0}, {0,-1}} // y가 홀수일 경우
    };

    // Variable
    private static int w,h; // 가로, 세로

    // Main
    public static void main(String[] args) throws IOException{
        // Input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        // 앞뒤로 여백을 둔다. (건물이 위치한 1~w 혹은 1~h외에 빈공간이 연결될 부분을 마련하기 위함.)
        int[][] orgArr = new int[h + 2][w + 2]; // 0=빈공간, 1=건물, 2=바깥부분인 빈공간
        for(int y = 1; y<=h ; y++){
            st = new StringTokenizer(reader.readLine()," ");
            for(int x=1;x<=w;x++){
                orgArr[y][x] = Integer.parseInt(st.nextToken());
            }
        }


        // Initialize
        Queue<Space> q = new LinkedList<>(); // 바깥 부분을 담아 빈공간을 연결하는데 사용할 큐
        q.add(new Space(0,0)); // 빈공간의 최상단인 0,0을 넣어줌.

        boolean[][] checkSpace = new boolean[h+2][w+2]; //밖부분으로 체크를 했는지 여부를 담는 배열
        checkSpace[0][0] = true; // 0,0을 큐에 넣었으니 이또한 true로 셋팅.

        orgArr[0][0] =2; // 2=바깥공간임을 의미하기 때문에 orgArr을 미리 바꿔둠.

        // Logic
        // 건물 탐색 및 외벽 표시
        while(!q.isEmpty()){
            // get a empty space
            Space cur = q.poll();

            // 빈공간을 기준으로 주변의 빈공간을 탐색
            for(int i=0;i<6;i++) {
                // get tmp x,y
                int tmpX = cur.x+diff[cur.y%2][i][0];
                int tmpY = cur.y+diff[cur.y%2][i][1];

                // 범위를 벗어나거나 이미 체크된 부분이라면 넘어간다.
                if(!checkBound(tmpX,tmpY) || checkSpace[tmpY][tmpX]) continue;

                // 빈 공간이라면(밖부분이라면)
                if(orgArr[tmpY][tmpX]==0){
                    // 빈공간임을 체크하고, 큐에 넣어서 계속 탐색할 수 있도록 한다.
                    checkSpace[tmpY][tmpX] = true;
                    q.add(new Space(tmpX, tmpY));

                    // 바깥공간임을 체크 (건물 안쪽의 빈공간(0)과 차이를 주기위해 값을 변경함)
                    orgArr[tmpY][tmpX] = 2;
                }
            }
        }

        // 바깥 공간을 돌면서 벽을 만나면 접촉 수(ans)를 추가한다.
        int ans = 0;
        for(int y = 0; y<=h+1 ; y++){
            for(int x=0;x<=w+1;x++){
                // 바깥 공간이라면,
                if(orgArr[y][x]==2){
                    for(int i=0;i<6;i++){
                        // get tmp x,y
                        int tmpX = x+diff[y%2][i][0];
                        int tmpY = y+diff[y%2][i][1];

                        // 범위를 벗어나면 넘어간다.
                        // +) visited가 없는 이유는 한 육각형과 다른 육각형이 만나는 면은 해당 육각형이 지나면 다시 만날수 없는 면이기 떄문이다
                        if(!checkBound(tmpX,tmpY)) continue; // 범위 체크

                        // 벽을 만나면 접촉 면(ans)를 1 증가시켜준다.
                        if(orgArr[tmpY][tmpX]==1) ans++;

                    }

                }
            }
        }

        // Output
        System.out.println(ans);
    }

    // Method - 범위체크
    private static boolean checkBound(int x,int y){ return 0<=x && x<=w+1 && 0<=y && y<=h+1;}

    // Inner Class
    private static class Space {
        int x,y;
        public Space(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
}