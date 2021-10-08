package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2251 {
    // https://www.acmicpc.net/problem/2251

    // 소요시간 >>
    // 5시간(아이디어 40분, 구현 4시간...)
    // 출력형식과 정렬상태 체크를 안해서 긴 삽질 시간을 가졌다.
    // 저 두가지 요소를 체크하는 대신 어느 부분이 문제일까 고민하며 조금 더 편하고 간결한 클래스와 문법을 찾는 시간이 가졌다.
    // 덕분에 stream의 map, filter, anyMatch, 예약어 assert, TreeSet의 descendingIterator등 새롭게 알게된 함수가 많았던 문제...
    // + 기록하고 싶은 알게된 것
        // * contains를 쓰는 것보다 visited를 쓰는게 시간적으로 여유있다.
        // * TreeSet = 중복 불가 + 순서 보장X + 오름차순 정렬 -> 해당 문제에 어울리는 자료구조 / iterator와 hasNext, next로 편리하게 사용가능
            // 유사한 것
            // HashSet = 중복 불가 + 순서 보장X
            // LinkedHashSet = 중복 불가 + 순서 보장(입력 순서 데이터 관리)
            // 참고 링크 : https://blog.naver.com/PostView.nhn?blogId=heartflow89&logNo=220994601249&parentCategoryNo=&categoryNo=28&viewDate=&isShowPopularPosts=false&from=postView


    // 아이디어 >>
    // * 6가지의 이동방식이 존재한다.(a->b,c / b->a,c / c->a,b)
    // * 이들은 주는 물통과 받는 물통의 상태에 따라 그 값이 바뀐다.
        // 주는 물통의 양 + 받는 물통의 양 <= 받는 물통의 크기
            // -> 주는물통 = 0
            // -> 받는 물통 = 주는 물통의 양 + 받는 물통의 양
        // 주는 물통의 양 + 받는 물통의 양 > 받는 물통의 크기
            // -> 주는물통 = 주는 물통의 양 + 받는 물통의 양 - 받는 뭁통의 크기
            // -> 받는 물통 = 받는 물통의 크기
    // * 처음에 생각한 주의해야할 3가지의 체크요소는 다음과 같았다.
        // 1) 주는 물통이 비어있는가? -> 계산양이 늘어나지만 이걸 체크하는 것 또한 계산 시간이 들기 때문에 상관 X
        // 2) 받는 물통이 이미 full인가? -> 이 경우 계산 전과 후가 같은데, bfs방식이기 때문에 visited를 통해 3번 요소와 퉁칠수 있다.
        // 3) 이미 bucketlist에 있는가? -> contains를 쓰는건 비효율적, visited를 사용해 해결.

    // 에러로그 >>
    // * 틀렸습니다
    // - 출력형식 오류..(띄어쓰기를 줄바꿈으로 처리함)
    // - TreeSet의 정렬이 제대로 되었는지 8 9 10에서 확인할 수 없었다. (지금보니 8 9 10은 운좋게 a+b+c의 정렬과 c의 정렬 순서가 같았다)

    // Attribute
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<String> bucketlist = new ArrayList<String>(); // 물통 상태의 종류를 담아두는 용도
    private static boolean[][][] visited; // 이미 bucketlist에 추가된 값인지 체크하는 용도
    private static Queue<String> subBuck; // 물통의 상태들을 담아두는 용도

    // Variable
    private static int maxA,maxB,maxC; // 각각 a,b,c의 물통 크기

    // Main
    public static void main(String[] args) throws IOException{
        // Input
        String[] strArr = reader.readLine().split(" ");
        maxA = Integer.parseInt(strArr[0]);
        maxB = Integer.parseInt(strArr[1]);
        maxC = Integer.parseInt(strArr[2]);

        // Initialize
        visited = new boolean[maxA+1][maxB+1][maxC+1];
        subBuck = new LinkedList<String>();

        // Logic
        subBuck.add(makeString(0,0,maxC));
        while (!subBuck.isEmpty()){
            // Queue에서 값 가져오기
            String one = subBuck.poll();

            // a,b,c setting
            String[] line = one.split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            int c = Integer.parseInt(line[2]);

            // 방문 여부 확인
            if(visited[a][b][c]) continue ;
            // a가 0인것만 list에 추가
            if(a==0) bucketlist.add(one);
            // 방문 기록
            visited[a][b][c] = true;

            // a->b
            subBuck.add(a+b>maxB ? makeString(a+b-maxB,maxB,c) : makeString(0,a+b,c));
            // a->c
            subBuck.add(a+c>maxC ? makeString(a+c-maxC,b,maxC) : makeString(0,b,a+c));
            // b->a
            subBuck.add(b+a>maxA ? makeString(maxA,b+a-maxA,c) : makeString(b+a,0,c));
            // b->c
            subBuck.add(b+c>maxC ? makeString(a,b+c-maxC,maxC) : makeString(a,0,b+c));
            // c->a
            subBuck.add(c+a>maxA ? makeString(maxA,b,c+a-maxA) : makeString(c+a,b,0));
            // c->b
            subBuck.add(c+b>maxB ? makeString(a,maxB,c+b-maxB) : makeString(a,c+b,0));
        }

        // Output
        // 세번째 물통만 담아 정렬
        TreeSet<Integer> ansSet = new TreeSet<>();
        for(String str : bucketlist) ansSet.add(Integer.parseInt(str.split(" ")[2]));

        // 정답 출력을 위해 가공
        String ansStr = ""; // 정답을 담을 String 그릇
        for(int i : ansSet) {
            ansStr+=(i+" ");
        }
        System.out.println(ansStr.stripTrailing()); // 끝쪽 공백 제거

    }

    // Method
    private static String makeString(int a, int b, int c){return a+" "+b+" "+c;}

}
