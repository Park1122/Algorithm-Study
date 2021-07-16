package sujeong.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ1182 {
    private static int n,s;
    private static int[] intArr;

    private static int count;

    private static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] strArrFirst = reader.readLine().split(" ");
        n = Integer.parseInt(strArrFirst[0]);
        s = Integer.parseInt(strArrFirst[1]);

        String[] strArrSec = reader.readLine().split(" ");
        intArr = new int[n];
        for(int i =0;i<n;i++) intArr[i]=Integer.parseInt(strArrSec[i]);
        Arrays.sort(intArr);

        count=0;

    }

    private static void func(int[] ints, int index) {
        if (index == n) {
            int sum = 0;
            for (int anInt : ints) sum += anInt;
            if (sum == s) count++;
        }else{
            // include
            ints[index]=intArr[index];
            func(ints, index+1);

            // include X
            ints[index]=0;
            func(ints, index+1);
        }

    }

    public static void main(String[] args) throws IOException {
        input();
        func(new int[n], 0);
        if(s==0) count--;
        System.out.println(count);
    }

}
