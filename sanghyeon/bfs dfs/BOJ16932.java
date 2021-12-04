package year_2021.month_12.day_01;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16932 {

    static int[][] map, temp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        temp = new int[H][W];

        for(int y=0; y<H; y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<W; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int max =0;
        for(int y=0; y<H; y++){
            for(int x=0; x<W; x++){
                if(map[y][x]==1 && temp[y][x]==0){
                    Queue<Point> pointQueue = new LinkedList<>();
                    HashSet<Point> nearZero = new HashSet<>();
                    pointQueue.add(new Point(x, y));
                    temp[y][x]=-1;
                    int size = 0;
                    while (!pointQueue.isEmpty()){
                        size++;
                        Point p = pointQueue.poll();
                        add(pointQueue, nearZero, p, 1, 0);
                        add(pointQueue, nearZero, p, 0, 1);
                        add(pointQueue, nearZero, p, -1, 0);
                        add(pointQueue, nearZero, p, 0, -1);
                    }
                    for(Point p : nearZero){
                        temp[p.y][p.x] += size;
                        max = Math.max(max, temp[p.y][p.x]);
                    }
                }
            }
        }
        System.out.println(max+1);
    }

    private static void add(Queue<Point> pointQueue, HashSet<Point> nearZero, Point p, int dx, int dy) {
        Point newPoint = new Point(p.x+dx, p.y+dy);
        try{
            if(map[newPoint.y][newPoint.x]==1 && temp[newPoint.y][newPoint.x]==0){
                temp[newPoint.y][newPoint.x]=-1;
                pointQueue.add(newPoint);
            }else if(map[newPoint.y][newPoint.x]==0){
                nearZero.add(newPoint);
            }
        }catch (Exception ignored){}
    }

}
