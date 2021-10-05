package year_2021.month_09.day_30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2606 {

    private static Computer[] computers;
    private static boolean[] traveled;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int computerNumber = Integer.parseInt(br.readLine());
        computers = new Computer[computerNumber];
        for(int i=0; i<computerNumber; i++) computers[i] = new Computer(i);

        int connectNumber = Integer.parseInt(br.readLine());
        for(int i=0; i<connectNumber; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int computer1Index = Integer.parseInt(st.nextToken()) - 1;
            int computer2Index = Integer.parseInt(st.nextToken()) - 1;
            computers[computer1Index].connectedComputers.add(computers[computer2Index]);
            computers[computer2Index].connectedComputers.add(computers[computer1Index]);
        }

        traveled = new boolean[computerNumber];
        travel(0);

        System.out.println(count - 1);
    }

    private static void travel(int i) {
        if(!traveled[i]) {
            traveled[i] = true;
            count++;
            for(Computer computer : computers[i].connectedComputers){
                travel(computer.index);
            }
        }
    }

    private static class Computer {
        private int index;
        private ArrayList<Computer> connectedComputers;

        public Computer(int i) {
            this.index = i;
            this.connectedComputers = new ArrayList<>();
        }
    }
}
