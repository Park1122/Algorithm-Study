package sujeong.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1715 {
    // https://www.acmicpc.net/problem/1715

    // 소요시간 >>
    // 5시간 (생각을 코드로 표현하는 과정이 어려웠음)

    // 아이디어 >>
    // 최소의 비교를 하기 위해서는 최소의 값을 더해나가야함.
    // 그렇다면 10+50 을 하고 30을 더하면 1차비교로 (10+50), 2차비교로 (60+30)로 총 150임.
    // 반면에 10+30을 하고 50을 더하면 1차비교로 (10+30), 2차비교로 (40+50)으로 130임.
    // 여기서 알 수 있는 핵심은 바로 1차비교(앞선 비교)의 값을 최소로 만들어야 최소의 값으로 더해나갈 수 있다.

    // 새로배운점 >>
    // 1) 자료구조 공부의 필요성
    // 넣었던 것을 반복해서 넣는 경우에는 리스트말고도 queue나 stack을 생각해보자.
    // 자료구조 공부의 중요성과 필요성을 몸소 느낌.

    // 2) PriorityQueue
    // 그냥 queue는 sort가 귀찮음 -> priorityQueue
    // priorityQueue = (poll을 오름차순으로 함.(comparator로 바꿀수도 있다.) 그냥 출력하면 넣은 순서대로)

    // 3)Queue의 peek, poll
    // 큐에 값을 넣었는데 자꾸 맨앞의 값이 최근에 넣은 값이 나오길래 어려움을 겪었다.
    // peek한다는건 가장 작은걸 가져오는거지 가장 먼저 넣은 값이 아님.

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        PriorityQueue<Long> intList = new PriorityQueue<Long>();
        for(int i =0;i<n;i++) intList.add(Long.parseLong(reader.readLine()));


        long answer = 0;
        while(intList.size()>=2){ // 비교가 가능하려면 2개이상이어야하기 떄문
            long tmp1 = intList.poll();
            long tmp2 = intList.poll();

            //앞의 두개를 더해서 기존의 답에 추가하고
            answer +=tmp1+tmp2;
            // 두 값의 합을 큐에 다시 추가해서 넣어줌. (값의 합을 반영하기 위함)
            intList.add(tmp1+tmp2);
        }
        System.out.println(answer);
    }
}
