package sujeong.tree;

import java.util.*;
import java.io.*;

public class BOJ1068{
    // https://www.acmicpc.net/problem/1068

    // 소요시간 >>
    // 40분

    // 아이디어 >>
    // 1. 입력으로 트리를 만들때 orgArr[부모]에 arrayList를 이용해 자식들을 담도록 한다.
    // 2. BFS로 root부터 탐색을 진행하며 rmNum(지워질 노드의 숫자)가 나타나면 넘어가고
    // 3. leafNode를 찾으면 leafNum을 추가한다.
    // 4. rmNum 노드도 leaf 노드도 아니라면 탐색을 위해 큐에 추가해준다.
    // 5. 탐색을 마친 뒤 leafNum을 출력하며 끝낸다.

    // 에러로그 >>
    // 틀렸습니다 
    // - 질문검색부분에 있던 글을 보고 해결. 일직선 상의 트리일 때 리프 노드의 수를 잘못 계산했기 때문이다.
    // - https://www.acmicpc.net/board/view/67627
    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // n(노드의 수)
        int root = 0; // root값을 담을 변수

        ArrayList<Integer>[] orgArr = new ArrayList[n]; // orgArr[부모번호](자식들...)로 담기위한 구조
        StringTokenizer st = new StringTokenizer(br.readLine());        
        for(int i=0;i<n;i++){
            // input value
            int input = Integer.parseInt(st.nextToken());
            // if parent node -> set root i
            if(input==-1) root = i;
            else{ 
                // if orgArr[input]==null 아직 아무런 자식도 추가되지 않아 new가 안되어있다면 new를 해줌.
                if(orgArr[input]==null) orgArr[input]=new ArrayList();
                // orgArr[부모]에 i(자식)들 더해준다.
                orgArr[input].add(i);
            }
        }
        int rmNum = Integer.parseInt(br.readLine());

        // Logic
        int leafCnt = 0; // leaf의 수를 담을 변수이자 정답
        Queue<Integer> q = new ArrayDeque<>(); // bfs로 자식을 탐색하기 위한 큐
        q.add(root); // root부터 시작이니 root를 담아준다.
        // do bfs
        while(!q.isEmpty()){
            // get a number
            int cur = q.poll();
            // check Condition
            // 현재 숫자가 지워질 노드의 번호라면 더이상 leaf나 q에 추가할 필요없으니 넘어감.
            if(cur == rmNum) continue; 
            // 현재 숫자가 자식이 없거나, 현재 숫자의 자식이 하나인데 그 자식이 지워질 번호라면 leaf수를 늘리고 넘어감.
            if(orgArr[cur]==null || (orgArr[cur].size()==1 && orgArr[cur].get(0)==rmNum)){
                leafCnt++;
                continue;
            }
            // add child into q for next loop
            for(int c : orgArr[cur]) q.add(c);
        }

        // Output
        System.out.println(leafCnt);
    }
}
