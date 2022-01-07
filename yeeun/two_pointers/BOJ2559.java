package codingstudy202107.w27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2559 {
    public static int dayLong;
    public static long maxTemperature;
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int dateCount = Integer.parseInt(tokenizer.nextToken());
        dayLong = Integer.parseInt(tokenizer.nextToken());

        arr = new int[dateCount];
        tokenizer = new StringTokenizer(reader.readLine());
        maxTemperature=0L;
        for (int i = 0; i < dateCount; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
            if(i<dayLong)maxTemperature+=arr[i];
        }
        System.out.print(twoPointer());
    }

    private static long twoPointer() {
        int start=0, end=dayLong-1;
        long sumTemperature=maxTemperature;
        while (end<arr.length-1) {
            //System.out.println("start: "+start+", end: "+end+" sum: "+sumTemperature);
            sumTemperature-=arr[start++];
            sumTemperature+=arr[++end];
            if(sumTemperature>maxTemperature)maxTemperature=sumTemperature;
        }
        return maxTemperature;
    }

}
