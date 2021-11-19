package year_2021.month_11.day_17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ13023 {

    static int depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Node[] nodes = new Node[N];
        for(int i=0; i<N; i++) nodes[i] = new Node();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int nodeAId = Integer.parseInt(st.nextToken());
            int nodeBId = Integer.parseInt(st.nextToken());
            nodes[nodeAId].friends.add(nodes[nodeBId]);
            nodes[nodeBId].friends.add(nodes[nodeAId]);
        }

        for(int i=0; i<N; i++){
            nodes[i].checked=true;
            dfs(nodes[i]);
            nodes[i].checked=false;
            if(depth==4) break;
        }

        System.out.println(depth==4? 1:0);
    }

    private static void dfs(Node node) {
        for(Node friend : node.friends){
            if(!friend.checked) {
                friend.checked = true;
                depth++;

                if (depth == 4) return;
                dfs(friend);
                if (depth == 4) return;

                friend.checked = false;
                depth--;
            }
        }
    }

    private static class Node {
        boolean checked;
        ArrayList<Node> friends = new ArrayList<>();
    }
}

