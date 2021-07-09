package codingstudy202107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 1주차
 * 문제 출처 : https://www.acmicpc.net/problem/15649
 * 이름 : N과 M (1)
 * 사용 알고리즘 : 백트래킹 순열
 * 설명 : for문에서 하나씩 이미 사용한 숫자인지 확인하고, 안 쓴 숫자면 배열에 넣고 재귀 호출.
 * 조사한 것과 다르게 짠 것: 조사한 웹페이지에서는 ABCD로 배열하는 것이라서, 사용할 알파벳을 넣는 배열과 프린트를 위한 결과 배열, 알파벳 사용 여부 배열 세가지 배열이 있었으나,
 * 여기서는 그렇게 하지 않고, 숫자는 1부터 n이 조건이므로, n이라는 숫자를 max로 하여 들고다니며 사용하였다.
 */
public class BOJ15649 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        BOJ15649 main = new BOJ15649();
        main.solution(n, m);
    }

    public void solution(int n, int m) {
        this.recursion(new boolean[n], new int[m], n, 0);
    }

    public void recursion(boolean[] visited, int[] arr, int max, int count) {
        if (arr.length == count) {
            this.printArray(arr);
        } else {
            for (int i = 0; i < max; i++) {
                if (visited[i]) continue;
                arr[count] = i + 1;
                visited[i] = true;
                this.recursion(visited, arr, max, count + 1);
                visited[i] = false;
            }
        }
    }

    public void printArray(int[] arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arr.length; ) {
            stringBuilder.append(arr[i++]);
            if (i != arr.length) {
                stringBuilder.append(" ");
            }
        }
        System.out.println(stringBuilder.toString());
    }
}
