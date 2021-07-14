package sujeong.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class BOJ15663 {
    private static int n,m;
    private static int[] intArr;
    private static boolean[] visited;
    private static LinkedHashSet<String> stringSet;

    private static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] strArrFirst = reader.readLine().split(" ");
        n = Integer.parseInt(strArrFirst[0]);
        m = Integer.parseInt(strArrFirst[1]);

        String[] strArrSec = reader.readLine().split(" ");
        intArr = new int[n];
        for(int i =0;i<n;i++) intArr[i]=Integer.parseInt(strArrSec[i]);
        Arrays.sort(intArr);

        visited = new boolean[n];
        stringSet = new LinkedHashSet<>();

    }

    private static void func(String str, int index){
        if(index==m){
            stringSet.add(str);
            return;
        }
        for(int i=0;i<n;i++) {
            if(visited[i]) continue;
            visited[i]=true;
            if (index==0) func(str + intArr[i], index + 1);
            else func(str+" "+intArr[i],index+1);
            visited[i]=false;
        }
    }

    private static void output() {
        for(String s : stringSet) System.out.println(s);
    }


    public static void main(String[] args) throws IOException {
        input();
        func("",0);
        output();
    }

}
