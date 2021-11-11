package year_2021.month_11.day_11;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16973 {

    static int[][] map, distanceMap;
    static int H, W;
    static Queue<Point> srscPoints;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int y=0; y<N; y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<M; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int Sy = Integer.parseInt(st.nextToken())-1;
        int Sx = Integer.parseInt(st.nextToken())-1;
        int Fy = Integer.parseInt(st.nextToken())-1;
        int Fx = Integer.parseInt(st.nextToken())-1;

        distanceMap = new int[N][M];
        distanceMap[Sy][Sx] = 1;
        srscPoints = new LinkedList<>();
        srscPoints.add(new Point(Sx, Sy));

        while(!srscPoints.isEmpty()){
            Point nowPoll = srscPoints.poll();
            checkAndMove(nowPoll, 1, 0);
            checkAndMove(nowPoll, 0, 1);
            checkAndMove(nowPoll, -1, 0);
            checkAndMove(nowPoll, 0, -1);

            if(distanceMap[Fy][Fx]!=0) break;
        }
        System.out.println(distanceMap[Fy][Fx]-1);
    }

    private static void checkAndMove(Point nowPoll, int dx, int dy) {
        try{
            Point p = new Point(nowPoll.x+dx, nowPoll.y+dy);
            if(distanceMap[p.y][p.x]==0 && possibleMove(p)){
                distanceMap[p.y][p.x] = distanceMap[nowPoll.y][nowPoll.x]+1;
                srscPoints.add(p);
            }
        }catch(Exception ignored){}
    }

    private static boolean possibleMove(Point p) {
        for(int y=0; y<H; y++){
            for(int x=0; x<W; x++){
                if(map[p.y+y][p.x+x]==1) return false;
            }
        }
        return true;
    }
}
