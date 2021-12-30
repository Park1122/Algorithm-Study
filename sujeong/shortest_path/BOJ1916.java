package sujeong.shortest_path;
import java.util.*;
import java.io.*;

public class BOJ1916 {
    // https://www.acmicpc.net/problem/1916

    // 소요시간 >>
    // 5시간 ... (분명 다왔는데 왜 안되나 삽질을 꽤 오래함..)

    // 아이디어 >>
    // 이 문제는 노드에는 특별한 조건이나 문제가 없고 간선의 계산이 중점인 문제이기 때문에 
    // 노드 마다의 무언갈 만들기보단(노드를 담은 배열) 간선의 정보를 담는 노드와노드간의 값을 담은 배열이 필요하다 
    // shotestPath를 구할때 대표적으로 사용하는 알고리즘인 dijkstra 알고리즘을 사용하려함.
    // 문제풀이 방법은 다음과 같다.
    // 0) edgeArr에 시작점에서 갈 수 있는 다음위치와 그 비용을 Node로 묶어 담아둔다.
    // 1) 방문여부를 체크하며 sp(첫 시작위치)에서부터 큐를 이용해 탐색을 시작한다.
    // 2) 탐색을 할때 다음위치에 미리 저장된 비용이 새롭게 이동했을때의 비용보다 큰지 확인하여 크다면 새 비용으로 갱신 및 큐에 추가를 반복한다.
    // 3) 탐색을 마친 뒤 minCostArr(최소비용을 담아내는 배열, dp와 같음)의 ep(마지막 종료지점)을 출력한다.

    // 에러로그 >>
    // 메모리초과 - Queue에서 PriorityQueue로 변경
    // 시간초과 - PriorityQueue니까 visted를 이용하면 속도를 개선할수 있으면서도 중복적인일을 덜하도록 만들었다.

    public static void main(String[] args) throws IOException{
        // Input
        MyIO io = new MyIO();
        int n=io.readIntLine(); // 정류장의 총 개수
        int m=io.readIntLine(); // 버스노선의 총 개수

        List<Node>[] edgeArr = new ArrayList[n+1]; // 간선 연결 정보
        for(int i=0;i<m;i++){
            io.readLine();
            int a = io.nextInt(); // 출발지점(sNode)
            int b = io.nextInt(); // 도착지점(eNode)
            int c = io.nextInt(); // 버스비(cost)

            if(edgeArr[a]==null) edgeArr[a] = new ArrayList<>(); 
            edgeArr[a].add(new Node(b,c)); // 0처리하기
        }

        io.readLine();
        int sp = io.nextInt(); // 시작 위치(start position)
        int ep = io.nextInt(); // 종료 위치(end position)

        // Logic
        Queue<Node> q = new PriorityQueue<>(); // 위치와 이동하는데 걸린 비용을 저장
        q.add(new Node(sp,0)); // 시작점과 시작비용(0)을 담음

        boolean[] visited = new boolean[n+1]; // 방문여부를 판단하기 위함.

        int[] minCostArr = new int[n+1]; // [시작점][도착점] 의 최소비용을 담음
        Arrays.fill(minCostArr,Integer.MAX_VALUE); // 가장큰값으로 초기화
        minCostArr[sp] = 0; // 시작점의 비용은 0으로 셋팅

        while(!q.isEmpty()){
            Node cur = q.poll();
            // 방문여부 체크
            if(visited[cur.pos]) continue;
            visited[cur.pos] = true;
            // 다음 노선이 없는 경우 체크
            if(edgeArr[cur.pos]==null) continue;
            for(Node next : edgeArr[cur.pos]){
                // 다음정류장으로 갔을때의 총 비용 계산
                int nextCost = cur.cost + next.cost;
                // 다음위치의 최소비용과 nextCost를 비교
                if(minCostArr[next.pos]>nextCost){
                    // for next (nextCost로 다음위치의 최소비용 변경 및 큐에 추가)
                    minCostArr[next.pos] = nextCost;
                    q.add(new Node(next.pos,nextCost));
                }
            }
        }
        // Output
        System.out.println(minCostArr[ep]);
    }
    // Inner Class - save pos and cost and allows comparisons to be made.
    private static class Node implements Comparable<Node>{
        // Variable
        int pos;
        int cost;
        // Constructor
        public Node(int next, int cost){
            this.pos = next;
            this.cost = cost;
        }
        // Method
        @Override
        public int compareTo(Node o) {return this.cost - o.cost;}
    }

    // Inner Class - Read & Write values
    private static class MyIO{
        // Variable
        BufferedReader br;
        StringTokenizer st;
        // Constuctor
        public MyIO(){br = new BufferedReader(new InputStreamReader(System.in));} 
        // Method - for input
        public int readIntLine() throws IOException { readLine(); return nextInt();}
        public void readLine() throws IOException { st = new StringTokenizer(br.readLine());}
        public int nextInt(){ return Integer.parseInt(st.nextToken());}
    }
}
