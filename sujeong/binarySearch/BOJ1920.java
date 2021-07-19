package sujeong.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class BOJ1920 {
    private static int n,m;
    private static int[] arrAList;
    private static int[] arrBList;
    private static int[] answer;

    private static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(reader.readLine());
        String[] strArrN = reader.readLine().split(" ");
        arrAList = new int[n];
        for(int i =0;i<n;i++) arrAList[i]=Integer.parseInt(strArrN[i]);
        Arrays.sort(arrAList);

        m = Integer.parseInt(reader.readLine());
        String[] strArrM = reader.readLine().split(" ");
        arrBList = new int[m];
        for(int i =0;i<m;i++) arrBList[i]=Integer.parseInt(strArrM[i]);

        answer = new int[m];
    }

    private static void func(){
        for(int i=0;i<m;i++){
            boolean b=false;
            for(int j=0;j<n;j++){
                if(arrBList[i]==arrAList[j]){
                    b=true;
                    break;
                } else if (arrBList[i]<arrAList[j]) break;
            }
            if(b) answer[i]=1;
            else answer[i]=0;
        }
    }

    private static void output() {
        for(int i : answer){
            System.out.println(i);
        }
    }


    public static void main(String[] args) throws IOException {
        input();
        func();
        output();
    }

}
