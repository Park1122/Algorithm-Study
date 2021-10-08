package year_2021.month_10.day_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P186_BOJ2178_¹Ì·ÎÅ½»ö {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[][] map = new int[h][w];
        for(int i=0; i<h; i++){
            String line = br.readLine();
            for(int j=0; j<w; j++){
                map[i][j] = line.charAt(j)-'0';
            }
        }

        int answer = 0;
        Queue<Point> pointQueue = new LinkedList<>();
        pointQueue.add(new Point(0, 0, 1));
        while(pointQueue.size()!=0){
            Point p = pointQueue.poll();
            if(map[p.y][p.x]==1){
                map[p.y][p.x] = 2;
                System.out.println(p.x+", "+p.y+", "+p.depth);
                if(p.y == h-1 && p.x == w-1) {answer = p.depth; pointQueue.clear();}
                try{ if(map[p.y+1][p.x]==1) pointQueue.add(new Point(p.y+1, p.x, p.depth+1)); }catch(Exception ignored){}
                try{ if(map[p.y-1][p.x]==1) pointQueue.add(new Point(p.y-1, p.x, p.depth+1)); }catch(Exception ignored){}
                try{ if(map[p.y][p.x+1]==1) pointQueue.add(new Point(p.y, p.x+1, p.depth+1)); }catch(Exception ignored){}
                try{ if(map[p.y][p.x-1]==1) pointQueue.add(new Point(p.y, p.x-1, p.depth+1)); }catch(Exception ignored){}
            }
        }
        System.out.println(answer);
    }

    private static class Point {
        public int y, x, depth;
        public Point(int y, int x, int depth){
            this.y = y;
            this.x = x;
            this.depth = depth;
        }
    }
}
