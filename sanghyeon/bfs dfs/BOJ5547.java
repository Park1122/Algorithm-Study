package year_2021.month_11.day_02;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.*;

public class BOJ5547 {

    /**
     * 1. 주어진 좌표들을 감싸는 영역을 추가한다.
     * 2. (0, 0)으로 bfs 하여 건물 바깥의 영역을 찾는다.
     * 3. 찾은 영역에 붙어있는 건물의 수를 확인한다.
     */

    private static int[][] Map;
    private static int W, H;

    public static void main(String[] args) throws IOException {
        readInputWithPadding();
        ArrayList<Point> outsidePoints = getOutsidePoints();
        int outSideWallCount = getMeetHouseCount(outsidePoints);
        System.out.println(outSideWallCount);
    }

    private static ArrayList<Point> getOutsidePoints() {
        boolean[][] check = new boolean[H][W];
        ArrayList<Point> result = new ArrayList<>();
        Queue<Point> points = new LinkedList<>();
        points.add(new Point(0, 0));
        while ( ! points.isEmpty()){
            Point p = points.poll();
            for(Point meetPoint : getMeetPoint(p)) {
                if(Map[meetPoint.y][meetPoint.x]==0 && ! check[meetPoint.y][meetPoint.x]){
                    check[meetPoint.y][meetPoint.x] = true;
                    result.add(meetPoint);
                    points.add(meetPoint);
                }
            }
        }
        return result;
    }
    private static int getMeetHouseCount(ArrayList<Point> points) {
        int count = 0;
        for(Point point : points){
            ArrayList<Point> meetPoints = getMeetPoint(point);
            for(Point meetPoint : meetPoints){
                if(Map[meetPoint.y][meetPoint.x]==1) count++;
            }
        }
        return count;
    }
    private static ArrayList<Point> getMeetPoint(Point p) {
        ArrayList<Point> temp = new ArrayList<>(Arrays.asList(new Point(p.x + 1, p.y), new Point(p.x - 1, p.y), new Point(p.x, p.y + 1), new Point(p.x, p.y - 1)));
        if(p.y%2==0) temp.addAll(Arrays.asList(new Point(p.x-1, p.y-1), new Point(p.x-1, p.y+1)));
        else temp.addAll(Arrays.asList(new Point(p.x+1, p.y-1), new Point(p.x+1, p.y+1)));
        ArrayList<Point> result = new ArrayList<>();
        for(Point tempPoint : temp) if(isValidatePoint(tempPoint)) result.add(tempPoint);
        return result;
    }
    private static boolean isValidatePoint(Point point) {
        try {
            int i = Map[point.y][point.x];
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private static void readInputWithPadding() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken())+2;
        H = Integer.parseInt(st.nextToken())+2;
        Map = new int[H][W];
        for(int h=1; h<H-1; h++){
            st = new StringTokenizer(br.readLine());
            for(int w=1; w<W-1; w++) Map[h][w] = Integer.parseInt(st.nextToken());
        }
    }
}
