package codingstudy202107.w05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 회차 : 코딩 스터디 5주차
 * 문제 출처 : https://www.acmicpc.net/problem/1931
 * 이름 : 회의실 배정
 * 사용 알고리즘 : 그리디, 정렬
 */
public class BOJ1931 {

    public static void main(String[] args) throws IOException {
        BOJ1931 main = new BOJ1931();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        PriorityQueue<Meeting> queue = new PriorityQueue<>();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(reader.readLine());
            Meeting meeting = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            queue.add(meeting);
        }

        int result = main.solution(queue);
        System.out.print(result);
    }

    private int solution(PriorityQueue<Meeting> queue) {
        int size = 0;
        Meeting[] arr = new Meeting[queue.size()];
        while (!queue.isEmpty()) {
            Meeting meeting = queue.poll();
            boolean isDuplicated = size > 0 ? this.checkDuplicate(meeting, arr[size - 1]) : false;
            if (!isDuplicated) {
                arr[size++] = meeting;
            }
        }
        return size;
    }

    private boolean checkDuplicate(Meeting a, Meeting b) {
        if (a.endTime <= b.startTime || a.startTime >= b.endTime) {
            return false;
        } else {
            return true;
        }
    }

}

class Meeting implements Comparable<Meeting> {
    int startTime, endTime;

    public Meeting(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Meeting that) {
        if (that.endTime < this.endTime) return 1;
        else return -1;
    }

    public String toString() {
        return startTime + ":" + endTime + "(" + (endTime-startTime) + ")";
    }
}



