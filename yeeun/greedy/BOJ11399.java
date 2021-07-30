package codingstudy202107.w04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 4주차
 * 문제 출처 : https://www.acmicpc.net/problem/111399
 * 이름 : ATM
 * 사용 알고리즘 : 그리디 알고리즘, 정렬
 */
public class BOJ11399 {

    public static void main(String[] args) throws IOException {
        BOJ11399 main = new BOJ11399();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.print(main.solution(arr));
    }

    private int solution(int[] arr) {
        Arrays.sort(arr);

        int sum=0;
        int temp=0;

        for(int val: arr){
            temp+=val;
            sum+=temp;
        }

        return sum;
    }


}
