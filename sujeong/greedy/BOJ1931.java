package sujeong.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1931 {
    //https://www.acmicpc.net/problem/1931

    private static class Time implements Comparable {

        private final int startTime;
        private final int finishTime;

        // get & set
        public int getStartTime() {return startTime;}
        public int getFinishTime() {return finishTime;}

        // Constructor
        public Time(int startTime, int finishTime){
            this.finishTime = finishTime;
            this.startTime = startTime;
        }

        @Override
        public String toString() {return startTime+" "+finishTime;}

        @Override
        public int compareTo(Object o) {
            Time t = (Time)o;
            if (this.finishTime==t.finishTime) return this.startTime - t.startTime;
            else return this.finishTime-t.finishTime;
        }
    }
    
    // Variable
    private static int n;
    private static Time[] timeArr;

    // Input & Initialize
    private static void input() throws IOException {
        // Input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(reader.readLine());
        timeArr = new Time[n];

        for(int i=0;i<n;i++) {
            String[] strings = reader.readLine().split(" ");
            timeArr[i]=new Time(Integer.parseInt(strings[0]),Integer.parseInt(strings[1]));
        }
        
        // Initialize
        Arrays.sort(timeArr);

    }

    // Logic
    private static void func(){
        // Local variable
        int afterTime=0, count=0;
        
        // Logic
        for(int i=0;i<n;i++){
            if(afterTime<=timeArr[i].getStartTime()) {
                afterTime = timeArr[i].getFinishTime();
                count++;
            }
        }
        
        // Output
        System.out.println(count);
    }

    // Main
    public static void main(String[] args) throws IOException {
       input();
       func();
    }
}

