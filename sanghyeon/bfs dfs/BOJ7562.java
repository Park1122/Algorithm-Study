package year_2021.month_10.day_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7562 {

    public static void main(String[] args) throws IOException {
        int[][] moves = {{1,2}, {1,-2}, {2,1}, {2,-1}, {-1,2}, {-1,-2}, {-2,1}, {-2,-1}};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcaseCount = Integer.parseInt(br.readLine());
        for(int testNum =0; testNum<testcaseCount; testNum++){
            int boardSize = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int horseX = Integer.parseInt(st.nextToken()), horseY = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int targetX = Integer.parseInt(st.nextToken()), targetY = Integer.parseInt(st.nextToken());

            if(horseX==targetX && horseY==targetY) System.out.println(0);
            else{
                int[][] board = new int[boardSize][boardSize];
                board[horseY][horseX] = 1;
                board[targetY][targetX] = 2;

                Queue<Point> pointQueue = new LinkedList<>();
                pointQueue.add(new Point(horseY, horseX, 0));
                int minMove = -1;
                while(minMove==-1){
                    Point pollPoint = pointQueue.poll();
                    assert pollPoint != null;
                    for(int[] move : moves){
                        int newY = pollPoint.y+move[1], newX = pollPoint.x+move[0];
                        try{
                            if(board[newY][newX]==0){
                                board[newY][newX]=1;
                                pointQueue.add(new Point(newY, newX, pollPoint.depth+1));
                            } else if(board[newY][newX]==2){
                                minMove = pollPoint.depth+1;
                                break;
                            }
                        }catch (Exception ignored){/*Out Of Index*/}
                    }
                }
                System.out.println(minMove);
            }
        }
    }

    private static class Point {
        int y, x, depth;
        public Point(int y, int x, int depth){
            this.y=y;
            this.x=x;
            this.depth=depth;
        }
    }

}
