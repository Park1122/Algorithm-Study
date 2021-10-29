package year_2021.month_10.day_19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ5567 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int classStudentCount = Integer.parseInt(br.readLine());
        Student[] classStudents = new Student[classStudentCount];
        for(int num = 0; num<classStudentCount; num++) classStudents[num] = new Student(num);

        int connectionCount = Integer.parseInt(br.readLine());
        for(int i=0; i<connectionCount; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int studentAIndex = Integer.parseInt(st.nextToken())-1;
            int studentBIndex = Integer.parseInt(st.nextToken())-1;
            classStudents[studentAIndex].friends.add(classStudents[studentBIndex]);
            classStudents[studentBIndex].friends.add(classStudents[studentAIndex]);
        }

        Student sang = classStudents[0];
        HashSet<Student> inviteFriends = new HashSet<>();
        for(Student friend : sang.friends){
            inviteFriends.add(friend);
            for(Student friendFriend : friend.friends){
                inviteFriends.add(friendFriend);
            }
        }
        inviteFriends.remove(sang);
        System.out.println(inviteFriends.size());
    }

    private static class Student {
        int number;
        ArrayList<Student> friends = new ArrayList<>();
        public Student(int number){
            this.number = number;
        }
    }
}
