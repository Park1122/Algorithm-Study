package codingstudy202107.w24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Vector;

public class BOJ2252 {
    public static void main(String[] args) throws IOException {
        BOJ2252 main = new BOJ2252();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int studentCount = Integer.parseInt(st.nextToken());
        int compareCount = Integer.parseInt(st.nextToken());

        Student[] students = new Student[studentCount + 1];
        for (int i = 1; i <= studentCount; i++) {
            students[i] = new Student(i);
        }

        for (int i = 0; i < compareCount; i++) {
            st = new StringTokenizer(reader.readLine());
            int smallerIdx = Integer.parseInt(st.nextToken());
            int tallerIdx = Integer.parseInt(st.nextToken());
            Student smaller = students[smallerIdx];
            Student taller = students[tallerIdx];
            smaller.tallerStudents.add(taller);
            taller.smallerCount += 1;
        }

        StringBuilder sb = new StringBuilder();
        main.topologicalSort(students, sb);

        System.out.print(sb);
    }

    private void topologicalSort(Student[] students, StringBuilder sb) {
        Queue<Student> queue = new ArrayDeque<>();
        this.addNoSmallerStudents(queue, students, sb);
        do {
            Student std = queue.poll();
            for (Student taller : std.tallerStudents) {
                taller.smallerCount -= 1;
            }
            this.addNoSmallerStudents(queue, students, sb);
        } while (!queue.isEmpty());
    }

    private void addNoSmallerStudents(Queue<Student> queue, Student[] students, StringBuilder sb) {
        for (int i = 1; i < students.length; i++) {
            Student std = students[i];
            if (std != null && std.smallerCount == 0) {
                sb.append(std.val).append(' ');
                students[i] = null;
                queue.add(std);
            }
        }
    }


    public static class Student {
        int val, smallerCount;
        Vector<Student> tallerStudents;

        public Student(int val) {
            this.val = val;
            this.smallerCount = 0;
            this.tallerStudents = new Vector<>();
        }
    }
}
