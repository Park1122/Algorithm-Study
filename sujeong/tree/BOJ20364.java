package sujeong.tree;

import java.util.*;
import java.io.*;

public class BOJ20364{
    // https://www.acmicpc.net/problem/20364

    // 소요시간 >>
    // 1시간

    // 아이디어
    // 문제의 트리구조의 규칙성을 파악하여 각 오리의 목적지를 받아 nowGoal에 저장하고,
    // nowGoal을 2로 나눈 것의 몫으로 목적지에서 루트(1)까지 타고 올라가며 이미 주인이 있는지 owned(주인 목록)에 검색하여
    // 있으면 주인번호를 blockedNum에 저장하여 루트와 가장 가까운 주인있는 땅을 for문의 마지막엔 blockedNum에 담게 된다.
    // 이때 주인 있는 땅을 지난 적 없다면 blockedNum의 초기값인 0이 담겨져 있기 때문에 요구되는 결과대로 0이 출력되고,
    // 주인있는 땅을 지났다면 blockedNum에 저장된 값이 출력된다.
    // 출력을 마친 뒤 주인있는 땅을 지난 적 없을 땐 owned에 nowGoal을 넣어 주인목록을 업데이트한다.

    // 에러로그
    // X

    public static void main(String[] args) throws IOException{
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        HashSet<Integer> owned = new HashSet<>();
        // Logic
        // go ahead ducks!
        for(int i=1;i<=q;i++){
            // set nowGoal
            int nowGoal = Integer.parseInt(br.readLine());
            // check is route already blocked
            int blockedNum = 0;
            for(int tmp=nowGoal;tmp>=2;tmp/=2) if(owned.contains(tmp)) blockedNum = tmp;
            // Output
            System.out.println(blockedNum);
            // for next
            if(blockedNum==0) owned.add(nowGoal);
        }
    }
}