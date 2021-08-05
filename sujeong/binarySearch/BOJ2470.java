package sujeong.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class BOJ2470 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        String[] strArrN = reader.readLine().split(" ");
        int[] intArr = new int[n];
        for(int i = 0; i< n; i++){
            intArr[i]=Integer.parseInt(strArrN[i]);
        }
        Arrays.sort(intArr);


        int frontidx=0, endIdx= n-1;
        int max=2000000000;
        int minSide=0, maxSide=0;

        while(frontidx<endIdx){
            int sum = intArr[frontidx]+ intArr[endIdx];

            if(Math.abs(sum)<max){
                minSide= intArr[frontidx];
                maxSide= intArr[endIdx];
                max=Math.abs(sum);
            }

            if(sum>0) endIdx--;
            else frontidx++;
        }
        System.out.println(minSide+" "+maxSide);
    }

}