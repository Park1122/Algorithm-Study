package codingstudy202107.w10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 회차 : 코딩 스터디 10주차
 * 문제 출처 : https://www.acmicpc.net/problem/2688
 * 이름 : 줄어들지 않아
 * 사용 알고리즘 : 다이나믹 프로그래밍
 * 아까 문제랑 똑같은데 숫자가 여러 개.
 */
public class BOJ2688 {

    public static void main(String[] args) throws IOException {
        BOJ2688 main = new BOJ2688();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i]=Integer.parseInt(reader.readLine());
        }
        System.out.print(main.solution(arr));
    }

    private String solution(int [] arr) {
        StringBuilder builder = new StringBuilder();
        boolean first=true;

        Long[][] memoi = new Long[65][10];
        Arrays.fill(memoi[1],1L);// 1자리일 때 1로 채움.
        this.iteration(memoi);

        for(int val: arr){
            if(first) first=false;
            else builder.append('\n');
            builder.append(this.sum(memoi[val]));
        }
        return builder.toString();
    }

    private Long sum(Long[] memoi) {
        Long result = 0L;
        for (Long l : memoi) {
            result += l;
        }
        return result;
    }

    private void iteration(Long[][] memoi) {
        for (int i = 2; i < memoi.length; i++) {
            Long sum = 0L;
            for (int j = 9; j >= 0; j--) {
                sum += memoi[i - 1][j];
                memoi[i][j] = sum;
            }
            System.out.println("메모이 보여주기"+i+Arrays.toString(memoi[i]));
        }
    }

}
