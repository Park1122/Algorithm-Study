package year_2021.month_11.day_27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1991 {

    static String result = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int nodeCount = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[nodeCount];
        for(int i=0; i<nodeCount; i++) nodes[i] = new Node(i);

        StringTokenizer st;
        for(int i=0; i<nodeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int index = st.nextToken().charAt(0)-'A';
            String left = st.nextToken();
            if(!left.equals(".")) nodes[index].left = nodes[left.charAt(0)-'A'];
            String right = st.nextToken();
            if(!right.equals(".")) nodes[index].right = nodes[right.charAt(0)-'A'];
        }

        result = "";
        nodes[0].front();
        System.out.println(result);

        result = "";
        nodes[0].mid();
        System.out.println(result);

        result = "";
        nodes[0].back();
        System.out.println(result);
    }

    private static class Node{
        int index;
        char c;
        Node left, right;

        public Node(int index){
            this.index=index;
            this.c = (char) (index+'A');
        }

        public void front(){
            result += c;
            if(left!=null) left.front();
            if(right!=null) right.front();
        }
        public void mid(){
            if(left!=null) left.mid();
            result += c;
            if(right!=null) right.mid();
        }
        public void back(){
            if(left!=null) left.back();
            if(right!=null) right.back();
            result += c;
        }
    }
}
