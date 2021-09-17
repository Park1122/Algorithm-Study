import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1149 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] cost = new int[n][3];
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] memory = new int[n][3];
        memory[0] = cost[0];
        for(int i=1; i<n; i++){
            memory[i][0] = Math.min(memory[i-1][1], memory[i-1][2]) + cost[i][0];
            memory[i][1] = Math.min(memory[i-1][0], memory[i-1][2]) + cost[i][1];
            memory[i][2] = Math.min(memory[i-1][0], memory[i-1][1]) + cost[i][2];
        }
        System.out.println(Math.min(memory[n-1][0], Math.min(memory[n-1][1], memory[n-1][2])));
    }
}
