package year_2021.month_09.day_29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3184 {

    static int sheepTemp, wolfTemp;
    static boolean[][] checked;
    static char[][] map;

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        map = new char[h][w];
        for(int i=0; i<h; i++){
            String line = br.readLine();
            for(int j=0; j<w; j++){
                map[i][j] = line.charAt(j);
            }
        }
        checked = new boolean[h][w];

        int totalSheep = 0, totalWolf = 0;
        for(int i=0; i<h; i++){
            for(int j=0; j<w; j++){
                if(!checked[i][j] && map[i][j]!='#'){
                    sheepTemp = wolfTemp = 0;
                    travel(i, j);
                    if(wolfTemp < sheepTemp) wolfTemp =0;
                    else sheepTemp = 0;
                    totalSheep+= sheepTemp;
                    totalWolf+= wolfTemp;
                }
            }
        }
        System.out.println(totalSheep+" "+totalWolf);
    }

    private static void travel(int i, int j) {
        if(i<0 || map.length<=i || j<0 || map[0].length<=j) return;

        if(!checked[i][j] && map[i][j]!='#'){
            checked[i][j]=true;
            if(map[i][j]=='v') wolfTemp++;
            else if(map[i][j]=='o') sheepTemp++;

            travel(i+1, j);
            travel(i, j+1);
            travel(i-1, j);
            travel(i, j-1);
        }
    }
}
