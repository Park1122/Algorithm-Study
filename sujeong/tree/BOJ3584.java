package sujeong.tree;

import java.util.*;
import java.io.*;

public class BOJ3584  {
    // https://www.acmicpc.net/problem/3584
    
    // 소요시간 >>
    // 40분 (생각보다 너무 빨리 통과해서 놀랐음. 골드보단 실버아닐까 생각 들 정도...?)
    
    // 아이디어 >>
    // 입력받은 값을 트리로 만드는게 가장 어려울 문제같다.
    // 1. 입력받은 값을 이용하여 트리로 만든다. (노드의 부모를 노드가 알고있는 방향으로 짜야할 듯)
    // 2. 입력받은 두 정점 중 한 정점의 부모 노드들을 모두 찾는다.
    // 3. 다른 한 정점의 부모 노드를 따라가며 두 정점이 만나는 가장 가까운 지점(깊이가 가장 큰 공통 조상)을 발견하면 탐색을 종료하고 그 값을  출력한다.
    
    // 에러로그 >>
    // 한번에 맞췄는데, 속도 개선하고자 값을 검색하는 부분을 바꾸려고 a의 조상을 담는 리스트를 
    // ArrayList에서 HashSet으로 변경함.
    
    public static void main(String[] args) throws IOException{
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // get testcase
        int T = Integer.parseInt(br.readLine());
        for(int test = 0; test<T;test++){
            // get node nums
            int N = Integer.parseInt(br.readLine());
            // initialize lines            
            int[] orgArr = new int[N+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int l=1;l<N;l++){
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                orgArr[c] = p;
                st = new StringTokenizer(br.readLine());
            }
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            // Logic
            // find a's ancestor
            HashSet<Integer> aAnc = new HashSet<>();
            int tmp=a;
            int end=a;
            while(end!=0){
                aAnc.add(tmp);
                end = tmp;
                tmp = orgArr[end];
            }
            // find b's ancestor & check is in aAnc(a' ancestor list)
            tmp=b;
            end=b;
            while(end!=0){
                if(aAnc.contains(tmp)) break;
                end = tmp;
                tmp = orgArr[end];
            }
            
            // Output - print common ancestor number
            System.out.println(tmp);
        }
    }
}
