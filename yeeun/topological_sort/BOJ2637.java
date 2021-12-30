package codingstudy202107.w25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2637 {
    public static void main(String[] args) throws IOException {
        BOJ2637 main = new BOJ2637();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int partCount = Integer.parseInt(reader.readLine());
        Vector<Integer[]>[] arr = new Vector[partCount + 1];

        int needCount = Integer.parseInt(reader.readLine());
        StringTokenizer st;
        for (int i = 1; i <= needCount; i++) {
            st = new StringTokenizer(reader.readLine());
            int partNum = Integer.parseInt(st.nextToken());
            int needPartNum = Integer.parseInt(st.nextToken());
            int needPartCount = Integer.parseInt(st.nextToken());
            if (arr[partNum] == null) arr[partNum] = new Vector<>(1);
            arr[partNum].add(new Integer[]{needPartNum, needPartCount});
        }
        System.out.print(main.calcBasics2(arr, partCount));
    }

    private String calcBasics2(Vector<Integer[]>[] arr, int targetIdx) {
        int[] result = new int[arr.length];
        for (Integer[] part : arr[targetIdx]) {
            result[part[0]] += part[1];
        }
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < arr.length; i++) {
                if (result[i] != 0 && arr[i] != null) {//need this part and it is not basic part
                    flag = true;
                    int count = result[i];
                    for (Integer[] part : arr[i]) {
                        result[part[0]] += (part[1] * count);
                    }
                    result[i]=0;
                }
            }
        }
        return this.buildString2(result);
    }

    private String buildString2(int[] result) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            if (result[i] != 0) builder.append(i).append(" ").append(result[i]).append('\n');
        }
        builder.setLength(builder.length() - 1);
        return builder.toString();
    }
}
