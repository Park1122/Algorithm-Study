package sujeong.tree;

import java.util.*;
import java.io.*;

public class BOJ15900 {
    // https://www.acmicpc.net/problem/15900

    // 소요시간 >>
    // 3시간(값을 생각하는 과정이 꽤나 걸림...)

    // 아이디어 >>
    // leaf에서 root까지의 거리의 총합을 BFS방식으로 구하고 이를 2로 나눴을때,
    // 나머지가 1이면 Yes를 출력하고 0이면 No를 출력한다

    // 에러로그 >>
    // 에러는 아닌데 너무 부족한 결과라 생각해서 개선한 내용을 적어둠.
    // [1차] 메모리 437028 / 시간 1868
    // - 속도 개선을 위해 split으로 쪼개지않고 StringTokenizer를 이용함.
    // - 값 저장을 위해 HashSet을 배열에 넣는 대신 ArrayList로 변경함.
    // [2차] 메모리 220616 / 시간 1016
    // - 1차에 비해선 확실히 속도가 개선됨.
    // - StringTokenizer의 힘에 깜짝 놀랐답니다...

    // Main
    public static void main(String[] args) throws IOException{
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // initialize orgArr
        ArrayList<Integer>[] orgArr = new ArrayList[n + 1];
        for(int i=1;i<=n;i++) orgArr[i] = new ArrayList<>();
        // get info from the trunk line
        StringTokenizer st;
        for(int i=1;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int one = Integer.parseInt(st.nextToken());
            int other = Integer.parseInt(st.nextToken());
            orgArr[one].add(other);
            orgArr[other].add(one);
        }


        // Initialize
        int sum = 0; // 각 leaf에서 root까지의 거리의 총합을 담을 변수

        Queue<int[]> q = new LinkedList<>(); // 노드번호와 건넌 다리수를 넘겨줄 큐
        q.add(new int[]{1,0}); // 노드번호, 건넌 다리수

        boolean[] visited = new boolean[n+1]; // 방문여부를 체크할 배열
        visited[1]=true; // 시작 루트인 1은 이미 방문했다고 셋팅


        // Logic
        // leaf -> root의 거리 합 구하기 (using bfs)
        while(!q.isEmpty()){
            int[] cur = q.poll();
            // check is leaf (leaf이라면 sum에 더해주고자 이를 체크할 boolean 변수를 만든것)
            boolean isLeaf = true;
            // chdeck has child and add child
            for(int child : orgArr[cur[0]]){
                // check visited
                if(visited[child]) continue;
                visited[child] = true;
                // it has child  = it isn't leaf
                isLeaf = false;
                // for next
                q.add(new int[]{child,cur[1]+1});
            }
            // 끝 부분에 도착했다면 sum에 깊이를 추가함.
            if(isLeaf) sum+=cur[1];
        }


        // Output
        // 나머지가 1이면 먼저 시작한 성원이가 이길 수 있기 때문에 Yes
        // 나머지가 0이면 먼저 시작한 성원이가 이길 수 없기 떄문에 No
        System.out.println(sum%2==1?"Yes":"No");
    }
}