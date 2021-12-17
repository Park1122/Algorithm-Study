package sujeong.topological_sort;

import java.util.*;
import java.io.*;

public class BOJ9470 {
    // https://www.acmicpc.net/problem/9470
    // 소요시간 >>
    // 5시간 (아직도 위상정렬은 어렵다..)

    // 아이디어 >>
    // 기본 위상정렬 문제에 strahler 순서에 따른 조건이 추가되어 문제를 이해하고 만드는데 어려움을 겪었던 문제다.
    // 문제를 푸는 방식은 다음과 같다.
    // 0. 테스트 케이스 수 만큼 1~n을 반복한다.
    // 1. 노드들이 연결되는 노드들을 담고, 각 노드의 진입차수를 계산한다.
    // 2. 큐에 진입차수가 0인 시작 노드들을 담고, 그들의 strahArr 수를 초기화해준다.
    // 3. 큐를 돌기시작한다.
    // 4. 큐에서 뽑은 수의 strahArr의 순서가 가장 큰 경우의 개수를 찾고 그 수가 2개이상이면 조건에 맞게 maxCnt를 증가시켜준다.
    // 5. 그 후 가장 큰 maxCnt값을 업데이트하고
    // 6. 전위순회로 strahArr의 개수를 업데이트해나간다.
    // 7. 모든 큐를 다 돌았다면 가장 큰 strahler 순서의 개수가 담긴 ans를 테스트케이스 번호와 함께 출력한다.

    // 에러로그 >>
    // 틀렸습니다 (25%)
    // - "나머지 노드는 그 노드로 들어오는 강의 순서 중 가장 큰 값을 i라고 했을 때, ... " 부분의 조건을 제대로 안읽었다.

    public static void main(String[] args) throws IOException {
        // Input
        MyReader mr = new MyReader();
        mr.readLine();
        int t = mr.nextInt(); // number of tests for this program
        // repeat for loop as many as testcase
        for(int i=0;i<t;i++){
            mr.readLine();
            int tNum = mr.nextInt(); // testcase number
            int nNum = mr.nextInt(); // node number
            int eNum = mr.nextInt(); // edge number

            // initialize connecting info and indegree value
            ArrayList<Integer>[] orgArr = new ArrayList[nNum+1]; //[노드번호][노드가 연결하는 노드들]
            for(int j=1;j<=nNum;j++) orgArr[j] = new ArrayList<>(); // 진출간선을 담음 + 초기화
            int[] indegree = new int[nNum+1]; // 진입간선의 수를 담기 위함.

            // connecting edges
            for(int j=0;j<eNum;j++){
                mr.readLine();
                int a = mr.nextInt(); // start point
                int b = mr.nextInt(); // end point
                orgArr[a].add(b); // connecting (a->b)
                indegree[b]++; // increase b's indegree value
            }
            // Logic
            // initialize queue and strahler array
            Queue<Integer> q = new ArrayDeque<>(); // 위상정렬을 할 때 수를 담을 큐
            int[][] strahArr = new int[nNum + 1][nNum + 1]; // [노드번호][해당 strahler순서의 개수]
            for(int j=1;j<=nNum;j++) {
                // 진입차수가 0이라면 q와 strahArr에 해당 노드를 넣어줌.
                if(indegree[j]==0){
                    q.add(j);
                    strahArr[j][1] = 1; // => j의 strahler순서가 1인 것의 개수는 1이다
                }
            }
            // do topological sort
            int ans = 0;
            while(!q.isEmpty()){
                // get a number
                int cur = q.poll();
                // find Max Strahler order count
                int maxCnt = 0;
                for(int loop=nNum-1;loop>=0;loop--){
                    if(strahArr[cur][loop]>0) {
                        maxCnt = loop;
                        break;
                    }
                }
                // strahler 순서가 cur인게 2개 이상이면 들어오는 strahler 순서 중 가장 큰값을 1증가시킨다.
                if(strahArr[cur][maxCnt]>1) {
                    maxCnt++;
                    strahArr[cur][maxCnt] = 1;
                }
                // update max strahler order
                ans = Math.max(maxCnt,ans);
                // do preorder traversal
                for(int next:orgArr[cur]) {
                    // increase next's strahler count
                    strahArr[next][maxCnt]++;
                    // cut the edge -> subtract the degree of entry
                    indegree[next]--;
                    // if indegree  is 0, put it in the q
                    if(indegree[next]==0) q.add(next);
                }
            }
            // Output
            System.out.println(tNum+" "+ans);
        }
    }
    // Inner Class - Read Input values
    private static class MyReader{
        BufferedReader br;
        StringTokenizer st;
        public MyReader(){ br = new BufferedReader(new InputStreamReader(System.in));}
        public void readLine() throws IOException{ st = new StringTokenizer(br.readLine());}
        public int nextInt(){ return Integer.parseInt(st.nextToken());}
    }
}