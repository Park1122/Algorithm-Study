package sujeong.topological_sort;

import java.util.*;
import java.io.*;

public class BOJ2056 {
    // https://www.acmicpc.net/problem/2056

    // 소요시간 >>
    // 1시간 

    // 아이디어 >>
    // 이제 위상정렬의 기본 틀이 머리에 생겼다. 
    // (이번 문제는 거의 기본과 동일하고 output만 달라서 해당 내용을 기반삼아 기본틀을 설명하고자 함.)
    // 0. indegree값과 진출노선들을 정리해야하는 게 Input에서 해야할 내용들이다.
    // 1. 그 후에 진입차수(indeg)가 0인것을 먼저 모아 큐에 넣어주고
    // 2. 큐에서 수를 하나(cur)빼서 그 노드를 진입노드로 생각하는 노드들(next)의 indegree값을 하나씩 빼내 연결을 끊어간다. 
    // 3. 그러다 next의 indegree가 0이되면 q에 넣어 탐색을 수행함.
    // 4. 모든 노드들의 indegree가 0이되고 탐색도 끝났다면 각 노드들의 총 소요시간(totalTime) 중 가장 큰 값을 출력
    // 요약하면 위상정렬은 
    // 정의 - indegree가 0인 노드들부터 큐에 넣어 다음 노드들의 indegree를 줄여가며 모든 노드가 탐색될때까지 큐에넣고 탐색하기를 반복한다.
    // 사용상황 - 선행해야할 과제/문제/노드가 있을 경우
    
    // 에러로그 >>
    // X

    // 개선과정 >>
    // * StringBuilder를 미리 만들어뒀다가 삭제 -> 원상복귀함.(근데 왜 늘어나는지 잘모르겠음... 메모리는 더 줄어야하는거 아닌가)
    // -> 메모리 72316 -> 87788 / 시간 644 -> 868 

    public static void main(String[] args) throws IOException {
        // Input
        MYIO io = new MYIO();
        io.readLine();
        int n = io.nextInt();
        // initialize array
        int[] delayArr = new int[n+1];
        int[] indeg = new int[n+1];
        ArrayList<Integer>[] outdeg = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            io.readLine();
            // save delay time
            delayArr[i] = io.nextInt();
            // make connection 
            for(int con = io.nextInt();con>0;con--){
                int prev = io.nextInt();
                if(outdeg[prev]==null) outdeg[prev] = new ArrayList<>();
                outdeg[prev].add(i);
                indeg[i]++;
            }
        }
        // Logic
        // find zero indegree
        int[] totalTime = new int[n+1]; // 각노드가 종료되기까지 걸리는 총 시간
        Queue<Integer> q = new ArrayDeque<>(); // 탐색을 위해 사용한 큐
        for(int i=1;i<=n;i++){
            // indegree값이 0이면 진입차선이 없다는 것이기 때문에 시작점으로 사용
            if(indeg[i]==0) {
                q.add(i);
                totalTime[i] = delayArr[i];
            }
        }
        // topological sort
        while(!q.isEmpty()){
            // get a number
            int cur = q.poll();
            // if outdeg == null (it means out-connection is not exist -> skip this loop)
            if(outdeg[cur]==null) continue;
            // for next
            for(int next : outdeg[cur]){
                // save cur's out-connection node's construction time
                totalTime[next] = Math.max(totalTime[next],totalTime[cur]+delayArr[next]);
                // subtract indegree value and add it into q if indegree eqals zero 
                if(--indeg[next]==0) q.add(next);
            }
        }
        // Output
        System.out.println(Arrays.stream(totalTime).max().getAsInt());
    }

    // Inner Class - Read & Write values
    private static class MYIO{
        // Variable
        BufferedReader br;
        StringTokenizer st;
        StringBuilder sb;
        // Constuctor
        public MYIO(){
            br = new BufferedReader(new InputStreamReader(System.in)); 
            sb = new StringBuilder();
        } 
        // Method - for input
        public void readLine() throws IOException{ st = new StringTokenizer(br.readLine());}
        public int nextInt(){ return Integer.parseInt(st.nextToken());}
        // Method - for output
        public void append(int i) {sb.append(i+"\n");}
        public String toString(){return sb.toString();}
    }
}
