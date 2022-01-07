package codingstudy202107.w27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2003 {
    public static int targetNumber;
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int numberCount = Integer.parseInt(tokenizer.nextToken());
        targetNumber = Integer.parseInt(tokenizer.nextToken());

        arr = new int[numberCount];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < numberCount; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        System.out.print(twoPointer());
    }

    private static int twoPointer() {
        int count = 0;
        int start = 0, end = 0;
        long sum = arr[0];
        while (true) {
            if (sum > targetNumber) {
                if(start==arr.length-1)break;
                sum -= arr[start++];
            } else {
                if (sum == targetNumber) {
                    count++;
                    //System.out.println("start " + start + ", end " + end + ", sum " + sum);
                }
                if (end == arr.length - 1) break;
                sum += arr[++end];
            }

        }
        return count;
    }

}
