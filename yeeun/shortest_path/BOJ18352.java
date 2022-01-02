package codingstudy202107.w26;

import java.io.*;
import java.util.*;

public class BOJ18352 {
    static int cityCount, loadCount, targetDistance, startCity;
    static Vector<Integer>[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        cityCount = Integer.parseInt(st.nextToken());
        loadCount = Integer.parseInt(st.nextToken());
        targetDistance = Integer.parseInt(st.nextToken());
        startCity = Integer.parseInt(st.nextToken());

        arr = new Vector[cityCount + 1];
        for (int i = 0; i <= cityCount; i++) arr[i] = new Vector<>(1);

        for (int i = 0; i < loadCount; i++) {
            st = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(st.nextToken());
            int destiny = Integer.parseInt(st.nextToken());
            arr[start].add(destiny);
        }

        System.out.print(dijkstra());


    }

    public static String dijkstra() {
        int[] answer = new int[cityCount + 1];
        for (int i = 0; i <= cityCount; i++)
            answer[i] = Integer.MAX_VALUE;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        answer[startCity] = 0;//출발지, 도착지 같은 경우.
        pq.add(startCity);

        while (!pq.isEmpty()) {
            int start = pq.poll();
            int currentDistance=answer[start];
            for (int m : arr[start]) {
                if (answer[m] > currentDistance + 1) {
                    answer[m] = currentDistance + 1;
                    pq.add(m);
                }
            }

        }

        StringBuilder builder= new StringBuilder();
        for (int i = 1; i <= cityCount; i++) {
            if (answer[i] == targetDistance)
                builder.append(i).append('\n');
        }
        if(builder.length()==0){
            return "-1";
        }else{
            builder.setLength(builder.length()-1);
            return builder.toString();
        }
    }
}
