package year_2021.month_10.day_22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

public class BOJ3055 {

    private static final char WATER = '*', STONE = 'X', EMPTY = '.', DOCHI = 'S', BIBER = 'D';
    private static char[][] map;
    private static ArrayList<Point> waterPoints = new ArrayList<>();
    private static ArrayList<Point> dochiPoints = new ArrayList<>();
    private static Point biberPoint;

    public static void main(String[] args) throws IOException {
        map =readMap();

        int move = 0;
        while(isDochiAlive() && !isDochiArrive()) {
            waterPoints = spread(waterPoints, WATER);
            dochiPoints = spread(dochiPoints, DOCHI);
            System.out.println(isDochiAlive()+", "+isDochiArrive());
            showMap();
            move++;
        }

        if(isDochiArrive()) System.out.println(move);
        else System.out.println("KAKTUS");
    }

    private static void showMap() {
        for(char[] line : map){
            System.out.println(Arrays.toString(line));
        }
        System.out.println();
    }

    private static boolean isDochiAlive() {return dochiPoints.size()!=0;}
    private static boolean isDochiArrive() {return map[biberPoint.y][biberPoint.x] == DOCHI;}

    private static ArrayList<Point> spread(ArrayList<Point> points, char target) {
        ArrayList<Point> newPoints = new ArrayList<>();
        for(Point point : points){
            newPoints.add(isSpreadPossible(new Point(point.x+1, point.y), target));
            newPoints.add(isSpreadPossible(new Point(point.x, point.y+1), target));
            newPoints.add(isSpreadPossible(new Point(point.x-1, point.y), target));
            newPoints.add(isSpreadPossible(new Point(point.x, point.y-1), target));
        }
        newPoints.removeIf(Objects::isNull);
        return newPoints;
    }
    private static Point isSpreadPossible(Point targetPoint, char target) {
        try{
            int targetPointVal = map[targetPoint.y][targetPoint.x];
            boolean dochiSpreadPossible = target==DOCHI && (targetPointVal==EMPTY || targetPointVal==BIBER);
            boolean waterSpreadPossible = target==WATER && (targetPointVal==EMPTY || targetPointVal==DOCHI);
            boolean spreadPossible = dochiSpreadPossible || waterSpreadPossible;
            if(spreadPossible){
                map[targetPoint.y][targetPoint.x] = target;
                return targetPoint;
            }
        }catch (Exception ignored){}
        return null;
    }

    private static char[][] readMap() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] map = new char[R][C];

        for(int y=0; y<R; y++){
            String line = br.readLine();
            for(int x=0; x<C; x++){
                map[y][x] = line.charAt(x);
                if(map[y][x]==WATER) {waterPoints.add(new Point(x, y));}
                else if(map[y][x]==DOCHI) {dochiPoints.add(new Point(x, y));}
                else if(map[y][x]==BIBER) {biberPoint = new Point(x, y);}
            }
        }
        return map;
    }

    private static class Point {
        int x, y;
        public Point(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
}
