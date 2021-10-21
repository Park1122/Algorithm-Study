package year_2021.month_10.day_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2644 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[n];
        for(int i=0; i<n; i++) nodes[i] = new Node(i);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int targetNode1Index = Integer.parseInt(st.nextToken())-1;
        int targetNode2Index = Integer.parseInt(st.nextToken())-1;

        int connectionCount = Integer.parseInt(br.readLine());
        for(int i=0; i<connectionCount; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int nodeAIndex = Integer.parseInt(st2.nextToken())-1;
            int nodeBIndex = Integer.parseInt(st2.nextToken())-1;
            nodes[nodeAIndex].childNodes.add(nodes[nodeBIndex]);
            nodes[nodeBIndex].childNodes.add(nodes[nodeAIndex]);
        }

        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(nodes[targetNode1Index]);
        while(!nodeQueue.isEmpty()){
            Node pollNode = nodeQueue.poll();
            for(Node child : pollNode.childNodes){
                child.childNodes.remove(pollNode);
                nodeQueue.add(child);
            }
        }

        int minDiff = -1;
        nodeQueue = new LinkedList<>();
        nodeQueue.add(nodes[targetNode1Index]);
        nodes[targetNode1Index].depth=0;
        while(!nodeQueue.isEmpty()){
            Node pollNode = nodeQueue.poll();
            for(Node child : pollNode.childNodes){
                child.depth = pollNode.depth+1;
                nodeQueue.add(child);
                if(child.index == targetNode2Index){
                    minDiff = child.depth;
                    nodeQueue.clear();
                }
            }
        }
        System.out.println(minDiff);
    }

    private static class Node {
        int index, depth;
        ArrayList<Node> childNodes = new ArrayList<>();
        public Node (int index){ this.index=index;}
    }
}
