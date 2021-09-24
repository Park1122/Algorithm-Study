package year_2021.month_09.day_21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15681 {

    public static void main(String[] args) throws IOException {
        // Read Values
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeNum = Integer.parseInt(st.nextToken());
        int rootNum = Integer.parseInt(st.nextToken());
        int queryNum = Integer.parseInt(st.nextToken());

        // Create Nodes
        Node[] nodes = new Node[nodeNum+1];
        for(int i=1; i<=nodeNum; i++){
            nodes[i] = new Node(i);
        }

        // Connect Nodes
        for(int i=0; i<nodeNum-1; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int node1Id = Integer.parseInt(st2.nextToken());
            int node2Id = Integer.parseInt(st2.nextToken());
            nodes[node1Id].connectedNodes.add(nodes[node2Id]);
            nodes[node2Id].connectedNodes.add(nodes[node1Id]);
        }

        // Clear Nodes
        nodes[rootNum].clearNode();

        // Get SubtreeSize
        nodes[rootNum].getSubTreeSize();

        // Query
        for(int i=0; i<queryNum; i++){
            int queryNode = Integer.parseInt(br.readLine());
            System.out.println(nodes[queryNode].subTreeSize);
        }
    }

    public static class Node {
        public int nodeId, subTreeSize=0;
        public ArrayList<Node> connectedNodes = new ArrayList<>();


        public Node(int nodeId) {
            this.nodeId=nodeId;
        }

        public void clearNode() {
            for(Node childNode : connectedNodes){
                childNode.connectedNodes.remove(this);
                childNode.clearNode();
            }
        }

        public int getSubTreeSize() {
            for(Node childNode : connectedNodes){
                subTreeSize+=childNode.getSubTreeSize();
            }
            subTreeSize += 1;
            return subTreeSize;
        }
    }
}
