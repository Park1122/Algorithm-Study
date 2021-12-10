package codingstudy202107.w23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ3584 {
    public static void main(String[] args) throws IOException {
        BOJ3584 main = new BOJ3584();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int testCaseCount = Integer.parseInt(reader.readLine());
        StringTokenizer st;
        StringBuilder builder = new StringBuilder();
        for (int testCase = 0; testCase < testCaseCount; testCase++) {

            int nodeCount = Integer.parseInt(reader.readLine());
            Node[] arr = new Node[nodeCount + 1];
            for (int line = 0; line < nodeCount - 1; line++) {

                st = new StringTokenizer(reader.readLine());
                int parentVal = Integer.parseInt(st.nextToken());
                int childVal = Integer.parseInt(st.nextToken());

                if (arr[parentVal] == null) arr[parentVal] = new Node(parentVal);
                if (arr[childVal] == null) arr[childVal] = new Node(childVal);

                arr[parentVal].children.add(arr[childVal]);
                arr[childVal].parent = arr[parentVal];
            }


            st = new StringTokenizer(reader.readLine());
            int nodeAVal = Integer.parseInt(st.nextToken());
            int nodeBVal = Integer.parseInt(st.nextToken());

            builder.append(main.find(arr, nodeAVal, nodeBVal)).append('\n');

        }
        builder.setLength(builder.length()-1);
        System.out.print(builder);
    }

    private int find(Node[] arr, int nodeAVal, int nodeBVal) {
        Node nodeA = arr[nodeAVal];
        Node nodeB = arr[nodeBVal];

        Vector<Node> ancestors = new Vector<>();
        while(nodeA.parent!=null){
            System.out.println("A ans "+nodeA.val);
            ancestors.add(nodeA);
            nodeA=nodeA.parent;
        }
        System.out.println("A ans "+nodeA.val);
        ancestors.add(nodeA);


        Stack<Node> stack= new Stack<>();
        while(nodeB.parent!=null){
            System.out.println("B ans "+nodeB.val);
            stack.push(nodeB);
            nodeB=nodeB.parent;
        }
        System.out.println("B ans "+nodeB.val);
        stack.push(nodeB);

        Node prev=null;
        while(!stack.isEmpty()){
            Node ans= stack.pop();
            if(ancestors.contains(ans)){
                prev=ans;
            }else break;
        }
        return prev.val;
    }

    public static class Node {
        int val;
        Node parent;
        Vector<Node> children = new Vector<>(1);

        public Node(int val) {
            this.val = val;
        }
    }
}
