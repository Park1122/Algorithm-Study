package codingstudy202107.w12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * 회차 : 코딩 스터디 12주차
 * 문제 출처 : https://www.acmicpc.net/problem/15681
 * 이름 : 트리와 쿼리
 * 사용 알고리즘 : 다이나믹 프로그래밍
 * 트리를 하나 주고, 루트를 알려주고, 거기서 qi를 루트로 하는 서브트리의 개수를 구하는 문제.
 * 처음에 루트를 qi로 한다길래 읭 그럼 서브 트리의 개수는 늘 같은거 아닌가?? 했는데
 * 진짜 루트는 처음에 정해진 그거고, 서브트리의 루트를 그거로 하라는 뜻이었다.
 * (트리 전체 루트를 바꾸는 코드도 있다 그래서..나중에 재활용할 날이 올거 같아서 안 지웠다.)
 * 의사코드가 잘 되어 있어서 그냥 그거 보고 하면 되는 거였다.
 */
public class BOJ15681 {

    public static void main(String[] args) throws IOException {
        BOJ15681 main = new BOJ15681();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());


        Node[] nodes = new Node[n+1];
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(reader.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            if (nodes[first] == null) nodes[first] = new Node(first);
            if (nodes[second] == null) nodes[second] = new Node(second);
            nodes[first].addNode(nodes[second]);
            nodes[second].addNode(nodes[first]);
        }


        StringBuilder sb = new StringBuilder();
        nodes[r].makeTree(null);
        int[] memoi = new int[n+1];

        for(int i=0; i<q; i++){
            if(i>0)sb.append('\n');
            int query= Integer.parseInt(reader.readLine());
            sb.append(nodes[query].countSubtreeNode(memoi));
        }
        System.out.print(sb.toString());
    }


    private static class Node{
        private final int val;
        private Vector<Node> nodes;

        private Node parent;
        private Node[] childs;

        public Node(int val){
            this.val=val;
            this.nodes= new Vector<>();
        }

        public void makeTree(Node parent){
            this.parent=parent;
            this.childs= new Node[nodes.size()];
            int size=0;
            for(Node node : nodes){
                if((node!=null&&parent==null)||(node!=null&&node.getVal()!=parent.getVal())) {
                    this.childs[size++] = node;
                    node.makeTree(this);
                }
            }
        }

        public int countSubtreeNode(int[] memoi){
            if(memoi[this.val]!=0) return memoi[this.val];
            int count=1;
            for(Node node: childs){
                if(node!=null)
                count+=node.countSubtreeNode(memoi);
            }
            memoi[this.val]=count;
            return count;
        }

        private int getVal() {
            return this.val;
        }


        public void addNode(Node node) {
            this.nodes.add(node);
        }
    }



}
