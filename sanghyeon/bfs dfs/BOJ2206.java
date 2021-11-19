package year_2021.month_11.day_18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {

    private static Queue<Point> points;
    private static int[][] map;
    private static int[][] check;
    private static int sizeX, sizeY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sizeY = Integer.parseInt(st.nextToken());
        sizeX = Integer.parseInt(st.nextToken());

        map = new int[sizeY][sizeX];
        check = new int[sizeY][sizeX];
        for(int y=0; y<sizeY; y++){
            String line = br.readLine();
            for(int x=0; x<sizeX; x++){
                map[y][x] = line.charAt(x)-'0';
                check[y][x] = Integer.MAX_VALUE;
            }
        }

        int depth = 0;
        boolean success = false;
        points = new LinkedList<>();
        points.add(new Point(0, 0, 1,0));
        while (!points.isEmpty()){
            Point nowPoint = points.poll();
            if(nowPoint.x==sizeX-1 && nowPoint.y==sizeY-1){
                depth = nowPoint.distance;
                success = true;
                break;
            }
            testAndAdd(nowPoint, 1, 0);
            testAndAdd(nowPoint, 0, 1);
            testAndAdd(nowPoint, -1, 0);
            testAndAdd(nowPoint, 0, -1);
        }

        System.out.println(success? depth:-1);
    }

    private static void testAndAdd(Point nowPoint, int dx, int dy) {
        Point newPoint = new Point(nowPoint, dx, dy);
        if(newPoint.x<0 || newPoint.x>=sizeX || newPoint.y<0 || newPoint.y>=sizeY) return;
        if(check[newPoint.y][newPoint.x] > newPoint.breakWall){
            if(map[newPoint.y][newPoint.x]==0){
                points.add(newPoint);
                check[newPoint.y][newPoint.x]= newPoint.breakWall;
            }else if(newPoint.breakWall==0){
                newPoint.breakWall++;
                points.add(newPoint);
                check[newPoint.y][newPoint.x]= newPoint.breakWall;
            }
        }
    }

    private static class Point {
        int x, y, distance;
        int breakWall;
        public Point(int x, int y, int distance, int breakWall){
            this.x=x;
            this.y=y;
            this.distance=distance;
            this.breakWall=breakWall;
        }
        public Point(Point point, int dx, int dy){
            this.x=point.x+dx;
            this.y=point.y+dy;
            this.distance=point.distance+1;
            this.breakWall=point.breakWall;
        }
    }
}
