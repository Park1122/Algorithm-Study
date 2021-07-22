package codingstudy202107.w03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 3주차
 * 문제 출처 : https://www.acmicpc.net/problem/7795
 * 이름 : 수 찾기
 * 사용 알고리즘 : 이분 탐색
 */
public class BOJ1920 {

    public static void main(String[] args) throws IOException {
        BOJ1920 main = new BOJ1920();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(reader.readLine());
        int[] aArr = new int[n];
        for (int i = 0; i < n; i++) {
            aArr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(reader.readLine());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(reader.readLine());
        int[] bArr = new int[m];
        for (int i = 0; i < m; i++) {
            bArr[i] = Integer.parseInt(st.nextToken());
        }

        main.solution(aArr, bArr);
    }

    private void solution(int[] aArr, int[] bArr) {
        Arrays.sort(aArr);
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < bArr.length; i++) {
            if(i>0)builder.append('\n');
            this.findNumber(aArr, bArr[i], builder, 0, aArr.length - 1);
        }

        System.out.print(builder.toString());
    }

    private void findNumber(int[] aArr, int target, StringBuilder builder, int start, int end) {
        int mid = (start + end) / 2;
        if(mid==start){
            if(aArr[start]==target||aArr[end]==target){
                builder.append(1);
            }
            else{
                builder.append(0);
            }
            return;
        }

        if (aArr[mid] > target) {
            this.findNumber(aArr, target, builder, start, mid - 1);
        } else if (aArr[mid] < target) {
            this.findNumber(aArr, target, builder, mid + 1, end);
        } else {
            builder.append(1);
            return;
        }
    }


}
