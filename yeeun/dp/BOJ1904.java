package codingstudy202107.w07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 회차 : 코딩 스터디 7주차
 * 문제 출처 : https://www.acmicpc.net/problem/1904
 * 이름 : 01타일
 * 사용 알고리즘 : 다이나믹 프로그래밍
 * 이 문제의 함정: 2자리면 11이랑 00이 들어간다고 해서 한자리로 1을 넣는 경우와 2자리로 00 혹은 11을 넣는 경우를 합치게 만들었었다.
 * 근데 그렇게 하면 1 넣고 1넣고 00 넣은 1100과 11 넣고 00 넣은 1100 이런식으로 겹치는 것이 생겨버리는 것이다..
 * 그래서 아 그럼 먼저 1로 다 채운 경우의 수를 구하고, 00이 하나 들어간거, 00이 두개 들어간거 00이 세개 들어간거 식으로 합해서 구하는건가 했는데
 * 머리도 터지고 속도도 느려서 아이고 제길 이게모야 했는데 생각해보니 한 자리로 1을 넣어도 그걸 두번 반복하면 11을 만들 수 있으니
 * 따로 11을 연속적으로 넣는 경우를 왜 구했던거지? 라는 생각이 들었다.
 * 1 넣는 경우와 00 넣는 경우를 합치게 만드니까 되는거 보고 아...이걸 헤맸다니 창피하군 하면서 냈는데 세상에 답이 틀렸다는 거였음!!
 * 아니 왜 틀린거야 왜애애ㅐㅐ 하고 보니까 나머지를 구하라는게 있었는데 앞에서 헤매는 바람에 그만 머리에서 새카맣게 사라졌던 것이었다.
 * 제발..이런 실수좀 그만 하자는 뜻으로 박제해둡니다. 실전에서 이러면 어떡하려고 그래! 정신차려 이 친구야.
 */
public class BOJ1904 {

    public static void main(String[] args) throws IOException {
        BOJ1904 main = new BOJ1904();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        System.out.print(main.solution(n));
    }


    private String solution(int val) {
        int[] memoi = new int[1000001];
        Arrays.fill(memoi,-1);
        memoi[1]=1;
        memoi[2]=2;

        int result = this.recursion(memoi, val);
        return String.valueOf(result);
    }

    private int recursion(int[] memoi, int val) {
        if(memoi[val]!=-1){
            return memoi[val];
        }else{
            int result= this.recursion(memoi,val-1)+this.recursion(memoi,val-2);
            result%=15746;
            memoi[val]=result;
            return result;
        }
    }

}
