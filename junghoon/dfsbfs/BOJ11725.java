package dfsbfs;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ11725 {
	/* 트리의 부모 찾기
	 * 
	 * 문제: 루트 없는 트리가 주어진다. 이때, 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성하시오.
	 * 
	 * 입력: 첫째 줄에 노드의 개수 N (2 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N-1개의 줄에 트리 상에서 연결된 두 정점이 주어진다.
	 * 
	 * 출력: 첫째 줄부터 N-1개의 줄에 각 노드의 부모 노드 번호를 2번 노드부터 순서대로 출력한다.
	 * 
	 * 풀이: 방문 배열을 만들어서 배추가 있는 곳에서 방문하지 않았으면 count 1증가후 DFS 탐색
	 *      DFS 탐색에서 4방면을 확인하면서 배추가 있고 방문하지 않으면 DFS 탐색 후에 배추밭을 다 탐색
	 *     
	 * */
    static int n ;
    static ArrayList<Integer>[] list;
    static int[] parents;
    static boolean[] check;
    
    private static void dfs(int v){
        if(check[v]){
            return;
        }
        check[v] =true;
        for (int vv: list[v]) {
            if(!check[vv]){
                parents[vv] = v;
                dfs(vv);
            }

        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        list = new ArrayList[n+1];
        parents = new int[n+1];
        check = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<Integer>();
        }
        for (int j = 1; j <n ; j++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            list[a].add(b);
            list[b].add(a);
        }

        for (int k = 1; k <=n ; k++) {
            if(!check[k]){
                dfs(k);
            }
        }
        for (int i = 2; i <=n ; i++) {
            System.out.println(parents[i]);
        }

    }

}
