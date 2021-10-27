package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1325 {
    // https://www.acmicpc.net/problem/1325

    // 소요시간 >>
    // 8시간.... (완전 어려웠다... + 문제 잘못이해해서 5시간은 뻘짓함)

    // 아이디어 >>
    // 가장 많은 신뢰를 받는 컴퓨터가 신뢰하는 컴퓨터를 찾으면 된다. => 문제를 잘못 이해 했던 것....
    // a가 b를 신뢰할 때, a를 trustor b를 trustee라고 한다.
    // 자신의 trustee들의 trustor들과 그들의 trustor들까지 모두 포함하여 가장 많은 trustor들을 가진 trustee을 구하면 된다.

    // 에러로그 >>
    // X

    // Variable
    private static Computer[] comps;
    private static boolean[] visited;


    // Main
    public static void main(String[] args) throws IOException{
        // Input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());// 전체 컴퓨터 수,
        int m = Integer.parseInt(st.nextToken());// 관계의 수

        comps = new Computer[n + 1]; // 모든 컴퓨터들을 담은 배열
        for(int i=1;i<=n;i++) comps[i] = new Computer(i); // 처음에 만들어 nullPointException을 방지

        for(int i=0;i<m;i++){
            st = new StringTokenizer(reader.readLine(), " ");
            int a =  Integer.parseInt(st.nextToken());
            int b =  Integer.parseInt(st.nextToken());

            // a가 b를 신뢰한다.
            comps[b].trustors.add(comps[a]);
        }

        // Logic
        int[] trustors = new int[n + 1]; // 자신을 신뢰하는 모든 컴퓨터 수를 trustors에 저장
        int maxVal = 0; // 출력에서 가장 큰 값 찾는데 쓸 시간 단축을 위해 사용
        for(int i=1;i<=n;i++){
            // visited check - 1<-2 / 2<-3 / 1<-3 같은 상황에서 무한루프도는거 방지
            visited = new boolean[n+1]; // 해당 i의 산하를 셀 떄마다 visited를 new해줌.
            visited[i] = true; // 자기자신도 visited true로 변경
            trustors[i] = dfs(i); // trustors 결과 저장
            maxVal = Math.max(maxVal, trustors[i]);// maxVal 개선
        }

        // Output
        StringBuilder ans = new StringBuilder();
        for(int i=1;i<=n;i++){ // maxVal과 trustors 수가 일치하는 컴퓨터 인덱스만 추가
            if(trustors[i]==maxVal) ans.append(i).append(" ");
        }
        System.out.println(ans.toString().stripTrailing());

    }

    // Method
    private static int dfs(int index){
        int retVal = 1; // 자기자신
        for(Computer trustor: comps[index].trustors){ // 자신을 신뢰하는 컴퓨터들을 탐색
            if(visited[trustor.index]) continue; // 방문한 적 있다면 넘어감.
            visited[trustor.index] = true; // 방문여부 체크
            retVal+=dfs(trustor.index); // 해당 trustor를 신뢰하는 trustor들을 탐색해 retVal에 그 수를 더해줌.
        }
        return retVal;
    }


    // Inner Class
    private static class Computer {
        // Variable
        int index;
        ArrayList<Computer> trustors;
        // Constructor
        public Computer(int index){
            this.index = index;
            this.trustors = new ArrayList<>();
        }
    }
}
