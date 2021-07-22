package sujeong.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BOJ6236 {
    private static int n,m,startVal,endVal;
    private static int[] intArr;

    private static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] strArr = reader.readLine().split(" ");
        n = Integer.parseInt(strArr[0]);
        m = Integer.parseInt(strArr[1]);

        intArr = new int[n];
        for(int i =0;i<n;i++) {
            intArr[i]=Integer.parseInt(reader.readLine());
            startVal = Math.max(intArr[i], startVal);
            endVal += intArr[i];
        }

    }

    private static int func(int start, int end){
        if(start>end)  return start;
        if(testFunc((end+start)/2)){
            return func(start,((end+start)/2)-1);
        } else{
            return func(((end+start)/2)+1,end);
        }

    }

    private static boolean testFunc(int k){
        int repo=0, count=1;
        for(int i =0;i<n;i++){
            if(repo+intArr[i]>k){
                repo=0;
                count++;
            }
            repo+=intArr[i];
        }
        if(repo==0) count--;
        return count <= m;
    }

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(func(startVal,endVal));
    }

}
