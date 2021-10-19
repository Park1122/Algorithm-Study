package sujeong.dfs_bfs;

import java.io.IOException;
import java.util.*;

public class BOJ7569 {
    // https://www.acmicpc.net/problem/7569

    // 소요시간 >>
    // 23:00 ~ 4:00 (약 5시간 )

    // 아이디어 >>
    // 높이,세로,가로 순으로 map에 입력받은 토마토의 위치를 담는다.
    // 그리고 익은 토마토를 큐에 담아 주변 토마토를 익히고
    // 그렇게 익은 토마토에 기존 익은 토마토의 값+1을 해주어
    // 가장 마지막에 익은 토마토는 걸린 날짜+1이 되도록 돌려준다.
    // 출력에서 가장 큰값을 찾아다니며 익지 않은 토마토를 찾으면 -1
    // 탐색 후에 가장 큰 값이 1이라면 처음부터 모두 익었단 의미이기 때문에 0
    // 그외에는 가장 큰 값 - 1을 출력한다.

    // 에러로그 >>
    // 시간초과 -> Set으로 Point의 중복을 피해보고자 검색...
    //            Set이 중복을 체크하는데 쓰는게 hashCode와 equals라서
    //            Point클래스에 hashCode와 equals를 만듦...
    //            clone은 시간도 오래걸리고 iterator도 번거롭다.
    // 시간초과 ->100 30 10 정도만 되어도 시간이 오래 걸린다.
    //          퍼지기만 하면 되니까 최소한으로 간략하게 만드는 데 초점을 둬야할 듯.
    // 시간초과 -> 불필요한 복제, 클래스, 함수 최대한 삭제 (초반에 비하면 마지막 결과는 반의반으로 양이 줄어듬...)

    // Variable
    private static Scanner sc = new Scanner(System.in);
    private static int M, N, H; // 가로 세로 높이
    private static int[][][] map;
    private static Queue<Point> ripedPoints;

    // Constants
    // 토마토 상자 위에서 봤을 때,
    // 현재 익은 토마토의 같은 층 시계방향 순 -> 윗층(나와 가까운 층) -> 아래층(나와 먼 층)
    private static final int[] xDiff = {0,1,0,-1,0,0};
    private static final int[] yDiff = {1,0,-1,0,0,0};
    private static final int[] hDiff = {0,0,0,0,1,-1};

    // Main
    public static void main(String[] args) throws IOException, InterruptedException{
        // Input - get basic Information
        M = sc.nextInt();
        N = sc.nextInt();
        H = sc.nextInt();

        // Initialize
        map = new int[H][N][M]; // 높이 세로 가로 순으로 배열
        ripedPoints = new LinkedList<>();

        // Input
        input();

        // Logic
        logic();

        // Output
        output();
    }

    // Input
    private static void input(){
        // get map information
        for(int h=0;h<H;h++){
            for(int n=0;n<N;n++){
                for(int m=0;m<M;m++){
                    map[h][n][m] = sc.nextInt();
                    if(map[h][n][m]==1) ripedPoints.add(new Point(h,n,m));
                }
            }
        }
    }

    // Logic
    private static void logic(){
        while(!ripedPoints.isEmpty()) {
            // get a riped tomato
            Point rp = ripedPoints.poll();

            // 방향 별 토마토 익히기
            for (int i = 0; i < 6; i++) {
                int tmpX = rp.x + xDiff[i];
                int tmpY = rp.y + yDiff[i];
                int tmpH = rp.h + hDiff[i];

                // 범위 체크
                if (!checkBound(tmpX, tmpY, tmpH)) continue;

                // spread it
                if (map[tmpH][tmpY][tmpX] == 0) {
                    map[tmpH][tmpY][tmpX] = map[rp.h][rp.y][rp.x] + 1;
                    ripedPoints.add(new Point(tmpH, tmpY, tmpX));
                }
            }
        }
    }

    // Output
    private static void output(){
        // initialize ans
        int ans = Integer.MIN_VALUE;

        // find unripe
        for(int h=0;h<H;h++){
            for(int n=0;n<N;n++){
                for(int m=0;m<M;m++){
                    // 안익은게 있다.
                    if (map[h][n][m] == 0) {
                        System.out.println(-1);
                        return; // 빠른 종료를 위함.
                    }
                    // 가장 큰 수를 찾아나감 (가장 마지막에 익은 토마토 값 -1 = 토마토가 모두 익는데 걸린 시간)
                    ans = Math.max(ans,map[h][n][m]);
                }
            }
        }
        // 처음부터 다 익었던 거라면 0, 아니라면 ans-1(걸린 날짜 = 최대 날짜 -1)
        if(ans==1) System.out.println(0);
        else System.out.println(ans-1);
    }

    // Method - for condition check
    private static boolean checkBound(int tmpX, int tmpY, int tmpH){
        return 0<=tmpX && tmpX<M && 0<=tmpY && tmpY<N && 0<=tmpH && tmpH<H;
    }

    // Inner Class
    private static class Point {
        // Variable
        int x,y,h;

        // Constructor
        public Point(int h, int y, int x){
            this.x=x;
            this.y=y;
            this.h=h;
        }
    }
}
