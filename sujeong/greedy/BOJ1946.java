package sujeong.greedy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1946 {
    //https://www.acmicpc.net/problem/1946

    // 소요시간 >>
    // 2시간 15분

    // idea >>
    // 둘다 떨어지면 탈락, 그외엔 합격인거니까
    // doc 1등의 itv보다 큰애들만 살아남음
    // doc 1등의 itv값보다 itv에서 값이 높은 애들만 비교해 나가면 되는거같음.

    // 새로배운것>>
    // 순차적 + 두개의 값을 담음 - > int[x]=y처럼 사용가능
    // (굳이 클래스를 만들거나 int[][]사용하지 않아도 된다.)
    // 정렬에 시간을 쓸 필요가 없다.


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(reader.readLine());

        for(int i=0;i<cases;i++){
            int n=Integer.parseInt(reader.readLine()); // 지원자수
            int[] scoreArr = new int[n+1];
            for (int j = 0; j < n; j++) {
                String[] strArr = reader.readLine().split(" ");
                int doc=Integer.parseInt(strArr[0]);
                int itv=Integer.parseInt(strArr[1]);
                scoreArr[doc]=itv;
            }
            int answer = 1; // doc 1등은 미리 선정됐기에 1로 시작
            int standardItv = scoreArr[1];
            for(int k=2; k<=n;k++){
                if(scoreArr[k]<standardItv){
                    answer++;
                    standardItv=scoreArr[k];
                }
            }
            System.out.println(answer);
        }
    }
}

