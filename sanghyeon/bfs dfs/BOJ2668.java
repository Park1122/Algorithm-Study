package year_2021.month_11.day_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ2668 {

    private static Node[] arr;
    private static int N;

    public static void main(String[] args) throws IOException {
        readInput();

        ArrayList<Integer> answerArr = new ArrayList<>();
        for(int i=0; i<N; i++){
            Node n = arr[i];
            if(!n.checked){
                n.checked = true;
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(n.index);
                while(true){
                    n = n.next;
                    if(!n.checked){
                        n.checked = true;
                        temp.add(n.index);
                    }else{
                        while(!temp.isEmpty() && temp.get(0)!=n.index) temp.remove(0);
                        break;
                    }
                }
                answerArr.addAll(temp);
            }
        }

        answerArr.sort(Integer::compareTo);
        System.out.println(answerArr.size());
        for(int i : answerArr) System.out.println(i+1);
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new Node[N];

        for(int i=0; i<N; i++) arr[i] = new Node(i);
        for(int i=0; i<N; i++) arr[i].next = arr[Integer.parseInt(br.readLine())-1];
    }

    private static class Node {
        int index;
        Node next;
        boolean checked = false;
        public Node(int index){
            this.index = index;
        }
    }
}
