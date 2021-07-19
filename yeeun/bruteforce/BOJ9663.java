package codingstudy202107.w02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 회차 : 코딩 스터디 2주차
 * 문제 출처 : https://www.acmicpc.net/problem/9663
 * 이름 : N-Queen
 * 사용 알고리즘 : 브루트포스 백트래킹
 */
public class BOJ9663 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        BOJ9663 main = new BOJ9663();
        main.solution(n);
    }

    public void solution(int n) {
        int[] arr = new int[n];
        Arrays.fill(arr, -1);
        System.out.print(this.placeQueen(arr, 0, 0));
    }

    public int placeQueen(int[] arr, int targetI, int queenCount) {
        if (queenCount == arr.length) return 1;

        int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (!this.isQueenAttackable(arr, targetI, j)) {
                    arr[targetI] = j;
                    count += this.placeQueen(arr, targetI+1, queenCount + 1);
                    arr[targetI] = -1;
                }
            }
        return count;
    }

    public boolean isQueenAttackable(int[] arr, int index1, int index2) {
        for (int i = 0; i < index1; i++) {
            int col =arr[i];
            if (col == index2 || index1 - i == index2 - col || index1 - i == col - index2) return true;
        }
        return false;
    }

}
