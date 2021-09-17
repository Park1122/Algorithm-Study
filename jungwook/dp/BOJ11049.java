package jungwook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11049 {
    static int N;
    static int[][] matrix, Dy;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        matrix = new int[N + 1][2];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        Dy = new int[N + 1][N + 1];

        // i번째 행렬과 (i+1)번째 행렬을 곱했을 때의 곱셈 연산값 저장
        for (int i = 1; i <= N - 1; i++) {
            Dy[i][i + 1] = matrix[i][0] * matrix[i][1] * matrix[i + 1][1];
        }

        //len==1인 것은 바로 앞뒤 행렬은 곱한 것으로 위에서 이미 구해둠
        for (int len = 2; len < N; len++) { // 시작행렬과 끝 행렬 인덱스 차
            for (int i = 1; i <= N - len; i++) { //첫번째 행렬의 인덱스
                int j = i + len; //마지막 행렬의 인덱스
                for (int k = i; k < j; k++) { //중간 행렬의 인덱스
                    int result = Dy[i][k] + Dy[k + 1][j] + matrix[i][0] * matrix[k + 1][0] * matrix[j][1];

                    // 아직 최소 곱셈 연산 값이 저장되지 않았거나 더 작은 값이 존재하면 새로 갱신
                    if (Dy[i][j] == 0 || Dy[i][j] > result) {
                        Dy[i][j] = result;
                    }
                }
            }
        }

        System.out.println(Dy[1][N]);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
