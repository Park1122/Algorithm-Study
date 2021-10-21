package year_2021.month_10.day_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ18404 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int wh = Integer.parseInt(st1.nextToken());
        int n = Integer.parseInt(st1.nextToken());
        int[][] moveMap = new int[wh][wh];
        for(int i=0; i<wh; i++){
            for(int j=0; j<wh; j++){
                moveMap[i][j] = -1;
            }
        }

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int horseX = Integer.parseInt(st2.nextToken())-1;
        int horseY = Integer.parseInt(st2.nextToken())-1;
        moveMap[horseY][horseX] = 0;

        int[][] moves = {{1,2}, {1,-2}, {2,1}, {2,-1}, {-1,2}, {-1,-2}, {-2,1}, {-2,-1}};
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(horseX, horseY));

        for(int i=0; i<n; i++){
            StringTokenizer st3 = new StringTokenizer(br.readLine());
            int targetX = Integer.parseInt(st3.nextToken())-1;
            int targetY = Integer.parseInt(st3.nextToken())-1;
            if(moveMap[targetY][targetX] != -1) {
                System.out.print(moveMap[targetY][targetX]+" ");
            }else{
                boolean stop = false;
                while (!stop && !queue.isEmpty()){
                    Point p = queue.poll();
                    for(int[] move : moves){
                        try{
                            Point newP = new Point(p.x + move[0], p.y + move[1]);
                            if(moveMap[newP.y][newP.x] == -1){
                                moveMap[newP.y][newP.x] = moveMap[p.y][p.x] + 1;
                                queue.add(newP);
                            }
                            if(targetY==newP.y && targetX==newP.x) {stop = true;}
                        }catch(Exception ignored){}
                    }
                }
                System.out.print(moveMap[targetY][targetX]+" ");
            }
        }
    }

    public static class Point {
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
