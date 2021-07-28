package sujeong.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


// 5분컷
public class BOJ11399 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        String[] strArrN = reader.readLine().split(" ");
        int[] intArr = new int[n];
        for(int i =0;i<n;i++) intArr[i]=Integer.parseInt(strArrN[i]);
        Arrays.sort(intArr);

        int sum=0;
        for(int i=0;i<n;i++) sum += intArr[i]*(n-i);
        System.out.println(sum);
    }

}
