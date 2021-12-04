package sujeong.dfs_bfs;

import java.util.*;
import java.io.*;

public class BOJ16932 {
    // https://www.acmicpc.net/problem/16932

    // 소요시간 >>
    // 6시간
    // 너무..,, 너무,,, 어려웠따...

    // 아이디어 >>
    // 그룹들을 먼저 찾아서 그 크기를 저장해두고,
    // 모든 0을 방문하면서 사방에 있는 그룹들의 값을 다 더해 최대값을 갱신해나간다.

    // 에러로그 >>
    // 시간 초과 - 1의 주변에 1을 추가해가는 방법은 중복도 많고 로직도 길어서 시간이 초과되었다.
    // 시간초과  - orgArr에서 모든 0을 방문하는 방식에서 입력받을 때 0만 따로 골라내어 이들만 돌도록 하여 해결
    //          - 어레이를 쓰기위해 여러 값을 저장하는 대신, hashset을 사용하여 해결

    // Constant
    private static final int[][] diff = {{0,1},{1,0},{0,-1},{-1,0}};

    // Variable
    private static int row,col; // 세로길이, 가로길이
    private static int cnt, gIdx; // 1들의 그룹 구성원의 수를 갱신하기 위해 사용, 그룹 번호를 나타내기 위해 사용
    private static int[][] orgArr, visited; // 맵정보 담는 배열, 방문여부를 담는 배열
    private static int[] glist; // 그룹들의 크기를 담는 배열


    // Main
    public static void main(String[] args) throws IOException{
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        // draw map(input orgArr)
        orgArr = new int[row][col];
        visited = new int[row][col];
        for (int y = 0; y < row; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < col; x++) orgArr[y][x] = Integer.parseInt(st.nextToken());
        }

        // initialize for grouping
        gIdx=2; // 1은 혼자 떨어져있는 1들을 위해 냅두고, 2부터 시작.
        glist = new int[row*col+1]; // 가로 x 세로 정도면 1010식으로 하나씩 1이 떨어져있어도 그룹의 수를 충분히 감당할 수 있다. (고정적으로 10000001 하는 건 크기가 너무 크다.)
        Queue<int[]> zero = new ArrayDeque<>(); // 0의 좌표를 담기위한 큐

        // find groups
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++){
                // if 0 -> into zero queue
                if(orgArr[y][x]==0) zero.add(new int[]{y,x});
                // if 1 -> dfs
                else {
                    dfs(y,x, gIdx);
                    gIdx++;
                }
            }
        }

        // find answer
        int ans = 0;
        while(!zero.isEmpty()){
            // get a zero point
            int[] cur = zero.poll();
            int y= cur[0];
            int x= cur[1];
            // explore locations that could be the largest group
            int tmp = 1; // 일시적인 정답을 담음.
            HashSet<Integer> set = new HashSet<>(); // visited와 동일하게 사용됨. (nowGIdx를 위한 visited임)
            for(int i=0;i<4;i++){
                // get a tmp Point
                int tmpY = y+diff[i][0];
                int tmpX = x+diff[i][1];
                // check condition
                if(!checkBound(tmpY,tmpX)) continue; // 범위체크
                int nowGIdx = orgArr[tmpY][tmpX]; // orgArr에 그룹인덱스가 담겨있으니 이를 가져와서 사용.
                if(nowGIdx==0 || set.contains(nowGIdx)) continue; // 1인지 여부 체크 & 방문여부체크
                // set value
                set.add(nowGIdx); // 방문 체크
                tmp+=glist[nowGIdx]; // tmp(일시적 ans)에 연결될 그룹원의 수(glist[nowGIdx])를 더한다.

            }
            // update ans
            ans=Math.max(ans,tmp);
        }

        // Output
        System.out.println(ans);
    }

    // Method - for grouping
    private static void dfs(int y,int x,int idx){
        // add start point;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{y,x});

        int sum = 0; // 그룹원의 수
        while(!q.isEmpty()){
            // get a tmp point
            int[] cur = q.poll();
            int tmpY = cur[0];
            int tmpX = cur[1];
            // set group info
            if(orgArr[tmpY][tmpX]==0) continue; // 1을 묶는 것이기 때문에 제외
            orgArr[tmpY][tmpX] = idx; // 그룹 인덱스 부여
            sum++;
            // explore the surroundings
            for(int i=0;i<4;i++){
                // calc a new tmp point
                int newY = tmpY+diff[i][0];
                int newX = tmpX+diff[i][1];
                // 범위체크 & 1인지 체크(시간단축을 위함)
                if(!checkBound(newY,newX) || orgArr[newY][newX]==0) continue;
                // for next
                q.add(new int[]{newY,newX});
            }
        }
        // set group number
        glist[idx] = sum; // 해당 그룹인덱스(idx)의 그룹원의 수를 sum으로 변경한다.
    }

    // Method - 범위를 체크하는 함수
    private static boolean checkBound(int tmpY, int tmpX) {return 0 <= tmpX && tmpX < col && 0 <= tmpY && tmpY < row; }
}