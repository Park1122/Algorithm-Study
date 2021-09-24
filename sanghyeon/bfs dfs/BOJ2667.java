package year_2021.month_09.day_23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ2667 {

    static int n, count;
    static int[][] town, checkedTown;
    static ArrayList<Integer> houseCounts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        houseCounts = new ArrayList<>();
        n = Integer.parseInt(br.readLine());
        town = new int[n][n];
        checkedTown = new int[n][n];
        for(int i=0; i<n; i++){
            String line = br.readLine();
            for(int j=0; j<n; j++){
                town[i][j] = line.charAt(j) - '0';
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(town[i][j]!=0 && checkedTown[i][j]==0) { // unchecked apart
                    count = 0;
                    travel(i, j);
                    houseCounts.add(count);
                }
            }
        }

        System.out.println(houseCounts.size());
        houseCounts.sort(Integer::compare);
        for(int i : houseCounts) System.out.println(i);
    }

    private static void travel(int i, int j) {
        boolean rightFormat = 0<=i && i<n && 0<=j && j<n;
        if(rightFormat){
            boolean unCheckedHouse = town[i][j]==1 && checkedTown[i][j]==0;
            if(unCheckedHouse){
                checkedTown[i][j] = houseCounts.size()+1; // 1~n
                count++;

                travel(i+1, j);
                travel(i-1, j);
                travel(i, j+1);
                travel(i, j-1);
            }
        }
    }
}
