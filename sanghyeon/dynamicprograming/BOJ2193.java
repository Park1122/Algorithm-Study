package year_2021.month_08.day_25;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2193 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[][] memory = new long[n+1][2];
        memory[1] = new long[]{0, 1}; // 0으로 끝나는 개수, 1로 끝나는 개수
        for(int i=2; i<n+1; i++) memory[i] = new long[]{memory[i-1][0]+memory[i-1][1], memory[i-1][0]};
        System.out.println(memory[n][0]+memory[n][1]);
    }
}
