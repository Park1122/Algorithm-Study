package sujeong.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class BOJ1541 {
    // 아이디어 >>
    // 최소를 구해야함 -> 마이너스를 크게해야함 -> 마이너스 부호부터 다음 마이너스 부호가 나올때까지 값을 더해
    // 마이너스를 가진 값의 크기를 크게하면, 숫자가 작아짐.


    private static int answer; // 정답
    private static String expression; // 식

    public static void input() throws IOException{
        //input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        expression = reader.readLine();
    }

    private static int calculator(String subExp) {
        int retVal = 0;
        // "+"는 정규표현식으로 앞문자가 하나 이상인 경우를 의미하지 addition의 의미가 X
        // 따라서 대체제인 \\+를 사용해야함. (\\는 정규표현식이 아니라는 의미)
        // 혹은 [+]을 사용하면 된다. (split으로 사용할 수 없는 특수문자들이 있다... ex. *,$,|)
        // 추가정보 ) 정규표현식 체크는 String.matches()를 사용하면 된다.
        String[] splitedExp = subExp.split("\\+");
        for(String s:splitedExp) retVal+=Integer.parseInt(s);
        return retVal;
    }

    public static void func(){
        String[] splitedExp = expression.split("-");
        for(int i=0;i<splitedExp.length;i++){
            int subExp = calculator(splitedExp[i]);
            if(i==0) answer=subExp;
            else answer-=subExp;
        }
    }


    public static void main(String[] args) throws IOException {
        input();
        func();
        System.out.println(answer);
    }
}
