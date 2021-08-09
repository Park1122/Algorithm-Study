package year_2021.month_08.day_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ14916 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int exchange = Integer.parseInt(br.readLine());
		int needCoin = Integer.MAX_VALUE;
		
		for(int i=exchange/5; i>=0; i--) {
			int tempNeedCoin = 0, tempExchange = exchange;
			tempNeedCoin+=i;
			tempExchange-=i*5;
			tempNeedCoin+=tempExchange/2;  
			tempExchange%=2;
			if(tempExchange==0 && needCoin>tempNeedCoin) {
				needCoin = tempNeedCoin; 
				break;
			}
		}
		
		System.out.println((needCoin==Integer.MAX_VALUE)? -1 : needCoin);
	}
}

//System.out.println("i: "+i);
//System.out.println("tempExchange: "+tempExchange);
//System.out.println("tempNeedCoin: "+tempNeedCoin);