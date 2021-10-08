package year_2021.month_10.day_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P185_BOJ2251_π∞≈Î {

    private static int bottleCount = 3;
    private static int[] bottleSizes = new int[bottleCount];
    private static boolean[][][] travelHistory;
    private static boolean[] possibleSizes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<bottleCount; i++) bottleSizes[i] = Integer.parseInt(st.nextToken());
        travelHistory = new boolean[bottleSizes[0]+1][bottleSizes[1]+1][bottleSizes[2]+1]; // 0~bottleSize
        possibleSizes = new boolean[bottleSizes[2]+1]; // 0~bottleSizes

        travel(0, 0, bottleSizes[2]);

        for(int i=0; i<possibleSizes.length; i++){
            if(possibleSizes[i]) System.out.print(i+" ");
        }
    }

    private static void travel(int bottleWater0, int bottleWater1, int bottleWater2) {
        if(!travelHistory[bottleWater0][bottleWater1][bottleWater2]){
            travelHistory[bottleWater0][bottleWater1][bottleWater2] = true;

            if(bottleWater0 == 0) possibleSizes[bottleWater2] = true;

            int[] moveResult0To1 = getWaterMoveAToBResult(bottleWater0, bottleWater1, bottleSizes[1]);
            travel(moveResult0To1[0], moveResult0To1[1], bottleWater2);

            int[] moveResult0To2 = getWaterMoveAToBResult(bottleWater0, bottleWater2, bottleSizes[2]);
            travel(moveResult0To2[0], bottleWater1, moveResult0To2[1]);

            int[] moveResult1To0 = getWaterMoveAToBResult(bottleWater1, bottleWater0, bottleSizes[0]);
            travel(moveResult1To0[1], moveResult1To0[0], bottleWater2);

            int[] moveResult1To2 = getWaterMoveAToBResult(bottleWater1, bottleWater2, bottleSizes[2]);
            travel(bottleWater0, moveResult1To2[0], moveResult1To2[1]);

            int[] moveResult2To0 = getWaterMoveAToBResult(bottleWater2, bottleWater0, bottleSizes[0]);
            travel(moveResult2To0[1], bottleWater1, moveResult2To0[0]);

            int[] moveResult2To1 = getWaterMoveAToBResult(bottleWater2, bottleWater1, bottleSizes[1]);
            travel(bottleWater0, moveResult2To1[1], moveResult2To1[0]);
        }
    }
    private static int[] getWaterMoveAToBResult(int bottleWaterA, int bottleWaterB, int bottleSizeB) {
        int sum = bottleWaterA + bottleWaterB;
        int newBottleWaterB = Math.min(sum, bottleSizeB);
        int newBottleWaterA = sum - newBottleWaterB;
        return new int[]{newBottleWaterA, newBottleWaterB};
    }
}
