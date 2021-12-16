package sujeong.topological_sort;

import java.util.*;
import java.io.*;

public class BOJ2623 {
    // https://www.acmicpc.net/problem/2623

    // 소요시간 >>
    // 일단 1시간 + 4시간
    // 2252를 풀고나서 다시한번 풀어본건데 사이클을 체크하는 과정에서 시간이 오래 걸렸다.
    // 사이클을 체크하는 부분, 중복을 체크하는 부분을 따로 넣어둬야할 것 같다.

    // 아이디어 >>
    // 사이클 유무를 체크하는게 중요한 문제같다.
    // 1. 입력 - 진입간선의 수, 진출간선의 정보를 저장한다.
    // 2. 로직 - 입력간선의 수가 0인 노드를 찾아 큐에 넣어준다(= 시작점 셋팅)
    //         - 위상정렬을 실시한다.
    // 3. 출력할 값들의 수가 n과 같다면(사이클이 없다면) 정렬 결과를 출력하고, 아니라면 0을 출력한다.

    // 에러로그 >>
    // 틀렸습니다(9%) 
    // - m이 0인 경우 처리
    //    1) m이 0이면 값을 n만큼을 출력한 뒤 종료시킨다 -> 어차피 m이 0이면 진입차수 계산도 안이뤄져서 다 큐에 들어가 출력이 됨
    // - 중복 처리
    //    1) 해당 글(https://www.acmicpc.net/board/view/21871)의 댓글을 보고 visited를 큐에 넣을때 체크하도록 넣어봤다 -> 효과 없음 -> 삭제 
    //    2) extArr을 ArrayList로 해서 중복이 들어간 것 같아 HashSet으로 변경 및 중복 방지 코드 추가 -> 효과 없음
    // - 사이클 처리
    //    1) Logic>make connection에서 사이클이 생기는 경우를 체크함 -> 효과없음 
    //    2) 바로 종료하면 문제 생기니 check만 해두고 입력값을 다 받은 뒤 종료시킴.
    //    3) make connection>connecting에서 "if(extArr[one].contains(tmp)) isCycle=true;"을 넣어 입력을 다 받고 종료를 시켰더니 
    //       3개 이상의 노드로 구성된 사이클은 검출해내지 못함. -> 해결
    // => 9퍼에서 틀리는건 3개 이상의 노드로 구성된 사이클일 경우였다! isCycle()을 통해 해결!
    // 틀렸습니다(18%) 
    // - 사이클처리를 출력 시 ansList에 담아서 하는 방법으로 변경하여 ansList에 담겨진 노드의 수가 n과 같다면
    // - int cnt = mr.nextInt(); 을 추가하며 통과! -> 인줄 알았으나 ArrayList대신 HashSet을 사용해서 틀린 문제였다.


    private static ArrayList<Integer>[] extArr;
    private static int[] entArr;
    private static boolean[] visited, innerVisited;

    public static void main(String[] args) throws IOException{
        // Input
        MyReader mr = new MyReader();
        mr.readLine();
        int n = mr.nextInt(); // 노드의 수 
        int m = mr.nextInt(); // 보조피디의 수

        // initialize
        entArr = new int[n+1]; // 각 노드의 진입차수를 저장하기 위한 배열
        extArr = new ArrayList[n+1]; // 각 노드의 진출간선들을 담기 위한 배열
        for(int i=1;i<=n;i++) extArr[i] = new ArrayList<>(); // extArr에 값들을 바로바로 넣을 수 있게 ArrayList를 new해둠

        // Logic
        // make connection
        for(int i=0;i<m;i++){
            // read a line
            mr.readLine();
            int cnt = mr.nextInt();
            // 입력 cnt가 0일 경우를 판단함.
            if(cnt==0) continue;
            int tmp = mr.nextInt();
            // connecting
            for(int loop=1;loop<cnt;loop++){
                int one = mr.nextInt();
                // tmp와 one의 간선이 없다면, 둘을 연결하고 one의 진입차수를 1증가시킨다.
                extArr[tmp].add(one);
                entArr[one]++;
                tmp = one;
            }
        }
        // initialize
        Queue<Integer> q = new ArrayDeque<>();
        ArrayList<Integer> ansList = new ArrayList<>();
        // find indegree euqals zero 
        for(int i=1;i<=n;i++) if(entArr[i]==0) q.add(i); 
        // do topological sort
        while(!q.isEmpty()){
            // get a number
            int cur = q.poll();
            ansList.add(cur);
            // do preorder traversal
            for(int next:extArr[cur]) {
                // cut the edge -> subtract the degree of entry
                entArr[next]--;
                // if indegree  is 0, put it in the q
                if(entArr[next]==0) q.add(next);
            }
        }
        // Output
        if(ansList.size()!=n) System.out.println(0);
        else {
            StringBuilder sb = new StringBuilder();
            for(int cur : ansList) sb.append(cur+"\n");
            System.out.print(sb.toString());
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
