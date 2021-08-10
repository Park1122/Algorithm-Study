package jungwook.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1715 {
    static int N;
    static PriorityQueue<Long> pq;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            pq.add(Long.parseLong(br.readLine()));
        }
    }

    static void solve() {
        long sum = 0;
        while (pq.size()>1) {
            long first = pq.poll();
            long second = pq.poll();

            sum += (first + second);
            pq.add(first + second);
        }
        System.out.println(sum);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
