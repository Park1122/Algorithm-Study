package codingstudy202107.w25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Vector;

public class BOJ2056 {
    public static void main(String[] args) throws IOException {
        BOJ2056 main = new BOJ2056();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int buildingTypeCount = Integer.parseInt(reader.readLine());
        Task[] arr = new Task[buildingTypeCount + 1];
        StringTokenizer st;
        for (int i = 1; i <= buildingTypeCount; i++) {
            st = new StringTokenizer(reader.readLine());
            int buildTime = Integer.parseInt(st.nextToken());
            if (arr[i] == null) arr[i] = new Task(i);
            arr[i].spentTime = buildTime;
            int prevCount = Integer.parseInt(st.nextToken());
            for (int j=0; j<prevCount; j++) {
                int prevBuildingIdx = Integer.parseInt(st.nextToken());
                arr[i].prevTaskCount += 1;
                if (arr[prevBuildingIdx] == null) arr[prevBuildingIdx] = new Task(prevBuildingIdx);
                arr[prevBuildingIdx].nextTasks.add(arr[i]);
            }
        }
        System.out.print(main.calcTimeToFinishAllTask(arr));
    }

    private String calcTimeToFinishAllTask(Task[] arr) {
        Queue<Task> queue = new ArrayDeque<>();
        this.addBuildableBuildings(queue, arr);
        do {
            Task bd = queue.poll();
            for (Task next : bd.nextTasks) {
                next.prevTaskCount -= 1;
                next.prevTaskTime = Math.max(next.prevTaskTime, bd.prevTaskTime + bd.spentTime);
            }
            this.addBuildableBuildings(queue, arr);
        } while (!queue.isEmpty());

        int maxTime=0;
        for(Task bd: arr){
            if(bd!=null)
                maxTime=Math.max(maxTime,bd.prevTaskTime+bd.spentTime);
        }
        return String.valueOf(maxTime);
    }

    private void addBuildableBuildings(Queue<Task> queue, Task[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Task bd = arr[i];
            if (bd.prevTaskCount == 0) {
                bd.prevTaskCount = -1;
                queue.add(bd);
            }
        }
    }


    private static class Task {
        int spentTime, prevTaskCount, prevTaskTime, val;
        Vector<Task> nextTasks;

        private Task(int val) {
            this.val = val;

            this.spentTime = 0;
            this.prevTaskCount = 0;
            this.prevTaskTime = 0;
            this.nextTasks = new Vector<>();
        }
    }
}
