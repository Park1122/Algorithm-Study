package year_2021.month_10.day_25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ7576 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st1.nextToken());
        int y = Integer.parseInt(st1.nextToken());
        int[][] tomatoes = new int[y][x];

        for(int yIndex = 0; yIndex<y; yIndex++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int xIndex = 0; xIndex<x; xIndex++){
                tomatoes[yIndex][xIndex] = Integer.parseInt(st2.nextToken());
            }
        }

        ArrayList<Point> points = new ArrayList<>();
        points.addAll(getValue(tomatoes, 1));
        int repeat = -1;
        while(!points.isEmpty()){
            ArrayList<Point> newPoints = new ArrayList<>();
            for(Point p : points){
                effect(tomatoes, newPoints, p, 1, 0);
                effect(tomatoes, newPoints, p, 0, 1);
                effect(tomatoes, newPoints, p, -1, 0);
                effect(tomatoes, newPoints, p, 0, -1);
            }
            points = newPoints;
            repeat++;
        }

        if(getValue(tomatoes, 0).size()!=0) System.out.println(-1);
        else System.out.println(repeat);
    }

    private static void effect(int[][] tomatoes, ArrayList<Point> newPoints, Point p, int dx, int dy) {
        try{
            Point newPoint = new Point(p.x+dx, p.y+dy);
            if(tomatoes[newPoint.y][newPoint.x]==0) {
                tomatoes[newPoint.y][newPoint.x]=1;
                newPoints.add(newPoint);
            }
        }catch(Exception ignored){}
    }

    private static ArrayList<Point> getValue(int[][] tomatoes, int value) {
        ArrayList<Point> points = new ArrayList<>();
        for(int y = 0; y < tomatoes.length; y++){
           for(int x = 0; x < tomatoes[0].length; x++){
               if(tomatoes[y][x] == value) points.add(new Point(x, y));
           }
        }
        return points;
    }

    private static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }
}
