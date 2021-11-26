package codingstudy202107.w21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 회차 : 코딩 스터디 21주차
 * 문제 출처 : https:/www.acmicpc.net/problem/16932
 * 이름 : 모양 만들기
 * 사용 알고리즘 : bfs
 */
public class BOJ16932 {
    //val movable coordinate dx dy
    public static final int[][] COORDINATES = new int[][]{{-1, -0}, {0, -1}, {0, 1}, {1, 0}};

    public static final int SPACE = 0;
    public static final int DOHYEONG = 1;


    public static void main(String[] args) throws IOException {
        BOJ16932 main = new BOJ16932();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int lineLen = Integer.parseInt(st.nextToken());
        int colLen = Integer.parseInt(st.nextToken());

        int[][] arr = new int[lineLen][colLen];
        Vector<Pair<Integer, Integer>> spaces = new Vector<>();

        for (int line = 0; line < lineLen; line++) {
            st = new StringTokenizer(reader.readLine());
            for (int col = 0; col < colLen; col++) {
                int val = Integer.parseInt(st.nextToken());
                arr[line][col] = val;
                if (val == SPACE) spaces.add(new Pair<>(line, col));
            }
        }

        System.out.print(main.solution(arr, spaces));
    }

    private int solution(int[][] arr, Vector<Pair<Integer, Integer>> spaces) {
        int maxSize = 0;

        Dohyeong[][] dohyeongs = makeDohyeongArray(arr);
        Set<Dohyeong> tempset = new HashSet<>();

        for (Pair<Integer, Integer> node : spaces) {//실제 검사할 공백값.
            int line = node.key, col = node.value;
            for (int[] coord : COORDINATES) {
                try {
                    int nextLine = line + coord[0], nextCol = col + coord[1];
                    tempset.add(dohyeongs[nextLine][nextCol]);
                }catch (ArrayIndexOutOfBoundsException ignore){}
            }

            int size=1;//바뀐 자신의 사이즈부터 시작.
            for(Dohyeong doh: tempset){
                if(doh!=null) size+=doh.val;
            }

            if(size>maxSize)maxSize=size;
            tempset.clear();
        }


        return maxSize;
    }


    //완성
    private Dohyeong[][] makeDohyeongArray(int[][] arr) {
        Dohyeong[][] dohyeongs = new Dohyeong[arr.length][arr[0].length];
        boolean[][] visited = new boolean[arr.length][arr[0].length];
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();

        for (int line = 0; line < arr.length; line++) {
            for (int col = 0; col < arr[0].length; col++) {
                if (arr[line][col] == DOHYEONG && !visited[line][col]) {
                    visited[line][col] = true;
                    dohyeongs[line][col] = new Dohyeong();
                    queue.add(new Pair<>(line, col));
                    this.bfsFillDohyeongs(dohyeongs, arr, visited, queue);
                }
            }
        }
        return dohyeongs;
    }

    //완성
    private void bfsFillDohyeongs(Dohyeong[][] dohyeongs, int[][] arr, boolean[][] visited, Queue<Pair<Integer, Integer>> queue) {
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> node = queue.poll();
            int nodeLine = node.key;
            int nodeCol = node.value;
            Dohyeong dohyeong = dohyeongs[nodeLine][nodeCol];

            for (int[] coord : COORDINATES) {
                try {
                    int nextLine = nodeLine + coord[0], nextCol = nodeCol + coord[1];
                    if (arr[nextLine][nextCol] == DOHYEONG && !visited[nextLine][nextCol]) {
                        visited[nextLine][nextCol] = true;
                        dohyeongs[nextLine][nextCol] = dohyeong;
                        dohyeong.val+=1;
                        queue.add(new Pair<>(nextLine, nextCol));
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
    }




    public static class Dohyeong {
        int val = 1;
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