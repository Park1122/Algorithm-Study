package sujeong.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ1759 {
    private static final String[] vowelsType ={"a","e","i","o","u"};
    private static final String[] consonantType ={"b","c","d","f","g","h","j",
            "k","l","m","n","p","q","r","s","t","v","w","x","y","z"};

    private static int l,c;
    private static String[] strArr;
    private static ArrayList<String> answers;


    private static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] strArrFirst = reader.readLine().split(" ");
        l = Integer.parseInt(strArrFirst[0]);
        c = Integer.parseInt(strArrFirst[1]);

        strArr = reader.readLine().split(" ");
        Arrays.sort(strArr);

        answers = new ArrayList<String>();
    }

    private static void recFunc(String str, int index){
        if(index==l) answers.add(str);
        for(int i=index;i<strArr.length;i++){
            if(str.equals("") ||(str.substring(str.length()-1).compareTo(strArr[i]))<0){
                recFunc(str+strArr[i],index+1);
            }
        }
    }

    private static boolean containsVow(String answer){
        for(String s:vowelsType) if(answer.contains(s)) return true;
        return false;
    }

    private static boolean containsCon(String answer){
        int cases = 0;
        for(String s:consonantType) if(answer.contains(s)) cases++;
        return cases >= 2;
    }

    private static void output() {
        int k=0;
        for (String answer : answers) {
            if(containsVow(answer) && containsCon(answer))
                System.out.println(answer);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        recFunc("",0);
        output();
    }

}
