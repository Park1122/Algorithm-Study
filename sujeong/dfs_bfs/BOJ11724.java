package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ11724 {
    // https://www.acmicpc.net/problem/11724

    // 소요시간 >>
    // 1시간

    // 아이디어 >>
    // 1. 1~n까지 방문했음 여부를 체크하여 방문하지 않았을 경우 bfsFunc를 호출
    // 2. bfsfunc는 bfs를 이용해 자신을 먼저 담고 하위 노드를 탐색하여 queue에 담고 방문여부(visited)를 true로 변경.
    // 3. 더이상 탐색할 하위 노드가 없다면 돌아와서 queue의 결과를 retArr에 담고 초기화 한 뒤 다시 만든 뒤 1부터 다시 반복함.
    // 4. 모든 노드를 탐색했다면, retArr의 개수를 출력함.

    // 에러 로그 >>
    // 틀렸습니다 : 말그대로 내용자체가 틀렸으나 예제만 어떻게든 넘어갔던 듯하다... (하위 노드의 수를 세는 부분부터 틀림)

    // Attribute
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int n, m;
    private static int[][] orgArr;
    private static boolean[] visited;
    private static Queue<Integer> queue;


    public static void main(String[] args) throws IOException{
        // Input
        String[] basicInfo = reader.readLine().split(" ");
        n = Integer.parseInt(basicInfo[0]);
        m = Integer.parseInt(basicInfo[1]);

        orgArr = new int[n+1][n+1];
        for (int i=0;i<m;i++){
            String[] line = reader.readLine().split(" ");
            orgArr[Integer.parseInt(line[0])][Integer.parseInt(line[1])] = 1;
            orgArr[Integer.parseInt(line[1])][Integer.parseInt(line[0])] = 1;
        }

        // Initialize
        LinkedList<Queue<Integer>> retArr = new LinkedList<Queue<Integer>>(); // 그래프(큐)들을 담음
        queue = new LinkedList<Integer>(); // bfs 탐색한 그래프를 저장.
        visited = new boolean[n+1]; // 방문 여부 기록

        // Logic
        for(int i=1;i<=n;i++){
            if(!visited[i]) {
                bfsFunc(i);
                retArr.add(queue);
                queue=new LinkedList<Integer>();
            }
        }

        // Output
        System.out.println(retArr.size());

    }

    private static void bfsFunc(int index){
        if(queue.contains(index)) return;

        // 자신을 먼저 더하고
        queue.add(index);
        visited[index]=true;

        // 자식을 탐색하러 감.
        for(int i=1;i<=n;i++){
            if(orgArr[index][i]==1)  bfsFunc(i);
        }
    }

}
