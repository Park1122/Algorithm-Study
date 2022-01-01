package codingstudy202107.w26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Vector;

public class BOJ1753 {
    static Vector<Node>[] arr;
    static int startCity;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int pointCount = Integer.parseInt(st.nextToken());
        int lineCount = Integer.parseInt(st.nextToken());

        arr = new Vector[pointCount + 1];
        for (int i = 0; i < arr.length; i++) arr[i] = new Vector<>(1);
        startCity = Integer.parseInt(reader.readLine());

        for (int i = 0; i < lineCount; i++) {
            st = new StringTokenizer(reader.readLine());
            int startCity = Integer.parseInt(st.nextToken());
            int destinyCity = Integer.parseInt(st.nextToken());
            int pay = Integer.parseInt(st.nextToken());
            arr[startCity].add(new Node(destinyCity, pay));
        }

        System.out.print(dijkstra());
    }

    private static String dijkstra() {
        long[] answer = new long[arr.length];
        Arrays.fill(answer, Integer.MAX_VALUE);

        PriorityQueue<Node> queue = new PriorityQueue<>();
        answer[startCity] = 0;
        queue.add(new Node(startCity,0));

        while (!queue.isEmpty()) {
            Node poll=queue.poll();
            int check =poll.destiny;
            long currentDistance = answer[check];
            if (currentDistance == Integer.MAX_VALUE) continue;
            for (Node node : arr[check]) {
                int destiny=node.destiny;
                if (answer[destiny] > node.pay + currentDistance) {
                    answer[destiny] = node.pay + currentDistance;
                    queue.add(node);
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < arr.length; i++) {
            if (answer[i] == Integer.MAX_VALUE) builder.append("INF");
            else builder.append(answer[i]);
            builder.append('\n');
        }
        builder.setLength(builder.length() - 1);
        return builder.toString();
    }

    private static class Node implements Comparable<Node> {
        int destiny, pay;

        public Node(int destiny, int pay) {
            this.destiny = destiny;
            this.pay = pay;
        }


        @Override
        public int compareTo(Node that) {
            return this.pay - that.pay;
        }
    }


}
