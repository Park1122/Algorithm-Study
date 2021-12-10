package year_2021.month_12.day_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ3584 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcaseCount = Integer.parseInt(br.readLine());

        for(int i=0; i<testcaseCount; i++){
            int nodeCount = Integer.parseInt(br.readLine());
            Node[] nodes = new Node[nodeCount];
            for(int j=0; j<nodeCount; j++) nodes[j] = new Node(j);
            for(int j=0; j<nodeCount-1; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                Node parent = nodes[Integer.parseInt(st.nextToken())-1];
                Node child = nodes[Integer.parseInt(st.nextToken())-1];
                child.parent = parent;
            }
            StringTokenizer st = new StringTokenizer(br.readLine());
            int nodeAIndex = Integer.parseInt(st.nextToken())-1;
            int nodeBIndex = Integer.parseInt(st.nextToken())-1;
            ArrayList<Integer> parents = new ArrayList<>();
            Node node = nodes[nodeAIndex];
            while(true){
                parents.add(node.index);
                if(node.parent!=null) node = node.parent;
                else break;
            }
            node = nodes[nodeBIndex];
            while(true){
                if(parents.contains(node.index)){
                    System.out.println(node.index+1);
                    break;
                }else{
                    node = node.parent;
                }
            }
        }
    }

    private static class Node {
        int index;
        Node parent;
        public Node(int index){
            this.index = index;
        }
    }
}
