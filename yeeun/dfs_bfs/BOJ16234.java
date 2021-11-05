package codingstudy202107.w17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 17주차
 * 문제 출처 : https:/www.acmicpc.net/problem/16234
 * 이름 : 인구 이동
 * 사용 알고리즘 : bfs
 *
 */
public class BOJ16234 {

    public static void main(String[] args) throws IOException {
        BOJ16234 main = new BOJ16234();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken()),
                l = Integer.parseInt(st.nextToken()),
                r = Integer.parseInt(st.nextToken());
        System.out.print(main.solution(reader, n, l, r));
    }

    private int solution( BufferedReader reader,int n, int l, int r) throws IOException {
        Node[][] arr= new Node[n][n];

        StringTokenizer st;
        for (int j = 0; j < n; j++) {
            st = new StringTokenizer(reader.readLine());
            for (int k = 0; k < n; k++) {
                arr[j][k]= new Node(j,k,Integer.parseInt(st.nextToken()));
                if(j>0) {
                    arr[j][k].next.add(arr[j-1][k]);
                    arr[j-1][k].next.add(arr[j][k]);
                }
                if(k>0){
                    arr[j][k].next.add(arr[j][k-1]);
                    arr[j][k-1].next.add(arr[j][k]);
                }
            }
        }
        int count=0;
        while(true){
            boolean[][] visited = new boolean[n][n];
            if(!this.check(l, r, n, visited, arr)){
                count++;
            } else {
                break;
            }
        }

        return count;
    }

    private boolean check(int l, int r,int n, boolean[][] visited, Node[][] arr) {
        List<Node> n_list;
        boolean isDone = true;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j]){
                    Node target= arr[i][j];
                    n_list = new LinkedList<>();
                    n_list.add(target);
                    int sum = dfs(l,r,target,n_list,visited);
                    if(n_list.size() > 1){
                        int avg = sum/n_list.size();
                        for(Node node:n_list){
                            node.val = avg;
                        }
                        isDone= false;
                    }
                }
            }
        }
        return isDone;
    }



    public static int dfs(int l, int r, Node target, List<Node> n_list, boolean[][] visited){
        visited[target.x][target.y] =true;
        int sum= target.val;

        for(Node next: target.next){
            if(!visited[next.x][next.y]){
                int d = target.val - next.val;
                if(d<0)d*=-1;
                if(d >= l && d <= r){
                    n_list.add(next);
                    sum+= dfs(l, r,next,n_list, visited);
                }
            }
        }
        return sum;
    }



    private class Node {
        int x, y, val;
        Vector<Node> next;

        public Node(int i, int j, int val) {
            this.x =i;
            this.y =j;
            this.val=val;
            this.next = new Vector<>();
        }

    }


}
