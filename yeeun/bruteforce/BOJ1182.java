package codingstudy202107.w02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 2주차
 * 문제 출처 : https://www.acmicpc.net/problem/1182
 * 이름 : 부분 수열의 합
 * 사용 알고리즘 : -
 */
public class BOJ1182 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        BOJ1182 main = new BOJ1182();
        main.solution(n, s, arr);
    }

    public void solution(int n, int s, int[] arr) {
        System.out.print(this.checkSumMatch(s, arr));
    }

    public int checkSumMatch(int match, int[] arr) {
        int result = 0;
        for (int i = 1; i < Math.pow(2, arr.length); i++) {
            int sum = 0;
            String binary = Integer.toBinaryString(i);
            byte[] bytes = binary.getBytes();
            for (int j = 0; j < arr.length; j++) {
                    if (bytes.length < arr.length ) {
                        int index=j-arr.length+bytes.length;
                        if(index>=0&&bytes[index]=='1') sum+=arr[j];
                    } else if(bytes[j]=='1') sum += arr[j];

            }
            if (sum == match) result++;
        }
        return result;
    }
}
