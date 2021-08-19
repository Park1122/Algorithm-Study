package codingstudy202107.w07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 회차 : 코딩 스터디 7주차
 * 문제 출처 : https://www.acmicpc.net/problem/15991
 * 이름 : 1, 2, 3 더하기 6
 * 사용 알고리즘 : 다이나믹 프로그래밍
 */
public class BOJ15991 {

    public static void main(String[] args) throws IOException {
        BOJ15991 main = new BOJ15991();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }

        System.out.print(main.solution(arr));
    }


    private String solution(int[] arr) {
        StringBuilder builder = new StringBuilder();

        int[][] memoi = new int[100001][4];

        for(int[] line: memoi){
            Arrays.fill(line,-1);
        }

        for (int val : arr) {
            builder.append(this.recursion(memoi, val, 0) % 1000000009).append('\n');
        }

        return builder.toString();
    }

    private long recursion(int[][] memoi, int val, int add) {
        if(val<0) return 0;
        if (val == 0) return 1;
        if(memoi[val][add]!=-1){
            return memoi[val][add];
        }

        long ones= this.recursion(memoi, checkPossible(val,1), 1);
        long twos= this.recursion(memoi, checkPossible(val,2), 2);
        long threes= this.recursion(memoi, checkPossible(val,3), 3);

        long result= ones+twos+threes;

        memoi[val][add]= (int) (result%1000000009);
        return result;
    }

    private int checkPossible(int val, int add){
        if(val<add){//더하기로 만드려는 값보다 더할 숫자가 큼. 불가능.
            return -1;
        }else if(val==add){//더하기로 만드려는 숫자와 더할 숫자가 같음. 대칭의 한가운데로 가능.
            return 0;
        }else if(val<(add*2)){//더하기로 만드려는 숫자가 더할 숫자의 2배보다 작고 1배보다 큼. 불가능.
            return -1;
        }else if(val==add*2){//더하기로 만드려는 숫자가 더할 숫자의 2배임. 대칭의 한가운데에 두번 들어가서 가능.
            return val-(2*add);
        }else{//더하기로 만드려는 숫자가 더할 숫자의 2배보다 큼. 가능.
            return val-(2*add);
        }
    }

}
