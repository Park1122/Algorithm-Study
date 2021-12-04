package sujeong.tree;

import java.io.*;
import java.util.*;

public class BOJ5639 {
    // https://www.acmicpc.net/problem/5639

    // 소요시간 >>
    // 5시간.... (전위 순회 결과를 트리로 만드는 과정...이 어려웠다.. 내 머리가 안돌아간걸지도 모르지만..)

    // 아이디어 >>
    // 전위순회를 트리로만 만들면 그냥 끝나는 문제였다...
    // 방식은 1) 입력을 받아 트리로 만든다. 2) postorder함수로 후위 순회한 결과를 출력한다. 가 전부이다.
    // 자세한 내용은 블로그를 참고하면 좋다.. 
    // (https://codingjerk-diary.tistory.com/entry/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98%ED%92%80%EC%9D%B4%EB%B0%B1%EC%A4%80-5639-%EC%9D%B4%EC%A7%84-%EA%B2%80%EC%83%89-%ED%8A%B8%EB%A6%AC-JAVA)

    // 에러로그 >>
    // X

    public static void main(String[] args) throws IOException{
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        Node rootNode = new Node(Integer.parseInt(br.readLine()));
        while((input = br.readLine())!=null)
            rootNode.makeChild(Integer.parseInt(input));
            
        // Logic & Output
        postorder(rootNode);
    }
    
    // Method - 좌-우-본
    private static void postorder(Node node){
        // 좌우의 자식이 있다면 탐색해나가고(dfs방식) 없다면 좌->우->본 순서로 출력을 진행함.
        if(node.left != null) postorder(node.left);
        if(node.right != null) postorder(node.right);
        System.out.print(node);
    }
    
    // Inner Class
    private static class Node{
        // Variable
        Node left,right;
        int val;
        
        // Constructor
        public Node(int val){
            this.val = val;
        }
        
        // Method - insert child Node
        public void makeChild(int cNum){
            if(cNum<val){ //left child
            	// if null -> make cNum node to left/ if not null -> make cNum to left's child
                if(this.left!=null) this.left.makeChild(cNum);
                else this.left = new Node(cNum);
            }else{ // right child
            	// if null -> make cNum node to right/ if not null -> make cNum to left's child
                if(this.right!=null) this.right.makeChild(cNum);
                else this.right = new Node(cNum);
            }
        }
        // Method - for output
        public String toString(){return val+"\n";}
    }
}
