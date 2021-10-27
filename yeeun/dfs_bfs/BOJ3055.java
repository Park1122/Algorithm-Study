package codingstudy202107.w16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 15주차
 * 문제 출처 : https://www.acmicpc.net/problem/3055
 * 이름 : 탈출
 * 사용 알고리즘 : bfs
 * 사악한 문제가 확실하다 골드이다 무섭다..
 * 오늘은 종각 쉑쉑버거집이다 ㅎㅎ..
 * 사악한 골드 문제라서 이틀 걸렸다. 오늘은 다시 백석역 카페.
 * 물 넘치는 것과 이동하는 것을 각각 큐와 벡터를 만들어서 구현하였다.
 * 벡터에는 다음 분에 큐에 넣을 애들을 넣고, 큐에는 이번 분꺼로 해서 처음에 벡터에서 큐로 넣고  벡터를 비우고 시작한다.
 * 이렇게 하는게 맞나 싶긴 한데 일단 답은 맞췄다.
 */
public class BOJ3055 {

    public enum ENodeType {water, space, rock}

    public static final String waterString = "*";
    public static final String spaceString = ".";
    public static final String rockString = "X";
    public static final String startPointString = "S";
    public static final String destinyPointString = "D";


    public static void main(String[] args) throws IOException {
        BOJ3055 main = new BOJ3055();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        String[][] arr = new String[R][C];
        for (int i = 0; i < R; i++) {
            String line = reader.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = line.substring(j, j + 1);
            }
        }

        int result = main.solution(arr);
        System.out.print(result == -1 ? "KAKTUS" : result);
    }

    private int solution(String[][] arr) {
        Queue<Node> waterQueue = new ArrayDeque<>();
        Queue<Node> moveQueue = new ArrayDeque<>();
        Vector<Node> nextWaterVector = new Vector<>();
        Vector<Node> nextMoveVector = new Vector<>();
        Node destiny = null;

        Node[][] nodes = new Node[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                String st = arr[i][j];
                if (st.equals(rockString)) {
                    nodes[i][j] = new Node(st, i, j);
                } else if (st.equals(waterString)) {
                    nodes[i][j] = new Node(st, i, j);
                    nextWaterVector.add(nodes[i][j]);
                } else {
                    nodes[i][j] = new Node(spaceString, i, j);
                    if (st.equals(startPointString)) {
                        nextMoveVector.add(nodes[i][j]);
                    } else if (st.equals(destinyPointString)) {
                        destiny = nodes[i][j];
                    }
                }

                if (i > 0) {
                    nodes[i][j].next.add(nodes[i - 1][j]);
                    nodes[i - 1][j].next.add(nodes[i][j]);
                }
                if (j > 0) {
                    nodes[i][j].next.add(nodes[i][j - 1]);
                    nodes[i][j - 1].next.add(nodes[i][j]);
                }
            }
        }


        return this.bfsMove(nodes, waterQueue, nextWaterVector, moveQueue, destiny, nextMoveVector, -1);
    }

    private int bfsMove(Node[][] nodes, Queue<Node> waterQueue, Vector<Node> nextWaterVector, Queue<Node> moveQueue, Node destiny, Vector<Node> nextMoveVector, int count) {
        //물이 아예 꽉 차버렸음
        if (nextMoveVector.isEmpty() || this.bfsFlood(nodes, waterQueue, nextWaterVector, destiny)) return -1;

        moveQueue.addAll(nextMoveVector);
        nextMoveVector.clear();

        count++;
//        System.out.println( count+"회차 시작");
        while (!moveQueue.isEmpty()) {
            Node target = moveQueue.poll();
//            System.out.println(target.getLocation());
            if (target.i == destiny.i && target.j == destiny.j) {
                return count;
            }

            for (Node next : target.next) {
                if (!next.isFlood && !next.type.equals(ENodeType.rock) && !nextMoveVector.contains(next)) {
                    nextMoveVector.add(next);
                }
            }
        }
        return this.bfsMove(nodes, waterQueue, nextWaterVector, moveQueue, destiny, nextMoveVector, count);
    }

    private boolean bfsFlood(Node[][] nodes, Queue<Node> waterQueue, Vector<Node> nextWaterVector, Node destiny) {
        if (nextWaterVector.isEmpty() && this.nodesFlood(nodes)) return true;

        waterQueue.addAll(nextWaterVector);
        nextWaterVector.clear();

        while (!waterQueue.isEmpty()) {
            Node target = waterQueue.poll();
            for (Node next : target.next) {
                if (!next.equals(destiny) && !next.isFlood && !next.type.equals(ENodeType.rock) && !nextWaterVector.contains(next)) {
                    next.isFlood = true;
                    nextWaterVector.add(next);
                }
            }
        }

        //시각화
//        for (Node[] line : nodes) {
//            System.out.println(Arrays.toString(line));
//        }
        return false;
    }

    private boolean nodesFlood(Node[][] nodes) {
        for (Node[] line : nodes) {
            for (Node node : line) {
                if (!node.isFlood && node.type.equals(ENodeType.space)) {
                    return false;
                }
            }
        }
        return true;
    }


    private class Node {
        int count, id, i, j;
        boolean visited, isFlood;
        ENodeType type;
        Vector<Node> next;

        public Node(String id, int i, int j) {
            this.i = i;
            this.j = j;
            if (id.equals(waterString)) {
                this.type = ENodeType.water;
                this.isFlood = true;
            } else if (id.equals(rockString)) {
                this.type = ENodeType.rock;
            } else if (id.equals(spaceString)) {
                this.type = ENodeType.space;
            }
            this.next = new Vector<>();
            this.init();
        }

        public void init() {
            if (this.type.equals(ENodeType.water)) this.isFlood = true;
            else this.isFlood = false;
            this.visited = false;
            this.count = -1;
        }

        public void visit() {
            this.visited = true;
        }

        public String toString() {
            if (this.isFlood)
                return waterString;
            else if (this.type.equals(ENodeType.rock))
                return rockString;
            else if (this.type.equals(ENodeType.space))
                return spaceString;
            else return "?";
        }

        public String getLocation() {
            return this.i + ", " + j;
        }
    }


}
