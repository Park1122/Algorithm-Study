package codingstudy202107.w02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 2주차
 * 문제 출처 : https://www.acmicpc.net/problem/1759
 * 이름 : 암호 만들기
 * 사용 알고리즘 : 수학 브루트포스 알고리즘 조합론 백트래킹
 */
public class BOJ1759 {
    private char[] moum= {'a','e','i','o','u'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        char[] arr= new char[c];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            arr[i] = (char) st.nextToken().getBytes()[0];
        }
        BOJ1759 main = new BOJ1759();
        main.solution(l, c, arr);
    }

    private void solution(int l, int c, char[] arr) {
        StringBuilder builder = new StringBuilder();
        boolean[] pick = new boolean[c];
        Arrays.sort(arr);
    String result=this.addOneAlphabet(pick,l,0, 0,0, arr, builder).toString();
        System.out.print(result);

    }

    private StringBuilder addOneAlphabet(boolean[] pick, int pickAmount, int startIndex, int selected, int moumCount, char[] arr, StringBuilder builder) {
        if(selected==pickAmount){
            if(moumCount>0&&pickAmount-moumCount>=2) this.makeString(pick,arr,builder, pickAmount);
            return builder;
        }

        for(int i = startIndex; i<=arr.length-pickAmount+selected; i++){
            pick[i]=true;
            int moumC=moumCount+this.checkMoum(arr[i]);
            builder=this.addOneAlphabet(pick, pickAmount, i+1, selected+1, moumC, arr, builder);
            pick[i]=false;
        }
        return builder;
    }

    private int checkMoum(char target) {
        for(char ch: moum){
            if(target==ch)return 1;
        }
        return 0;
    }

    private void makeString(boolean[] pick, char[] arr, StringBuilder builder, int pickAmount) {
        int count=0;
        for(int i=0; i<pick.length; i++){
            if(pick[i]){
                builder.append(arr[i]);
                if(++count==pickAmount){
                    builder.append('\n');
                    break;
                }
            }
        }

    }
}
