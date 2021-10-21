package codingstudy202107.w15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 15주차
 * 문제 출처 : https://www.acmicpc.net/problem/1697
 * 이름 : 숨바꼭질
 * 사용 알고리즘 : dfs bfs
 */
public class BOJ1697 {

    public static void main(String[] args) throws IOException {
        BOJ1697 main = new BOJ1697();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int p1 = Integer.parseInt(st.nextToken()), p2 = Integer.parseInt(st.nextToken());

        System.out.print(main.solution(p1,p2));
    }

    private int solution(int p1, int p2) {
        int[] arr = new int[100001];
        Arrays.fill(arr,-1);

        Queue<Integer> queue = new ArrayDeque<>();
        this.addAndVisitAndSetCount(queue,arr,p1,0);

        return this.bfs(queue,p2,arr);
    }


    private void addAndVisitAndSetCount(Queue<Integer> queue, int[] arr,int index, int count){
        queue.add(index);
        arr[index]=count;
    }

    private int bfs(Queue<Integer> queue,int destination, int[] arr) {
        while(!queue.isEmpty()){
            int index=queue.poll();
            if(index==destination) return arr[index];
                this.ifExistsCheckAndAdd(queue,arr,index+1,arr[index]+1);
                this.ifExistsCheckAndAdd(queue,arr,index-1,arr[index]+1);
                this.ifExistsCheckAndAdd(queue,arr,index*2,arr[index]+1);
        }
        return -1;
    }

    private void ifExistsCheckAndAdd(Queue<Integer> queue, int[] arr, int index, int count) {
        if(index>=0&&index<=100000&&arr[index]==-1){
            this.addAndVisitAndSetCount(queue,arr,index,count);
        }
    }


    private class Node {
        int count;
        boolean visited;

        public void visit() {
            this.visited = true;
        }

        public boolean isVisited() {
            return this.visited;
        }
    }


}
