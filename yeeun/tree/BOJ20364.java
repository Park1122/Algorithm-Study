package codingstudy202107.w22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 회차 : w22
 * 문제 출처 : <a href =https://www.acmicpc.net/problem/20364>링크 </a>
 * 이름 : 부동산다툼
 * 사용 알고리즘 : 트리
 * 설명
 * ==========================================================================
 */
public class BOJ20364 {

    public static void main(String[] args) throws IOException {
        BOJ20364 main = new BOJ20364();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int nodeCount = Integer.parseInt(st.nextToken());
        int chickCount = Integer.parseInt(st.nextToken());

        boolean[] isNonVisitableArr = new boolean[nodeCount + 1];
        StringBuilder sb = new StringBuilder();

        for (int chick = 0; chick < chickCount; chick++) {
            int hopeNodeNum = Integer.parseInt(reader.readLine());
            sb.append(main.checkVisitable(hopeNodeNum, isNonVisitableArr)). append('\n');
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

    private int checkVisitable(int hopeNodeNum, boolean[] isNonVisitableArr) {
        int nodeNum = hopeNodeNum;
        int othersNodeNum=0;
        while (nodeNum > 1) {
            if (isNonVisitableArr[nodeNum]) othersNodeNum=nodeNum;
            if (nodeNum % 2 == 1) {
                nodeNum = (--nodeNum) / 2;
            } else nodeNum /= 2;
        }
        if(othersNodeNum==0) isNonVisitableArr[hopeNodeNum] = true;
        return othersNodeNum;
    }
}
