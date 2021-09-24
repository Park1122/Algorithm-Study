package year_2021.month_09.day_22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1260 {

    public static void main(String[] args) throws IOException {
        // Read Values
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int nodeNum = Integer.parseInt(st1.nextToken());
        int connectNum = Integer.parseInt(st1.nextToken());
        int startNodeId = Integer.parseInt(st1.nextToken());

        // Create Nodes
        Node[] nodes = new Node[nodeNum];
        for(int i=0; i<nodeNum; i++){
            nodes[i] = new Node(i+1); // index=0~i, id=1~i+1
        }

        // Connect Nodes
        for(int i=0; i<connectNum; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1Id = Integer.parseInt(st.nextToken());
            int node2Id = Integer.parseInt(st.nextToken());
            nodes[node1Id-1].connectedNodes.add(nodes[node2Id-1]);
            nodes[node2Id-1].connectedNodes.add(nodes[node1Id-1]);
        }

        // Sort Nodes
        for(Node node : nodes){
            node.sortConnectedNode();
        }

        // DFS
        Stack<Node> stack = new Stack<>();
        stack.add(nodes[startNodeId-1]);
        String dfs = "";
        while(stack.size()!=0){
            Node travelNode = stack.pop();
            if(!travelNode.traveled){
                travelNode.traveled = true;
                dfs += travelNode.id+" ";
                ArrayList<Node> connectedNodes = travelNode.connectedNodes;
                for(int i=connectedNodes.size()-1; i>=0; i--){
                    stack.add(connectedNodes.get(i));
                }
            }
        }
        System.out.println(dfs);
        for(Node node : nodes){
            node.traveled = false;
        }

        // BFS
        Queue<Node> queue = new LinkedList<>();
        queue.add(nodes[startNodeId-1]);
        String bfs = "";
        while(queue.size()!=0){
            Node travelNode = queue.poll();
            if(!travelNode.traveled){
                travelNode.traveled = true;
                bfs += travelNode.id+" ";
                queue.addAll(travelNode.connectedNodes);
            }
        }
        System.out.println(bfs);
    }

    private static class Node {
        public int id;
        public boolean traveled = false;
        public ArrayList<Node> connectedNodes;

        public Node(int id) {
            this.connectedNodes = new ArrayList<>();
            this.id=id;
        }

        public void sortConnectedNode() {
            connectedNodes.sort(Comparator.comparingInt(o -> o.id));
        }
    }
}
