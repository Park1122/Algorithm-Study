package year_2021.month_12.day_18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2623 {

    public static void main(String[] args) throws Exception {
        // Read Value
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int singerCount = Integer.parseInt(st.nextToken());
        int slaveCount = Integer.parseInt(st.nextToken());

        // Create Node
        Node[] nodes = new Node[singerCount];
        for(int i=0; i<singerCount; i++) nodes[i] = new Node(i);

        // Create Graph
        for(int i=0; i<slaveCount; i++) {
            st = new StringTokenizer(br.readLine());
            int length = Integer.parseInt(st.nextToken());
            if(length<2) continue;
            int beforeId = Integer.parseInt(st.nextToken())-1;
            for(int j=0; j<length-1; j++){
                int afterId = Integer.parseInt(st.nextToken())-1;
                nodes[beforeId].after.add(nodes[afterId]);
                nodes[afterId].before.add(nodes[beforeId]);
                beforeId = afterId;
            }
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
        if(sortedList.size()<singerCount) System.out.println(0);
        else for(Node node : sortedList) System.out.println((node.id+1)+" ");
    }

    private static class Node {
        int id;
        ArrayList<Node> before = new ArrayList<>();
        ArrayList<Node> after = new ArrayList<>();

        public Node(int id) { this.id=id; }
    }
}
