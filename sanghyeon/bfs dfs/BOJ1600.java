package year_2021.month_11.day_19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1600 {

    static int[][] moves = new int[][]{{1, 0, 0}, {0, 1, 0}, {-1, 0, 0}, {0, -1, 0}, {1, 2, -1}, {1, -2, -1}, {2, 1, -1}, {2, -1, -1}, {-1, 2, -1}, {-1, -2, -1}, {-2, 1, -1}, {-2, -1, -1}};
    static int W, H;
    static int[][] map, check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        check = new int[H][W];
        for(int y=0; y<H; y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<W; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
                check[y][x] = -1;
            }
        }

        boolean success = false;
        int depth=0;
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(new Node(0, 0, K));
        loop:while (!nodes.isEmpty()){
            int size = nodes.size();
            for(int i=0; i<size; i++){
                Node nowNode = nodes.poll();
                if(nowNode.x==W-1 && nowNode.y==H-1){
                    success=true;
                    break loop;
                }
                for(int[] move : moves) checkAndAdd(nowNode, nodes, move[0], move[1], move[2]);
            }
            depth++;
        }

        System.out.println(success? depth:-1);
    }

    private static void checkAndAdd(Node nowNode, Queue<Node> nodes, int dx, int dy, int dj) {
        Node newNode = new Node(nowNode.x+dx, nowNode.y+dy, nowNode.jump+dj);
        if(newNode.x<0 || W<=newNode.x || newNode.y<0 || H<=newNode.y || newNode.jump<0) return;
        if(map[newNode.y][newNode.x]==1) return;
        if(check[newNode.y][newNode.x] < newNode.jump) {
            check[newNode.y][newNode.x] = newNode.jump;
            nodes.add(newNode);
        }
    }

    private static class Node{
        int x, y, jump;
        public Node(int x, int y, int jump){
            this.x=x;
            this.y=y;
            this.jump=jump;
        }
    }
}
