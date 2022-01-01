package codingstudy202107.w26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11403 {
    static int pointCount;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        pointCount = Integer.parseInt(reader.readLine());

        arr = new int[pointCount][pointCount];
        StringTokenizer st;
        for (int i = 0; i < pointCount; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < pointCount; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < pointCount; i++)
            solution(i, builder);
        builder.setLength(builder.length());
        System.out.print(builder);
    }

    public static void solution(int start, StringBuilder builder) {
        int[] answer= new int[arr.length];
        Queue<Integer> queue= new ArrayDeque<>();
        queue.add(start);
        while(!queue.isEmpty()){
         int check=queue.poll();
         int[] checkArr=arr[check];
         for(int i=0; i<arr.length; i++) {
            if(answer[i]==0&&checkArr[i]>0){
                answer[i]=1;
                queue.add(i);
            }
         }
        }
        for (int val : answer) {
            builder.append(val).append(' ');
        }
        builder.append('\n');
    }


}
