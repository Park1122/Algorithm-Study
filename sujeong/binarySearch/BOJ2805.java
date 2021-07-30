package sujeong.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BOJ2805 {
    private static int n,m,b ;
    private static int[] intArr;

    private static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] strArr = reader.readLine().split(" ");
        n = Integer.parseInt(strArr[0]);
        m = Integer.parseInt(strArr[1]);

        String[] strArrN = reader.readLine().split(" ");
        intArr = new int[n];
        for(int i =0;i<n;i++) {
            int temp = Integer.parseInt(strArrN[i]);
            intArr[i]= temp;
            b = Math.max(b, temp);
        }
    }

    private static void func(int s, int f){
        if(s>f) {System.out.println(f); return;}

        int mid = (s+f)/2;
        if(testFunc(mid)) func(mid+1,f);
        else func(s,mid-1);
    }

    private static boolean testFunc(int num){
        long sum=0;
        for (int temp : intArr) {
            if(temp>num) sum+=(temp-num);
        }
        return sum >= m;
    }

    public static void main(String[] args) throws IOException {
        input();
        func(0,b);
    }
}
