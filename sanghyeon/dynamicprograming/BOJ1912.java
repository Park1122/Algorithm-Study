import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1912 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[n];
        for(int i=0; i<n; i++) numbers[i] = Integer.parseInt(st.nextToken());

        int[] memory = new int[n];
        memory[0] = numbers[0];
        int max = memory[0];
        for(int i=1; i<n; i++){
            memory[i] = Math.max(memory[i-1]+numbers[i], numbers[i]);
            if(max < memory[i]) max=memory[i];
        }
        System.out.println(max);
    }
}
