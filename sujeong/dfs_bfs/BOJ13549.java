package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ13549 {
    // https://www.acmicpc.net/problem/13549

    // 소요시간 >>
    // 2시간
    // 값은 반례까지 잘나오는데 메모리 초과와 시간 초과를 해결하는게 어려웠다.

    // 아이디어 >>
    // 시간에 따라 수빈이가 움직이는 상황을 큐에 담아 그 결과를 계산해본다.
    // 큐 내에서의 구성은 1) 수빈이가 동생을 만났는지 체크
    // 2) 이미 방문한 적 있는지 체크
    // 3) 순간이동하는 경우, 뒤로 한칸가는 경우, 앞으로 한칸가는 경우를 queue에 담음.
    // 수빈이가 동생을만나면 큐를 빠져나와 ans(걸렸던 시간)을 출력한다.

    // 에러로그 >>
    // 런타임에러 - checkBound를 넣어 ArrayIndexOutOfBoundsException을 해결
    // 메모리초과/시간초과 - 순간이동하는 경우의 수를 줄임
    // (pos=2라면, 이전엔 2,4,8,16,...<2k였다면 수정후엔 4만 반영하여 sec을 유지하는 방법으로 변경)

    // 개선과정 >>
    // - MAX_DIST를 200001에서 100001로 줄여 메모리와 시간을 반으로 줄임
    // - checkBound를 queue에 넣기 전에 체크하여 메모리와 시간을 조금 단축함.

    // Constants
    private static final int MAX_DIST = 100001;
    // Variable
    private static int n,k, ans=0;
    private static Queue<Situation>  q; //
    private static boolean[] visited; // 방문한적있는지를 따짐(FIFO라서 후에 들어온게 먼저 들어온거보다 sec이 작을 수 없다.)

    // Main
    public static void main(String[] args) throws IOException{
        // Input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // Initialize
        q = new LinkedList<Situation>();
        q.add(new Situation(n,0));

        visited = new boolean[MAX_DIST];

        // Logic
        logic();

        // Output
        System.out.println(ans);
    }

    private static void logic(){
        while(!q.isEmpty()){
            // check is done?
            Situation s = q.poll();
            if(isDone(s.pos,s.sec)) break; // 종료조건

            // check visited
            if(visited[s.pos]) continue;
            visited[s.pos] = true;

            // teleportation
            if(checkBound(s.pos*2)) q.add(new Situation(s.pos*2,s.sec));

            // walking
            if(checkBound(s.pos-1)) q.add(new Situation(s.pos-1,s.sec+1));
            if(checkBound(s.pos+1)) q.add(new Situation(s.pos+1,s.sec+1));

        }
    }

    // Method - 수빈이가 동생을 만났는지 체크하여, 만났다면 ans를 저장하고 만났는지 여부를 리턴하는 함수
    private static boolean isDone(int pos, int sec){
        if(pos==k) {
            ans = sec;
            return true;
        } return false;
    }

    // Method - 범위체크하는 함수
    private static boolean checkBound(int pos){return 0<=pos && pos<MAX_DIST;}

    // Inner Class - 시간(sec)에 따른 수빈이의 위치(pos)를 동시에 저장하는 클래스
    private static class Situation{
        int pos, sec;
        public Situation(int pos, int sec){
            this.pos = pos;
            this.sec = sec;
        }
    }
}