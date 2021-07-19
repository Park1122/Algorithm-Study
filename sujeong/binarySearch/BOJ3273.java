package sujeong.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;


public class BOJ3273 {
    private static int n,m;
    private static int[] intArr;
    private static int answer;

    private static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(reader.readLine());

        String[] strArrN = reader.readLine().split(" ");
        intArr = new int[n];
        for(int i =0;i<n;i++) intArr[i]=Integer.parseInt(strArrN[i]);
        Arrays.sort(intArr);

        m = Integer.parseInt(reader.readLine());
    }

    private static void func(){
        LinkedHashMap<Integer,Integer> map = new LinkedHashMap<>();
        for(int i=0;i<n;i++){
            int index = Arrays.binarySearch(intArr,(m-intArr[i]));
            if(index>=0)map.put(i,index);
        }
        answer=map.size()/2;
    }

    public static void main(String[] args) throws IOException {
        input();
        func();
        System.out.println(answer);
    }

}
