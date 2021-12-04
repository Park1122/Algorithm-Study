package codingstudy202107.w22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : w22
 * 문제 출처 : <a href =https://www.acmicpc.net/problem/5639>링크 </a>
 * 이름 : 이진 검색 트리
 * 사용 알고리즘 : 스택
 * 설명
 * ==========================================================================
 */
public class BOJ5639 {

    public static void main(String[] args) {
        BOJ5639 main = new BOJ5639();
        Scanner scanner = new Scanner(System.in);

        Map<Integer, Node> map = new HashMap<>();
        Stack<Node> stack = new Stack<>();

        int ord = 0;
        while (scanner.hasNext()) {
            String value = scanner.nextLine();
            if (value.equals("&")) break;
            Node node = new Node(Integer.parseInt(value));
            map.put(ord++, node);

            if (stack.isEmpty()) {
                stack.add(node);
                continue;
            }
            Node prev = stack.pop();
            if (prev.val > node.val) {
               main.appendChild(stack,prev,node);
            } else {
                while (prev.parent != null && prev.val<node.val&&!stack.isEmpty()) {
                    prev = stack.pop();
                }
                main.appendChild(stack,prev,node);
            }
        }

        StringBuilder sb = new StringBuilder();
        main.postOrder(map.get(0), sb);
        sb.setLength(sb.length() - 1);
        System.out.print(sb);
    }

    private void appendChild(Stack<Node> stack,Node parent, Node child){
        if(parent.val>child.val){
            if(parent.smaller==null){
                parent.smaller=child;
                child.parent=parent;
                stack.push(parent);
                stack.push(child);
            }else{
                stack.push(parent);
                this.appendChild(stack,parent.smaller,child);
            }
        }else{
            if(parent.bigger==null){
                parent.bigger=child;
                child.parent=parent;
                stack.push(parent);
                stack.push(child);
            }else{
                stack.push(parent);
                this.appendChild(stack,parent.bigger,child);
            }
        }
    }

    private void postOrder(Node node, StringBuilder sb) {
        if (node == null) return;
        this.postOrder(node.smaller, sb);
        this.postOrder(node.bigger, sb);
        sb.append(node.val).append('\n');
    }


    public static class Node {
        int val;
        Node bigger, smaller, parent;

        public Node(int val) {
            this.val = val;
        }
    }


}
