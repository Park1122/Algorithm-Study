package codingstudy202107.w27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1806 {
    public static int targetNumber;
    public static int[] arr;
    public static int minLength;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int numberCount = Integer.parseInt(tokenizer.nextToken());
        targetNumber = Integer.parseInt(tokenizer.nextToken());
        arr = new int[numberCount];
        tokenizer = new StringTokenizer(reader.readLine());
        long sum=0L;
        for (int i = 0; i < numberCount; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
            sum+=arr[i];
        }
        if(sum<targetNumber){
            System.out.print(0);
        }else {
            minLength = numberCount;
            System.out.print(twoPointer());
        }
    }

    private static long twoPointer() {
        int start = 0, end = 0;
        long sum = arr[0];

        while (true) {
            if (sum < targetNumber) {
                if (end == arr.length - 1) break;
                sum += arr[++end];
            } else {
                int length = end - start + 1;
                if (length < minLength) minLength = length;
                if(start==arr.length-1)break;
                sum -= arr[start++];
            }

        }
        return minLength;
    }


}
