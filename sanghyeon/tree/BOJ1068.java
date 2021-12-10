package year_2021.month_12.day_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1068 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodeCount = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[nodeCount];
        for(int i=0; i<nodeCount; i++) nodes[i] = new Node(i);

        int rootIndex = -1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<nodeCount; i++){
            int parentIndex = Integer.parseInt(st.nextToken());
            if(parentIndex!=-1) nodes[parentIndex].childs.add(nodes[i]);
            else rootIndex = i;
        }
        int removeIndex = Integer.parseInt(br.readLine());

        int leafNodeCount = 0;
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(nodes[rootIndex]);
        while (!nodeQueue.isEmpty()){
            Node node = nodeQueue.poll();
            if(node.index != removeIndex){
                for(Node child : node.childs) nodeQueue.add(child);
                if(node.childs.contains(nodes[removeIndex])) node.childs.remove(nodes[removeIndex]);
                if(node.childs.isEmpty()) leafNodeCount++;
            }
        }

        System.out.println(leafNodeCount);
    }

    private static class Node {
        int index;
        ArrayList<Node> childs = new ArrayList<>();
        public Node(int index){ this.index = index; }
    }
}
