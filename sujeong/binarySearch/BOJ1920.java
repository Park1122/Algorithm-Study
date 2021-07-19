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
        // binarySearch를 제대로 사용하지 못한 듯함....
        // 이 방식은 완전 탐색에 더 가깝다.
        // 풀고나서 다른사람들과 비교하니 보임.
//        for(int i=0;i<m;i++){
//            for(int j=0;j<n;j++){
//                if(arrBList[i]==arrAList[j]){
//                    answer[i]=1;
//                    break;
//                } else if (arrBList[i]<arrAList[j]){
//                    answer[i]=0;
//                    break;
//                }
//            }
//        }

        for(int i=0;i<m;i++){
            answer[i]=Arrays.binarySearch(arrAList,arrBList[i])<0?0:1;
        }

    }

    private static void output() {
        for(int i : answer) System.out.println(i);
    }

    public static void main(String[] args) throws IOException {
        input();
        func();
        output();
    }

}
