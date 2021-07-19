package jungwook.binarysearch;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ1920 {
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static ArrayList<Integer> A, B;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }

        M = Integer.parseInt(br.readLine());
        B = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            B.add(Integer.parseInt(st.nextToken()));
        }
    }

    static void solve() {
        //Collections.sort() -> timsort
        Collections.sort(A);
        for (Integer num : B) {
            if (Collections.binarySearch(A, num) >= 0) {
                sb.append('1');
            } else {
                sb.append('0');
            }
            sb.append('\n');
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.println(sb.toString());
    }
}
