package sujeong.dfs_bfs;

import java.util.*;
import java.io.*;

public class BOJ16954 {
    // https://www.acmicpc.net/problem/16954

    // 소요시간 >>
    // 5시간
    // (요즘 푸는 골드레벨 문제들은 생각은 잘나오고, 그 생각이 정답도 맞는데 구현능력이 부족한 것 같다.)
    // (다양한 자료구조와 방식을 익히기 위해 공부를 더 해야겠다..!)
    // 큐에 더하는 것과 벽을 움직이는 순서를 맞추는게 어려웠다.
    // 큐가 끝날때까지 돌아야하는데 큐안에서 벽을 옮기는건 안되니 어떻게 해결할지 생각하는게 어려웠따.

    // 아이디어 >>
    // check Condition -> move Character -> add Charater Point for next Situation -> move wall

    // 에러로그 >>
    // 메모리 초과 - moveWall할때 tmpArr을 만들어서 만든 뒤 orgArr에 넣던 것을 바로 orgArr에서 작업하도록 바꿈.
    // 메모리 초과 - LinkedList -> ArrayDeque로 바꿔서 해결.

    // Constant
    private static final int[][] diff = {{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1},{0,0}};
    private static final char[] newLine = {'.','.','.','.','.','.','.','.'};
    // Attribute
    private static char[][] orgArr;

    // Main
    public static void main(String[] args) throws IOException{
        // Input
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        orgArr = new char[8][8];
        for(int y=0;y<8;y++) orgArr[y] = r.readLine().toCharArray();

        // Output
        System.out.println(logic());
    }

    private static int logic(){
        // Logic
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(7,0));

        while(!q.isEmpty()){
            int size = q.size();
            for(int l=0;l<size;l++){
                Point cur = q.poll();

                // check Condition
                if(orgArr[cur.y][cur.x]=='#') continue; // 벽체크 (벽이 이동되고 캐릭터가 살아있는지 체크를 위함. 큐에 캐릭터 상태를 넣는것보다 벽체크가 빨라서.)
                if(cur.x==7 && cur.y==0) return 1; // 종료 체크

                // move Character
                for(int i=0;i<diff.length;i++){
                    int tmpX=cur.x+diff[i][0];
                    int tmpY=cur.y+diff[i][1];

                    // check next Point for reduce adding to the queue
                    if(!(0<=tmpY && tmpY<8 && 0<=tmpX && tmpX<8)) continue; // 범위체크
                    if(orgArr[tmpY][tmpX]=='#') continue; // 벽 체크

                    // add Queue
                    q.add(new Point(tmpY,tmpX));
                }
            }
            // move wall
            for(int i=7;1<=i;i--)orgArr[i]=orgArr[i-1];
            orgArr[0] = newLine;
        }
        return 0;
    }


    private static class Point{
        int y,x;
        public Point(int y, int x){
            this.x=x;
            this.y=y;
        }
    }
}
