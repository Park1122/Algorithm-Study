package year_2021.month_10.day_18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1389 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Node[] nodes = new Node[N];
        for(int i=0; i<N; i++) nodes[i] = new Node(i);

        for(int i=0; i<M; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int nodeAId = Integer.parseInt(st2.nextToken()) - 1;
            int nodeBId = Integer.parseInt(st2.nextToken()) - 1;
            nodes[nodeAId].connectedNode.add(nodes[nodeBId]);
            nodes[nodeBId].connectedNode.add(nodes[nodeAId]);
        }

        int minVal = Integer.MAX_VALUE, minId = -1;
        for(int i=0; i<N; i++){
            int[] checked = new int[N];
            Queue<Node> queue = new LinkedList<>();
            queue.add(nodes[i]);
            while (!queue.isEmpty()) {
                Node nowNode = queue.poll();
                for(Node node : nowNode.connectedNode){
                    if(checked[node.id]==0 && node.id!= nodes[i].id){
                        queue.add(node);
                        checked[node.id] = checked[nowNode.id] + 1;
                    }
                }
            }
            int num = 0;
            for(int depth : checked) num += depth;
            if(num < minVal) {minId = i + 1; minVal = num;}
        }
        System.out.println(minId);
    }

    private static class Node {
        int id;
        ArrayList<Node> connectedNode = new ArrayList<>();

        public Node(int id) {
            this.id=id;
        }
    }
}
