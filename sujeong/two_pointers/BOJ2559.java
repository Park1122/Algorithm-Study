package sujeong.two_pointers;

import java.io.*;
import java.util.*;

public class BOJ2559 {
    // https://www.acmicpc.net/problem/2559

    // 소요시간 >>
    // 3시간 (이중 for문 방식으로도 충분히 값이 잘 나오는데 무슨 반례가 있는건지 모르겠다.. 방식을 바꿔서 해결하긴 했지만 숫자 범위 문제도 아니라서 ,,, )

    // 아이디어 >>
    // 문제핵심 - n일 중에 연속되는 k일의 온도 합이 가장 컸을때의 온도합구하기
    // 풀이과정은 다음과 같다.
    // 0) orgArr에 입력받은 수열의 값을 담으며 0~k까지의 합을 max에 미리 구한다.
    // 1) while문을 돌며 왼쪽인덱스(left)와 오른쪽인덱스(right)를 한칸 옮겼을때의 값을 tmp에 담고
    // 2) tmp와 max를 비교해 더 큰 값을 max에 넣어 연속된 k개의 최대합(max)을 갱신해간다.
    // 3) 오른쪽인덱스가 끝에 도달하면 while문을 빠져나와 max를 출력하고 종료한다.

    // 에러로그 >>
    // 틀렸습니다(40%) - left값을 이동하는 for문 내에 left~left+k까지의 합을 구하는 for문으로 구성했을 때 문제가 있던것 같다(로직이 옳지않아보임..)
    // 메모리초과 - 짧게 코딩해보고싶어져서 Array.stream방식들을 이용했더니 메모리초과가 떴다... (원상복귀함)

    // 개선과정 >>
    // * readLine()의 사용이 빈번하지 않아 main에 다시 넣어봤다. (안좋아져서 원상복구)
    // => 메모리 23696 -> 23956 / 시간 268 -> 272
    // * tmp의 계산을 left, right 마다 각 줄로 둬 계산했다가 한줄로 줄임. (메모리는 늘었지만 시간은 줄어서 유지)
    // => 메모리 23696 -> 23808 / 시간 268 ->260 


    // Variable
    static BufferedReader br;
    static StringTokenizer st;
    // Method - for read input values
    private static void readLine() throws IOException{st=new StringTokenizer(br.readLine());}
    private static int nextInt() {return Integer.parseInt(st.nextToken());}
    // Main
    public static void main(String[] args) throws IOException{
        // Input
        br = new BufferedReader(new InputStreamReader(System.in));
        readLine();
        int n = nextInt(); // 온도를 측정한 전체날짜의 수
        int k = nextInt(); // 연속되는 날의 수

        readLine();
        int max=0; // n개의 수 중 연속된 k개의 합중 가장 큰 값을 담을 변수
        int[] orgArr = new int[n];
        for(int i=0;i<n;i++) {
            orgArr[i] = nextInt();
            if(i<k) max+=orgArr[i]; // 미미 수열의 값을 더해 max를 초기화한다.
        }

        // Logic
        int left = 0; // 왼쪽 인덱스
        int right = k; // 오른쪽 인덱스
        int tmp = max; // 임시 계산된 총합값을 담을 변수
        while(right<n){ // 오른쪽인덱스가 n(끝)이 되기전까지 
            tmp+=(orgArr[right++]-orgArr[left++]);
            // tmp-=orgArr[left++]; // 이전 tmp에서 제일 왼쪽값을 빼고 왼쪽인덱스를 오른쪽으로 한칸 옮기고  
            // tmp+=orgArr[right++]; // 이전 tmp에서 세로운 오른쪽값을 더하고 오른쪽인덱스를 오른쪽으로 한칸 옮김 
            max = Math.max(tmp,max); // tmp와 기존의 값(max)를 비교해 더 큰값으로 max를 갱신해나감
        }

        // Output
        System.out.println(max);

    }
    
}
