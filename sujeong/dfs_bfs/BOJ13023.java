package sujeong.dfs_bfs;

import java.util.*;
import java.io.*;

public class BOJ13023 {
    // https://www.acmicpc.net/problem/13023

    // 소요시간 >>
    // 1시간 (DFS라고 생각이 난 뒤로는 꽤나 쉽게 완성했다.)
    //(오예 4등했다 -> int[][]로 하려다가 ArrQayList로 친구들만 따로 관리하게 바꾼게 시간 단축의 큰 역할을 한듯하다.)

    // 아이디어 >>
    // dfs 문제같다.
    // dfs로 i=0부터 시작해 연속 5번을 친구로 이어질 수 있는지 체크하여 이어지면 1을 출력하고 종료한다.
    // 그전에 이미 탐색한 친구가 나오면 이전으로 돌아와 다른 친구를 탐색한다.
    // (a->b->c->a가 되면 a->b->c->?로 a가 아닌 다른 친구로 연결)

    // 에러로그 >>
    // 런타임에러(NullPointer) -> 초반에 Person을 생성하여 orgArr 배열에 넣어준다.

    // Variable
    private static Person[] orgArr;
    private static boolean[] visited;

    // Main
    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        orgArr = new Person[n];
        for(int i = 0; i< n; i++) orgArr[i] = new Person(i);

        for(int i = 0; i< m; i++){
            st = new StringTokenizer(reader.readLine());
            int one = Integer.parseInt(st.nextToken());
            int other = Integer.parseInt(st.nextToken());
            orgArr[one].friends.add(orgArr[other]);
            orgArr[other].friends.add(orgArr[one]);
        }

        // Logic
        visited = new boolean[n];
        for(int i = 0; i< n; i++){
            visited[i] = true;
            dfs(i,1);
            visited[i] = false;
        }

        // Output
        System.out.println(0);
    }

    private static void dfs(int nowNum, int index){
        // end condition (이어지는 친구 5명이 되면, 1을 출력하고 종료)
        if(index == 5) {
            System.out.println(1);
            System.exit(0);
        }
        // 현재 넘버(nowNum)의 친구들을 돌며 visited를 이용해 해당친구와 연결되지 않았던 관계로 dfs를 해나감.
        for(Person p : orgArr[nowNum].friends){
            if (visited[p.idx]) continue;
            visited[p.idx] = true;
            dfs(p.idx,index+1);
            visited[p.idx] = false;
        }
    }

    // Inner class
    private static class Person{
        // Variable
        int idx;
        ArrayList<Person> friends;
        // Constructor
        public Person(int idx){
            this.idx = idx;
            friends = new ArrayList<>();
        }
    }
}