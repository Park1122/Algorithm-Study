package codingstudy202107.w25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Vector;

public class BOJ1516 {
    public static void main(String[] args) throws IOException {
        BOJ1516 main = new BOJ1516();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int buildingTypeCount = Integer.parseInt(reader.readLine());
        Building[] arr = new Building[buildingTypeCount + 1];
        StringTokenizer st;
        for (int i = 1; i <= buildingTypeCount; i++) {
            st = new StringTokenizer(reader.readLine());
            int buildTime = Integer.parseInt(st.nextToken());
            if (arr[i] == null) arr[i] = new Building(i);
            arr[i].buildTime = buildTime;
            while (true) {
                int prevBuildingIdx = Integer.parseInt(st.nextToken());
                if (prevBuildingIdx == -1) break;
                arr[i].prevBuildingCount += 1;
                if (arr[prevBuildingIdx] == null) arr[prevBuildingIdx] = new Building(prevBuildingIdx);
                arr[prevBuildingIdx].nextBuildings.add(arr[i]);
            }
        }
        System.out.print(main.calcTimeToBuild(arr));
    }

    private String calcTimeToBuild(Building[] arr) {
        Queue<Building> queue = new ArrayDeque<>();
        this.addBuildableBuildings(queue, arr);
        do {
            Building bd = queue.poll();
            for (Building next : bd.nextBuildings) {
                next.prevBuildingCount -= 1;
                next.prevBuildingTime = Math.max(next.prevBuildingTime, bd.prevBuildingTime + bd.buildTime);
            }
            this.addBuildableBuildings(queue, arr);
        } while (!queue.isEmpty());

        StringBuilder builder= new StringBuilder();
        for(Building bd: arr){
            if(bd!=null)
            builder.append(bd.prevBuildingTime+bd.buildTime).append('\n');
        }
        builder.setLength(builder.length()-1);
        return builder.toString();
    }

    private void addBuildableBuildings(Queue<Building> queue, Building[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Building bd = arr[i];
            if (bd.prevBuildingCount == 0) {
                bd.prevBuildingCount = -1;
                queue.add(bd);
            }
        }
    }


    private static class Building {
        int buildTime, prevBuildingCount, prevBuildingTime, val;
        Vector<Building> nextBuildings;

        private Building(int val) {
            this.val = val;

            this.buildTime = 0;
            this.prevBuildingCount = 0;
            this.prevBuildingTime = 0;
            this.nextBuildings = new Vector<>();
        }


    }
}
