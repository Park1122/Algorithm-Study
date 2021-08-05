package year_2021.month_08.day_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ1541 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String formula = br.readLine();
		
		ArrayList<Integer> numbers = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		int temp = 0;
		for(char c : formula.toCharArray()) {
			if(c=='+') {
				temp += Integer.parseInt(sb.toString());
				sb.delete(0, sb.length());
			}else if(c=='-') {
				temp+= Integer.parseInt(sb.toString());
				sb.delete(0, sb.length());
				numbers.add(temp);
				temp = 0;
			}else {
				sb.append(c);
			}
		}
		temp += Integer.parseInt(sb.toString());
		numbers.add(temp);
		
		int result = numbers.get(0);
		for(int i=1; i<numbers.size(); i++) result -= numbers.get(i);
		
		System.out.println(result);
	}
}
