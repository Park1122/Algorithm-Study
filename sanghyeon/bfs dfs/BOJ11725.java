package year_2021.month_10.day_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P184_BOJ11725_트리의부모찾기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodeNum = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[nodeNum];
        for(int i=0; i<nodeNum; i++) nodes[i] = new Node(i);

        int connectionNum = nodeNum-1;
        for(int i=0; i<connectionNum; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1Id = Integer.parseInt(st.nextToken());
            int node2Id = Integer.parseInt(st.nextToken());
            int node1Index = node1Id-1;
            int node2Index = node2Id-1;
            nodes[node1Index].connectedNode.add(nodes[node2Index]);
            nodes[node2Index].connectedNode.add(nodes[node1Index]);
        }

        int id1NodeIndex = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(nodes[id1NodeIndex]);
        boolean[] visited = new boolean[nodeNum];
        while(!queue.isEmpty()){
            Node nowPollNode = queue.poll();
            if(!visited[nowPollNode.index]){
                visited[nowPollNode.index] = true;
                for(Node child : nowPollNode.connectedNode) {
                    if(!visited[child.index]) child.parent = nowPollNode;
                }
                queue.addAll(nowPollNode.connectedNode);
            }
        }

        for(int i = 1; i<nodes.length; i++){
            System.out.println(nodes[i].parent.id);
        }
    }

    private static class Node {
        int index, id;
        ArrayList<Node> connectedNode = new ArrayList<>();
        Node parent;

        public Node(int index) {
            this.index = index;
            this.id = index+1;
        }
    }

}
