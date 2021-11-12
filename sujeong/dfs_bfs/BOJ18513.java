package sujeong.dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ18513 {
    // https://www.acmicpc.net/problem/18513

    // 소요시간 >>
    // 8시간... (진짜.. 거의 다 됐고 끝난거 아닌가 했는데 시간초과 여러번 맞고,,,
    // 다른 분들 참고해서 다시 풀어봐도 계속 반려되고,,,
    // 덕분에 자료구조 공부 다시한번 보며 HashSet의 존재를 상기할 수 있었다.

    // 아이디어 >>
    // 처음에 생각한 아이디어는 샘터들의 위치를 담고 그 샘터들을 돌며 dist를 늘려나가는 방법이었다.
    // 하지만 이는 후에 더이상 갈 수 없는 경우들을 제외하는데 적합하지 않았고, 시간초과를 만들었다.
    // 그래서 생각한 두번째 방법은 샘터들의 위치는 첫 시작점의 위치일 뿐이고,
    // 양쪽의 좌표를 큐에 담아 탐색을 넓혀나가는 방식이었다.
    // 범위와 방문여부만 체크하여 허용이된다면 큐에 tmp 좌표를 담아 넓혀나가고,
    // dist로 불행도를 계산하는데 문제가 없도록 하였다.

    // 에러로그 >> (17개나 제출했던...)
    // 시간초과
    //  - 정답은 잘뜨지만 k가 끝나갈때까지 모든 경우를 탐색하는 방식은 시간초과를 불러왔다.
    //  - map과 arraylist의 contains로 방문여부를 체크한것은 시간 소요가 컸다.
    // 메모리초과
    //  - 샘터의 위치를 배열로도 저장하고 큐로도 저장하고 클래스로도 저장하니 중복된 내용이 많아 메모리가 초과되었다.

    // 알게된 점>>
    // - 배열(ex. int[])보다 hashSet이 빠르다. (HashSet은 탐색속도가 O(1)이다.)
    // - 코드길이 줄이려다 알게된 건데, 큐와 해시셋을 전역에서 new해두는게 선언만 해두고 main에서 생성하는 것보다
    //  메모리와 시간이 더 오래걸린다.

    // Constants
    private static final int MAX = 100000000;
    private static final int[] diff = {-1,1};

    // Variable
    private static int n,k;
    private static Queue<Place> q;
    private static HashSet<Integer> visited;

    // Main
    public static void main(String[] args) throws IOException{
        // Initialize
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        q = new LinkedList<>();
        visited= new HashSet<>();

        st = new StringTokenizer(reader.readLine());
        for(int i=0;i<n;i++){
            int cur = Integer.parseInt(st.nextToken());
            q.add(new Place(cur,1)); // add queue for searching
            visited.add(cur); // put the current place in the hashset to check for visits.
        }

        // Logic & Output
        System.out.println(logic());
    }

    // Logic
    private static long logic(){
        // local Varaible
        long ans = 0;
        // logic
        while(!q.isEmpty()){
            // get a place
            Place cur = q.poll();
            for(int i=0;i<2;i++){
                // calc tmp place
                int tmp = cur.pos + diff[i];
                // check condition (범위, 방문여부)
                if(-MAX==tmp||tmp==MAX||visited.contains(tmp)) continue;
                // calc 불행도(ans)
                ans += cur.dist;
                // check end condtion (k가 0이되면 더이상 지을 집이 없을 때를 의미)
                if(--k==0) return ans;
                // 지을 집이 남았다면, tmp place의 방문여부를 체크하고 큐에 tmp와 다음 장소의 불행도(dist+1)를 넣음.
                visited.add(tmp);
                q.add(new Place(tmp, cur.dist+1));
            }
        } return ans;
    }
    // Inner Class - 위치와 불행도(거리)를 담은 클래스
    private static class Place{
        // Variable
        int pos, dist;
        // Constructor
        public Place(int pos, int dist){
            this.pos = pos;
            this.dist = dist;
        }
    }
}