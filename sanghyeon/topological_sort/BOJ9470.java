package year_2021.month_12.day_19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9470 {

    public static void main(String[] args) throws Exception {
        // Read Value
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcaseCount = Integer.parseInt(br.readLine());
        for(int t=0; t<testcaseCount; t++){
            // Read Value
            StringTokenizer st = new StringTokenizer(br.readLine());
            int testcaseNum = Integer.parseInt(st.nextToken());
            int nodeCount = Integer.parseInt(st.nextToken());
            int connectionCount = Integer.parseInt(st.nextToken());

            // Create Node
            Node[] nodes = new Node[nodeCount];
            for(int i=0; i<nodeCount; i++) nodes[i] = new Node();

            // Create Graph
            for(int i=0; i<connectionCount; i++) {
                st = new StringTokenizer(br.readLine());
                int beforeId = Integer.parseInt(st.nextToken())-1;
                int afterId = Integer.parseInt(st.nextToken())-1;
                nodes[beforeId].after.add(nodes[afterId]);
                nodes[afterId].before.add(nodes[beforeId]);
            }

            // Sort
            Queue<Node> queue = new LinkedList<>();
            for(Node node : nodes){
                if(node.before.size()==0) {
                    node.val=1;
                    queue.add(node);
                }
            }
            while(!queue.isEmpty()){
                Node node = queue.poll();
                for(Node after : node.after){
                    after.before.remove(node);
                    if(after.max < node.val){
                        after.max=node.val;
                        after.maxCount=1;
                    }else if(after.max == node.val){
                        after.maxCount++;
                    }
                    if(after.before.size()==0) {
                        if(after.maxCount>=2) after.val = after.max+1;
                        else after.val = after.max;
                        queue.add(after);
                    }
                }
            }

            // Print
            System.out.println(testcaseNum+" "+nodes[nodeCount-1].val);
        }
    }

    private static class Node {
        int val, max, maxCount;
        ArrayList<Node> before = new ArrayList<>();
        ArrayList<Node> after = new ArrayList<>();
    }
}
