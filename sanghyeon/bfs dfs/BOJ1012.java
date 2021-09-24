package year_2021.month_09.day_24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1012 {

    static int w, h, n, count;
    static int[][] town, checkedTown;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcaseNumber = Integer.parseInt(br.readLine());
        for(int k = 0; k< testcaseNumber; k++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            town = new int[w][h];
            checkedTown = new int[w][h];
            count = 0;
            for(int i=0; i<n; i++){
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st2.nextToken());
                int y = Integer.parseInt(st2.nextToken());
                town[x][y] = 1;
            }

            for(int i=0; i<w; i++){
                for(int j=0; j<h; j++){
                    if(town[i][j]==1 && checkedTown[i][j]==0) { // unchecked apart
                        travel(i, j);
                        count++;
                    }
                }
            }

            System.out.println(count);
        }
    }

    private static void travel(int i, int j) {
        boolean rightFormat = 0<=i && i<w && 0<=j && j<h;
        if(rightFormat){
            boolean unCheckedHouse = town[i][j]==1 && checkedTown[i][j]==0;
            if(unCheckedHouse){
                checkedTown[i][j] = 1;

                travel(i+1, j);
                travel(i-1, j);
                travel(i, j+1);
                travel(i, j-1);
            }
        }
    }

}
