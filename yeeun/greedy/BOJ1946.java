package codingstudy202107.w06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 6주차
 * 문제 출처 : https://www.acmicpc.net/problem/1946
 * 이름 : 신입 사원
 * 사용 알고리즘 : 그리디 알고리즘, 정렬
 */
public class BOJ1946 {

    public static void main(String[] args) throws IOException {
        BOJ1946 main = new BOJ1946();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int m = Integer.parseInt(reader.readLine());
            int[][] arr = new int[m][2];
            for (int j = 0; j < m; j++) {
                StringTokenizer st = new StringTokenizer(reader.readLine());
                arr[j][0] = Integer.parseInt(st.nextToken());
                arr[j][1] = Integer.parseInt(st.nextToken());
            }
            if(i>0)sb.append('\n');
            sb.append(main.solution(arr));
        }
        System.out.print(sb.toString());
    }

    private int solution(int[][] arr) {
        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] > o2[0]) return 1;
            else if(o1[0]==o2[0]) return 0;
            else return -1;
        });

        int max=arr[0][1], count=0;
        for(int[] line: arr){
            if(line[1]<=max){
                max=line[1];
                count++;
            }
        }

        return count;
    }

}



