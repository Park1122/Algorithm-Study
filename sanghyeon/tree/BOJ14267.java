package year_2021.month_12.day_16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14267 {

    public static void main(String[] args) throws Exception {
        // Read Value
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int peopleCount = Integer.parseInt(st.nextToken());
        int praiseCount = Integer.parseInt(st.nextToken());

        // Create Nodes
        Node[] nodes = new Node[peopleCount];
        for(int i=0; i<peopleCount; i++) nodes[i]= new Node();

        // Create Tree
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<peopleCount; i++){
            int parent = Integer.parseInt(st.nextToken());
            if(parent==-1) continue;
            nodes[parent-1].directChild.add(nodes[i]);
        }

        // First Praise
        for(int i=0; i<praiseCount; i++){
            st = new StringTokenizer(br.readLine());
            int praisePeopleId = Integer.parseInt(st.nextToken())-1;
            int praiseLevel = Integer.parseInt(st.nextToken());
            nodes[praisePeopleId].praise+=praiseLevel;
        }

        // Child Praise
        Queue<Node> queue = new LinkedList<>();
        queue.add(nodes[0]);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            for(Node child : node.directChild){
                child.praise+=node.praise;
                queue.add(child);
            }
        }

        // Print
        for(Node node : nodes) System.out.print(node.praise+" ");
    }

    private static class Node {
        int praise=0;
        ArrayList<Node> directChild = new ArrayList<>();
    }
}
