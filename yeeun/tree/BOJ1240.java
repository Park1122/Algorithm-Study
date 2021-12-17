package codingstudy202107.w23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1240 {
    public static void main(String[] args) throws IOException {
        BOJ1240 main = new BOJ1240();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int nodeCount = Integer.parseInt(st.nextToken());
        int caseCount = Integer.parseInt(st.nextToken());
        Map<Integer, Integer>[] arr = new HashMap[nodeCount + 1];

        for (int i = 0; i < nodeCount - 1; i++) {
            st = new StringTokenizer(reader.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            if (arr[nodeA] == null) arr[nodeA] = new HashMap<>();
            if (arr[nodeB] == null) arr[nodeB] = new HashMap<>();
            arr[nodeA].put(nodeB, distance);
            arr[nodeB].put(nodeA, distance);
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < caseCount; i++) {
            st = new StringTokenizer(reader.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());
            builder.append(main.getDistance(arr, nodeA, nodeB)).append('\n');
        }
        builder.setLength(builder.length() - 1);
        System.out.print(builder);
    }

    private long getDistance(Map<Integer, Integer>[] arr, int nodeA, int nodeB) {
        boolean[] visited = new boolean[arr.length];
//        return this.getDFSDistance(arr,nodeA,nodeB,visited);
        return this.getBFSDistance(arr, nodeA, nodeB, visited);
    }

    private long getBFSDistance(Map<Integer, Integer>[] arr, int nodeA, int nodeB, boolean[] visited) {
        Queue<Pair> queue = new ArrayDeque<>();
        visited[nodeA] = true;
        queue.add(new Pair(nodeA, 0L));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            if (pair.idx == nodeB) return pair.distance;

            for (int childIdx : arr[pair.idx].keySet()) {
                if (!visited[childIdx]) {
                    visited[childIdx] = true;
                    int childDistance = arr[pair.idx].get(childIdx);
                    queue.add(new Pair(childIdx, childDistance + pair.distance));
                }
            }
        }
        return -1L;
    }

    private long getDFSDistance(Map<Integer, Integer>[] arr, int nodeA, int nodeB, boolean[] visited) {
        Map<Integer, Integer> lines = arr[nodeA];
        if (nodeA == nodeB) return 0;//

        for (int dest : lines.keySet()) {
            if (!visited[dest]) {
                visited[dest] = true;
                long result = this.getDFSDistance(arr, dest, nodeB, visited);
                if (result > -1L) {
                    return lines.get(dest) + result;
                }
            }
        }
        return -1L;
    }

    public static class Pair {
        int idx;
        long distance;

        public Pair(int idx, long distance) {
            this.idx = idx;
            this.distance = distance;
        }
    }
}
