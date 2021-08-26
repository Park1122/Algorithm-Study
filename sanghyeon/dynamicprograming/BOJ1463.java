package year_2021.month_08.day_22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BOJ1463 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int startN = Integer.parseInt(br.readLine());

        Map<Integer, Integer> memory = new HashMap<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {startN, 0});

        if(startN==1){
            System.out.println(0);
        }else{
            int answer, n, depth;
            int[] now;
            while(true) {
                now = queue.poll();
                n = now[0];
                depth = now[1];

                if(!memory.containsKey(n) || memory.get(n) > depth) memory.put(n, depth);

                if(memory.get(n) <= depth) {
                    if(n%3==0) {
                        if(n/3==1) {answer=depth+1; break;}
                        else queue.add(new int[]{n/3, depth+1});
                    }
                    if(n%2==0) {
                        if(n/2==1) {answer=depth+1; break;}
                        else queue.add(new int[]{n/2, depth+1});
                    }
                    if(n-1>=0) {
                        if(n-1==1) {answer=depth+1; break;}
                        else queue.add(new int[]{n-1, depth+1});
                    }
                }
            }
            System.out.println(answer);
        }
    }

}
