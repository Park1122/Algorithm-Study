package sujeong.shortest_path;

import java.util.*;
import java.io.*;

public class BOJ1753 {
    // https://www.acmicpc.net/problem/1753

    // 소요시간 >>
    // 2시간 (하나를 풀고나니까(1916) 그 다음에 푸는건 조금 더 쉽게 풀 수 있었다.)

    // 아이디어 >>
    // 0) s->f로의 비용에 맞춰 ArrayList배열에 연결정보를 넣는다.
    // 1) 배열을 만들고, 시작점 위치를 넣어준다.
    // 2) 방문여부와 이어지는 노드가 있는지 체크하고 통과가되면 새 비용과 원래비용을 비교한다.
    // 3) 새 비용이 원래비용보다 작다면 새비용을 dp와 q에 값을 넣는다.

    // 에러로그 >>
    // 틀렸습니다 - visited의 위치를 for문안에서 q에 넣을때 미리 처리하면 시간이 절약될거라 생각했는데
    // visited를 안에서 처리한 것 때문에 q에는 처음의 값만 들어가고 그 외의 값은 못들어가서 틀렸었다.
    // visited를 for문 밖으로 빼내어 q에 들어왔을때 처리(priorityqueue기 때문에 같은 값중에 작은 값부터 들어와서 이 경우가 최단거리가 됨)하여 해결
    public static void main(String[] args) throws IOException{
        // Input
        MyIO io = new MyIO();
        io.readLine();
        int n = io.nextInt(); // 노드의 수
        int e = io.nextInt(); // 간선의 수
        int sp = io.readIntLine(); // 시작점

        ArrayList<int[]>[] edgeArr = new ArrayList[n+1];
        for(int i=0;i<e;i++){
            io.readLine();
            int s = io.nextInt(); // 첫 위치
            int f = io.nextInt(); // 끝 위치
            int cost = io.nextInt(); // 이동 비용
            // s->f로 갈때 비용이 cost인걸 맞춰 edgeArr에 값을 넣어줌.
            if(edgeArr[s]==null) edgeArr[s]=new ArrayList<>();
            edgeArr[s].add(new int[]{f,cost});
        }

        // Logic
        // initialize arrays
        Queue<int[]> q = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1],o2[1])); // 탐색할때 사용할 큐
        int[] dp = new int[n+1]; // 각 노드까지의 최단거리를 저장할 배열
        Arrays.fill(dp, Integer.MAX_VALUE); // 초기값 셋팅
        boolean[] visited = new boolean[n+1]; // 방문여부 체크
        // set first value
        q.add(new int[]{sp,0});
        dp[sp] = 0;
        // start searching
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int pos = cur[0]; 
            int cost = cur[1];
            // 방문여부 체크
            if(visited[pos]) continue;
            visited[pos] = true;
            // 이어지는 노드가 없다면 넘어가도록 체크
            if(edgeArr[pos]==null) continue;
            for(int[] next : edgeArr[pos]){
                int nextPos = next[0];
                int nextCost = next[1];
                // 새 비용과 원래 있던 최단비용을 비교하여 새 비용이 최단이면 이를 큐와 dp에 넣는다.
                if(cost+nextCost < dp[nextPos]){
                    q.add(new int[]{nextPos, cost+nextCost});
                    dp[nextPos] = cost+nextCost;
                }
            }
        }

        // Output
        for(int i=1;i<=n;i++) System.out.println(dp[i]==Integer.MAX_VALUE?"INF":dp[i]);
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
