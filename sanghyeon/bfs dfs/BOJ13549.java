package year_2021.month_11.day_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13549 {

    private static int N, M;
    private static int[] locationArriveTime;
    private static Queue<Integer> locations;

    public static void main(String[] args) throws IOException {
        readInput();
        getArriveTime();
        System.out.println(locationArriveTime[M]-1);
    }

    private static void getArriveTime() {
        locationArriveTime = new int[100_000+1];
        locationArriveTime[N] = 1;
        locations = new LinkedList<>(Arrays.asList(N));
        while(true){
            int location = locations.poll(), time = locationArriveTime[location];
            if(location!=0) for(int i=location; i<=100000; i*=2) testAndAddLocation(i, time);
            testAndAddLocation(location+1, time+1);
            testAndAddLocation(location-1, time+1);
            if(locationArriveTime[M]!=0) break;
        }
    }
    private static void testAndAddLocation(int i, int time) {
        try{
            if(locationArriveTime[i]==0) {
                locations.add(i);
                locationArriveTime[i] = time;
            }
        }catch (Exception ignored){}
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }
}
