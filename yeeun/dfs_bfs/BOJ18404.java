package codingstudy202107.w15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 15주차
 * 문제 출처 : https://www.acmicpc.net/problem/18404
 * 이름 : 현명한 나이트
 * 사용 알고리즘 : dfs bfs
 */
public class BOJ18404 {

    public static void main(String[] args) throws IOException {
        BOJ18404 main = new BOJ18404();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(reader.readLine());
        int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());

        int[][] enemies = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            enemies[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        System.out.print(main.solution(n, m, x, y, enemies));
    }

    private String solution(int n, int m, int x, int y, int[][] enemies) {
        Node[][] chessBoard = new Node[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                chessBoard[i][j] = new Node(i, j);
            }
        }

        Queue<Node> queue = new ArrayDeque<>();

        this.addAndVisitAndSetCount(queue,chessBoard[x][y],0);

        return this.bfs(queue, chessBoard, enemies.length, enemies);
    }

    private void addAndVisitAndSetCount(Queue<Node> queue, Node node, int count) {
        if (!node.isVisited()) {
            node.visit();
            node.count = count;
            queue.add(node);
        }
    }

    private String bfs(Queue<Node> queue, Node[][] chessBoard, int length, int[][] enemies) {
        StringBuilder builder = new StringBuilder();

        while (!queue.isEmpty()) {
            Node target = queue.poll();
            int x = target.x, y = target.y, count = target.count;
            if (x > 0) {//좌측에 자리있어요
                if (y > 1) {//위쪽 두번째 자리있어요
                    this.addAndVisitAndSetCount( queue, chessBoard[x - 1][y - 2], count + 1);
                }
                if (y < chessBoard.length - 2) {//아래쪽 두번째 자리있어요
                    this.addAndVisitAndSetCount(queue, chessBoard[x - 1][y + 2], count + 1);
                }
            }


            if (x > 1) {//좌측 두번째 자리있어요
                if (y > 0) {//위쪽 자리있어요
                    this.addAndVisitAndSetCount(queue, chessBoard[x - 2][y - 1], count + 1);
                }
                if (y < chessBoard.length - 1) {//아래쪽 자리있어요
                    this.addAndVisitAndSetCount(queue, chessBoard[x - 2][y + 1], count + 1);
                }
            }
            if (x < chessBoard.length - 1) {//우측에 자리있어요
                if (y > 1) {//위쪽 두번째 자리있어요
                    this.addAndVisitAndSetCount( queue, chessBoard[x + 1][y - 2], count + 1);
                }
                if (y < chessBoard.length - 2) {//아래쪽 두번째 자리있어요
                    this.addAndVisitAndSetCount(queue, chessBoard[x + 1][y + 2], count + 1);
                }
            }
            if (x < chessBoard.length - 2) {//우측 두번째 자리있어요
                if (y > 0) {//위쪽 자리있어요
                    this.addAndVisitAndSetCount( queue, chessBoard[x + 2][y - 1], count + 1);
                }
                if (y < chessBoard.length - 1) {//아래쪽 자리있어요
                    this.addAndVisitAndSetCount( queue, chessBoard[x + 2][y + 1], count + 1);
                }
            }

        }

        for (int[] lines : enemies) {
            builder.append(chessBoard[lines[0]][lines[1]].count).append(" ");
        }

        builder.setLength(builder.length() - 1);
        return builder.toString();
    }


    private class Node {
        int x, y;
        boolean visited;
        int count;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.count = -1;
        }

        public void visit() {
            this.visited = true;
        }

        public boolean isVisited() {
            return this.visited;
        }
    }


}
