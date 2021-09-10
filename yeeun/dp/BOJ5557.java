package codingstudy202107.w10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 10주차
 * 문제 출처 : https://www.acmicpc.net/problem/5557
 * 이름 : 1학년
 * 사용 알고리즘 : 다이나믹 프로그래밍
 */
public class BOJ5557 {

    public static void main(String[] args) throws IOException {
        BOJ5557 main = new BOJ5557();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int[] arr = new int[n - 1];
        for (int i = 0; i < n - 1; i++) arr[i] = Integer.parseInt(st.nextToken());

        int val = Integer.parseInt(st.nextToken());

        System.out.print(main.solution(arr, val));
    }

    private long solution(int[] arr, int val) {
        long[][] memoi = new long[arr.length][21];
        Arrays.fill(memoi[0],0);
        memoi[0][arr[0]] = 1;

        for (int i = 1; i < arr.length; i++) {
            long[] prev = memoi[i - 1];
            long[] line = memoi[i];
            Arrays.fill(line,0);

            int target = arr[i];
//            System.out.println("---------타겟"+i+"번째, 숫자 "+target+"--------");
            for (int j = 0; j < prev.length; j++) {
                if (prev[j] != 0) {
//                    System.out.println("앞에서 계산해서 나올 수 있는 값- " + j);
                    if (j + target >= 0 && j + target <= 20) {
//                        System.out.println("이번값- " + (j + target));
                        line[j + target] += prev[j];
                    }
                    if (j - target >= 0 && j - target <= 20) {
                        line[j - target] += prev[j];
//                        System.out.println("이번값- " + (j - target));
                    }
                }
            }
//            for(long[] l:memoi){
//                System.out.println(Arrays.toString(l));
//            }
//            System.out.println("-----------------");
        }

        return memoi[arr.length - 1][val];
    }


}
