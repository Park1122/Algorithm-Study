package codingstudy202107.w03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 3주차
 * 문제 출처 : https://www.acmicpc.net/problem/6236
 * 이름 : 용돈 관리
 * 사용 알고리즘 : 이분 탐색
 */
public class BOJ6236 {

    public static void main(String[] args) throws IOException {
        BOJ6236 main = new BOJ6236();

        BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] bill = new int[n];
        for(int i=0; i<n; i++){
            bill[i]=Integer.parseInt(reader.readLine());
        }
        main.solution(bill, m);
    }

    private void solution(int[] bill, int m) {
        int max=0, min=0;
        for (int val : bill) {
            max += val;
            if (val > min) min = val;
        }

        int candi = 0, count;

        while(candi!=min) {
            candi=(min+max)/2;
            count=this.calculateMoney(bill, candi, m);

            if(count==-1){
                min=candi+1;
            }else{
                max=candi;
            }
        }

        System.out.print(candi);
    }

    private int calculateMoney(int[] bill, int candi, int m) {
        int count=0, leftover=0;

        for(int val:bill){
            if(val<=leftover){
                leftover=leftover-val;
            }else{
                if(++count>m) return -1; //횟수 초과시 끝내기.

                leftover=candi-val;
            }
        }
        return count;
    }
}
