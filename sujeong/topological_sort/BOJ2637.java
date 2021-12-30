package sujeong.topological_sort;

import java.util.*;
import java.io.*;

public class BOJ2637 {
    // https://www.acmicpc.net/problem/2637

    // 소요시간 >>
    // 6시간 (항상 indegree를 세는 방식이었어서 머리로는 진출차수를 세는게 맞는데 하면서도 이 방식을 고집했던 것 같다...)

    // 아이디어 >>
    // 이번문제는 다른 topological sort와 달리 outdegree를 세고 진입하는 노선들을 arrayList로 담는 문제였다.
    // 1. leaf에서 root로 타고올라가는 문제이지만 기본부품(=root)을 찾아야하기 때문에 탐색 전에 basic에 이들을 담아준다.
    // 2. 큐(q)와 필요한 부품의 수를 구하는 배열(needArr)에 최종부품인 n의 번호와 갯수를 담아준다.
    // 3. 큐가 빌때까지 needArr에 값을 갱신하며 이전노드의 진출차수를 끊어간다.
    // 4. 큐를 다 돌았다면 기본부품들의 needArr값(needArr[basic의 요소들])을 가져와 출력한다.

    // 에러로그 >>
    // 메모리 초과 
    // - 짜느라 남았던 찌꺼기 변수들을 정리했음 (-> 소용없었다)
    // - treeMap을 쓰다가 LinkedHashMap으로 변경했음. (-> 소용없었다)
    // ~~~이후 아예 다시 갈아엎음~~~~

    // 개선과정 >>
    // * 중복되는 변수와 코드 정리를 했다.
    // -> 메모리 16048 -> 15988 / 시간 152->148
    public static void main(String[] args) throws IOException {
        // Input
        MyIO io = new MyIO();
        io.readLine();
        int n = io.nextInt(); // 총 노드의 수이자 완제품의 번호
        // initialize
        int[] outdeg = new int [n+1];
        ArrayList<int[]>[] indeg = new ArrayList[n+1];
        // make connection
        io.readLine();
        for(int m=io.nextInt();m>0;m--){
            io.readLine();
            // y->x
            int x = io.nextInt();
            int y = io.nextInt();
            int k = io.nextInt();
            // make connections and count out-degree 
            if(indeg[x]==null) indeg[x]=new ArrayList<>();
            indeg[x].add(new int[]{y,k});
            outdeg[y]++;
        }

        // find indegree zero (find 기본부품) (내 코드는 진입노드가 없는 경우 배열값에 arrayList를 new하지 않음)
        ArrayList<Integer> basic = new ArrayList<>(); // 기본부품들을 담아둘 리스트
        for(int i=1;i<=n;i++) if(indeg[i]==null) basic.add(i); 

        // Logic
        // initialize for searching
        Queue<Integer> q= new ArrayDeque<>(); // leaf노드부터 탐색을 위해 사용할 큐
        q.add(n);   // 제작의 가장 마지막인 부품번호 n을 넣어줌
        int[] needArr = new int[n+1];
        needArr[n] = 1;
        // do topological sort
        while(!q.isEmpty()){
            // get a number
            int cur = q.poll();
            // check if cur is root node
            if(indeg[cur]==null) continue;
            // for the next
            for(int[] prev : indeg[cur]){
                int num = prev[0];
                int cnt = prev[1];
                // needArr에 기존(cur)에서 요구하는 개수와 새로운 개수를 곱해서 담아줌.
                needArr[num] += (needArr[cur]*cnt);
                // outdegree가 더이상 없으면 타고 올라가야하기 때문에 q에 추가
                if(--outdeg[num]==0) q.add(num);
            }
        }
        // Output
        for(int bNum : basic) System.out.println(bNum+" "+needArr[bNum]);
    }

    // Inner Class - Read & Write values
    private static class MyIO{
        // Variable
        BufferedReader br;
        StringTokenizer st;
        // Constuctor
        public MyIO(){ br = new BufferedReader(new InputStreamReader(System.in));}
        // Method - for input
        public void readLine() throws IOException { st = new StringTokenizer(br.readLine());}
        public int nextInt(){ return Integer.parseInt(st.nextToken());}
    }
}