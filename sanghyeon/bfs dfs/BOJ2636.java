package year_2021.month_11.day_01;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.*;

public class BOJ2636 {

    private static final int AIR=0, CHEESE=1;
    private static int[][] board;
    private static int totalCheese =0, day=0;
    private static int height, width;

    public static void main(String[] args) throws Exception {
        readInput();

        Set<Point> air = new HashSet<>();
        air.add(new Point(0, 0));
        boolean[][] checked = new boolean[height][width];
        checked[0][0] = true;

        while (totalCheese!=0){
            HashSet<Point> allAir = new HashSet<>(air);
            Queue<Point> pointQueue = new LinkedList<>(air);
            while(!pointQueue.isEmpty()){
                Point p =pointQueue.poll();
                ArrayList<Point> airs = searchNear(p, AIR);
                for(Point pp : airs){
                    if(!checked[pp.y][pp.x]) {
                        allAir.add(pp);
                        pointQueue.add(pp);
                        checked[pp.y][pp.x]=true;
                    }
                }
            }
            Set<Point> cheese = new HashSet<>();
            for(Point p : allAir) cheese.addAll(searchNear(p, CHEESE));
            for(Point p : cheese) board[p.y][p.x] = AIR;
            day++;
            totalCheese -= cheese.size();
            air = cheese;
        }
        System.out.println(day);
        System.out.println(air.size());
    }
    private static ArrayList<Point> searchNear(Point startPoint, int searchType) {
        ArrayList<Point> result = new ArrayList<>();
        result.add(searchNearDirection(startPoint, searchType, 1, 0));
        result.add(searchNearDirection(startPoint, searchType, 0, 1));
        result.add(searchNearDirection(startPoint, searchType, -1, 0));
        result.add(searchNearDirection(startPoint, searchType, 0, -1));
        result.removeAll(Collections.singleton(null));
        return result;
    }
    private static Point searchNearDirection(Point startPoint, int searchType, int dx, int dy) {
        try{
            Point p = new Point(startPoint.x+dx, startPoint.y+dy);
            if(searchType==board[p.y][p.x]) return p;
        }catch (Exception ignored){}
        return null;
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        board = new int[height][width];

        for(int h=0; h<height; h++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int w=0; w<width; w++){
                board[h][w] = Integer.parseInt(st2.nextToken());
                if(board[h][w]==1) totalCheese++;
            }
        }
    }
}
