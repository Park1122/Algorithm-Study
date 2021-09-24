package codingstudy202107.w12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 12주차
 * 문제 출처 : https://www.acmicpc.net/problem/2667
 * 이름 : 단지 번호 붙이기
 * 사용 알고리즘 : dfs bfs
 * 집을 발견하면 그 집 주변을 탐색하여 큐에 넣어가며 단지 수를 세고, 단지에 포함된 집들과 빈집을 들렀을 때에는 방문처리를 한다.
 * 다음에 또 방문 안 한 집을 발견하면 또 반복. 단지에 포함 안 된 집에 들렀을 때 방문처리를 안하는 이유가 세야하기 때문.
 * 이슈가 하나 있는데, 문제는 이거다. 큐에 넣을 때 방문처리를 안하다 보니, 같은 집이 여러 번 큐에 들어간다..
 * 내 생각엔 방문 처리를 주변 집들을 넣을 때 하지 말고, 큐에 넣기 전에 해야 할 거 같다..아니면 지금처럼 큐에서 꺼낼때 방문했던건지 확인해도 되고.
 */
public class BOJ2667 {

    public static void main(String[] args) throws IOException {
        BOJ2667 main = new BOJ2667();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());


        Node[][] arr = new Node[n][n];
        for (int i = 0; i < n; i++) {
            String line = reader.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = new Node(Integer.parseInt(line.substring(j, j + 1)));
                if (i > 0) {
                    //지금꺼와 윗줄을 연결, 윗줄과 지금꺼를 연결
                    arr[i][j].addConnect(arr[i - 1][j]);
                    arr[i - 1][j].addConnect(arr[i][j]);
                }
                if (j > 0) {
                    //지금꺼와 왼쪽을 연결, 왼쪽과 지금꺼를 연결
                    arr[i][j - 1].addConnect(arr[i][j]);
                    arr[i][j].addConnect(arr[i][j - 1]);
                }
            }
        }

        System.out.print(main.solution(arr));
    }

    private String solution(Node[][] arr) {
        StringBuilder builder= new StringBuilder();
        Vector<Integer> answer = new Vector<>();

        for(Node[] line: arr){
            for(Node node: line){
                if(!node.isVisited()&&node.getValue()!=0){
                    int count=this.bfs(node);
                    answer.add(count);
                }
            }
        }
        builder.append(answer.size());

        Object[] ans= answer.toArray();
        Arrays.sort(ans);

        for(Object val: ans){
            builder.append('\n').append(val);
        }
        return builder.toString();
    }

    private int bfs(Node start){
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(start);
        int startVal=start.getValue();
        int count=0;

        while(!queue.isEmpty()){
            Node target = queue.poll();
            int val= target.getValue();

            if(val==0){//열어봤더니 집이 없음.
                target.visit();
            }else if(!target.isVisited()&&target.getValue()==startVal){//집이 있고, 시작점과 같은 번호 집이다. 그리고 들른적이 없다.
                target.visit();
                count++;
                Node[] nextNodes = target.getConnectedNodes();
                for(Node next: nextNodes){
                    if(next!=null&&!next.isVisited())queue.add(next); //사방에 있는 집 중 들른 적 없는 집들을 큐에 넣는다.
                }
            }
        }

        return count;
    }

    private static class Node {
        private final int value;
        private boolean visited;
        private final Node[] connectedNodes;
        private int connectedCount;

        public Node(int val) {
            this.visited = false;
            this.connectedNodes = new Node[4];
            this.connectedCount = 0;
            this.value = val;
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




    }
}
