package year_2021.month_11.day_29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ159000 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodeCount = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[nodeCount];
        for(int i=0; i<nodeCount; i++) nodes[i] = new Node();

        StringTokenizer st;
        for(int i=0; i<nodeCount-1; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeIndexA = Integer.parseInt(st.nextToken())-1;
            int nodeIndexB = Integer.parseInt(st.nextToken())-1;
            nodes[nodeIndexA].connected.add(nodes[nodeIndexB]);
            nodes[nodeIndexB].connected.add(nodes[nodeIndexA]);
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(nodes[0]);

        int sum = 0;
        while (!queue.isEmpty()){
            Node nowNode = queue.poll();
            ArrayList<Node> child = nowNode.getChild();
            if(child.size()==0) sum+=nowNode.parent;
            else queue.addAll(child);
        }

        System.out.println(sum%2==0? "No":"Yes");
    }

    private static class Node{
        int parent = 0;
        ArrayList<Node> connected = new ArrayList<>();

        public ArrayList<Node> getChild() {
            ArrayList<Node> child = new ArrayList<>();
            for(Node node : connected){
                node.parent = parent+1;
                node.connected.remove(this);
                child.add(node);
            }
            return child;
        }
    }
}
