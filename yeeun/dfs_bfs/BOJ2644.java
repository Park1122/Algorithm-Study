package codingstudy202107.w15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * 회차 : 코딩 스터디 15주차
 * 문제 출처 : https://www.acmicpc.net/problem/2644
 * 이름 : 촌수 계산
 * 사용 알고리즘 : dfs bfs
 * 부모와 자식을 구분해서 넣으려고 생각하고 있었다. 근데 다시 생각해보니까, 어차피 촌수만 구하는 건데,
 * 두 사람이 서로 1촌 관계인 것만 알면 되지 그게 부모인지 자식인지 구분할 필요가 없었던 것.
 */
public class BOJ2644 {

    public static void main(String[] args) throws IOException {
        BOJ2644 main = new BOJ2644();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());


        StringTokenizer st = new StringTokenizer(reader.readLine());
        int p1 = Integer.parseInt(st.nextToken()), p2 = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(reader.readLine());
        int[][] parent = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int p = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            parent[i]= new int[]{p, c};
        }

        System.out.print(main.solution(n, p1, p2, parent));
    }

    private int solution(int n, int p1, int p2, int[][] parent) {
        Node[] arr = new Node[n+1];
        for (int i = 0; i <= n; i++) {
            arr[i] = new Node(i);
        }


        for (int[] line: parent) {
                arr[line[0]].addIlchon(arr[line[1]]);
                arr[line[1]].addIlchon(arr[line[0]]);
        }

        Queue<Node> queue = new ArrayDeque<>();
        this.addAndVisitAndSetChon(queue,arr[p1],0);

        return this.bfs(queue,arr[p2]);
    }


    private void addAndVisitAndSetChon(Queue<Node> queue, Node node, int chon){
        queue.add(node);
        node.visit();
        node.chon=chon;
    }

    private int bfs(Queue<Node> queue, Node p2) {
        while(!queue.isEmpty()){
            Node target = queue.poll();
            if(target.equals(p2)) return target.chon;

            for(Node node: target.ilchons){
                if(!node.isVisited())this.addAndVisitAndSetChon(queue,node,target.chon+1);
            }
        }
        return -1;
    }


    private class Node {//귀찮아서 이제 encapulation 안할래오 인터넷에도 하는 사람 잘 없더라구..
        int val, chon;
        Vector<Node> ilchons;
        boolean visited;

        public Node(int val) {
            this.val = val;
            this.ilchons=new Vector<>();
        }

        public void addIlchon(Node ilchon)
        {
            this.ilchons.add(ilchon);
        }

        public void visit() {
            this.visited = true;
        }

        public boolean isVisited() {
            return this.visited;
        }
    }


}
