package year_2021.month_12.day_17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2252 {

    public static void main(String[] args) throws Exception {
        // Read Value
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int peopleCount = Integer.parseInt(st.nextToken());
        int compareCount = Integer.parseInt(st.nextToken());

        // Create Node
        Node[] nodes = new Node[peopleCount];
        for(int i=0; i<peopleCount; i++) nodes[i] = new Node(i);

        // Create Graph
        for(int i=0; i<compareCount; i++) {
            st = new StringTokenizer(br.readLine());
            int beforeId = Integer.parseInt(st.nextToken())-1;
            int afterId = Integer.parseInt(st.nextToken())-1;
            nodes[beforeId].after.add(nodes[afterId]);
            nodes[afterId].before.add(nodes[beforeId]);
        }

        // Sort
        Queue<Node> queue = new LinkedList<>();
        ArrayList<Node> sortedList = new ArrayList<>();
        for(Node node : nodes){
            if(node.before.size()==0) queue.add(node);
        }
        while(!queue.isEmpty()){
            Node node = queue.poll();
            for(Node after : node.after){
                after.before.remove(node);
                if(after.before.size()==0) queue.add(after);
            }
            sortedList.add(node);
        }

        // Print
        for(Node node : sortedList) System.out.print((node.id+1)+" ");
    }

    private static class Node {
        int id;
        ArrayList<Node> before = new ArrayList<>();
        ArrayList<Node> after = new ArrayList<>();

        public Node(int id) { this.id=id; }
    }
}
