package sujeong.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15650 {
    private static int n,m;
    private static StringBuilder sb;

    private static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] strArr = reader.readLine().split(" ");
        n = Integer.parseInt(strArr[0]);
        m = Integer.parseInt(strArr[1]);
        sb = new StringBuilder();
    }

    private static void func(String str, int index){
        if(index==m){
            sb.append(str).append("\n");
            return;
        }
        for(int i=1;i<=n;i++) {
            if (str.equals("")){
                func(str+i,index+1);
            }else{
                if(i> Integer.parseInt(str.substring(str.length()-1))){
                    func(str+" "+i,index+1);
                }
            }
        }
    }

    private static void output(){
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
    }
    public static void main(String[] args) throws IOException {
        input();
        func("",0);
        output();
    }

}
