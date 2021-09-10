import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ11057 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] memory = new int[n][10];
        memory[0] = new int[]{1,1,1,1,1,1,1,1,1,1};
        for(int i=1; i<n; i++){
            int sum = 0;
            for(int j=0; j<10; j++){
                sum+=memory[i-1][j];
            }
            for(int j=0; j<10; j++){
                memory[i][j] = sum%10_007;
                sum-=memory[i-1][j];
            }
        }

        int sum = 0;
        for(int j=0; j<10; j++){
            sum+=memory[n-1][j];
        }
        System.out.println(sum%10_007);

        for(int[] arr : memory){
            System.out.println(Arrays.toString(arr));
        }


        // 0: 1 10 55
        // 1: 1 9 45
        // 2: 1 8
        // 3: 1 7
        // 4: 1 6
        // 5: 1 5
        // 6: 1 4
        // 7: 1 3
        // 8: 1 2
        // 9: 1 1 1
    }
}
