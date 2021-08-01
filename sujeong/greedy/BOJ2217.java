package sujeong.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class BOJ2217 {
    public static void main(String[] args) throws IOException {
        //input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(reader.readLine());
        Integer[]intArr = new Integer[n];
        for(int i=0;i<n;i++)intArr[i]=Integer.parseInt(reader.readLine());
        Arrays.sort(intArr, Collections.reverseOrder());

        // logic
        long answer = 0;
        for(int i=0;i<n;i++) answer=Math.max(intArr[i]*(i+1),answer);

        // output
        System.out.println(answer);

    }
}
