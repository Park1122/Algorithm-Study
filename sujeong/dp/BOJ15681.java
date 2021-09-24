package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ15681 {

    // 소요시간
    // 3시간(어렵다...)
    // * int 배열을 사용 + dp를 까먹었을 떄, 자꾸 메모리 초과가 떠서 아예 새로운 방식을 찾느라 오래걸렸다...

    // 아이디어
    // * dfs & dp를 한번에 이용하는 방법이다.
    // * dfsFunc(int vertex)는 dfs를 이용해 해당 간선 아래의 개수를 찾아 dp에 저장한다.


    // 에러 로그 >>
    // * 메모리 초과 : 고정배열을 사용함 + int[][][]까지 늘려봄 -> 시간과 메모리 사용량이 컸다.

    // Attribute
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static ArrayList<ArrayList<Integer>> nodes; // orgArr [노드의 값][자식들 -> 유동적, 고정배열시 크기가 너무 큼. -> ArrayList사용]
    private static int[] dp; // dp값 (자식들의 수)
    private static boolean[] visited; // 방문 기록
    private static int n, r, q; // node의 수, vertex의 값, query의 값

    // Main
    public static void main(String[] args) throws IOException{
        // Basic Info Setting
        String[] infoStr = reader.readLine().split(" ");
        n = Integer.parseInt(infoStr[0]);
        r = Integer.parseInt(infoStr[1]);
        q = Integer.parseInt(infoStr[2]);

        // Nodes Setting
        nodes = new ArrayList<>();
        for(int i=0;i<=n;i++) {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            nodes.add(arr);
        }

        // Node Connection
        for(int i=1;i<n;i++){
            String[] lineStr = reader.readLine().split(" ");
            int one = Integer.parseInt(lineStr[0]);
            int other = Integer.parseInt(lineStr[1]);

            // 간선 연결(양방향)
            nodes.get(one).add(other);
            nodes.get(other).add(one);
        }

        // visited & dp Setting
        visited = new boolean[n+1];
        dp = new int[n+1];

        // Logic
        dfsFunc(r);

        // Output
        for(int i=1;i<=q;i++){
            int queryNum = Integer.parseInt(reader.readLine());
            System.out.println(dp[queryNum]);
        }
    }

    private static int dfsFunc(int val){
        // if dp exist
        if(dp[val]!=0) return dp[val];

        // if dp not exist
        int count = 1;
        visited[val]=true;
        for(int num :nodes.get(val)){
            if(!visited[num]) {
                count+=dfsFunc(num);
            }
        }
        dp[val] = count;
        return dp[val];
    }
}
