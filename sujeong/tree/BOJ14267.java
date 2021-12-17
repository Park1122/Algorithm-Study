package sujeong.tree;

import java.util.*;
import java.io.*;

public class BOJ14267{
    // https://www.acmicpc.net/problem/14267

    // 소요시간 >>
    // 1시간 30분 (아이디어는 빨랐지만 시간초과를 만났었다..)

    // 아이디어 >>
    // 1. 인덱스 별 상사를 읽을 때, 탐색 개선을 위해 HashSet으로 
    //    [상사(입력값)].add(부하(for인덱스))을 empArr에 넣어 직원 간의 연결을 만든다.
    // 2. 각 사람들이 직접 받은 칭찬을 meritArr에 담아 시간초과가 발생할 불상사를 피하고(에러로그 확인..)
    // 3. 1(사장)부터 dfs탐색을 하며 meritArr에 칭찬값들을 누적시켜나간다.
    // 4. meritArr의 값들을 형식에 맞게 가공하여 출력한다.
    
    // 에러로그 >>
    // 시간초과 (19%...)
    // - ArrayList -> HashSet 그러나 시간초과...
    // - bfs -> dfs로 변경 그러나 시간초과 again... (그래도 체점 속도가 빨라졌다 -> dfs사용)
    // - 원인) 편향된 트리에서 상단의 emp가 칭찬을 받은 경우 시간초과가 발생할 수 밖에 없다.
    // - 해결) eachMeritArr을 추가하여 각 사람들의 직접받은 메리트를 다 받은 뒤, 한번에 부하들에게 전달시킴

    // 개선과정 >>
    // HashMap<Integer,Integer>인 eachMeritArr의 사용했을 때 단순히 for문으로 meritArr을 모두 도는 방향이 아닌
    // meritArr의 값을 dfs탐색하며 그때그떄 가져오도록 한다면 속도와 코드 길이를 모두 개선시킬 수 있으리라 판단하여
    // HashMap<Integer,Integer>과 meritArr로 직접받은 칭찬과 누적칭찬을 나누는 방법에서
    // meritArr만 사용하는 방식으로 변경하였더니
    // 메모리 100008 -> 102096 / 시간 904 -> 760 으로 개선할 수 있었다.

    // Variable
    private static HashSet<Integer>[] empArr;
    private static int[] meritArr;

    public static void main(String[] args) throws IOException{
        // Input
        // input basic info
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        // initialize
        empArr = new HashSet[n+1]; // 각 사람들의 부하를 담을 배열
        meritArr = new int[n+1]; // 각 사람들이 받은 칭찬을 담을 배열
       
        // Logic
        // connect employees
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            empArr[i] = new HashSet<>();
            int input = Integer.parseInt(st.nextToken());
            if(input>0) empArr[input].add(i);
        }
        // combine the merits of each employee
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int emp = Integer.parseInt(st.nextToken());
            int merit = Integer.parseInt(st.nextToken());
            meritArr[emp] += merit;
        }
        // adding merit point
        addMerit(1);

        // Output
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++) sb.append(meritArr[i]+" ");
        System.out.println(sb.toString());
    }
    // Method - merit을 더해나가는 함수
    private static void addMerit(int cur){
        for(int e : empArr[cur]){
            meritArr[e] += meritArr[cur];
            addMerit(e);
        }
    }
}