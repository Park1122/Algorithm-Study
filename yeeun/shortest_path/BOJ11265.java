package codingstudy202107.w27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11265 {
    static int[][] arr, answerArr;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int partyCount = Integer.parseInt(tokenizer.nextToken());
        int customerCount = Integer.parseInt(tokenizer.nextToken());

        arr = new int[partyCount + 1][partyCount + 1];
        answerArr = new int[partyCount + 1][partyCount + 1];
        for (int i = 1; i <= partyCount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= partyCount; j++) {
                int distance = Integer.parseInt(tokenizer.nextToken());
                arr[i][j] = distance;
            }
        }

        for (int i = 1; i <= partyCount; i++) {
            dijkstra(i);
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < customerCount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int currentPlace = Integer.parseInt(tokenizer.nextToken());
            int destiny = Integer.parseInt(tokenizer.nextToken());
            int timeLimit = Integer.parseInt(tokenizer.nextToken());
            if (answerArr[currentPlace][destiny] > timeLimit) builder.append("Stay here\n");
            else builder.append("Enjoy other party\n");
        }
        builder.setLength(builder.length() - 1);
        System.out.print(builder);
    }

    private static void dijkstra(int startCity) {
        int[] answer = answerArr[startCity];
        Arrays.fill(answer, Integer.MAX_VALUE);

        boolean[] visited = new boolean[arr.length];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        answer[startCity] = 0;//출발지, 도착지 같은 경우.
        pq.add(new Node(startCity, 0));

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            int check = poll.destiny;
            if (!visited[check]) {
                visited[check] = true;
                int currentDistance = answer[check];
                for (int destiny = 1; destiny < arr.length; destiny++) {
                    int pay = arr[check][destiny];
                    if (!visited[destiny] &&
                            answer[destiny] > currentDistance + pay) {
                        answer[destiny] = currentDistance + pay;
                        pq.add(new Node(destiny, answer[destiny]));
                    }
                }
            }
        }
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
