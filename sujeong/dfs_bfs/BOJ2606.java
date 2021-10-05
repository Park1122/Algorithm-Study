package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2606 {
    // https://www.acmicpc.net/problem/2606

    // 소요시간 >>
    // 1시간 ( dfs를 했다가 bfs로 변경하면서 시간이 오래걸림)

    // 아이디어 >>
    // 그래프를 만들어 1번에 연결되어있는 컴퓨터를 찾는다.
    // x와 y의 값이 연결된다면, orgArr에 orgArr[x][y]와 orgArr[y][x]를 1로 변결해준다.
    // 1부터 시작해 지나가는 노드를 모두 큐에 넣어 그 수를 출력한다.

    // 에러 로그 >>
    // X

    // Attribute
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static int[][] orgArr;
    private static int comCount;
    private static Queue<Integer> queue;

    public static void main(String[] args) throws IOException{
        // input
        comCount = Integer.parseInt(reader.readLine());
        orgArr = new int[comCount+1][comCount+1]; // 1부터니까 +1을 해줘야됨.

        // initialize
        int pairCount = Integer.parseInt(reader.readLine());
        for(int  i = 0;i<pairCount;i++){
            String[] strs = reader.readLine().split(" ");
            orgArr[Integer.parseInt(strs[0])][Integer.parseInt(strs[1])] = 1;
            orgArr[Integer.parseInt(strs[1])][Integer.parseInt(strs[0])] = 1;
        }
        queue = new LinkedList<Integer>();

        // Logic
        bfsFunc(1);

        // Output
        System.out.println(queue.size()-1); // 1을 포함한 개수이기때문에 제외

//      // TEST
//        for(int x= 0; x<=comCount ; x++){
//            for(int y=0; y<=comCount ; y++){
//                System.out.print(orgArr[x][y]);
//            }
//            System.out.println();
//        }
//        System.out.println(Arrays.toString(queue.toArray()));
    }

    private static void bfsFunc(int index){
        if(queue.contains(index)) return;
        queue.add(index);

        for(int i=1;i<=comCount;i++){
            if(orgArr[index][i]==1) {
                bfsFunc(i);
            }
        }
    }
}
