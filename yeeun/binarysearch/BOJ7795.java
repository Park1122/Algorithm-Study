package codingstudy202107.w03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 3주차
 * 문제 출처 : https://www.acmicpc.net/problem/7795
 * 이름 : 먹을 것인가 먹힐 것인가
 * 사용 알고리즘 : 정렬, 이분 탐색, 두 포인터
 */
public class BOJ7795 {

    public static void main(String[] args) throws IOException {
        BOJ7795 main = new BOJ7795();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int t = Integer.parseInt(tokenizer.nextToken());

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < t; i++) {
            if(i>0)builder.append('\n');
            tokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            int m = Integer.parseInt(tokenizer.nextToken());

            tokenizer = new StringTokenizer(reader.readLine());
            int[] aTypes = new int[n];
            for (int j = 0; j < n; j++) {
                aTypes[j] = Integer.parseInt(tokenizer.nextToken());
            }

            tokenizer = new StringTokenizer(reader.readLine());
            int[] bTypes = new int[m];
            for (int k = 0; k < m; k++) {
                bTypes[k] = Integer.parseInt(tokenizer.nextToken());
            }
            Arrays.sort(bTypes);

            main.solution(aTypes, bTypes, builder);
        }
        System.out.print(builder.toString());
    }

    private void solution(int[] aTypes, int[] bTypes, StringBuilder builder) {
        int count = this.countFoodB(aTypes, bTypes);
        builder.append(count);
    }

    private int countFoodB(int[] aTypes, int[] bTypes) {
        int count = 0;
        for (int a : aTypes) {
            if (a > bTypes[0]) {
                 for(int i=bTypes.length-1; i>=0; i--){
                     if(a>bTypes[i]){
                         count+=i+1;
                         break;
                     }
                 }
            }
        }
        return count;
    }
}
