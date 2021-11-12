package year_2021.month_11.day_08;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14940 {

    private static int N, M;
    private static int[][] map;
    private static Point startPoint;

    public static void main(String[] args) throws IOException {
        readInput();
        writeDistance();
        adjustValueAndPrint();
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int y=0; y<N; y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<M; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
                if(map[y][x]==2) startPoint = new Point(x, y);
            }
        }
    }

    private static void writeDistance() {
        Queue<Point> pointQueue = new LinkedList<>();
        pointQueue.add(startPoint);
        while (!pointQueue.isEmpty()){
            Point nowPoint = pointQueue.poll();
            checkPoint(nowPoint, pointQueue, 1, 0);
            checkPoint(nowPoint, pointQueue, 0, 1);
            checkPoint(nowPoint, pointQueue, -1, 0);
            checkPoint(nowPoint, pointQueue, 0, -1);
        }
    }
    private static void checkPoint(Point nowPoint, Queue<Point> pointQueue, int dx, int dy) {
        try{
            Point newPoint = new Point(nowPoint.x+dx, nowPoint.y+dy);
            if(map[newPoint.y][newPoint.x]==1){
                pointQueue.add(newPoint);
                map[newPoint.y][newPoint.x] = map[nowPoint.y][nowPoint.x] + 1;
            }
        }catch (Exception ignored){}
    }

    private static void adjustValueAndPrint() {
        for(int y=0; y<N; y++){
            for(int x=0; x<M; x++){
                switch (map[y][x]){
                    case 1: map[y][x]=-1; break;
                    case 0: map[y][x]=0; break;
                    default: map[y][x]-=2;
                }
                System.out.print(map[y][x]+" ");
            }
            System.out.println();
        }
    }
}
