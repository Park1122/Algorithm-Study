package codingstudy202107.w13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 13주차
 * 문제 출처 : https://www.acmicpc.net/problem/3184
 * 이름 : 양
 * 사용 알고리즘 : dfs bfs
 */
public class BOJ3184 {

    public enum EType{
        barrier,sheep,wolf,space;
    }

    public static void main(String[] args) throws IOException {
        BOJ3184 main = new BOJ3184();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        Node[][] arr= new Node[row][col];
        for(int i=0; i<row; i++){
            String line = reader.readLine();
            Node[] nodes = new Node[col];
            for(int j=0; j<col; j++){
                nodes[j] = new Node(line.substring(j, j + 1));
                if (i > 0) {
                    nodes[j].addConnect(arr[i - 1][j]);
                    arr[i - 1][j].addConnect(nodes[j]);
                }
                if (j > 0) {
                    nodes[j].addConnect(nodes[j - 1]);
                    nodes[j - 1].addConnect(nodes[j]);
                    }
            }
            arr[i] = nodes;
        }
           System.out.print(main.solution(arr));
    }


    private String solution(Node[][] arr) {
        int countSheep=0, countWolf = 0;
        for (Node[] line : arr) {
            for (Node val : line) {
                if (!val.isVisited()) {
                    int[] result = this.bfs(val);
                    countSheep+=result[0];
                    countWolf+=result[1];
                }
            }
        }

        return new StringBuilder().append(countSheep).append(' ').append(countWolf).toString();
    }

    private int[] bfs(Node start) {
        int[] result = new int[2];

        Deque<Node> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Node target = queue.poll();
            EType val = target.getValue();

            if (val.equals(EType.barrier)) {//울타리.
                target.visit();
            } else if (!target.isVisited()) {
                if (val.equals(EType.sheep)) result[0]++;
                if (val.equals(EType.wolf)) result[1]++;
                target.visit();
                Node[] nextNodes = target.getConnectedNodes();
                for (Node next : nextNodes) {
                    if (next != null && !next.isVisited()) queue.add(next);
                }
            }
        }

        if(result[0]>result[1]) result[1]=0;
        else result[0]=0;

        return result;
    }


    private static class Node {
        private EType value;
        private boolean visited;
        private final Node[] connectedNodes;
        private int connectedCount;



        private EType stringToType(String value){
            switch(value){
                case "#" :
                    return EType.barrier;
                case "o" :
                    return EType.sheep;
                case "v" :
                    return EType.wolf;
                case "." :
                    return EType.space;
            }
            return null;
        }

        public Node(String value) {
            this.value=this.stringToType(value);
            this.visited = false;
            this.connectedNodes = new Node[4];
            this.connectedCount = 0;
        }

        public EType getValue() {
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
    }


}
