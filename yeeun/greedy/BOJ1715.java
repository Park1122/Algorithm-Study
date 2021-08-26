package codingstudy202107.w06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 6주차
 * 문제 출처 : https://www.acmicpc.net/problem/1715
 * 이름 : 카드 정렬하기
 * 사용 알고리즘 : 그리디 알고리즘, 정렬
 */
public class BOJ1715 {

    public static void main(String[] args) throws IOException {
        BOJ1715 main = new BOJ1715();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i=0; i<n; i++){
            pq.add(Integer.parseInt(reader.readLine()));
        }

        int result=main.solution(pq);
        System.out.print(result);
    }

    private int solution(PriorityQueue<Integer> pq) {
        int sum=0;
        while(pq.size()>1){
            int temp = pq.poll()+pq.poll();
            sum+=temp;
            pq.add(temp);
        }
            return sum;
    }
}
