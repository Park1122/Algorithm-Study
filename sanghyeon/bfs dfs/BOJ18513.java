package year_2021.month_11.day_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ18513 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Queue<Integer> fountains = new LinkedList<>();
        HashSet<Integer> houses = new HashSet<>();
        for(int i=0; i<N; i++) {
            int val = Integer.parseInt(st.nextToken());
            fountains.add(val);
            houses.add(val);
        }

        long sum = 0, distance = 1;
        loop: while(!fountains.isEmpty()){
            int size = fountains.size();
            for(int i=0; i<size; i++){
                int fountain = fountains.poll();
                int left = fountain-1, right = fountain+1;
                if(!houses.contains(left)){
                    houses.add(left);
                    fountains.add(left);
                    sum+=distance;
                    if(--K==0) break loop;
                }
                if(!houses.contains(right)){
                    houses.add(right);
                    fountains.add(right);
                    sum+=distance;
                    if(--K==0) break loop;
                }
            }
            distance++;
        }
        System.out.println(sum);
    }
}
