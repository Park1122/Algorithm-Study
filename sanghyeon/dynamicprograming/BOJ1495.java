package year_2021.month_09.day_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1495 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st1.nextToken());
        int s = Integer.parseInt(st1.nextToken());
        int m = Integer.parseInt(st1.nextToken());
        int[] numbers = new int[n];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            numbers[i] = Integer.parseInt(st2.nextToken());
        }

        boolean[][] memory = new boolean[n+1][m+1]; // index(0~n) val(0~m)
        memory[0][s] = true;
        for(int i=1; i<n+1; i++){
            for(int j=0; j<m+1; j++){
                if(memory[i-1][j]){
                    if(j+numbers[i-1] <= m) memory[i][j+numbers[i-1]] = true;
                    if(j-numbers[i-1] >= 0) memory[i][j-numbers[i-1]] = true;
                }
            }
        }

        int maxVal = -1;
        for(int i=0; i<m+1; i++){
            if(memory[n][i] && i>maxVal) maxVal = i;
        }
        System.out.println(maxVal);
        for(boolean[] mem : memory){
            System.out.println(Arrays.toString(mem));
        }
    }
}
