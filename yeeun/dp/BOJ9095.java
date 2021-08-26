package codingstudy202107.w06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 6주차
 * 문제 출처 : https://www.acmicpc.net/problem/9095
 * 이름 : 1,2,3 더하기
 * 사용 알고리즘 : 다이나믹 프로그래밍
 */
public class BOJ9095 {

    public static void main(String[] args) throws IOException {
        BOJ9095 main = new BOJ9095();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }
        System.out.print(main.solution(arr));
    }

    private String solution(int[] arr) {
        int[] memoi = new int[11];
        Arrays.fill(memoi, -1);

        StringBuilder sb = new StringBuilder();

        for (int val : arr) {
//            System.out.println("읽은 숫자:ㅣ: "+val);
            sb.append(this.recursion(memoi, val));
            sb.append('\n');
        }

        return sb.toString();
    }

    private int recursion(int[] memoi, int val) {
        if(val==0){
            return 1;
        }
        if (memoi[val] != -1) {
            return memoi[val];
        }

        int sum = 0;
        if (val >= 1) {
            sum += this.recursion(memoi, val - 1);
        }

        if (val >= 2) {
            sum += this.recursion(memoi, val - 2);
        }

        if (val >= 3) {
            sum += this.recursion(memoi, val - 3);
        }



//        System.out.println(val+"번 계산결과: "+ sum);
        memoi[val] = sum;
        return sum;
    }
}
