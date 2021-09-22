import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1309 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] memory = new int[n];
        memory[0] = 3;
        if(n>1) memory[1] = 7;

        // 00: 1 3 7 a
        // 01: 1 2 5 b
        // 10: 1 2 5 c
        // a b c, abc ac ab, abcacab abcab abcac
        // 1a 1b 1c, 3a 2b 2c, 7a 5b 5c,
        //    : 3 7 17

        for(int i=2; i<n; i++){
            memory[i] = (memory[i-1]*2 + memory[i-2])%9901;
        }
        System.out.println(memory[n-1]);
    }
}
