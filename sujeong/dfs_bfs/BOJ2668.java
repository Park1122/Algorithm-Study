package sujeong.dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ2668 {
    // https://www.acmicpc.net/problem/2668

    // 소요시간 >>
    // 3시간 30분

    // 아이디어 >>
    // DFS방식으로 풀면 좋을 것 같다.
    // 배열의 모든 인덱스를 돌면서 탐색 전 해당 idx의 방문여부를 체크하고,
    // idx의 val을 쭉 따라가다가 첫 idx와 같은 걸 발견하면 해당 idx를 ans에 담은 뒤
    // 이미 방문했던(첫지점 포함) 지점을 방문하면 탐색을 빠져나오고
    // 아니라면 계속 탐색을 진행한다.

    // 에러로그 >>
    // X

    // 몇가지 테스트해본것 >>
    // HashSet은 메모리/시간이 14260/124 였고, boolean[]은 14224/132였다. 확실히 bool만 따질거면 hashset이 빠른 것 같다.
    // goal을 지역변수에서 전역변수로 뺐더니 메모리/시간이 14260/124에서 14336/136으로 변했다.
    // 전에 HashSet new를 전역상태에서 한 것이 지역 상태에서 한 것보다 시간,메모리가 컸는데 이또한 비슷한 것 같다.

    // Variable
    private static int n; // 배열의 사이즈
    private static int[] orgArr; // 원래 입력받은 표를 저장하는 배열
    private static TreeSet<Integer> ans; // 정답을 저장하고 자동정렬을 하기위해 사용한 셋(정렬기능이 있음)
    private static HashSet<Integer> vst; // 방문여부를 담기위해 사용한 셋(탐색이 빠름)

    // Main
    public static void main(String[] args) throws IOException{
        // Input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        orgArr = new int[n+1];
        for(int i=1;i<=n;i++) orgArr[i] = Integer.parseInt(reader.readLine());

        // Logic
        ans = new TreeSet<>();
        vst = new HashSet<>();
        for(int i=1;i<=n;i++){
            vst.add(i);
            searching(i,orgArr[i]);
            vst.remove(i);
        }

        // Output
        System.out.println(ans.size());
        for(int a : ans) System.out.println(a);
    }
    private static void searching(int goal, int val){
        // if find goal value -> ans에 값 추가하기.
        if(orgArr[val]==goal) ans.add(goal);
        // check condition(방문여부)
        if(vst.contains(orgArr[val])) return;
        // 임시 방문 체크 후 orgArr[val]의 dfs탐색을 계속하고 돌아와 방문해제를 한다.
        vst.add(orgArr[val]);
        searching(goal, orgArr[val]);
        vst.remove(orgArr[val]);
    }
}