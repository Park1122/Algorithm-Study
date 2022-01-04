package sujeong.two_pointers;

import java.io.*;
import java.util.*;

public class BOJ2003 {
    // https://www.acmicpc.net/problem/2003

    // 소요시간 >>
    // 40분 (부분합 문제(1806)를 이미 풀고와서 쉽게 풀었다.)
    
    // 아이디어 >>
    // 0) orgArr에 인덱스에 따라 수열의 값을 저장한다.
    // 1) 왼쪽인덱스와 오른쪽인덱스를 지정해 두 인덱스 사이의 합을 sum에 더하는데,
    //    이 때 sum이 m과 같으면 cnt를 증가시키고, 같거나 크면 왼쪽인덱스를 오른쪽으로 이동시켜 범위를 줄인다.
    // 2) 그리고 right가 끝에 도달했다면 while문을 나오고, 그게 아니라면 오른쪽인덱스를 오른쪽으로 이동시켜 범위를 늘린다.
    // 3) while문을 나오면 cnt를 출력한뒤 종료한다.

    // 해당문제의 while문 속 조건문들의 순서와 종류가 중요한데,
    // 순서의 경우 n과 right가 같아질 경우가
    // sum와 m을 비교하는 if문보다 늦게 체크해야 right가 끝에 도달했지만 left가 더 오른쪽으로 이동해 맨마지막 값을 포함한채 가장 적은 범위를 갖는 경우도 포함할수있고
    // right를 추가해주는 else문보다 먼저 체크해야 탐색을 다 하되 인덱스 범위를 넘어가지 않기 때문이다.

    // 에러로그 >>
    // X

    // 개선과정 >>
    // BufferedWriter를 사용했지만 메모리는 늘고 시간은 그대로여서 변경하지않음.
    // => 메모리 15004 -> 15100 / 시간 152 -> 152

    // Variable
    static BufferedReader br;
    static StringTokenizer st;
    // Method - for read input
    private static void readLine() throws IOException{st=new StringTokenizer(br.readLine());}
    private static int nextInt() {return Integer.parseInt(st.nextToken());}
    // Main
    public static void main(String[] args) throws IOException{
        // Input
        br = new BufferedReader(new InputStreamReader(System.in));
        readLine();
        int n = nextInt(); // 숫자의 개수
        int m = nextInt(); // 원하는 부분합의 크기

        readLine();
        int[] orgArr = new int[n]; // 입력받은 수열을 저장하는 배열
        for(int i=0;i<n;i++) orgArr[i] = nextInt();


        // Logic
        int left=0, right=0; // 왼쪽인덱스, 오른쪽인덱스
        int sum=0, cnt=0; // 인덱스 범위의 합을 담은 변수, 부분합이 m인 집합의 개수
        while(true){
            if(sum>=m){ // sum이 원하는 부분합(m)보다 크거나 같아지면
                if(sum==m) cnt++; // 이때 sum==m이면 부분집합의 개수(cnt)를 늘려줌.
                sum-=orgArr[left++]; // 그 후 sum에서 왼쪽 인덱스를 오른쪽으로 한칸 이동시켜줌.
            } else if(right==n)break; // 오른쪽인덱스(right)가 끝까지 이미 도달한 상태라면 break문으로 종료함
            else sum+=orgArr[right++]; // 부분합이되지도 않았고 탐색을 마치지 않았다면 오른쪽인덱스를 오른쪽으로 한칸 이동시키며 sum에 해당 인덱스의 수열값을 추가한다.
        }


        // Output
        System.out.println(cnt);
    }
}
