package year_2021.month_09.day_27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11724 {

    private static Node[] nodes;
    private static boolean[] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeNum = Integer.parseInt(st.nextToken());

        nodes = new Node[nodeNum];
        for(int i=0; i<nodeNum; i++){
            nodes[i] = new Node(i+1);
        }
        checked = new boolean[nodeNum];

        int connectNum = Integer.parseInt(st.nextToken());
        for(int i=0; i<connectNum; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int node1Id = Integer.parseInt(st2.nextToken());
            int node2Id = Integer.parseInt(st2.nextToken());
            nodes[node1Id-1].connectedNode.add(nodes[node2Id-1]);
            nodes[node2Id-1].connectedNode.add(nodes[node1Id-1]);
        }

        int count = 0;
        for(int i=0; i<nodeNum; i++){
            if(!checked[i]){
                travel(i+1);
                count++;
            }
        }
        System.out.println(count);
    }

    private static void travel(int nodeId) {
        checked[nodeId-1] = true;
        for(Node n : nodes[nodeId-1].connectedNode){
            if(!checked[n.id-1]){
                travel(n.id);
            }
        }
    }

    private static class Node {
        public int id;
        public ArrayList<Node> connectedNode;

        public Node(int i){
            id = i;
            connectedNode = new ArrayList<>();
        }
    }
}
