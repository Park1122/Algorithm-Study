package sujeong.shortest_path;

import java.util.*;
import java.io.*;

public class BOJ18352 {
    // https://www.acmicpc.net/problem/18352

    // 소요시간 >>
    // 30분(다익스트라 알고리즘의 기본 흐름을 이해하니까 빠르게 풀수있던 문제였다)

    // 아이디어 >> 
    // 최단거리를 계산할 때 많이 사용하는 다익스트라 알고리즘의 틀을 사용하면 되니, 그 흐름을 말해보려한다.
    // 0) initialize arrays 
    //    - 간선을 연결짓는 정보는 ArrayList[]를 사용해 저장하고, 탐색을 위한 큐는 PriorityQueue를 사용한다.
    //    - 그리고 최단거리를 담는 dp 배열은 Integer.MAX_VALUE로 채워두고 추가로 방문체크를 위한 visited까지 만들어둔다.
    // 1) initialize first value
    //    - 큐에 첫 위치를 시작거리인 0과 함께 담고
    //    - dp도 첫 위치를 0으로 정해 초기값을 셋팅한다.
    // 2) check Condition - 큐를 통해 값을 전달하며 방문여부와 다음위치가 있는지 여부를 체크해주고
    // 3) check possibility for the next - 현재 위치에서 다음위치까지의 거리를 계산한 값(cnt+1)이
    //    다음위치에 저장된 최단거리(dp[next])보다 작다면 이를 큐와 dp로 갱신해주길 반복한다.
    // 4) print output - 모든 탐색이 끝이 나면 dp에 저장된 값이 k(원하는 최단 거리)인 인덱스를 StringBuilder에 담고
    //    이 크기가 0이라면 -1 아니라면 그 인덱스들을 오름차순으로 출력한다.

    // 에러로그 >>
    // X 

    public static void main(String[] args) throws IOException {
        // Input
        MyIO io = new MyIO();

        io.readLine();
        int n = io.nextInt(); // 도시의 개수
        int m = io.nextInt(); // 도로의 개수
        int k = io.nextInt(); // 원하는 최단 거리
        int x = io.nextInt(); // 출발 도시 번호
        
        ArrayList<Integer>[] orgArr = new ArrayList[n+1]; // 간선의 연결정보를 담을 배열
        for(int i=0;i<m;i++){
            io.readLine();
            int a = io.nextInt(); 
            int b = io.nextInt(); 
            // a->b
            if(orgArr[a]==null) orgArr[a]=new ArrayList<>();
            orgArr[a].add(b);
        }

        // Logic
        // initialize arrays
        Queue<int[]> q = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1])); // 탐색을 위한 큐
        int[] dp = new int[n+1]; // 최단 거리를 담아낼 dp 배열
        Arrays.fill(dp, Integer.MAX_VALUE); // dp의 초기값을 int의 최대값으로 채운다.
        boolean[] visited = new boolean[n+1]; // 방문여부를 체크할 배열
        // initialize first value
        q.add(new int[]{x,0});
        dp[x]=0;
        // start searching
        while(!q.isEmpty()){
            int[] cur =  q.poll();
            int pos = cur[0]; // 현재 위치
            int cnt = cur[1]; // 현재 위치까지의 거리 크기
            // check Condition - 방문여부 체크
            if(visited[pos]) continue;
            visited[pos] = true;
            // check possibility for the next - 다음 도시가 있는지 체크  
            if(orgArr[pos]==null) continue;
            for(int next : orgArr[pos]){
                // next의 현재까지의 최소값보다 새롭게 계산한 값이 더 작다면
                if(dp[next]>cnt+1){
                    // 큐와 dp에 갱신
                    q.add(new int[]{next,cnt+1});
                    dp[next] = cnt+1;
                }
            }
        }
        // Output - dp값이 k인게 없으면 -1아니면 인덱스를 출력
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++)if(dp[i]==k) sb.append(i+"\n");
        System.out.println(sb.length()==0?-1:sb.toString()); 
    }

    // Inner Class - Read & Write values
    private static class MyIO{
        // Variable
        BufferedReader br;
        StringTokenizer st;
        // Constuctor
        public MyIO(){br = new BufferedReader(new InputStreamReader(System.in));} 
        // Method - for input
        public void readLine() throws IOException { st = new StringTokenizer(br.readLine());}
        public int nextInt(){ return Integer.parseInt(st.nextToken());}
    }
}
