package year_2021.month_10.day_28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16234 {

    private static int N, L, R;
    private static int[][] land, areas;
    private static int day=-1, sum, n;
    private static final Queue<Point> queue = new LinkedList<>();
    private static final ArrayList<Integer> temp = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        read();

        areas = new int[N][N];
        while(true){
            day++;
            for(int y = 0; y<N; y++){
                for(int x = 0; x<N; x++){
                    if(areas[y][x]==0){
                        sum=0;
                        n=0;
                        queue.add(new Point(x, y));
                        int i=0;
                        while(!queue.isEmpty()){ // 여기를 오ㅓㅐ도누도누
                            Point p = queue.poll();
                            if(areas[p.y][p.x]!=0) continue;
                            areas[p.y][p.x] = temp.size()+1;
                            sum+=land[p.y][p.x];
                            n++;
                            checkArea(p, 1, 0);
                            checkArea(p, 0, 1);
                            checkArea(p, -1, 0);
                            checkArea(p, 0, -1);
                        }
                        temp.add(sum/n);
                    }
                }
            }

            boolean end = true;
            for(int y = 0; y<N; y++){
                for(int x = 0; x<N; x++){
                    if(land[y][x] != temp.get(areas[y][x]-1)) end = false;
                    land[y][x] = temp.get(areas[y][x]-1);
                    areas[y][x]=0;
                }
            }
            if (end) break;
            temp.clear();
        }
        System.out.println(day);
    }
    private static void checkArea(Point p, int dx, int dy) {
        try{
            int diff = Math.abs(land[p.y][p.x]-land[p.y+dy][p.x+dx]);
            if(L<=diff && diff<=R && areas[p.y+dy][p.x+dx]==0)
                queue.add(new Point(p.x+dx, p.y+dy));
        }catch (Exception ignored){}
    }
    private static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    private static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        land = new int[N][N];
        StringTokenizer st2;
        for(int y = 0; y<N; y++){
            st2 = new StringTokenizer(br.readLine());
            for(int x = 0; x<N; x++){
                land[y][x] = Integer.parseInt(st2.nextToken());
            }
        }
    }
}
