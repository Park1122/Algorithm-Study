package sujeong.topological_sort;

import java.util.*;
import java.io.*;

public class BOJ1516{
    // https://www.acmicpc.net/problem/1516
    
    // 소요시간 >>
    // 1시간 (1005랑 비슷한 느낌이라 금방 풀었다.)

    // 아이디어 >>
    // 1. indegree가 0인 노드들을 큐에 담는다.
    // 2. q로 부터 노드번호를 가져와 해당 노드로부터 연결되는(해당 노드를 진입노드로 생각하는) 노드들을 가져오며
    // 3. 그 노드들의 총 건설시간(timeArr[next])을 새로운 건설기간(timeArr[cur]+delayArr[next])과 비교하며 업데이트해나간다.
    // 4. 그 노드들의 진입차수가 3을 통해 0이된다면 큐에 담아 탐색할 수 있도록 하여 2~4를 반복한다.
    // 5. 각 노드들의 총 건설시간을 출력한다.

    // 에러로그 >>
    // X

    // 개선과정 >>
    // * 전역변수로 각 array들을 빼뒀다가 지역변수로 바꿨다
    // -> 메모리 23540->23512 / 시간 296 -> 296 (동일)
    // * 간단한 출력의 반복이기 때문에 MYIO에 StringBuilder를 넣어 한번에 출력하는 대신 for문으로 기본출력함.
    // -> 메모리 23512->23336 / 시간 296 -> 292

    // Main
    public static void main(String[] args) throws IOException{
        // Input
        MYIO io = new MYIO();
        int n = io.oneInt();
        // initialize
        int[] delayArr = new int[n+1]; // 각 건물의건축 소요시간
        int[] indeg = new int[n+1]; // 진입차수
        ArrayList<Integer>[] outdeg = new ArrayList[n+1]; // 진출차수
        for(int i=1;i<=n;i++){
            // save construction delay time
            io.readLine();
            int val = io.nextInt();
            delayArr[i] = val;
            // make connection between need and i (connection direction : need -> i)
            int need;
            while((need=io.nextInt())!=-1){
                if(outdeg[need]==null) outdeg[need]=new ArrayList<>();
                outdeg[need].add(i);
                indeg[i]++;
            }
        }
        // Logic
        // find zero indegree
        int[] timeArr = new int[n+1]; // 각 건물까지의 총 소요시간(3을 짓기위해 1의 건축이 필요하다면 1+3이 저장되는 구소)
        Queue<Integer> q= new ArrayDeque<>(); // 위상정렬을 위한 큐
        for(int i=1;i<=n;i++){
            // if indegree equals zero add it into q and set total construction time eqals construction delay time 
            if(indeg[i]==0){
                q.add(i);
                timeArr[i] = delayArr[i];
            }
        }
        // topological sort
        while(!q.isEmpty()){
            // get a current number from q
            int cur = q.poll();
            // if outdeg == null (it means out-connection is not exist -> skip this loop)
            if(outdeg[cur]==null) continue;
            // for next
            for(int next : outdeg[cur]){
                // save cur's out-connection node's construction time
                timeArr[next]=Math.max(timeArr[next],timeArr[cur]+delayArr[next]);
                // subtract indegree value and add it into q if indegree eqals zero 
                if(--indeg[next]==0) q.add(next);
            }
        }
        // Output
        for(int i=1;i<=n;i++) System.out.println(timeArr[i]);
    }
    // Inner Class - io process class
    private static class MYIO{
        // Variable
        BufferedReader br;
        StringTokenizer st;
        // Constructor
        public MYIO() {br=new BufferedReader(new InputStreamReader(System.in));}
        // Functions
        public void readLine() throws IOException {st=new StringTokenizer(br.readLine());}
        public int oneInt() throws IOException {return Integer.parseInt(br.readLine());}
        public int nextInt() {return Integer.parseInt(st.nextToken());}
    }
}