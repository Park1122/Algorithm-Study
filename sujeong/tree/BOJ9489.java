package sujeong.tree;

import java.util.*;
import java.io.*;

public class BOJ9489 {
    // https://www.acmicpc.net/problem/9489 

    // 소요시간 >>
    // 8시간 (NoSuchElement...)

    // 아이디어 >>
    // 1. tree에 입력받은 값과 부모를 저장해 인덱싱으로 그룹을 찾아갈 수 있도록 한다.
    // 2. 그리고 이와 함께 k의 위치(kIdx)를 찾아 저장한다.
    // 3. tree를 돌면서 k와 부모는 다르나 조부모는 같은 경우 ans를 증가시켜 그 결과를 StringBuilder에 append한다.
    // 4. StringBuilder의 값을 출력하며 마무리한다.

    // 에러로그 >>
    // NoSuchElement 
    // - 하... 이친구 도대체 뭐때문에 뜨는걸까... 모르겠다... 질문자료도 적어서 감이 안온다.
    // - StringTokenizer의 hasMoreTokens를 사용하고, System.exit(0)으로 종료를 시켜봐도 뜬다.
    // - StringTokenizer로 n과 k를 읽어오는 부분을 split으로 바꿨더니 ArrayIndexOutofBounds로 바뀌었다.
    // - 왜 틀렸었는지 알아냈다.
        // if(n==1 || k==1) {
        //     System.out.println(0);
        //     continue;
        // }
        // 이걸 if(n==0 && k==0) break; 아래에 넣었을 땐 NoSuchElement가 발생했다. 
        // (50%에서 n==1 또는 k==1을 주고 아래 내용을 더 읽게 해야하는 경우가 있는데,  
        // 내 경우에는 이걸 읽지 않아서 어긋나서 NoSuchElement가 발생했던 것이다.)
        // check condition을 모아두는 버릇때문에 이렇게 눈앞에서 고생을 할 줄 몰랐다.

    // 개선과정 >>
    // 어찌저찌 통과를 했는데 왜 통과했는지 그리고 뭐가 달랐는지, 어떤게 문제였는지 알아보자..
    // orgArr,tree,n,k,tIdx를 전역변수에서 지역변수로 변경함.(역시 지역변수가 빠르다)
    // 메모리 258500 -> 245572 / 시간 1268->916
    // StringTokenizer의 딜리미터 파라미터를 지웠더니 메모리는 줄어들고 시간은 늘어났다.
    // 메모리 245572->244984 / 시간 916-> 940 
    // StringTokenizer의 위치를 처음 new할때 사용하던것에서 미리 만들어두고 할당하도록 했더니 메모리와 시간이 늘어났다.
    // 메모리 244984->258192 / 시간 940-> 1316 
    // StringBuilder를 사용하여 출력을 함으로써 메모리와 시간 모두 단축했다.
    // 메모리 245572->244732 / 시간 916-> 852 
    // orgArr을 입력받는 부분과 tree를 만드는 부분을 하나로 합쳤더니 메모리와 시간 모두 증가했다.
    // 메모리 244732->252728 / 시간 852-> 1556 
    // orgArr을 입력받는 부분과 tree를 만드는 부분을 orgArr을 지우는 방향으로 합쳤더니 메모리와 시간 모두 증가했다.
    // 메모리 244732->249800 / 시간 852-> 1220 

    public static void main(String[] args) throws IOException{
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true){
            // get n,k info
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            // end condition            
            if(n==0 && k==0) break;
            // input node values and make tree
            int[][] tree = new int[n][2]; //[노드의 입력 인덱스][0=자신의값,1=부모의 값]
            int kIdx = 0; // k의 인덱스
            int pIdx = 0; // 트리를 만들때 부모 노드가 뭔지 기억하기 위함.
            int prev = -1; // 이전 값을 담기위한 변수
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++){
                // get a node number
                int input = Integer.parseInt(st.nextToken());
                // save k's Idx
                if(input==k) kIdx = i;
                // if not continuous pIdx++
                if(prev+1!=input&&i>1) pIdx++;
                // if first Input -> parent Idx = -1 / other -> pIdx
                tree[i] = new int[]{input,i==0?-1:pIdx};
                // set prev for the next
                prev = input;
            }
            // Logic
            int ans = 0; // 정답을 담을 변수(k의 사촌의 수)
            int kpIdx = tree[kIdx][1]; // k의 부모 인덱스
            for(int idx = 1; idx < n; idx++){
                int iVal = tree[idx][1]; // 현재 노드의 부모 인덱스
                if(tree[kpIdx][0] != tree[iVal][0] // 부모는 다른데
                && tree[kpIdx][1] == tree[iVal][1]) ans++; // 부모의부모(조부모)는 같다면 ans++
            }
            // follow the answer
            sb.append(ans).append("\n");
        }
        // Output
        System.out.print(sb.toString());
    }
}
