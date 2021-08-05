package sujeong.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class BOJ2217 {
    public static void main(String[] args) throws IOException {
        //input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(reader.readLine());
        Integer[]intArr = new Integer[n];
        for(int i=0;i<n;i++)intArr[i]=Integer.parseInt(reader.readLine());
        Arrays.sort(intArr, Collections.reverseOrder());

        // logic
        long answer = 0;
        // 내림차순 해놓은 배열에서 로프의 개수(인덱스+1) * 새로운 값 곱한게
        // 병렬연결한 로프가 감당할 수 있는 무게임.
        // 그래서 하나씩 인덱스 이동하면서 큰 값을 answer에 저장해나가면 됨.
        for(int i=0;i<n;i++) answer=Math.max(intArr[i]*(i+1),answer);

        // output
        System.out.println(answer);

    }
}
