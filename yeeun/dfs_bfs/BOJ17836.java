package codingstudy202107.w18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 18주차
 * 문제 출처 : https:/www.acmicpc.net/problem/17836
 * 이름 : 공주님을 구해라!
 * 사용 알고리즘 : bfs
 */
public class BOJ17836 {
    public static final int EMPTY = -1;
    public static final int WALL = -2;
    public static final int SWORD = -3;


    public static void main(String[] args) throws IOException {
        BOJ17836 main = new BOJ17836();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int lineLen = Integer.parseInt(st.nextToken()),
                colLen = Integer.parseInt(st.nextToken()),
                timeLimit = Integer.parseInt(st.nextToken());

        CoordinatePair[][] noSwordTable = new CoordinatePair[lineLen][colLen];
        int swordLine = 0, swordCol = 0;
        for (int line = 0; line < lineLen; line++) {
            st = new StringTokenizer(reader.readLine());
            for (int col = 0; col < colLen; col++) {
                int val = (Integer.parseInt(st.nextToken()) * -1) - 1;
                noSwordTable[line][col] = new CoordinatePair(line, col, val);
                if (val == SWORD) {
                    swordLine = line;
                    swordCol = col;
                }
            }
        }
        System.out.print(main.solution(noSwordTable, timeLimit, swordLine, swordCol));
    }

    private String solution(CoordinatePair[][] table, int timeLimit, int swordLine, int swordCol) {
        int timeSpent = Integer.MAX_VALUE;

        Queue<CoordinatePair> queue = new ArrayDeque<>();
        int destiny_line = table.length - 1, destiny_col = table[0].length - 1;

        //칼 찾으러 가기.
        boolean[][] visited = new boolean[table.length][table[0].length];
        CoordinatePair nextPair = table[0][0];
        nextPair.val = 0;
        visited[0][0] = true;
        queue.add(nextPair);
        this.bfsNoSword(queue, table, visited, timeLimit);

        int timeNoSword = table[destiny_line][destiny_col].val;
        if (timeNoSword >= 0 && timeNoSword <= timeLimit)
            timeSpent = timeNoSword;

        //칼 찾은 후 계산하기.
        visited = new boolean[table.length][table[0].length];
        CoordinatePair swordPair = table[swordLine][swordCol];
        if (swordPair.val > 0) {
            visited[swordLine][swordCol] = true;
            queue.add(swordPair);
        }
        this.bfsYesSword(queue, table, visited, timeLimit);

        int timeYesSword = table[destiny_line][destiny_col].val;
        if (timeYesSword >= 0 && timeYesSword <= timeLimit)
            timeSpent = Math.min(timeSpent, timeYesSword);


        //성공 여부 확인하기.
        if (timeSpent > timeLimit) return "Fail";
        else return Integer.toString(timeSpent);
    }

    private void printArrayArray(CoordinatePair[][] noSwordTable) {
        for (CoordinatePair[] line : noSwordTable) {
            for (CoordinatePair col : line) {
                System.out.print(col.val + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void addPairIfNotWall(Queue<CoordinatePair> queue, CoordinatePair[][] table, int line, int col, int time) {
        if (table[line][col].val != WALL)
            this.addPair(queue, table, line, col, time);
    }

    private void addPair(Queue<CoordinatePair> queue, CoordinatePair[][] table, int line, int col, int time) {
        CoordinatePair pair = table[line][col];
        pair.val = time;
        queue.add(pair);
    }



    private void bfsYesSword(Queue<CoordinatePair> queue, CoordinatePair[][] table, boolean[][] visited, int timeLimit) {
        while (!queue.isEmpty()) {
            CoordinatePair pair = queue.poll();
            int time = table[pair.line][pair.col].val;
            if (time > timeLimit) break; //시간초과
            time++;

            if (pair.line > 0 && !visited[pair.line - 1][pair.col]) { //index 검증
                visited[pair.line - 1][pair.col] = true;
                this.addPair(queue, table, pair.line - 1, pair.col, time);
            }
            if (pair.line < table.length - 1 && !visited[pair.line + 1][pair.col]) {
                visited[pair.line + 1][pair.col] = true;
                this.addPair(queue, table, pair.line + 1, pair.col, time);
                if (pair.line + 1 == table.length - 1 && pair.col == table[0].length) break;
            }
            if (pair.col > 0 && !visited[pair.line][pair.col - 1]) {
                visited[pair.line][pair.col - 1] = true;
                this.addPair(queue, table, pair.line, pair.col - 1, time);
            }
            if (pair.col < table[0].length - 1 && !visited[pair.line][pair.col + 1]) {
                visited[pair.line][pair.col + 1] = true;
                this.addPair(queue, table, pair.line, pair.col + 1, time);
                if (pair.line == table.length - 1 && pair.col + 1 == table[0].length) break;
            }
        }
    }


    private void bfsNoSword(Queue<CoordinatePair> queue, CoordinatePair[][] table, boolean[][] visited, int timeLimit) {
        while (!queue.isEmpty()) {
            CoordinatePair pair = queue.poll();
            int time = table[pair.line][pair.col].val;
            if (time > timeLimit) break; //시간초과
            time++;

            if (pair.line > 0 && !visited[pair.line - 1][pair.col]) { //index 검증
                visited[pair.line - 1][pair.col] = true;
                this.addPairIfNotWall(queue, table, pair.line - 1, pair.col, time);
            }
            if (pair.line < table.length - 1 && !visited[pair.line + 1][pair.col]) {
                visited[pair.line + 1][pair.col] = true;
                this.addPairIfNotWall(queue, table, pair.line + 1, pair.col, time);
                if (pair.line + 1 == table.length - 1 && pair.col == table[0].length - 1) timeLimit = time;
            }
            if (pair.col > 0 && !visited[pair.line][pair.col - 1]) {
                visited[pair.line][pair.col - 1] = true;
                this.addPairIfNotWall(queue, table, pair.line, pair.col - 1, time);
            }
            if (pair.col < table[0].length - 1 && !visited[pair.line][pair.col + 1]) {
                visited[pair.line][pair.col + 1] = true;
                this.addPairIfNotWall(queue, table, pair.line, pair.col + 1, time);
                if (pair.line == table.length - 1 && pair.col + 1 == table[0].length - 1) timeLimit = time;
            }
        }
    }


    public static class CoordinatePair {
        int line, col, val;

        public CoordinatePair(int line, int col, int val) {
            this.line = line;
            this.col = col;
            this.val = val;
        }
    }


}
