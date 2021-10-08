package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ11725 {
    // https://www.acmicpc.net/problem/11725

    // 소요시간 >>
    // 15:22 ~ 16:02 (40분)

    // 아이디어 >>
    // - Input & Initialize -
    // Node 클래스를 만들어 connect를 통해 연결된 노드를 갖도록 하여 인풋과정을 진행
    // - Logic -
    // 로직과정(makeTree)에서 루트인 1부터 child를 탐색하며 child(탐색을 당한 노드)의 parent에 parent(탐색을 시킨 노드)를 넣도록 함.
    // 그리고 connects에서 parent가 된 노드를 제외시켜 connects의 역할이 "연결된 노드"에서 "자식 노드"로 변경시킴.
    // - Output -
    // 출력 과정에서 2~n까지 nodeArr의 parent값을 출력하도록 함.

    // 에러로그 >>
    // X

    // Attribute
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Node[] nodeArr;

    // Variable
    private static int n;

    // Main
    public static void main(String[] args) throws IOException{
        // Input & Initialize
        n = Integer.parseInt(reader.readLine());
        nodeArr = new Node[n+1];
        for (int i=0 ; i<n-1;i++){
            // read value
            String[] line = reader.readLine().split(" ");
            int one = Integer.parseInt(line[0]);
            int other =  Integer.parseInt(line[1]);

            // if node is not exist
            if(nodeArr[one]==null)nodeArr[one] = new Node(one);
            if(nodeArr[other]==null)nodeArr[other] = new Node(other);

            // make connect between one and other
            nodeArr[one].addConnect(nodeArr[other]);
            nodeArr[other].addConnect(nodeArr[one]);
        }

        // Logic
        makeTree(nodeArr[1]);

        // Output
        for(int i=2;i<=n;i++){
            System.out.println(nodeArr[i].getParent().getValue());
        }
    }

    // Method
    private static void makeTree(Node parent){
        for(Node node : parent.getConnects()){
            node.parent = parent;
            node.deleteConnect(parent);
            makeTree(node);
        }
    }


    private static class Node{
        // Attribute
        private int value;
        private Node parent = null;
        private ArrayList<Node> connects;

        // Constructor
        public Node(int val){
            this.value = val;
            this.connects = new ArrayList<Node>();
        }

        // Method
        public void addConnect(Node child){this.connects.add(child);}
        public void deleteConnect(Node child){this.connects.remove(child);}
        public String toString(){
            String retVal = "";
            for(Node node:connects) retVal+=node.value+" ";
            return retVal;
        }

        // Get & Set
        public void setParent(Node parent){this.parent = parent;}
        public Node getParent(){return this.parent;}
        public int getValue(){
            return value;
        }
        public void setValue(int value){
            this.value = value;
        }
        public ArrayList<Node> getConnects(){
            return connects;
        }
        public void setConnects(ArrayList<Node> connects){
            this.connects = connects;
        }
    }
}

