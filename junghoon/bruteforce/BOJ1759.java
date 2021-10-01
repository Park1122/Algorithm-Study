package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1759 {


	/*
	 * 9월 마지막주차
	 * 
	 * 문제: 바로 어제 최백준 조교가 방 열쇠를 주머니에 넣은 채 깜빡하고 서울로 가 버리는 황당한 상황에 직면한 조교들은, 
	 *     702호에 새로운 보안 시스템을 설치하기로 하였다. 이 보안 시스템은 열쇠가 아닌 암호로 동작하게 되어 있는 시스템이다.
	 *     암호는 서로 다른 L개의 알파벳 소문자들로 구성되며 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성되어 있다고 알려져 있다. 
	 *     또한 정렬된 문자열을 선호하는 조교들의 성향으로 미루어 보아 암호를 이루는 알파벳이 암호에서 증가하는 순서로 배열되었을 것이라고 추측된다. 
	 *     즉, abc는 가능성이 있는 암호이지만 bac는 그렇지 않다.
	 *     새 보안 시스템에서 조교들이 암호로 사용했을 법한 문자의 종류는 C가지가 있다고 한다. 
	 *     이 알파벳을 입수한 민식, 영식 형제는 조교들의 방에 침투하기 위해 암호를 추측해 보려고 한다. 
	 *     C개의 문자들이 모두 주어졌을 때, 가능성 있는 암호들을 모두 구하는 프로그램을 작성하시오.
	 * 
	 * 입력: 첫째 줄에 두 정수 L, C가 주어진다. (3 ≤ L ≤ C ≤ 15) 다음 줄에는 C개의 문자들이 공백으로 구분되어 주어진다. 주어지는 문자들은 알파벳 소문자이며, 중복되는 것은 없다
	 * 
	 * 출력: 각 줄에 하나씩, 사전식으로 가능성 있는 암호를 모두 출력한다.
	 * 
	 * 풀이: Backtracking (DFS)
	 * 
	 * 해법: 시작과 동시에 배열 정렬하고 자음과 모음 갯수 파악하면 끝.
	 * */

	
	private static String[] arr;
    private static int l, c;
    
    //간단 요약 조건
    // 1. 문자열 L개
    // 2. 최소 모음 1개 자음 2개 이상이여야하네 == 이건 문제에서 나옴 잘봐야함
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        l = Integer.parseInt(tmp[0]);
        c = Integer.parseInt(tmp[1]);
 
        arr = br.readLine().split(" ");
        Arrays.sort(arr);
 
        solve(0,"");
    }
    private static void solve(int idx, String str){
        if(str.length() == l){// 길이 1
            if(isPossible(str)){ // 모음 1개, 자음 2개 이상 ㅇㅋ
                System.out.println(str);
            }
            return;
        }
        if(idx >= c ){// 맨끝까지 돌았으면 
            return;
        }
        
        // 문자에 자기가 포함되어 있는지 아닌지 확인
        solve(idx+ 1, str + arr[idx]);// 자기자신과 다음 문자까지 같이
        solve(idx+ 1, str );// 자기자신만
    }
 
    private static boolean isPossible(String str){
        int vowel = 0, consonant = 0;
        for (int i = 0; i < str.length(); i++) {
            if(isVowel(str.charAt(i))){
                vowel++;
            }else {
                consonant++;
            }
        }
        return vowel>=1 && consonant >=2;
    }
 
 
    private static boolean isVowel( char ch ){
        return ch == 'a' | ch=='e' | ch=='i' | ch=='o' | ch=='u';
    }
}






//미완
//이거는 내가 문제는 이해 했지만, 풀이 방법을 몰라 인터넷에서 찾아보았지만, 완벽하지 않았음
//그래서 이 방법을 발전시켜서 예제에 완벽한 해답을 도출해내려 했지만 문제를 찾지 못하겠음.
