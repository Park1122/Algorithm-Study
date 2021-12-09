package sujeong.tree;

import java.util.*;
import java.io.*;

public class BOJ1240 {
    // https://www.acmicpc.net/problem/1240

    // 소요시간 >>
    // 1시간 

    // 아이디어 >>
    // 0. n+1사이즈의 array안에 ArrayList를 넣고, ArrayList안에는 int[]가 있는 구조를 준비한다.
    // 1. 값을 입력받을 때 [노드번호]([연결된번호, 거리값])으로 값을 넣어준다.
    // 2. 로직을통해 큐에 현재위치와 시작점에서의 거리를 넘겨주며 탐색을 진행한다.
    // 3. ep(end point)에 도착하면 ans에 dist를 넣어주고 while문을 종료한다.
    // 4. ans를 출력하고 m번동안 2~4를 반복한 뒤 종료한다.

    // 에러로그 >>
    // 틀렸습니다
    // - DFS방식을 사용하여 메모리초과가 발생해 bfs로 변경래 봄.
    // - BFS방식을 사용했을 때 dist를 방문한 모든 곳에 더해나가다보니 visited를 쓰더라도 엉뚱한 경로의 값까지 더해져 틀렸다.
    // - dist를 현재 번호위치와 함께 큐로 넘겨주도록 만들어 해결함.

    // 개선과정 
    // - HashSet을 ArrayList로 바꾸고, 값을 가져오는데 오래걸리는 전역변수대신 지역변수로 전환하여
    //   메모리 64676->58032 시간 552->452 으로 개선함.
    // - BFS에서 자식을 탐색할때 contSet의 값을 변수에 담는 것에서 그냥 바로바로 불러 쓰도록 변경하여
    //   메모리는 58032->59912 시간도 452->456 으로 늘어서 원상복귀하였다.

    public static void main(String[] args) throws IOException {
        // Input
        // read n,m value
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        // read connection
        ArrayList<int[]>[] orgArr = new ArrayList[n+1];
        for(int i=1;i<n;i++){
            st= new StringTokenizer(br.readLine());
            int one = Integer.parseInt(st.nextToken());
            int oth = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            // make connection
            if(orgArr[one]==null) orgArr[one] = new ArrayList<>();
            orgArr[one].add(new int[]{oth,val}); // {연결된번호, 거리}
            if(orgArr[oth]==null) orgArr[oth] = new ArrayList<>();
            orgArr[oth].add(new int[]{one,val}); // {연결된번호, 거리}
        }

        // Logic
        for(int i=0;i<m;i++){
            // read the two points whose distance what I want to know
            st= new StringTokenizer(br.readLine());
            int sp = Integer.parseInt(st.nextToken());
            int ep = Integer.parseInt(st.nextToken());
            // make visited array - 방문여부 체크를 위해 visited를 만듦.(+시작점을 넣어줌)
            boolean[] visited = new boolean[n+1];
            visited[sp] = true; 
            // make q - 현재 위치와 시작점(sp)에서 해당 지점까지의 거리를 담아 state를 전달하기 위해 만듦(+시작점을 넣어줌)
            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[]{sp,0});
            // BFS
            int ans = 0;
            while(!q.isEmpty()){
                // read a state
                int[] cur = q.poll();
                // end condition - ep에 도착하면 dist를 ans에 저장하고 종료
                if(cur[0]==ep) {
                    ans=cur[1];
                    break; 
                }
                // 현재 위치의 자식을 탐색
                for(int[] contSet : orgArr[cur[0]]){
                    int cont = contSet[0];
                    int d = contSet[1];
                    // check visited
                    if(visited[cont]) continue;
                    visited[cont] = true;
                    // add state for the next
                    q.add(new int[]{cont,cur[1]+d});
                }
            }

            // Output
            System.out.println(ans);
        }
    }
}
