package codingstudy202107.w05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 회차 : 코딩 스터디 5주차
 * 문제 출처 : https://www.acmicpc.net/problem/1541
 * 이름 : 잃어버린 괄호
 * 사용 알고리즘 : 수학, 그리디, 문자열, 파싱
 */
public class BOJ1541 {

    public static void main(String[] args) throws IOException {
        BOJ1541 main = new BOJ1541();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        int result = main.solution(line);
        System.out.print(result);
    }

    private int solution(String line) {
        int sum = 0, temp = 0;
        boolean minus = false;
        StringBuilder builder = new StringBuilder();
        for (byte b : line.getBytes()) {
            if (b == '-') {
                if (minus) {
                    temp += Integer.parseInt(builder.toString());
                    builder.setLength(0);
                    sum -= temp;
                    temp = 0;
                } else {
                    minus=true;
                    sum += Integer.parseInt(builder.toString());
                    builder.setLength(0);
                }
            } else if (b == '+') {
                if (minus) {
                    temp += Integer.parseInt(builder.toString());
                } else {
                    sum += Integer.parseInt(builder.toString());
                }
                builder.setLength(0);
            } else {
                builder.append((char)b);
            }
        }
        if (minus) {
            temp += Integer.parseInt(builder.toString());
            sum -= temp;
        } else {
            sum += Integer.parseInt(builder.toString());
        }

        return sum;
    }
}
