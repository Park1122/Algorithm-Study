package codingstudy202107.w21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 21주차
 * 문제 출처 : https:/www.acmicpc.net/problem/4179
 * 이름 : 불!
 * 사용 알고리즘 : bfs
 */
public class BOJ4179 {
    //val movable coordinate dx dy
    public static final int[][] COORDINATES = new int[][]{{-1, -0}, {0, -1}, {0, 1}, {1, 0}};
    public static final String SPACE=".";
    public static final String FIRE="F";
    public static final String START_POINT="J";

    public static void main(String[] args) throws IOException {
        BOJ4179 main = new BOJ4179();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int lineLen = Integer.parseInt(st.nextToken());
        int colLen = Integer.parseInt(st.nextToken());

        String[][] arr = new String[lineLen][colLen];
        boolean[][] visited = new boolean[lineLen][colLen];
        Vector<Pair<Integer, Integer>> nextMoves = new Vector<>();
        Vector<Pair<Integer, Integer>> fireplaces = new Vector<>();

        for (int line = 0; line < lineLen; line++) {
            String str = reader.readLine();
            for (int col = 0; col < colLen; col++) {
                String val = str.substring(col, col + 1);
                arr[line][col] = val;
                if (val.equals(START_POINT)) {
                    arr[line][col] = SPACE;
                    visited[line][col] = true;
                    nextMoves.add(new Pair<>(line, col));
                }
                if (val.equals(FIRE)) fireplaces.add(new Pair<>(line, col));

            }
        }

        System.out.print(main.solution(arr, nextMoves, fireplaces, visited));
    }

    private String solution(String[][] arr, Vector<Pair<Integer, Integer>> nextMoves, Vector<Pair<Integer, Integer>> fireplaces, boolean[][] visited) {
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        int time = 0;

        while (!nextMoves.isEmpty()) {
            time++;
            queue.addAll(nextMoves);
            nextMoves.clear();

            while (!queue.isEmpty()) {
                Pair<Integer, Integer> node = queue.poll();
                int nodeLine = node.key;
                int nodeCol = node.value;
                String nodeType = arr[nodeLine][nodeCol];
                if (nodeType.equals(SPACE)) {//not burnt
                    if (nodeLine == 0 || nodeCol == 0 || nodeLine == arr.length - 1 || nodeCol == arr[0].length - 1)
                        return String.valueOf(time);
                    for (int[] coord : COORDINATES) {
                        try {
                            int nextLine = nodeLine + coord[0], nextCol = nodeCol + coord[1];
                            String nextType = arr[nextLine][nextCol];
                            if (nextType.equals(SPACE) && !visited[nextLine][nextCol]) {
                                visited[nextLine][nextCol] = true;
                                nextMoves.add(new Pair<>(nextLine, nextCol));
                            }
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                    }
                }
            }

            queue.addAll(fireplaces);
            fireplaces.clear();
            while (!queue.isEmpty()) {
                Pair<Integer, Integer> node = queue.poll();
                int nodeLine = node.key;
                int nodeCol = node.value;
                for (int[] coord : COORDINATES) {
                    try {
                        int nextLine = nodeLine + coord[0], nextCol = nodeCol + coord[1];
                        String nextType = arr[nextLine][nextCol];
                        if (nextType.equals(SPACE)) {
                            arr[nextLine][nextCol] = FIRE;
                            fireplaces.add(new Pair<>(nextLine, nextCol));
                        }
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                }
            }
        }
        return "IMPOSSIBLE";
    }


    public static class Pair<K, V> {
        K key;
        V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            Pair<K, V> that = (Pair<K, V>) o;
            return this.key.equals(that.key) && this.value.equals(that.key);
        }
    }

}