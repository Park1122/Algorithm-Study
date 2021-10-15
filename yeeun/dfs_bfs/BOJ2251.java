package codingstudy202107.w14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 14주차
 * 문제 출처 : https://www.acmicpc.net/problem/2251
 * 이름 : 물통
 * 사용 알고리즘 : dfs bfs
 */
public class BOJ2251 {

    public static void main(String[] args) throws IOException {
        BOJ2251 main = new BOJ2251();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer  st = new StringTokenizer(reader.readLine());

        int[] arr = new int[3];

        int i=0;
        arr[i++]= Integer.parseInt(st.nextToken());
        arr[i++]= Integer.parseInt(st.nextToken());
        arr[i]= Integer.parseInt(st.nextToken());

        System.out.print(main.solution(arr));
    }


    private String solution(int[] arr) {
        StringBuilder sb = new StringBuilder();
        Integer[] result =this.bfs(arr);
        Arrays.sort(result);
        for(int i=0; i<result.length; i++){
            if(i>0) sb.append(" ");
            sb.append(result[i]);
        }
        return sb.toString();
    }

    private Integer[] bfs(int[] arr) {
        //c의 값 저장용 배열
        Vector<Integer> result = new Vector<>();
        
        //큐
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, arr[2]});

        //방문 체크용 배열
        boolean[][][] memoi = new boolean[arr[2]+1][arr[2]+1][arr[2]+1];

        while(!queue.isEmpty()){
            int[] target = queue.poll();
            
            //방문 체크.
            if(memoi[target[0]][target[1]][target[2]]) continue; 
            else memoi[target[0]][target[1]][target[2]]=true;
            
            
            if(target[0]==0&&!result.contains(target[2])){
                result.add(target[2]);
            }
            int aCapacity = arr[0]-target[0], bCapacity = arr[1]-target[1], cCapacity = arr[2]-target[2];


            if(target[0]>0){//a에 물이 들어 있다.
                if(bCapacity>0) {//b로 물을 붓는다.
//                    System.out.println("a->b");
                    if (target[0] >= bCapacity) {//b가 꽉차게 붓는다.
                        queue.add(new int[]{target[0]-bCapacity, arr[1], target[2]});
                    } else {//a가 비도록 붓는다.
                        queue.add(new int[]{0, target[1] + target[0], target[2]});
                    }
                }
                if(cCapacity>0) {
                    if (target[0] < cCapacity) {//a가 비도록 붓는다. **c가 꽉차게 부으면 처음에 넣은 것이 되어버림.
//                        System.out.println("a->c");
                        queue.add(new int[]{0, target[1], target[0]+target[2]});
                    }
                }
            }

            if(target[1]>0){//b에 물이 들어 있다.
                if(aCapacity>0) {
//                    System.out.println("b->a");
                    if(target[1]>=aCapacity){//a가 꽉차게 붓는다.
                        queue.add(new int[]{arr[0],target[1]-aCapacity,target[2]});
                   }else{//b가 비도록 붓는다.
                       queue.add(new int[]{target[0]+target[1],0,target[2]});
                   }
                }
                if(cCapacity>0) {
                    if(target[1]<cCapacity){//b가 비도록 붓는다. **c가 꽉차게 부으면, 처음에 넣은 것이 되어버림.
//                        System.out.println("b->c");
                        queue.add(new int[]{target[0],0,target[2]+target[1]});
                    }
                }

            }

            if(target[2]>0){//c에 물이 들어 있다.
                if(aCapacity>0) {
//                    System.out.println("c->a");
                    if(target[2]>=aCapacity){//a가 꽉차게 붓는다.
                        queue.add(new int[]{arr[0],target[1],target[2]-aCapacity});
                    }else{//c가 비도록 붓는다.
                        queue.add(new int[]{target[0]+target[2],target[1],0});
                    }
                }
                if(bCapacity>0) {
//                    System.out.println("c->b");
                    if (target[2] >= bCapacity) {//b가 꽉차게 붓는다.
                        queue.add(new int[]{target[0], arr[1], target[2]-bCapacity});
                    } else {//c가 비도록 붓는다.
                        queue.add(new int[]{target[0], target[1] + target[2], 0});
                    }
                }

            }
            
        }



        return result.toArray(new Integer[result.size()]);
    }

}
