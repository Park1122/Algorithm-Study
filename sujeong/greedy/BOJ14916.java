package sujeong.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class BOJ14916 {
    //5분컷
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int ans = -1;

        for(int i=n/5;i>=0;i--){
            int except5 = n-(5*i);
            if(except5%2==0) {
                ans = except5/2+i;
                break;
            }
        }
        System.out.println(ans);
    }
}
