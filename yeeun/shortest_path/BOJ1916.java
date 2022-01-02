package codingstudy202107.w26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Vector;

public class BOJ1916 {
    static Vector<Node>[] arr;
    static int startCity, destinyCity;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int cityCount = Integer.parseInt(reader.readLine());
        int busCount = Integer.parseInt(reader.readLine());
        StringTokenizer st;

        arr = new Vector[cityCount + 1];
        for (int i=0; i<arr.length;i++)
            arr[i]=new Vector<>(1);

        for (int i = 0; i < busCount; i++) {
            st = new StringTokenizer(reader.readLine());
            int startCity = Integer.parseInt(st.nextToken());
            int destinyCity = Integer.parseInt(st.nextToken());
            int pay = Integer.parseInt(st.nextToken());
            arr[startCity].add(new Node(destinyCity, pay));
        }

        st = new StringTokenizer(reader.readLine());
        startCity = Integer.parseInt(st.nextToken());
        destinyCity = Integer.parseInt(st.nextToken());

        System.out.print(dijkstra());
    }



    private static long dijkstra() {
        int[] answer = new int[arr.length];
        Arrays.fill(answer, Integer.MAX_VALUE);

        boolean[] visited= new boolean[arr.length];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        answer[startCity] = 0;//출발지, 도착지 같은 경우.
        pq.add(new Node(startCity,0));

        while (!pq.isEmpty()) {
            Node poll= pq.poll();
            int check = poll.destiny;
            if(!visited[check]) {
                visited[check]=true;
                int currentDistance = answer[check];
                for (Node node : arr[check]) {
                    int destiny = node.destiny;
                    if (!visited[destiny]&&
                            answer[destiny] > currentDistance + node.pay) {
                        answer[destiny] = currentDistance + node.pay;
                        pq.add(new Node(node.destiny,answer[destiny]));
                    }
                }
            }
        }
        return answer[destinyCity];
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
