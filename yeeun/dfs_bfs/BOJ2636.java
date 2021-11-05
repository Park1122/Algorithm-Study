package codingstudy202107.w18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 18주차
 * 문제 출처 : https:/www.acmicpc.net/problem/2636
 * 이름 : 인구 이동
 * 사용 알고리즘 : bfs
 */
public class BOJ2636 {
    private static int bfsCount = -1;

    public static void main(String[] args) throws IOException {
        BOJ2636 main = new BOJ2636();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken()),
                l = Integer.parseInt(st.nextToken());
        int result = main.solution(reader, n, l);

        System.out.println(bfsCount);
        System.out.print(result);
    }

    private int solution(BufferedReader reader, int n, int l) throws IOException {
        Node[][] arr = new Node[n][l];
        Queue<Node> queue = new ArrayDeque<>();
        List<Node> meltingList = new ArrayList<>();

        StringTokenizer st;
        for (int j = 0; j < n; j++) {
            st = new StringTokenizer(reader.readLine());
            for (int k = 0; k < l; k++) {
                arr[j][k] = new Node(j, k, Integer.parseInt(st.nextToken()));
                if (j == 0 || j == n - 1 || k == 0 || k == l - 1) {
                    arr[j][k].visit = true;
                    queue.add(arr[j][k]);
                }

                if (j > 0) {
                    arr[j][k].next.add(arr[j - 1][k]);
                    arr[j - 1][k].next.add(arr[j][k]);
                }
                if (k > 0) {
                    arr[j][k].next.add(arr[j][k - 1]);
                    arr[j][k - 1].next.add(arr[j][k]);
                }
            }
        }

        return this.bfs(meltingList, queue);
    }

    private int bfs(List<Node> meltingList, Queue<Node> queue) {
        bfsCount++;
        int count = meltingList.size();
        meltingList.clear();

        while (!queue.isEmpty()) {
            Node target = queue.poll();
            for (Node next : target.next) {
                if (!next.visit) {
                    if (next.val == 0) {
                        next.visit = true;
                        queue.add(next);
                    } else {
                        next.val = 0;
                        next.visit = true;
                        meltingList.add(next);
                    }
                }
            }
        }

        queue.addAll(meltingList);

        if (meltingList.size() > 0) return this.bfs(meltingList, queue);
        else return count;
    }




    private class Node {
        boolean visit;
        int x, y, val;
        Vector<Node> next;

        public Node(int i, int j, int val) {
            this.x = i;
            this.y = j;
            this.val = val;
            this.visit = false;
            this.next = new Vector<>();
        }

    }


}
