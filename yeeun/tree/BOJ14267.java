package codingstudy202107.w24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14267 {
    public static void main(String[] args) throws IOException {
        BOJ14267 main = new BOJ14267();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int employeeCount = Integer.parseInt(st.nextToken());
        int complimentCount = Integer.parseInt(st.nextToken());

        int[] employeeArray = new int[employeeCount + 1];
        Employee[] juniorArray = new Employee[employeeCount + 1];

        st = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= employeeCount; i++) {
            Employee employee = new Employee(i);
            int senior = Integer.parseInt(st.nextToken());
            if (senior > 0) {
                employee.sister = juniorArray[senior];
                juniorArray[senior] = employee;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < complimentCount; i++) {
            st = new StringTokenizer(reader.readLine());
            int complimentedEmployeeIdx = Integer.parseInt(st.nextToken());
            int complimentedAmount = Integer.parseInt(st.nextToken());
            employeeArray[complimentedEmployeeIdx]+=complimentedAmount;
        }
        queue.add(1);//add root
        main.bfsAllCompliment(queue,employeeArray, juniorArray);

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=employeeCount;i++){
            sb.append(employeeArray[i]).append(' ');
        }
        sb.setLength(sb.length()-1);
        System.out.print(sb);
    }

    private void bfsAllCompliment(Queue<Integer> queue, int[] employeeArray, Employee[] juniorArray) {
        do{
            int seniorIdx= queue.poll();
            int seniorComplimented=employeeArray[seniorIdx];
            Employee juniorLinkedList = juniorArray[seniorIdx];
            while(juniorLinkedList!=null){
                employeeArray[juniorLinkedList.idx]+=seniorComplimented;
                queue.add(juniorLinkedList.idx);
                juniorLinkedList=juniorLinkedList.sister;
            }
        }while(!queue.isEmpty());
    }

    public static class Employee {
        Employee sister=null;
        int idx;
        public Employee (int idx){
            this.idx=idx;
        }
    }
}
