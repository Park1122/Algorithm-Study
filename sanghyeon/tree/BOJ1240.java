package year_2021.month_12.day_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1240 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeCount = Integer.parseInt(st.nextToken());
        int testcaseCount = Integer.parseInt(st.nextToken());
        Node[] nodes = new Node[nodeCount];
        for(int i=0; i<nodeCount; i++) nodes[i] = new Node(i);
        for(int i=0; i<nodeCount-1; i++){
            st = new StringTokenizer(br.readLine());
            int nodeAIndex = Integer.parseInt(st.nextToken())-1;
            int nodeBIndex = Integer.parseInt(st.nextToken())-1;
            int val = Integer.parseInt(st.nextToken());
            nodes[nodeAIndex].connections.add(new Connection(nodes[nodeBIndex], val));
            nodes[nodeBIndex].connections.add(new Connection(nodes[nodeAIndex], val));
        }
        for(int i=0; i<testcaseCount; i++){
            st = new StringTokenizer(br.readLine());
            int nodeAIndex = Integer.parseInt(st.nextToken())-1;
            int nodeBIndex = Integer.parseInt(st.nextToken())-1;
            int[] visited = new int[nodeCount];
            Arrays.fill(visited, -1);
            Queue<Node> nodeQueue = new LinkedList<>();
            visited[nodeAIndex] = 0;
            nodeQueue.add(nodes[nodeAIndex]);
            while(!nodeQueue.isEmpty()){
                Node node = nodeQueue.poll();
                for(Connection connection : node.connections){
                    if(visited[connection.node.index] == -1){
                        visited[connection.node.index] = visited[node.index] + connection.val;
                        nodeQueue.add(connection.node);
                    }
                }
                if(visited[nodeBIndex]!=-1){
                    System.out.println(visited[nodeBIndex]);
                    break;
                }
            }
        }
    }

    private static class Node {
        int index;
        ArrayList<Connection> connections = new ArrayList<>();
        public Node(int index){ this.index = index; }
    }
    private static class Connection {
        Node node;
        int val;
        public Connection(Node node, int val) {
            this.node=node;
            this.val = val;
        }
    }
}
