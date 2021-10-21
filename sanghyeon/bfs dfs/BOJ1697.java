package year_2021.month_10.day_14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken()), x2 = Integer.parseInt(st.nextToken());
        int[] line = new int[100001];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(x1);
        boolean stop = false;
        while(!stop){
            Integer x = queue.poll();
            if(x != x2){
                for(int newX : new int[] {x-1, x+1, x*2}){
                    try{ if(line[newX]==0) {line[newX]=line[x]+1; queue.add(newX);} }catch(Exception ignored){}
                }
            }else{
                System.out.println(line[x]);
                stop = true;
            }
        }
    }

}
