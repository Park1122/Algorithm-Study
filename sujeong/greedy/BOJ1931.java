package sujeong.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1931 {
    //https://www.acmicpc.net/problem/1931

    private static int n, maxCount;
    private static Time[] timeArr;


    //input
    private static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(reader.readLine());
        timeArr = new Time[n];

        for(int i=0;i<n;i++) {
            String[] strings = reader.readLine().split(" ");
            timeArr[i]=new Time(Integer.parseInt(strings[0]),Integer.parseInt(strings[1]));
        }
        Arrays.sort(timeArr);

    }

    //logic
    private static void func(){
        int index=0, afterTime=0, count=0;
        for(int i=index;i<n;i++){
            if(afterTime<=timeArr[i].getStartTime()) {
                afterTime = timeArr[i].getFinishTime();
                count++;
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
       input();
       func();
    }
}

class Time implements Comparable {

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

    @Override`
    public int compareTo(Object o) {
        Time t = (Time)o;
        if (this.finishTime==t.finishTime) return this.startTime - t.startTime;
        else return this.finishTime-t.finishTime;
    }
}