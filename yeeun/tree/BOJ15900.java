package codingstudy202107.w22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * 회차 : w22
 * 문제 출처 : <a href =https://www.acmicpc.net/problem/15900>링크 </a>
 * 이름 : 나무 탈출
 * 사용 알고리즘 : tree
 * 설명
 * ==========================================================================
 */
public class BOJ15900 {
    public static void main(String[] args) throws IOException {
        BOJ15900 main = new BOJ15900();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodeCount = Integer.parseInt(reader.readLine());

        Vector<Integer>[] nodes = new Vector[nodeCount + 1];
        StringTokenizer st;
        for (int i=0; i<nodeCount-1; i++) {
            String next= reader.readLine();

            st = new StringTokenizer(next);
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            if (nodes[first] == null) nodes[first] = new Vector<>();
            if (nodes[second] == null) nodes[second] = new Vector<>();
            nodes[first].add(second);
            nodes[second].add(first);
        }

        if (main.calc(0, nodes,1) % 2 == 1) {
            System.out.print("Yes");
        } else {
            System.out.print("No");
        }

    }

    private int calc(int depth, Vector<Integer>[] nodes, int index) {
        Vector<Integer> node=nodes[index];
        if (node==null&&node.size() == 0) {
            return depth;
        } else {
            int sum = 0;
            for (int child : node) {
                nodes[child].remove((Object)index);
                sum += this.calc(depth + 1, nodes,child);
            }
            return sum;
        }
    }
}
