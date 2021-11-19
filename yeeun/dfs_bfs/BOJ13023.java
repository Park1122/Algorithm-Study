package codingstudy202107.w20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 20주차
 * 문제 출처 : https:/www.acmicpc.net/problem/2668
 * 이름 : ABCDE
 * 사용 알고리즘 : dfs
 */
public class BOJ13023 {

    public static void main(String[] args) throws IOException {
        BOJ13023 main = new BOJ13023();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        int headCount = Integer.parseInt(st.nextToken());
        int relationshipCount = Integer.parseInt(st.nextToken());

        Set<Integer>[] arr = new HashSet[headCount];

        for (int i = 0; i < relationshipCount; i++) {
            st = new StringTokenizer(reader.readLine());
            int person1 = Integer.parseInt(st.nextToken());
            int person2 = Integer.parseInt(st.nextToken());
            if (arr[person1] == null) arr[person1] = new HashSet<>();
            if (arr[person2] == null) arr[person2] = new HashSet<>();
            arr[person1].add(person2);
            arr[person2].add(person1);
        }

        System.out.print(main.solution(arr));
    }

    private int solution(Set<Integer>[] data) {
        int nodeIndex = 0, max = 0;
        for (Set<Integer> node : data) {
            boolean[] visited = new boolean[data.length];
            visited[nodeIndex++]=true;
            max = Math.max(max, this.countMaxRelationshipLength(data, visited, node));
            if (max >= 5) return 1;
        }
        return 0;
    }

    private int countMaxRelationshipLength(Set<Integer>[] data, boolean[] visited, Set<Integer> node) {
        int count = 1, max = 0;

        if (node != null)
            for (int nextIndex : node) {
                if (!visited[nextIndex]) {
                    visited[nextIndex] = true;
                    max = Math.max(max, this.countMaxRelationshipLength(data, visited, data[nextIndex]));
                    visited[nextIndex] = false;
                    if(max>=5)return max;
                }
            }

        return count + max;
    }


}
