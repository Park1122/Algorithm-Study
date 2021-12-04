package sujeong.dfs_bfs;

import java.util.*;
import java.io.*;

public class BOJ1967 {
    // https://www.acmicpc.net/problem/1967

    // 소요시간 >>
    // 4시간 (기본문제인데 오래걸렸다...)
    // 처음엔 간선의 가중치의 합이 가장 큰 경로를 탐색하는 건줄알았는데,
    // 모든 경로들 중에서 가장 긴 경로를 탐색하는 거였다.

    // 아이디어 >>
    // 어차피 1에서부터 타고 갈거고 연결또한 한 노드에는 2개의 간선이 있는 상태이기 때문에
    // arraylist에 연결된 값들을 모두 담아 연결된 것들을 타고 가면 될듯(따로 parent, child구분해서 저장할 필요는 없다.)

    // 에러로그 >>
    // X

    // Variable
    private static int n, maxCnt, maxIdx; // 총 노드들의 수, 가장 큰 가중치, 가장 긴 인덱스
    private static ArrayList<int[]>[] orgArr; // 기본적인 노드들을 저장하는 배열
    private static boolean[] visited; // 방문여부를 체크하는 배열

    // Main
    public static void main(String[] args) throws  IOException{
        // Input
        // initialize
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        // create node
        orgArr = new ArrayList[n+1];
        for(int i=0;i<=n;i++) orgArr[i] = new ArrayList<>();
        // receive information from the trunk line
        for(int i=1;i<n;i++){
            // input a value
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // put in orgArr
            orgArr[p].add(new int[]{c,v});
            orgArr[c].add(new int[]{p,v});
        }

        // Logic
        // find maxIdx (정답을 찾기위해 가장 먼 인덱스를 찾는다)
        visited = new boolean[n+1];
        visited[1] = true;
        dfs(1,0); // 루트인 1부터 탐색

        // find ans (가장 멀리있는 인덱스부터 탐색하여 정답을 찾는다)
        visited = new boolean[n+1];
        visited[maxIdx] = true;
        dfs(maxIdx,0);

        // Output
        System.out.println(maxCnt);
    }
    // Method - dfs function
    private static void dfs(int idx, int cnt){
        // update maxCnt, maxIdx
        if(maxCnt<cnt){
            maxCnt = cnt;
            maxIdx = idx;
        }
        // Search for connected nodes
        for(int[] node:orgArr[idx]){
            // if not visited -> execute node's dfs
            if(!visited[node[0]]){
                visited[node[0]] = true;
                dfs(node[0], cnt+node[1]);
            }
        }
    }
}