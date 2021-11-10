package codingstudy202107.w19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 18주차
 * 문제 출처 : https:/www.acmicpc.net/problem/2668
 * 이름 : 숫자고르기
 * 사용 알고리즘 : ?
 * 첫 번째 숫자는 1부터 n까지 하나씩 존재하지만,
 * 두 번째 숫자는 겹치기도 하고, 첫 번째 숫자의 일부만 가지고 있을 것.
 * 그러므로, 두 번째에 없는 첫 번째 숫자를 계속 제거해주는 것으로 쉽게 풀 수 있다.
 * (두 번째 숫자가 여러 개 있어도, 각각이 지닌 첫 번째 숫자가 유니크해서 이 방법으로 다 걸러짐)
 */
public class BOJ2668 {

    public static void main(String[] args) throws IOException {
        BOJ2668 main = new BOJ2668();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int max = Integer.parseInt(reader.readLine());

        Map<Integer,Integer> data= new HashMap<>();
        for (int i = 1; i <= max; i++) {
            data.put(i, Integer.parseInt(reader.readLine()));
        }

        main.solution(data);
    }

    private void solution(Map<Integer, Integer> data) {
        Map<Integer, Integer> map = new HashMap<>(data);
        Set<Integer> valSet = new HashSet<>(data.values());
        while(!valSet.containsAll(map.keySet())) {
            map.clear();
            for(Integer val: valSet){
                map.put(val,data.get(val));
            }
            valSet.clear();
            valSet.addAll(map.values());
        }

        StringBuilder builder= new StringBuilder();
        builder.append(map.size()).append('\n');
        for(int val: map.keySet()){
            builder.append(val).append('\n');
        }
        builder.setLength(builder.length()-1);
        System.out.print(builder);
    }


}
