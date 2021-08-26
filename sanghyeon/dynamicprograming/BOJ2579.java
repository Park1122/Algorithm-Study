package year_2021.month_08.day_23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ2579 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfStair = Integer.parseInt(br.readLine())+1;
        int[] stairScores = new int[numberOfStair];
        for(int i=1; i<numberOfStair; i++) stairScores[i] = Integer.parseInt(br.readLine());

        int[] memory = new int[301];
        memory[0] = 0;
        memory[1] = stairScores[1];
        if(numberOfStair>=3) {memory[2] = stairScores[1] + stairScores[2];}

        for(int i=3; i<numberOfStair; i++){
            memory[i] = Math.max(memory[i-3] + stairScores[i-1], memory[i-2]) + stairScores[i];
        }

        System.out.println(memory[numberOfStair-1]);
    }
}
