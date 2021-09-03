package codingstudy202107.w09;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 9주차
 * 문제 출처 : https://www.acmicpc.net/problem/1912
 * 이름 : 연속합
 * 사용 알고리즘 : 다이나믹 프로그래밍
 */
public class BOJ1912 {

    public static void main(String[] args) throws IOException {
        BOJ1912 main = new BOJ1912();
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
        int[] memoi = new int[arr.length];
        Arrays.fill(memoi, 0);

        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            int temp=arr[i];
            if (i > 0)
                temp= Integer.max(memoi[i-1]+temp, temp);
            memoi[i]=temp;
            if(temp>max)max=temp;
        }

        return max;
    }



}
