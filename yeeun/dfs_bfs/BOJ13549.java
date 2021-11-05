package codingstudy202107.w18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 18주차
 * 문제 출처 : https:/www.acmicpc.net/problem/13549
 * 이름 : 숨바꼭질3
 * 사용 알고리즘 : bfs
 * 개인적으로 재밌었던 문제!!
 * 이건 이렇게 해야 한다.
 * 1. 하나를 큐에 넣는다.
 * 2. (dfs)그것의 2의 배수들을 큐에 넣는다.
 * 3. 그것의 +1과 -1을 큐에 넣는다.(각각 1로 돌아감)
 */
public class BOJ13549 {
    public static void main(String[] args) throws IOException {
        BOJ13549 main = new BOJ13549();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int subin = Integer.parseInt(st.nextToken()),
                sister = Integer.parseInt(st.nextToken());
        int result = main.solution(subin, sister);
        System.out.print(result);
    }

    private int solution(int subin, int sister) {
        Queue<Integer> queue = new ArrayDeque<>();
        int[] arr = new int[100001];
        Arrays.fill(arr, -1);
        arr[subin] = 0;
        queue.add(subin);
        this.dfs(arr,subin,queue,sister);
        return this.mix(arr, queue, sister);
    }

    private void dfs(int[] arr,int target, Queue<Integer> queue,int sister){
        int time=arr[target];
        int pastDistance = Math.abs(sister-target);

        //dfs 순간이동 계산하기.
        int dfsTarget = target * 2;
        while (true) {
            if (dfsTarget <= 100000&&arr[dfsTarget]==-1) {//가능한 인덱스범위이고, 들른 적 없을 때.
                int currentDistance = Math.abs(sister - dfsTarget);
                if (currentDistance > pastDistance) break; //dfs를 통해 멀어지고 있음 중지!

                //시간 저장하고, 다음으로 queue에 넣기.
                arr[dfsTarget]=time;
                queue.add(dfsTarget);

                //다음으로 이동할 준비하기
                dfsTarget*=2;
                pastDistance=currentDistance;
            }else break;
        }

    }

    private int mix(int[] arr, Queue<Integer> queue, int sister) {
        while (!queue.isEmpty()) {
            int target = queue.poll();
            int time = arr[target];
//            System.out.println(target+"->>"+time);
            if(target==sister)return time;

            int next1 = target - 1;
            int next2 = target + 1;

            if (next1 >= 0 && arr[next1] == -1) {//큐에 넣으면서 순간이동들 dfs로 추가
                arr[next1] = time + 1;
                queue.add(next1);
                this.dfs(arr,next1,queue,sister);
            }
            if (next2 <= 100000  && arr[next2] == -1) {//큐에 넣으면서 순간이동들 dfs로 추가
                arr[next2] = time + 1;
                queue.add(next2);
                this.dfs(arr,next2,queue,sister);
            }
        }
        return -1;
    }

}
