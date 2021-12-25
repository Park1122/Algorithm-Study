package codingstudy202107.w25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Vector;

public class BOJ1005 {
    public static void main(String[] args) throws IOException {
        BOJ1005 main = new BOJ1005();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder builder = new StringBuilder();

        int caseCount = Integer.parseInt(reader.readLine());
        StringTokenizer st;
        for (int i = 0; i < caseCount; i++) {
            st = new StringTokenizer(reader.readLine());
            int buildingCount = Integer.parseInt(st.nextToken());
            int ruleCount = Integer.parseInt(st.nextToken());

            Building[] arr = new Building[buildingCount + 1];
            st = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= buildingCount; j++) {
                arr[j] = new Building(j, Integer.parseInt(st.nextToken()));
            }

            for (int k = 0; k < ruleCount; k++) {
                st = new StringTokenizer(reader.readLine());
                int prevBuildingIndex = Integer.parseInt(st.nextToken());
                int buildingIndex = Integer.parseInt(st.nextToken());
                arr[prevBuildingIndex].nextBuildings.add(arr[buildingIndex]);
                arr[buildingIndex].prevBuildingCount += 1;
            }

            int targetBuildingIndex = Integer.parseInt(reader.readLine());
            int timeSpent = main.calcTimeToBuild(arr, targetBuildingIndex);
            builder.append(timeSpent).append('\n');
        }
        builder.setLength(builder.length() - 1);
        System.out.print(builder);
    }

    private int calcTimeToBuild(Building[] arr, int targetBuildingIndex) {
        Queue<Building> queue = new ArrayDeque<>();
        this.addBuildableBuildings(queue, arr);
        do {
            Building bd = queue.poll();
            if (bd.val == targetBuildingIndex) return bd.prevBuildingTime + bd.buildTime;
            for (Building next : bd.nextBuildings) {
                next.prevBuildingCount -= 1;
                next.prevBuildingTime=Math.max(next.prevBuildingTime,bd.prevBuildingTime+bd.buildTime);
            }
            this.addBuildableBuildings(queue, arr);
        } while (!queue.isEmpty());

        return -1;
    }

    private void addBuildableBuildings(Queue<Building> queue, Building[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Building bd = arr[i];
            if (bd != null && bd.prevBuildingCount == 0) {
                arr[i] = null;
                queue.add(bd);
            }
        }
    }


    private static class Building {
        int buildTime, prevBuildingCount, prevBuildingTime, val;
        Vector<Building> nextBuildings;

        private Building(int val, int buildTime) {
            this.val = val;
            this.buildTime = buildTime;

            this.prevBuildingCount = 0;
            this.prevBuildingTime = 0;
            this.nextBuildings = new Vector<>();
        }


    }
}
