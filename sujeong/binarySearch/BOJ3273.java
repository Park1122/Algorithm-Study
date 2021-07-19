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
        int index=0;
        for(int i=0;i<n;i++)index+=Arrays.binarySearch(intArr,(m-intArr[i]))>=0 ? 1:0;
        answer=index/2;
    }

    public static void main(String[] args) throws IOException {
        input();
        func();
        System.out.println(answer);
    }

}

// 코드 최대로 줄인 버전
//public class BOJ3273 {
//    private static int n, m;
//    private static int[] intArr;
//    private static int answer;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
//
//        n = Integer.parseInt(r.readLine());
//
//        String[] strArrN = r.readLine().split(" ");
//        intArr = new int[n];
//        for (int i = 0; i < n; i++) intArr[i] = Integer.parseInt(strArrN[i]);
//
//        m = Integer.parseInt(r.readLine());
//
//        int index = 0;
//        Arrays.sort(intArr);
//        for (int i = 0; i < n; i++) index += Arrays.binarySearch(intArr, (m - intArr[i])) >= 0 ? 1 : 0;
//        System.out.println(index / 2);
//    }
//}
