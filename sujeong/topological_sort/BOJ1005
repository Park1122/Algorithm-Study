package sujeong.topological_sort;

import java.util.*;
import java.io.*;

public class BOJ1005{
    // https://www.acmicpc.net/problem/1005
    
    // 소요시간 >>
    // 5시간 (혼자 풀어보려고 노력했다..)

    // 아이디어 >>

    // 에러로그 >>
    // X

    // 개선과정 >>
    // * 출력을 StringBuilder를 이용해 한번에 처리했다 
    // -> 메모리 241172 -> 239344 / 시간 880 -> 848
    // * logic부분을 따로 분리하여 함수로 만들었음 
    // -> 메모리 239344 -> 244324 / 시간 848 -> 876 (원상복귀함)
    // * entArr을 ArrayList에서 LinkedList로 변경함 
    // -> 메모리 239344 -> 252432 / 시간 848 -> 852 (원상복귀함)
    
    private static int n,k;
    private static int[] indeg, delayArr, timeArr;
    private static ArrayList<Integer>[] entArr;

    public static void main(String[] args) throws IOException{
        // Input & Initialize
        MyIO mr = new MyIO();
        mr.readLine();
        for(int T=mr.nextInt();T>0;T--){
            // read value
            mr.readLine();
            n=mr.nextInt(); // 건물의 개수 (1~n)
            k=mr.nextInt(); // 건물간의 건설순서 규칙의 총 개수
            // initialize arrays
            delayArr = new int[n+1];
            entArr = new ArrayList[n+1];
            indeg = new int[n+1];
            // read delay time and initialize entArr
            mr.readLine();
            for(int i=1;i<=n;i++){
                delayArr[i] = mr.nextInt();
                entArr[i] = new ArrayList<>();
            }
            // read connection information and count indegree nodes
            for(int i=0;i<k;i++){
                mr.readLine();
                int x = mr.nextInt();
                int y = mr.nextInt();

                entArr[x].add(y);
                indeg[y]++;
            }
           
            // Logic
            logic();

            // Output
            mr.readLine();
            mr.append(timeArr[mr.nextInt()]);
        }
        // Final Output
        System.out.println(mr.toString());
    }

    private static void logic(){
        // initalize
        Queue<Integer> q = new ArrayDeque<>();
        timeArr = new int[n+1];
        // find start Node(indeg==0) and initialize their arrival time
        for(int i=1;i<=n;i++){
            if(indeg[i]==0) {
                q.add(i);
                timeArr[i] = delayArr[i];
            }
        }
        // searching
        while(!q.isEmpty()){
            // get a number
            int num = q.poll();
            // for next
            for(int next : entArr[num]){
                // next Node에 도달하기 위해 걸리는 시간 계산
                timeArr[next] = Math.max(timeArr[next],timeArr[num]+delayArr[next]);
                // next Node에 도달할 수 있는 상태가 되면(indeg==0) 큐에 넣어 탐색할 수 있게함.
                if(--indeg[next]==0) q.add(next);
            }
        }
    }

    // Inner Class - Read & Write values
    private static class MyIO{
        // Variable
        BufferedReader br;
        StringTokenizer st;
        StringBuilder sb;
        // Constuctor
        public MyIO(){
            br = new BufferedReader(new InputStreamReader(System.in)); 
            sb = new StringBuilder();
        } 
        // Method - for input
        public void readLine() throws IOException{ st = new StringTokenizer(br.readLine());}
        public int nextInt(){ return Integer.parseInt(st.nextToken());}
        // Method - for output
        public void append(int i) {sb.append(i+"\n");}
        public String toString(){return sb.toString();}
    }
}
