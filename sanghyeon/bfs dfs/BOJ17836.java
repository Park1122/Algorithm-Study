package year_2021.month_11.day_03;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17836 {

    private static final int WALL=1, GRAM=2, PRINCESS=3, MAX_TIME = 100000;
    private static int N, M, T, D;
    private static int[][] MAP;

    public static void main(String[] args) throws IOException {
        readInput();
        int minTime = Math.min(getJustFindTime(), getWithGramFindTime());
        System.out.println(minTime <= T ? minTime : "Fail");
    }

    private static int getJustFindTime() { return getFindTargetTime(PRINCESS); }
    private static int getWithGramFindTime() { return getFindTargetTime(GRAM) + getGramToPrincessTime(); }
    private static int getGramToPrincessTime() {
        for(int h=0; h<N; h++){
            for(int w=0; w<M; w++){
                if(MAP[h][w] == GRAM) return (N-1-h) + (M-1-w);
            }
        }
        return MAX_TIME;
    }
    private static int getFindTargetTime(int target) {
        int result = MAX_TIME;
        int[][] check = new int[N][M];
        Queue<Point> pointQueue = new LinkedList<>();
        pointQueue.add(new Point(0,0));
        check[0][0] = 1;
        while ( ! pointQueue.isEmpty()) {
            Point p = pointQueue.poll();
            if(MAP[p.y][p.x]==target) { result = check[p.y][p.x]-1; break; }
            pointQueue.addAll(getNearMovablePoint(p, check));
        }

        return result;
    }
    private static ArrayList<Point> getNearMovablePoint(Point p, int[][] check) {
        ArrayList<Point> result = new ArrayList<>();
        testAndAddPoint(p, check, result, 1, 0);
        testAndAddPoint(p, check, result, 0, 1);
        testAndAddPoint(p, check, result, -1, 0);
        testAndAddPoint(p, check, result, 0, -1);
        return result;
    }
    private static void testAndAddPoint(Point p, int[][] check, ArrayList<Point> result, int dx, int dy) {
        try{
            Point point = new Point(p.x+dx, p.y+dy);
            if(MAP[point.y][point.x]!=WALL && check[point.y][point.x]==0){
                check[point.y][point.x] = check[p.y][p.x]+1;
                result.add(point);
            }
        }catch (Exception ignored){}
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        MAP = new int[N][M];
        for(int h=0; h<N; h++){
            st = new StringTokenizer(br.readLine());
            for(int w=0; w<M; w++) MAP[h][w] = Integer.parseInt(st.nextToken());
        }
        MAP[N-1][M-1] = PRINCESS;
    }
}
