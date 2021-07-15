package jungwook.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1759 {
    static int L, C;
    static StringBuilder sb = new StringBuilder();
    static char[] alphabets;
    static int[] selected;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        selected = new int[L + 1];
        alphabets = new char[C + 1];
        for (int i = 1; i <= C; i++) {
            alphabets[i] = st.nextToken().charAt(0);
        }
    }

    static void recFunc(int k) {
        if (k == L + 1) {
            int vowel = 0;
            int consonant = 0;
            for (int i = 1; i <= L; i++) {
                if (alphabets[selected[i]] == 'a' | alphabets[selected[i]] == 'e' | alphabets[selected[i]] == 'i' | alphabets[selected[i]] == 'o' | alphabets[selected[i]] == 'u') {
                    vowel++;
                } else {
                    consonant++;
                }
            }

            if (vowel >= 1 && consonant >= 2) {
                for (int i = 1; i <= L; i++) {
                    sb.append(alphabets[selected[i]]);
                }
                sb.append('\n');
            }
        } else {
            for (int cand = selected[k - 1] + 1; cand <= C; cand++) {
                selected[k] = cand;
                recFunc(k + 1);
                selected[k] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        Arrays.sort(alphabets, 1, C + 1);
        recFunc(1);
        System.out.println(sb.toString());
    }
}
