package year_2021.month_09.day_28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ4963 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            if(w==0 && h==0) break;

            boolean[][] visited = new boolean[h][w];
            int[][] map = new int[h][w];
            for(int i=0; i<h; i++){
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                for(int j=0; j<w; j++){
                    map[i][j] = Integer.parseInt(st2.nextToken());
                }
            }

            int count = 0;
            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    if(!visited[i][j] && map[i][j]==1){
                        travel(visited, map, i, j);
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }

    private static void travel(boolean[][] visited, int[][] map, int i, int j) {
        if(i<0 || visited.length<=i || j<0 || visited[0].length<=j) return;

        if(!visited[i][j] && map[i][j]==1){
            visited[i][j] = true;
            travel(visited, map, i+1, j);
            travel(visited, map, i, j+1);
            travel(visited, map, i-1, j);
            travel(visited, map, i, j-1);
            travel(visited, map, i+1, j+1);
            travel(visited, map, i-1, j-1);
            travel(visited, map, i+1, j-1);
            travel(visited, map, i-1, j+1);
        }
    }

}
