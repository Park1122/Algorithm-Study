package codingstudy202107.w11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 11주차
 * 문제 출처 : https://www.acmicpc.net/problem/2096
 * 이름 : 내려가기
 * 사용 알고리즘 : 다이나믹 프로그래밍
 * bottom-up으로 했음.
 * 어떤 칸을 밟았을 때 몇점의 점수를 받을 수 있었는지를 최대값과 최소값을 나누어 저장함.
 * 그리고 그 다음줄에서 앞 줄의 칸을 밟고 왔을 거니까 자신의 값과 합치고 최대값과 최소값을 나누어서 또 계산.
 * 마지막에는 이제 마지막 칸의 다음줄인척하고 가운데 칸으로 요청하면 3칸 모두 갈 수 있으므로 그렇게 호출해서 마지막 칸의 최소값과 최대값을 가져온다.
 */
public class BOJ2096 {

    public static void main(String[] args) throws IOException {
        BOJ2096 main = new BOJ2096();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(reader.readLine());
            arr[i][0]=Integer.parseInt(st.nextToken());
            arr[i][1]=Integer.parseInt(st.nextToken());
            arr[i][2]=Integer.parseInt(st.nextToken());
        }

        System.out.print(main.solution(arr));
    }

    private String solution(int[][] arr) {
        StringBuilder builder = new StringBuilder();

        int[][] bigDp= new int[arr.length][arr[0].length];
        int[][] smallDp= new int[arr.length][arr[0].length];

        bigDp[0][0]=arr[0][0];
        bigDp[0][1]=arr[0][1];
        bigDp[0][2]=arr[0][2];

        smallDp[0][0]=arr[0][0];
        smallDp[0][1]=arr[0][1];
        smallDp[0][2]=arr[0][2];

        for(int i=1;i<arr.length; i++){//줄 내려가기.
            for(int j=0; j<arr[0].length;j++){//3개 중 하나씩 보기
                int target = arr[i][j];
                bigDp[i][j]=target+this.getBigScore(bigDp[i-1],j);
                smallDp[i][j]=target+this.getSmallScore(smallDp[i-1],j);
            }
        }

        builder.append(this.getBigScore(bigDp[arr.length-1],1)).append(" ").append(this.getSmallScore(smallDp[arr.length-1],1));
        return builder.toString();
    }

    private int getBigScore(int[] bigDp, int colNum) {
        int big= bigDp[colNum];
        if(colNum-1>=0){
            big= Math.max(big, bigDp[colNum-1]);
        }

        if(colNum+1<bigDp.length){
            big= Math.max(big, bigDp[colNum+1]);
        }

        return big;
    }

    private int getSmallScore(int[] smallDp, int colNum) {
        int small= smallDp[colNum];
        if(colNum-1>=0){
            small= Math.min(small, smallDp[colNum-1]);
        }

        if(colNum+1< smallDp.length){
            small= Math.min(small, smallDp[colNum+1]);
        }

        return small;
    }


}
