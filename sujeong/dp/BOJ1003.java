package sujeong.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1003 {
    // https://www.acmicpc.net/problem/1003

    // 소요시간 >>
    // 1시간 (0,1,value를 어떻게 더할까 생각하는데 시간이 많이 소요됨)

    // 아이디어 >>
    // - 시간을 단축하는게 초점인 문제 같다.
    // - fibonacci는 테스트 케이스의 값이 얼마든 각 value의 값은 동일하니 굳이 케이스마다 새롭게 안불러도 됨.
    //   오히려 안부르는게 속도 개선에 도움이 된다.
    // - 0의 값, 1의 값, 피보나치 수열의 값 모두 각각 더해져서 합이 되는 점을 이용해 객체(FibDP)를 만듦.

    // 에러 로그 >>
    // X

    private static FibDP[] dpline;
    private static int targetNum;

    private static FibDP func(int indexNum) {
        if(dpline[indexNum]!=null) {
            return dpline[indexNum];
        }else {
            FibDP first = func(indexNum-1);
            FibDP second = func(indexNum-2);
            dpline[indexNum] = new FibDP(
                    first.getValue()+second.getValue(),
                    first.getZero()+second.getZero(),
                    first.getOne()+second.getOne()
            );
            return dpline[indexNum];
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(reader.readLine());

        FibDP caseZero = new FibDP(0,1,0);
        FibDP caseOne = new FibDP(1,0,1);
        for (int i=0;i<T;i++){
            targetNum=Integer.parseInt(reader.readLine());

            if(targetNum==0) System.out.println(1+" "+0);
            else if(targetNum==1) System.out.println(0+" "+1);
            else{
                dpline = new FibDP[targetNum+1]; //숫자로 인덱스를 쓰려고 함.
                dpline[0] = caseZero;
                dpline[1] = caseOne;
                func(targetNum);
                System.out.println(dpline[targetNum].getZero()+" "+dpline[targetNum].getOne());
            }
        }
    }

    private static class FibDP{
        // attribute
        private int value=-1;
        private int zero=-1;
        private int one=-1;

        // get & set
        public int getValue() { return value;}
        public int getZero() { return zero;}
        public int getOne() { return one;}

        public void setValue(int value){this.value = value;}
        public void setZero(int zero){this.zero = zero;}
        public void setOne(int one){this.one = one;}

        // Constructor
        public FibDP(int value, int zero, int one){
            this.value=value;
            this.zero=zero;
            this.one=one;
        }
    }

}
