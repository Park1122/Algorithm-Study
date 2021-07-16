package sujeong.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9663 {
    private static int n, ans=0;
    private static int[] chess;

    private static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());

        chess = new int[n];
    }

    private static void func(int y) {
        // 모든 열을 다돌았을 경우
        if(y==n){
//            print();
            ans++;
            return;
        }
        for(int x=0;x<n;x++){
            // 해당 x에 값을넣고
            chess[y]=x;
            // 그경우 이전것과 비교하여(가로세로, 대각선) 가능한가?
            if(promising(y)) func(y+1);
        }
    }

    private static boolean promising(int y){
        //지금까지 쌓아온걸로 해당 열에 들어갈 수 없게하는 조건들 확인
       for(int row=0;row<y;row++){
           // 가로세로 비교
           if (chess[row] == chess[y]) return false;
           // 대각선 비교
           else if (Math.abs(y-row)==Math.abs(chess[y]-chess[row])) return false;
       }
       // 모든 조건 만족시 true
       return true;
    }

    // for print chess's (테스트용 function)
//    private static void print(){
//        for(int i=0;i<chess.length;i++){
//            for(int j =0;j<chess.length;j++){
//                if(chess[i]==j) System.out.print("O");
//                else System.out.print("X");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        System.out.println();
//    }

    private static void output() {
        System.out.println(ans);

    }

    public static void main(String[] args) throws IOException {
        input();
        func(0);
        output();
    }

}
