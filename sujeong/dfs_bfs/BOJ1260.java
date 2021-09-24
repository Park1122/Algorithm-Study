package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1260 {
    // https://www.acmicpc.net/problem/1260

    // 소요시간 >>
    // 약 2시간 (00:34 ~ 2:36)

    // 아이디어 >>
    // * DFS는 자기 호출을 주로 사용하고, BFS는 Queue를 주로 이용한다.
    // * value를 가진 Node를 이용해 해결.
    // * 자세한 설명은 코드와 함께.

    // 에러 로그 >>
    // X

    // Attribute
    private static BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
    // Attribute - Basic Info
    private static int n,m,v;
    private static Node[] pocket;
    // Attribute - for use
    private static Queue<Node> bfsPocket = new LinkedList<Node>();
    private static String dfsAns="", bfsAns="";


    // Main
    public static void main(String[] args) throws IOException{
        // Input & Initialize
        initialize();

        // Logic - DFS
        dfsFunc(v);

        // Logic - BFS
        bfsFunc(v);

        // Output
        System.out.println(dfsAns);
        System.out.println(bfsAns);

    }

    // Input & Initialize
    public static void initialize() throws IOException{
        // Set Basic Info
        String[] strInfo = reader.readLine().split(" ");
        n=Integer.parseInt(strInfo[0]); // 노드의 수
        m=Integer.parseInt(strInfo[1]); // 간선의 수
        v=Integer.parseInt(strInfo[2]); // 탐색 시작 값

        // Create Nodes
        pocket =new Node[n+1];
        for(int i=1;i<=n;i++){
            pocket[i]=new Node(i);
        }

        // Connect Nodes
        for(int i=0;i<m;i++){
            String[] strline = reader.readLine().split(" ");
            pocket[Integer.parseInt(strline[0])].addFriend(pocket[Integer.parseInt(strline[1])]);
            pocket[Integer.parseInt(strline[1])].addFriend(pocket[Integer.parseInt(strline[0])]);
        }

        // Sorting Friends(Children)
        for(int i=1;i<=n;i++){
            pocket[i].friends.sort(Comparator.naturalOrder());
        }

    }

    // Logic - DFS
    private static void dfsFunc(int vertex){
        // visited를 true로 바꾸고, 출력 스트링에 값을 넣는다.
        pocket[vertex].setDfsVisited(true);
        dfsAns+=(vertex+" ");

        // child 노드를 계속 가져와 파고들고,
        // 다 파서 더이상 노드가 없으면 상위 노드의 다른 child 노드로 이동하여 반복
        ArrayList<Node> nodes = pocket[vertex].friends;
        for(int i=0;i<nodes.size();i++){
            if(!nodes.get(i).getDfsVisited()) {
                dfsFunc(nodes.get(i).value);
            }
        }
    }

    // Logic - BFS
    private static void bfsFunc(int vertex){
        // handle vertex
        pocket[vertex].setBfsVisited(true);
        bfsPocket.add(pocket[vertex]);
        bfsAns+=(vertex+" ");

        // handle Friends(children)
        while(!bfsPocket.isEmpty()){
            // 큐에 담겨있는 순으로 노드를 가져옴.
            Node tmp = bfsPocket.poll();

            // 첫 노드의 값들을 출력 스트링에 저장하고 visited를 true로 변경한 뒤, 이들을 큐에 넣음
            ArrayList<Node> nodes = tmp.friends;
            for(int i=0;i<nodes.size();i++){
                if(!nodes.get(i).getBfsVisited()){
                    bfsAns+=(nodes.get(i).value+" ");
                    nodes.get(i).setBfsVisited(true);
                    bfsPocket.add(nodes.get(i));
                }
            }
        }

    }

    // Inner Class
    private static class Node implements Comparable<Node>{

        // Attribute
        int value;
        boolean dfsVisited=false;
        boolean bfsVisited=false;
        ArrayList<Node> friends = new ArrayList<Node>();

        // Constructor
        public Node(int value){
            this.value = value;
        }

        // Method
        public void addFriend(Node other){
            this.friends.add(other);
        }

        // Method - Override
        @Override
        public int compareTo(Node other){
            return Integer.compare(this.value, other.value);
        }

        // get & set
        public int getValue(){
            return value;
        }
        public boolean getDfsVisited(){ return dfsVisited; }
        public boolean getBfsVisited(){ return bfsVisited; }

        public void setValue(int value){ this.value = value;}
        public void setDfsVisited(boolean dfsVisited){
            this.dfsVisited = dfsVisited;
        }
        public void setBfsVisited(boolean bfsVisited){ this.bfsVisited = bfsVisited;}

    }
}
