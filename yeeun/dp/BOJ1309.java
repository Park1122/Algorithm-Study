package codingstudy202107.w10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 10주차
 * 문제 출처 : https://www.acmicpc.net/problem/1309
 * 이름 : 동물원
 * 사용 알고리즘 : 다이나믹 프로그래밍
 * 마지막 줄이 다 비어있으면 -> 또 다 비는 경우, 하나만 채우는 경우(*2) 존재
 * 마지막 줄이 하나 차 있으면 -> 다 비는 경우, 대각선 방향 하나만 채우는 경우 존재.
 * 해당 식을 바탕으로 계산.
 */
public class BOJ1309 {

    public static void main(String[] args) throws IOException {
        BOJ1309 main = new BOJ1309();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        System.out.print(main.solution(n));
    }

    private long solution(int val) {
        long[][] memoi = new long[val+1][2];

        memoi[1][0]=1;
        memoi[1][1]=2;
        for(int i = 2; i <= val; i++){
            memoi[i][0]= (memoi[i-1][0]+memoi[i-1][1])%9901;
            memoi[i][1]= ((memoi[i-1][0]*2)+memoi[i-1][1])%9901;
        }
        return (memoi[val][0]+memoi[val][1])%9901;
    }

}
