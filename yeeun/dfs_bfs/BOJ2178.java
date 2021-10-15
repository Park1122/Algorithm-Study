package codingstudy202107.w14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 14주차
 * 문제 출처 : https://www.acmicpc.net/problem/2251
 * 이름 : 미로 팀색
 * 사용 알고리즘 : dfs bfs
 */
public class BOJ2178 {

    public static void main(String[] args) throws IOException {
        BOJ2178 main = new BOJ2178();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        Node[][] arr = new Node[n][m];
        for (int i = 0; i < n; i++) {
            String st = reader.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = new Node(Integer.parseInt(st.substring(j, j + 1)), i, j);
                if (i > 0) {
                    arr[i][j].addConnect(arr[i - 1][j]);
                    arr[i - 1][j].addConnect(arr[i][j]);
                }
                if (j > 0) {
                    arr[i][j].addConnect(arr[i][j - 1]);
                    arr[i][j - 1].addConnect(arr[i][j]);
                }
            }
        }

        System.out.print(main.solution(arr));
    }


    private int solution(Node[][] arr) {
        int[][] memoi = new int[arr.length][arr[0].length];
        return this.bfs(memoi, arr[0][0], 1);
    }

    private void visitAndMemorizeAndAdd(Node target, int[][] memoi,int count, Queue<Node> queue){
        target.visit();
        memoi[target.getRow()][target.getCol()] = count;
//        System.out.println(target.getRow()+" "+target.getCol()+" : "+count);
        queue.add(target);
    }

    private int bfs(int[][] memoi, Node start, int count) {
        Queue<Node> queue = new ArrayDeque<>();
        this.visitAndMemorizeAndAdd(start,memoi,count,queue);

        while (!queue.isEmpty()) {
            Node target = queue.poll();
            int targetCount = memoi[target.getRow()][target.getCol()];

            Node[] nodes = target.getConnectedNodes();
            for (Node next : nodes) {
                if (next != null && !next.isVisited()&&next.getValue()==1) {
                    this.visitAndMemorizeAndAdd(next,memoi,targetCount+1,queue);
                }
            }
        }

      return memoi[memoi.length-1][memoi[0].length-1];
    }


    private static class Node {
        private int value, row, col;
        private boolean visited;
        private final Node[] connectedNodes;
        private int connectedCount;

        public Node(int value, int row, int col) {
            this.row = row;
            this.col = col;
            this.value = value;
            this.visited = false;
            this.connectedNodes = new Node[4];
            this.connectedCount = 0;
        }

        public int getValue() {
            return this.value;
        }

        public void visit() {
            this.visited = true;
        }

        public void addConnect(Node node) {
            this.connectedNodes[connectedCount++] = node;
        }

        public boolean isVisited() {
            return this.visited;
        }

        public Node[] getConnectedNodes() {
            return this.connectedNodes;
        }

        public int getRow() {
            return this.row;
        }

        public int getCol() {
            return this.col;
        }
    }


}
