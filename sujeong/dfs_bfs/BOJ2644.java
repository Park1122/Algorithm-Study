package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class BOJ2644 {
    // https://www.acmicpc.net/problem/2644

    // 소요시간 >>
    // 1시간

    // 아이디어 >>
    // 두 사람의 촌수를 계산하는 문제이기 때문에 한사람을 기준으로 큐를 사용하는 bfs 방식으로 부모, 자식을 모두 탐색하여 가족 족보를 만들고,
    // 그 곳에 다른 사람이 있는지 여부에 따라 있다면 촌수를 출력하고 없다면 -1을 출력한다.

    // 에러로그 >>
    // X

    // Attribute
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // Main
    public static void main(String[] args) throws IOException{
        // Input
        int n = Integer.parseInt(reader.readLine()); // 전체 사람의 수

        String[] basicInfo = reader.readLine().split(" ");
        int one = Integer.parseInt(basicInfo[0]); // 촌수 계산해야할 사람 1
        int two = Integer.parseInt(basicInfo[1]); // 촌수 계산해야할 사람 2

        int m = Integer.parseInt(reader.readLine()); // 관계의 개수

        int[] family = new int[n + 1]; // 자식(인덱스)의 부모(값)를 담은 배열
        for(int i = 0; i< m; i++){
            String[] familyLine = reader.readLine().split(" ");
            int daddy = Integer.parseInt(familyLine[0]);
            int son = Integer.parseInt(familyLine[1]);
            family[son] = daddy; // 자신의 부모를 저장함. (부모는 한명이니까 가능한 구조)
        }

        // Initialize
        int[] dp = new int[n + 1]; // one의 촌수를 담을 배열
        boolean[] visited = new boolean[n + 1]; // 탐색하며 방문했는지 여부를 담은 배열

        Queue<Integer> queue = new LinkedList<>(); // 촌수 계산을 할 사람을 담을 배열
        queue.add(one); // 한명의 족보만 완성해도 그 족보에 two가 있는지 여부만 판단하면 되기에 one만 미리 넣어줌.

        // Logic
        while(!queue.isEmpty()){
            // 사람 하나를 큐에서 꺼냄.
            int person = queue.poll();

            if(visited[person]) continue; // 방문했다면 넘어감.
            visited[person] = true; // 방문했음을 체크

            // 부모 탐색
            int parent = family[person];
            if(parent!=0 && !visited[parent]) { // 부모가 0이면 자신의 최고 조상이기 때문에 더이상 계산할 수 없어서 제외 && 시간 단축을 위해 방문한 적 있다면 미리 제외.
                queue.add(family[person]); // 큐에 부모를 넣고
                dp[parent] = dp[person] + 1; // dp에 촌수를 1증가시켜 넣는다.
            }

            // 자식 탐색
            for(int child = 1; child<= n; child++){ // 최대 인원이 100밖에 안되기 때문에 모든 사람을 돌며 자식인지 체크
                if(!visited[child]) { //시간 단축을 위해 방문한 적 있다면 미리 제외.
                    queue.add(child); // 큐에 자식을 넣고
                    dp[child] = dp[person]+1; // dp에 촌수를 1증가시켜 넣는다.
                }
            }
        }

        // Output
        System.out.println(dp[two]!=0 ? dp[two] : -1); // 가족 관계가 아니라면 값이 0이기 때문에 그럴 경우만 출력 요구대로 -1로 변경하여 촌수 출력.

    }
}
