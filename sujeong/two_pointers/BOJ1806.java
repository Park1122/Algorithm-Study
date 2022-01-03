package sujeong.two_pointers;

import java.util.*;
import java.io.*;

public class BOJ1806 {
    // https://www.acmicpc.net/problem/1806

    // 소요시간 >>
    // 3시간 (투포인트로 처음풀어본 문제였다. 하지만 풀고나니 이전에 풀었던 문제같아서 오래걸린게 속상했다..)

    // 아이디어 >>
    // 부분집합의 개수를 조금씩 늘려가서 S이상이 되는 걸 발견하면 멈추도록 한다.
    // 0) 배열(orgArr)에 입력받은 수열의 값을 인덱스에 따라 넣어준다.
    // 1) Logic에서 l~r까지의 합을 구해나가되, sum이 s(목표값)을 넘으면 l을 오른쪽으로 이동시키고
    //    그 외에는 r을 오른쪽으로 이동시켜 범위를 늘려나간다.
    // 2) 그러다 r이 n이되어(끝에 도달하여) 모든 값을 포함하게되었다면 while문을 종료시킨다. 
    // 3) 1)~2)를 통해 sum이 s를 넘는 경우의 부분집합의 최소크기가 담긴 ans를 
    //    값이 변화되었다면 ans, 아니라면 0을 출력하며 종료한다.

    // 에러로그 >>
    // 시간초과 
    // - dp를 사용해 시간을 줄여보려고 함.
    // 메모리초과 
    // - https://bingorithm.tistory.com/13을 방문하여 모든 반례를 다 통과하였지만 계속 빡구....   
    // - 몇몇 곳을 찾아봤더니 dp로 a, a~a+1, a~a+2 ... 이런식으로 쌓아가지 않고 left,right 방식으로 나눠내려간다.
    //   내가 했던 방식이면 중복되는 경우까지 모든 값을 다 찾아야하지만 새로운 방식은 그렇지 않고 바로바로 값을 가져오거나 계산하기 둘중하나로 해결함..
    // 틀렸습니다
    // - (3%) r과 n을 비교하는 부분을 while문 조건으로 줬었는데 이를 l부터 r까지의 합이 s를 넘는지 체크한 이후로 바꿈
    // - (2?%) ans가 0으로 출력되는 부분을 까먹었었다.. (문제를 잘읽자)

    // 개선과정 >>
    // * n,s,orgArr을 전역변수에서 지역변수로 변경 
    // => 메모리 23640 -> 23288 / 시간 336 -> 284 / 등수 431 -> 31 (오예!!)

    // Variable
    static BufferedReader br;
    static StringTokenizer st;
    // Read Method 
    private static void readLine() throws IOException {st = new StringTokenizer(br.readLine()); }
    private static int nextInt() {return Integer.parseInt(st.nextToken());}
    
    public static void main(String[] args) throws IOException {
        // Input
        br = new BufferedReader(new InputStreamReader(System.in));
        readLine();
        int n = nextInt(); // 수열의 길이
        int s = nextInt(); // 원하는 부분합의 최소 값

        readLine();
        int[] orgArr = new int[n]; // 인덱스에 따른 수열의 값을 저장할 배열
        for(int i=0;i<n;i++) orgArr[i] = nextInt();
        
        // Logic
        // initialize
        int l=0; // 왼쪽인덱스
        int r=0; // 오른쪽인덱스
        int sum=0; // 총합을 담는 변수 
        int ans=Integer.MAX_VALUE; // 가장 짧은 연속길이를 담는 변수
        // searching
        while(true){
            if(sum>=s){ // l부터 r까지의 합이 s를 넘으면
                ans = Math.min(ans, r-l); // 최단연속길이(ans) 갱신
                sum-=orgArr[l++]; // sum에 더했던 왼쪽의 값을 빼내고 왼쪽인덱스(l)를 늘림.
            }else if (r==n) break;
            else sum+=orgArr[r++]; // l부터 r까지의 합이 s를 아직 못넘으면 오른쪽인덱스(r)을 늘리며 합계를 늘림.
        }
        // Output
        // 초기값과 달라진게 없다면 0, 아니라면 ans값을 출력 
        System.out.println(ans!=Integer.MAX_VALUE?ans:0);
    }
}
