package sujeong.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ11047 {

    private static int n,k;

    private static ArrayList<Integer> list;
    private static Integer[] pocket;
    private static Integer[] counter;
    private static int listSize;

    public static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] strArr = reader.readLine().split(" ");
        n=Integer.parseInt(strArr[0]);
        k=Integer.parseInt(strArr[1]);

        list = new ArrayList<Integer>();
        for(int i =0;i<n;i++) {
            int tmp = Integer.parseInt(reader.readLine());
            if(k>=tmp) list.add(tmp);
            else break;
        }
        list.sort(Comparator.reverseOrder());
        listSize=list.size();
    }

    private static void func(){
        int remain = k;
        int coin = 0;
        for (int i = 0; i < listSize ; i++) {
            coin += remain/list.get(i);
            remain %= list.get(i);
        }
        System.out.println(coin);
    }

    public static void main(String[] args) throws IOException {
        input();
        func();
    }
}
