package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9095 {
    // https://www.acmicpc.net/problem/9095

    // 소요시간 >>
    // 2시간 30분(문제 분석에 오래걸림)

    // 아이디어 >>
    // 3,2,1순으로 값을 나눠 보면서 카운트를 셈.
    // 14199와 비슷한 느낌(해당 문제는 5와 2로 나눴다면 이경우는 3,2,1로 나눔)
    // -> 중복을 적용 안함 (1+2+1과 1+1+2를 같게 봄... but 문제는 다르게 봄)
    // n의 경우의수는 n-1의 경우 + n-2의 경우 + n-3과 같다는 특징이 있다.
    // 이를 각각에서 계산하거나 1,2,3을 계속 조합해서하면 시간이 오래걸림
    // -> dp가 필요하다. (->중복문제 해결)

    // 에러 로그 >>
    // ArrayIndexOutofBounds 발생 -> 2와 3같이 1,2,3중하나면 값 셋팅부터 불가
    // (에러는 아니지만 dp사용안해서 dp로 저장하게 수정함) func의 else문을 바로 리턴에서 dp저장 후 리턴으로
    // 변경했다. 하지만 시간은 동일하고 메모리, 코드길이는 동일했다.



    private static int[] dpline;
    private static int targetNum;

    private static int func(int indexNum) {
        // dp에있니?
        // 있다면 -> dp의 값을 리턴해줘
        // 없다면 -> 1,2,3 중 하나가
        // ->> 맞다면, 1이면 1, 2면 2, 3이면 4를 넣어줘
        // ->> 아니라면, 쪼개서 아래값으로 넘겨줘

        if(dpline[indexNum]!=0) return dpline[indexNum];
        else {
            dpline[indexNum] = func(indexNum-1)+func(indexNum-2)+func(indexNum-3);
            return dpline[indexNum];
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(reader.readLine());

        for (int i=0;i<n;i++){
            targetNum=Integer.parseInt(reader.readLine());

            dpline = new int[targetNum+1]; //숫자로 인덱스를 쓰려고 함.
            dpline[1] = 1;
            if(targetNum>=2) dpline[2] = 2;
            if(targetNum>=3) dpline[3] = 4;

            System.out.println(func(targetNum));
        }
    }

}
