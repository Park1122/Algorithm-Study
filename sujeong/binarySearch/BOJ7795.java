package sujeong.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ7795 {
    private static int T;
    private static Vector<int[]> arrList;
    private static int[] answer;

    private static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(reader.readLine());
        answer = new int[T];

        arrList = new Vector<int[]>();
        for(int j =0;j<T;j++){
            String[] strArrFirst = reader.readLine().split(" ");
            int n = Integer.parseInt(strArrFirst[0]);
            int m = Integer.parseInt(strArrFirst[1]);
            int[] intArrNM = new int[]{n,m};
            arrList.add(intArrNM);

            String[] strArrSec = reader.readLine().split(" ");
            int[] intArrA = new int[n];
            for(int i =0;i<n;i++) intArrA[i]=Integer.parseInt(strArrSec[i]);
            Arrays.sort(intArrA);
            arrList.add(intArrA);

            String[] strArrThird = reader.readLine().split(" ");
            int[] intArrB = new int[m];
            for(int i =0;i<m;i++) intArrB[i]=Integer.parseInt(strArrThird[i]);
            Arrays.sort(intArrB);
            arrList.add(intArrB);
        }
    }

    private static void func(){
        for(int i=0;i<T;i++){
            int[] intArrNM= arrList.get((i * 3));
            int[] intArrA= arrList.get(1 + (i*3));
            int[] intArrB= arrList.get(2 + (i*3));

            int num=0;
            for(int A : intArrA){
                for(int B : intArrB){
                    if(A<=B) break;
                    else num++;
                }
            }
            answer[i]=num;
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
