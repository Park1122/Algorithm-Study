import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2688 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numOfTestcase = Integer.parseInt(br.readLine());
        long[][] memory = new long[64][10];
        memory[0] = new long[]{1,1,1,1,1,1,1,1,1,1};

        for(int k=0; k<numOfTestcase; k++){
            int n = Integer.parseInt(br.readLine());
            for(int i=1; i<n; i++){
                long sum = 0;
                for(int j=0; j<10; j++){
                    sum+=memory[i-1][j];
                }
                for(int j=0; j<10; j++){
                    memory[i][j] = sum;
                    sum-=memory[i-1][j];
                }
            }
            long sum = 0;
            for(int j=0; j<10; j++){
                sum+=memory[n-1][j];
            }
            System.out.println(sum);
        }
    }
}
