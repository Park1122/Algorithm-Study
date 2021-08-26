package year_2021.month_08.day_24;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ11053 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numbersLength = Integer.parseInt(br.readLine());
        int[] numbers = new int[numbersLength];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<numbersLength; i++) numbers[i] = Integer.parseInt(st.nextToken());

        int answer = 0;
        int[] memory = new int[numbersLength];
        for(int i=0; i<numbersLength; i++) {
            int maxBefore = 0;
            for(int j=0; j<i; j++) {
                if(numbers[j] < numbers[i]) maxBefore = Math.max(maxBefore, memory[j]);
            }
            memory[i] = maxBefore+1;
            answer = Math.max(answer, memory[i]);
        }

        System.out.println(answer);
    }

}
