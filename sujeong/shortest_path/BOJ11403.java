package sujeong.shortest_path;

import java.util.*;
import java.io.*;

public class BOJ11403 {
    // https://www.acmicpc.net/problem/11403

    // 소요시간 >>
    // 30분 (입력부분만 잘 처리하면 기본 탐색 알고리즘과 다를바없는 문제였다.)

    // 아이디어 >>
    // 0) i->j에 맞춰 값을 orgArr 배열에 넣어준다.
    // 1) 각 줄마다의 dp를 계산하여 출력하는 문제이기 때문에 탐색을 시작하곤 for문으로 각 줄마다 큐에 줄번호(i)를 넣고 visited를 new해준다.
    // 2) 큐가 빌때까지 그 줄의 시작 인덱스의 노드부터 갈수있는 노드들을  dp와 visited로 체크하며 탐색한다.
    // 3) 모든 탐색이 끝나면 dp의 값을 순서대로 모두 출력한다.

    // 에러로그 >>
    // X
    
    // 개선과정 >> 
    // * 바로 출력 -> StringBuilder사용 
    // -> 메모리 24332 -> 23488 / 시간 540->328 
    // * BufferedReader와 StringTokenizer를 MyIO란 클래스로 묶어 사용하다가 이를 분해함
    // -> 메모리 23488 -> 23304 / 시간 328->296 
    // * 꼭 다익스트라일 필요는 없는 문제 같아서 PrioirtyQueue에서 ArrayDequeue로 변경
    // -> 메모리 23304 -> 20472 / 시간 296->300 / (오히려 더 안좋아져서 원상복구, 메모리는 줄어듬)
    // * visited[next]란 boolean배열로 방문여부를 따로 체크하는 거에서 dp[i][next]로 체크하도록 변경
    // -> 메모리 23304 -> 21024 / 시간 296->304 / (더 안좋아졌지만 깔끔해서 유지시킴)
    // * orgArr에 1이 없는 줄이라면 ArrayList를 new하지 않도록 변경
    // -> 메모리 23304 -> 23436	/ 시간 296->316 / (오히려 더 안좋아져서 원상복구)
    // * StringBuilder로 출력할때 한줄의 마지막 인덱스인지 계속 확인하여 마지막일경우에는 "\n", 아니면 " "를 append하도록 함.
    // -> 메모리 23304 -> 23728	/ 시간 296->300 / (오히려 더 안좋아져서 원상복구)
    // * StringBuilder로 출력할때 줄 안에서는 " "로 값을 연결하고, 한줄이 끝나면 마지막 값을 "\n"으로 변경
    // -> 메모리 23304 -> 22628	/ 시간 296->292 
    // * 출력부분을 미리 계산하여 시간과 불필요한 메모리(dp로 출력할 값을 담았다가 String으로 바로바로 추가하게함.) 사용을 줄임 
    // -> 메모리 22628 -> 16296	/ 시간 292->212 

    // Variable
    private static BufferedReader br;
    private static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        // Input
        br = new BufferedReader(new InputStreamReader(System.in));
        readLine();
        int n = nextInt(); // 노드의 수 & 행/열의 개수

        ArrayList<Integer>[] orgArr = new ArrayList[n+1]; // 연결정보를 담아낼 배열
        for(int i=0;i<n;i++){
            readLine();
            orgArr[i] = new ArrayList<>();
            for(int j=0;j<n;j++) if(nextInt()==1) orgArr[i].add(j);
        }

        // Logic
        // initialize settings
        Queue<Integer> q= new PriorityQueue<>(); // 탐색을 윈한 큐
        boolean[] visited; // 각 줄에서의 방문여부를 체크하고자 사용하는 배열
        StringBuilder sb = new StringBuilder(); // 출력할 내용을 담고자 사용하는 StringBuilder
        // start all line searching 
        for(int i=0;i<n;i++) {
            // reinitialize 
            visited = new boolean[n+1]; // 각 줄(i)마다 new해준다.
            q.add(i);
            // start one line searching
            while(!q.isEmpty()){
                // get a number
                int cur = q.poll();
                for(int next : orgArr[cur]){
                    // check visited condition and add the next into q
                    if(visited[next]) continue;
                    visited[next] = true;
                    q.add(next);
                }
            }
            // 출력부분을 미리 계산하여 시간과 불필요한 메모리 사용을 줄임
            for(int j=0;j<n;j++) {
                // i란 노드가(해당 i줄이) j에 방문한적 있다면 1을 추가하고 아니면 0을 추가
                if(visited[j]) sb.append("1 ");
                else sb.append("0 ");
            } sb.replace(2*n*(i+1)-1, 2*n*(i+1), "\n"); // 마지막 인덱스값만 변경해줌.
        }

        // Output
        System.out.println(sb.toString());
    }
    // Method - for read input values
    public static void readLine() throws IOException { st = new StringTokenizer(br.readLine());}
    public static int nextInt(){ return Integer.parseInt(st.nextToken());}
}
