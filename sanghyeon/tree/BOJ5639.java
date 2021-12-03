package year_2021.month_11.day_28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5639 {

    static String result = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node(Integer.parseInt(br.readLine()));
        while (true){
            String line = br.readLine();
            if(line==null || line.length()==0) break;
            int value = Integer.parseInt(line);

            Node newNode = new Node(value);
            Node up = root;
            while(true){
                if(up.value<value){
                    if(up.right==null){ up.right = newNode;break; }
                    else up = up.right;
                }else {
                    if(up.left==null){ up.left = newNode;break; }
                    else up = up.left;
                }
            }
        }
        root.back();
        System.out.println(result);
    }

    private static class Node{
        int value;
        Node left, right;

        public Node(int value){
            this.value=value;
        }

        public void back(){
            if(left!=null) left.back();
            if(right!=null) right.back();
            result += value+"\n";
        }
    }
}
