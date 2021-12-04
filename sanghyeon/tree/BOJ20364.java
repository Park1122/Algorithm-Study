package year_2021.month_11.day_30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20364 { // 커밋 잘못했다...

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int landCount = Integer.parseInt(st.nextToken());
        int birdCount = Integer.parseInt(st.nextToken());

        boolean[] occupied = new boolean[landCount+1];
        for (int i = 0; i < birdCount; i++) {
            int landIndex = Integer.parseInt(br.readLine());
            int temp = landIndex;
            int blockIndex = -1;
            while(temp>=2){
                if(occupied[temp]) blockIndex=temp;
                temp/=2;
            }
            if(blockIndex==-1){
                occupied[landIndex] = true;
                System.out.println(0);
            }else{
                System.out.println(blockIndex);
            }
        }
    }
}
