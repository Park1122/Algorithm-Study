package codingstudy202107.w24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Vector;

public class BOJ9470 {
    public static void main(String[] args) throws IOException {
        BOJ9470 main = new BOJ9470();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int caseCount = Integer.parseInt(reader.readLine());
        StringBuilder builder = new StringBuilder();
        StringTokenizer st;
        for (int caseNo = 0; caseNo < caseCount; caseNo++) {
            st = new StringTokenizer(reader.readLine());
            int caseNum = Integer.parseInt(st.nextToken());
            int riverCount = Integer.parseInt(st.nextToken());
            int connectCount = Integer.parseInt(st.nextToken());

            River[] arr = new River[riverCount + 1];
            for (int i = 0; i < connectCount; i++) {
                st = new StringTokenizer(reader.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                if (arr[from] == null) arr[from] = new River();
                if (arr[to] == null) arr[to] = new River();
                River fromRiver = arr[from];
                River toRiver = arr[to];
                fromRiver.goingOutRivers.add(toRiver);
                toRiver.comingInCount += 1;
            }
//            main.printAllRivers(arr);
            main.calcAllRivers(arr);
            int result = arr[riverCount].calculatedVal;
            builder.append(caseNum).append(' ').append(result).append('\n');
        }
        builder.setLength(builder.length() - 1);
        System.out.print(builder);
    }

    private void printAllRivers(River[] arr) {
        StringBuilder sb = new StringBuilder();
        for (River r : arr) {
            if (r == null) {
                sb.append("null").append('\n');
            } else {
                sb.append("inputCount: " + r.comingInCount + " outPutCount: " + r.goingOutRivers.size() + " Max: " + r.maxComingInCalc + " MaxCount: " + r.maxCommingInCount + " calculatedVal: " + r.calculatedVal);
                sb.append('\n');
            }
        }
        System.out.println(sb);
    }

    private void calcAllRivers(River[] arr) {
        Queue<River> queue = new ArrayDeque<>();
        this.addNoComingInRiversToQueue(arr, queue);
        do {
            River riv = queue.poll();
            this.calculateComingInCountMinusToQueueGoingOutRivers(riv);
            this.addNoComingInRiversToQueue(arr, queue);
        } while (!queue.isEmpty());
    }

    private void calculateComingInCountMinusToQueueGoingOutRivers(River riv) {
        for (River goOut : riv.goingOutRivers) {
            goOut.comingInCount -= 1;
            if (goOut.maxComingInCalc == riv.calculatedVal) {
                goOut.maxCommingInCount += 1;
            } else if (goOut.maxComingInCalc < riv.calculatedVal) {
                goOut.maxComingInCalc = riv.calculatedVal;
                goOut.maxCommingInCount = 1;
            }
        }
    }

    private void addNoComingInRiversToQueue(River[] arr, Queue<River> queue) {
        for (River r : arr) {
            if (r != null && r.comingInCount == 0 && r.calculatedVal == 0) {
                if(r.maxComingInCalc==0&&r.maxCommingInCount==0){//초기값 그대로인경우.
                    r.calculatedVal=1;
                }else if (r.maxCommingInCount > 1) {
                    r.calculatedVal = r.maxComingInCalc + 1;
                } else {
                    r.calculatedVal = r.maxComingInCalc;
                }
                queue.add(r);
            }
        }
    }


    public static class River {
        Vector<River> goingOutRivers = new Vector<>();
        int maxComingInCalc = 0, maxCommingInCount = 0;
        int calculatedVal = 0, comingInCount = 0;
    }
}
