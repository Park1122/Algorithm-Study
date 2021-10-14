package codingstudy202107.w15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 15주차
 * 문제 출처 : https://www.acmicpc.net/problem/7562
 * 이름 : 나이트의 이동
 * 사용 알고리즘 : dfs bfs
 */
public class BOJ7562 {

    public static void main(String[] args) throws IOException {
        BOJ7562 main = new BOJ7562();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) builder.append('\n');
            int len = Integer.parseInt(reader.readLine());
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int cX = Integer.parseInt(st.nextToken()), cY = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(reader.readLine());
            int dX = Integer.parseInt(st.nextToken()), dY = Integer.parseInt(st.nextToken());
            builder.append(main.solution(len, cX, cY, dX, dY));
        }

        System.out.print(builder.toString());
    }

    private int solution(int len, int cX, int cY, int dX, int dY) {
        Node[][] chessBoard = new Node[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                chessBoard[i][j] = new Node(i, j);
            }
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(chessBoard[cX][cY]);
        this.addAndVisitAndSetCount(queue,chessBoard[cX][cY],0);
        return this.bfs(chessBoard,queue, dX, dY);
    }

    private void addAndVisitAndSetCount(Queue<Node> queue,Node node, int count){
        if(!node.visited) {
            queue.add(node);
            node.visit();
            node.count = count;
        }
    }

    private int bfs(Node[][] chessBoard, Queue<Node> queue, int dX, int dY) {
        while (!queue.isEmpty()) {
            Node target = queue.poll();
            int x=target.x, y=target.y, count=target.count;
//            System.out.println(x+" "+y+" 카운트: "+count);
            if(x==dX&&y==dY)return count;

            if(x>0){//좌측에 자리있어요
                if(y>1){//위쪽 두번째 자리있어요
                    this.addAndVisitAndSetCount(queue,chessBoard[x-1][y-2],count+1);
                }
                if(y< chessBoard.length-2){//아래쪽 두번째 자리있어요
                    this.addAndVisitAndSetCount(queue,chessBoard[x-1][y+2],count+1);
                }
            }


            if(x>1){//좌측 두번째 자리있어요
                if(y>0){//위쪽 자리있어요
                    this.addAndVisitAndSetCount(queue,chessBoard[x-2][y-1],count+1);
                }
                if(y< chessBoard.length-1){//아래쪽 자리있어요
                    this.addAndVisitAndSetCount(queue,chessBoard[x-2][y+1],count+1);
                }
            }
            if(x< chessBoard.length-1){//우측에 자리있어요
                if(y>1){//위쪽 두번째 자리있어요
                    this.addAndVisitAndSetCount(queue,chessBoard[x+1][y-2],count+1);
                }
                if(y< chessBoard.length-2){//아래쪽 두번째 자리있어요
                    this.addAndVisitAndSetCount(queue,chessBoard[x+1][y+2],count+1);
                }
            }
            if(x< chessBoard.length-2){//우측 두번째 자리있어요
                if(y>0){//위쪽 자리있어요
                    this.addAndVisitAndSetCount(queue,chessBoard[x+2][y-1],count+1);
                }
                if(y< chessBoard.length-1){//아래쪽 자리있어요
                    this.addAndVisitAndSetCount(queue,chessBoard[x+2][y+1],count+1);
                }
            }

        }
        return -1;
    }


    private class Node {//귀찮아서 이제 encapulation 안할래오 인터넷에도 하는 사람 잘 없더라구..
        int x, y;
        boolean visited;
        int count;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void visit() {
            this.visited = true;
        }

        public boolean isVisited() {
            return this.visited;
        }
    }


}
