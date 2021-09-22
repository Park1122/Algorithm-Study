import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ9465 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNumber = Integer.parseInt(br.readLine());
        for(int i=0; i<testCaseNumber; i++){
            int width = Integer.parseInt(br.readLine());
            int[][] stickers = new int[2][width];
            for(int j=0; j<2; j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int k=0; k<width; k++){
                    stickers[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] memory = new int[2][width];
            memory[0][0] = stickers[0][0];
            memory[1][0] = stickers[1][0];
            if(width>=2){
                memory[0][1] = memory[1][0] + stickers[0][1];
                memory[1][1] = memory[0][0] + stickers[1][1];
            }
            for(int j=2; j<width; j++){
                memory[0][j] = Math.max(memory[1][j-1], Math.max(memory[0][j-2], memory[1][j-2])) + stickers[0][j];
                memory[1][j] = Math.max(memory[0][j-1], Math.max(memory[0][j-2], memory[1][j-2])) + stickers[1][j];
            }
            System.out.println(Arrays.toString(memory[0]));
            System.out.println(Arrays.toString(memory[1]));
            System.out.println(Math.max(memory[0][width-1], memory[1][width-1]));
        }
    }
}
