package year_2021.month_09.day_14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2096 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] numbers = new int[n][3];
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            numbers[i][0] = Integer.parseInt(st.nextToken());
            numbers[i][1] = Integer.parseInt(st.nextToken());
            numbers[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] memory = new int[n][3];
        memory[0] = numbers[0];
        for(int i=1; i<n; i++){
            memory[i][0] = Math.max(memory[i-1][0], memory[i-1][1]) + numbers[i][0];
            memory[i][1] = Math.max(memory[i-1][0], Math.max(memory[i-1][1], memory[i-1][2])) + numbers[i][1];
            memory[i][2] = Math.max(memory[i-1][1], memory[i-1][2]) + numbers[i][2];
        }
        int max = Math.max(memory[n-1][0], Math.max(memory[n-1][1], memory[n-1][2]));

        for(int i=1; i<n; i++){
            memory[i][0] = Math.min(memory[i-1][0], memory[i-1][1]) + numbers[i][0];
            memory[i][1] = Math.min(memory[i-1][0], Math.min(memory[i-1][1], memory[i-1][2])) + numbers[i][1];
            memory[i][2] = Math.min(memory[i-1][1], memory[i-1][2]) + numbers[i][2];
        }
        int min = Math.min(memory[n-1][0], Math.min(memory[n-1][1], memory[n-1][2]));

        System.out.println(max+" "+min);
    }

}
