package codingstudy202107.w23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ9489 {
    public static void main(String[] args) throws IOException {
        BOJ9489 main = new BOJ9489();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        Queue<Node> queue;
        int nodeCount,targetVal;
        Node targetNode=null,parent,prevNode;
        while (true) {
            queue=new ArrayDeque<>();
            st = new StringTokenizer(reader.readLine());
            nodeCount = Integer.parseInt(st.nextToken());
            targetVal = Integer.parseInt(st.nextToken());
            if (nodeCount == 0 && targetVal == 0) break;
            parent=null;
            prevNode=null;

            st = new StringTokenizer(reader.readLine());
            for(int i=0;i< nodeCount;i++) {
                int nodeVal = Integer.parseInt(st.nextToken());
                Node node = new Node(nodeVal);
                if(nodeVal==targetVal)targetNode=node;
                queue.add(node);
                if (!queue.isEmpty()) {//앞선 노드가 있다.앞선 노드의 val만 얻었다.
                    if (prevNode!=null&&nodeVal > prevNode.val + 1) {//앞선 노드의 자식이나 조카일때.
                        parent = queue.poll();
                    }
                    node.parent = parent;
                    if(parent!=null)parent.addChild(node);
                }
                prevNode=node;
            }
            sb.append(main.countCousins(targetNode)).append('\n');
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

    private int countCousins(Node node) {
        int cousinCount = 0;
        if(node.parent==null)return 0;
        Node parent = node.parent;
        if(parent.parent==null)return 0;
        Node grandParent = parent.parent;
        Node aunt= grandParent.child;
        while (aunt!=null) {
            if (aunt.equals(parent)) {
                aunt=aunt.sister;
                continue;
            }
            cousinCount += aunt.childCount;
            aunt=aunt.sister;
        }
        return cousinCount;
    }


    public static class Node {
        Node parent,child,sister;
        int val,childCount;
        public Node(int val) {
            this.val = val;
        }
        public void addChild(Node child){
            childCount++;
            child.sister=this.child;
            this.child=child;
        }
    }
}
