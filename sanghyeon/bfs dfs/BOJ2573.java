package year_2021.month_11.day_22;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573 {

    static int width, height;
    static int[][] map;
    static ArrayList<Ice> ices;
    static int[][] arounds = new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public static void main(String[] args) throws IOException {
        readInput();

        int year = 0;
        boolean divided = false;
        while (!ices.isEmpty() && !divided){
            if(isDivided()) divided = true;
            else { melt(); year++; }
        }

        System.out.println(divided? year : 0);
    }

    private static boolean isDivided() {
        Ice ice = ices.get(0);
        Queue<Point> pointsQueue = new LinkedList<>();
        pointsQueue.add(new Point(ice.x, ice.y));

        boolean[][] check = new boolean[height][width];
        check[ice.y][ice.x]=true;
        int count = 1;
        while (! pointsQueue.isEmpty()){
            Point point = pointsQueue.poll();
            for(int[] around : arounds) {
                Point nPoint = new Point(point.x+around[0], point.y+around[1]);
                try{
                    if(map[nPoint.y][nPoint.x]!=0 && !check[nPoint.y][nPoint.x]){
                        count++;
                        pointsQueue.add(nPoint);
                        check[nPoint.y][nPoint.x] = true;
                    }
                }catch (Exception ignored){}
            }
        }
        return count!=ices.size();
    }
    private static void melt() {
        for(Ice ice : ices){
            for (int[] around : arounds) {
                try {
                    if (map[ice.y + around[1]][ice.x + around[0]] == 0) ice.height--;
                } catch (Exception ignored) {}
            }
            ice.height = Math.max(ice.height, 0);
        }
        for(Ice ice : ices){
            map[ice.y][ice.x] = ice.height;
        }
        for(int i=ices.size()-1; i>=0; i--){
            if(ices.get(i).height==0) ices.remove(i);
        }
    }

    private static class Ice {
        int x, y, height;
        public Ice(int x, int y, int height){
            this.x=x;
            this.y=y;
            this.height=height;
        }
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        map = new int[height][width];
        ices = new ArrayList<>();
        for(int y=0; y<height; y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<width; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
                if(map[y][x]!=0) ices.add(new Ice(x, y, map[y][x]));
            }
        }
    }
}
