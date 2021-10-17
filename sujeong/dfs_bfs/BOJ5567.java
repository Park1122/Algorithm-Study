package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ5567 {
    // https://www.acmicpc.net/problem/5567

    // 소요시간 >>
    // 1시간 (조금 헤맸다...)

    // 아이디어 >>
    // 상근이의 친구과 상근이의 친구의 친구까지만 초대를 하기 때문에,
    // 인풋을 받으면서 1차적으로 상근이의 친구를 sgFriend와 attendees에 넣고,
    // 2차적으로 sgFriend에서 상근이의 친구를 꺼내 상근이의 친구의 친구를 attendees에 넣는다.
    // 결과를 출력할 때에는 Set을 사용하면서 중복이 모두 제거되었기 때문에
    // 0인 경우만 제외하고 상근이를 빼 attendees.size()-1을 출력해준다.

    // 에러로그 >>
    // 틀렸습니다.
    // -> Arrays.stream().filter(value->value==1).toArray()의 결과는 인덱스가 담긴 배열이 아닌
    //      값이 담긴 배열이다.
    // -> 아예 친구가 없을 경우 자신조차 들어가지 않기 때문에 attendees.size()==0이라면 0을 리턴하고
    //      그 외엔 상근이가 포함된 경우를 제외시켜 attendees.size()-1을 해준다.

    // Attribute
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // Main
    public static void main(String[] args) throws IOException{
        // Initialize
        ArrayList<Integer> sgFriends = new ArrayList<>(); // 상근이의 친구를 담는 배열
        Set<Integer> attendees = new HashSet<>(); // 상근이의 친구 + 상근이의 친구의 친구 를 담는 배열

        // Input
        // Variable
        int n = Integer.parseInt(reader.readLine()); // 사람 수
        int m = Integer.parseInt(reader.readLine()); // 연결된 관계 수

        int[][] orgArr = new int[n + 1][n + 1];
        for(int i = 0; i< m; i++){
            String[] line = reader.readLine().split(" ");

            int one = Integer.parseInt(line[0]);
            int other = Integer.parseInt(line[1]);

            if(one==1){ // 문제에서 one<other이라고 했기 때문임.
                sgFriends.add(other);
                attendees.add(other);
            }

            orgArr[one][other]=1;
            orgArr[other][one]=1;
        }

        // Logic
        for(int f : sgFriends){
            for(int i = 1; i<= n; i++){
                int fof = orgArr[f][i];
                if(fof==1){
                    attendees.add(i);
                }
            }
        }

        // Output
        System.out.println(attendees.size()==0?0: attendees.size()-1); // 본인 제거(-1)

    }

}
