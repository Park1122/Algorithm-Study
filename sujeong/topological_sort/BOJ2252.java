package sujeong.topological_sort;

import java.util.*;
import java.io.*;

public class BOJ2252 {
    // https://www.acmicpc.net/problem/2252
    
    // 소요시간 >>
    // 4시간
    
    // 아이디어 >>
    // 0. 진입차수를 담는 배열과 진출 간선들을 담는 배열을 준비한다.
    // 1. 연결 만들기
    //   - 단순 for문으로만 조회할것이기 때문에 ArrayList사용하여 방향성있는 그래프를 표현함.
    // 2. 진입차수가 0인 노드를 찾아 큐에 넣어줌 
    //   - 진입차수가 0이다 = 시작노드다 / 진입차수가 0인게 없다 -> 순환그래프라 위상정렬 불가
    // 3. 큐에서 노드들을 빼내 출력한 뒤, 해당 노드와 연결된 노드들의 진입차수를 1 감소시키다 
    //    진입차수가 0인 노드가 되면 큐에 넣어 2~3의 탐색을 큐가 빌때까지 반복한다.
    // 4. 정답을 출력한다.

    // 에러로그 >>
    // 틀렸습니다 - visited를 쓰면 틀렸습니다가 나옴.
    // 메모리초과 - entArr에서 값을 가져올때마다 entArr[i]를 1씩 뺴줬더니 발생함.
    // 틀렸습니다 - 위상정렬할 때 if(entArr[i]==0) 을 넣어줬더니 해결되었다!

    // 위상정렬 공부한 핵심내용 요약 >>
    // (주로 여기 | https://terms.naver.com/entry.naver?docId=3579618&cid=59086&categoryId=59093)
    // 위상정렬
    // : 여러 가지 일들에 순서가 정해져 있을 때 순서에 맞게끔 나열하는 것
    // 위상정렬 사용 예시
    // - 순서가 있는 일을 순서에 맞게 나열하는 것들 (ex. 외출준비, 선수과목)
    // 위상정렬의 조건 
    // - 사이클이 없어야한다(진입차수가 0인 정점이 있어야한다.)
    

    // 들어오고 나가는게 여러개일 수 있으니 ArrayList로 사용
    private static ArrayList<Integer>[] extArr;
    private static int[] entArr;
    public static void main(String[] args) throws IOException{
        // Input
        MyReader mr = new MyReader();
        mr.readLine();
        int n = mr.nextInt(); // 노드의 수 
        int m = mr.nextInt(); // 키를 비교한 횟수
        // initialize
        entArr = new int[n+1]; // 각 노드의 진입차수를 저장하기 위한 배열
        extArr = new ArrayList[n+1]; // 각 노드의 진출간선들을 담기 위한 배열
        for(int i=1;i<=n;i++) extArr[i] = new ArrayList<>(); // extArr에 값들을 바로바로 넣을 수 있게 ArrayList를 new해둠

        // Logic
        // make connection
        for(int i=0;i<m;i++){
            // read a line
            mr.readLine();
            int one = mr.nextInt();
            int oth = mr.nextInt();
            // 방향성이 있는 그래프(one->oth)이기 때문에 one의 진출간선에 oth추가 및 oth의 진입차수 증가
            extArr[one].add(oth);
            entArr[oth]++;
        }
        // find indegree euqals zero 
        Queue<Integer> q = new LinkedList<>();
        for(int i=1;i<=n;i++){
            if(entArr[i]==0) q.add(i); 
        }
        // do topological sort
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            int cur = q.poll();
            // preorder traversal
            sb.append(cur+" ");
            for(int i:extArr[cur]) {
                // cut the edge -> subtract the degree of entry
                entArr[i]--;
                // if indegree  is 0, put it in the q
                if(entArr[i]==0) q.add(i);
            }
        }

        // Output
        System.out.println(sb.toString());
    }
    // Inner Class - Read Input values
    private static class MyReader{
        BufferedReader br;
        StringTokenizer st;
        public MyReader(){ br = new BufferedReader(new InputStreamReader(System.in));}
        public void readLine() throws IOException{ st = new StringTokenizer(br.readLine());}
        public int nextInt(){ return Integer.parseInt(st.nextToken());}
    }
}
