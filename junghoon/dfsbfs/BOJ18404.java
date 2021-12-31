package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ18404 {
	
	/* 현명한 나이트
	 * 
	 * 문제: NxN 크기 체스판의 특정한 위치에 하나의 나이트가 존재한다. 
	 *      이때 M개의 상대편 말들의 위치 값이 주어졌을 때, 각 상대편 말을 잡기 위한 나이트의 최소 이동 수를 계산하는 프로그램을 작성하시오.
	 *      나이트는 일반적인 체스(Chess)에서와 동일하게 이동할 수 있다. 현재 나이트의 위치를 (X,Y)라고 할 때, 나이트는 다음의 8가지의 위치 중에서 하나의 위치로 이동한다.
	 *      (X-2,Y-1), (X-2,Y+1), (X-1,Y-2), (X-1,Y+2), (X+1,Y-2), (X+1,Y+2), (X+2,Y-1), (X+2,Y+1)
	 *      N=5일 때, 나이트가 (3,3)의 위치에 존재한다면 이동 가능한 위치는 다음과 같다. 
	 *      나이트가 존재하는 위치는 K, 이동 가능한 위치는 노란색으로 표현하였다.
	 *      예를 들어 N=5, M=3이고, 나이트가 (2,4)의 위치에 존재한다고 가정하자. 
	 *      또한 상대편 말의 위치가 차례대로 (3,2), (3,5), (4,5)라고 하자. 이때 각 상대편 말을 잡기 위한 최소 이동 수를 계산해보자. 
	 *      아래 그림에서는 상대편 말의 위치를 E로 표현하였다. 단, 본 문제에서 위치 값을 나타낼 때는 (행,열)의 형태로 표현한다.
	 *      각 상대편 말을 잡기 위한 최소 이동 수는 차례대로 1, 2, 1이 된다.
	 * 
	 * 입력: 첫째 줄에 N과 M이 공백을 기준으로 구분되어 자연수로 주어진다. 
	 *      (1 ≤ N ≤ 500, 1 ≤ M ≤ 1,000) 
	 *      둘째 줄에 나이트의 위치 (X, Y)를 의미하는 X와 Y가 공백을 기준으로 구분되어 자연수로 주어진다. (1 ≤ X, Y ≤ N) 
	 *      셋째 줄부터 M개의 줄에 걸쳐 각 상대편 말의 위치 (A, B)를 의미하는 A와 B가 공백을 기준으로 구분되어 자연수로 주어진다. (1 ≤ A, B ≤ N)
	 *      단, 입력으로 주어지는 모든 말들의 위치는 중복되지 않으며, 나이트가 도달할 수 있는 위치로만 주어진다.
	 * 
	 * 출력: 첫째 줄에 각 상대편 말을 잡기 위한 최소 이동 수를 공백을 기준으로 구분하여 출력한다.
	 *      단, 출력할 때는 입력 시에 상대편 말 정보가 주어졌던 순서에 맞게 차례대로 출력한다.
	 * 
	 *     
	 * */
	
	 static int N, M;
	    static int dx[] = {-2, -2, -1, -1, 1, 1, 2, 2};
	    static int dy[] = {-1, 1, -2, 2, -2, 2, -1, 1};
	    static int arr[][];
	    static boolean visit[][];
	    static int cnt[][];
	    
	    static int atoi(String str) {
	        return Integer.parseInt(str);
	    }
	    
	    static void bfs(int x, int y) {
	        Queue<Integer> q = new ArrayDeque<>();
	        q.offer(x);
	        q.offer(y);
	        visit[x][y] = true;
	 
	        while (!q.isEmpty()) {
	            int X = q.poll();
	            int Y = q.poll();
	            for (int i = 0; i < 8; i++) {
	                int dX = X + dx[i];
	                int dY = Y + dy[i];
	                if(!isRangeTrue(dX,dY)) continue;
	                if(visit[dX][dY]) continue;
	                q.offer(dX);
	                q.offer(dY);
	                visit[dX][dY] = true;
	                cnt[dX][dY] = cnt[X][Y] + 1;
	            }
	        }
	    }
	 
	    static boolean isRangeTrue(int x, int y) {
	        return x > 0 && x <= N && y > 0 && y <= N;
	    }
	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        StringBuilder sb = new StringBuilder();
	 
	        N = atoi(st.nextToken());
	        M = atoi(st.nextToken());
	 
	        st = new StringTokenizer(br.readLine());
	 
	        int start = atoi(st.nextToken());
	        int end = atoi(st.nextToken());
	 
	        arr = new int[N + 1][N + 1];
	        visit = new boolean[N + 1][N + 1];
	        cnt = new int[N + 1][N + 1];
	 
	        bfs(start, end);
	 
	        for (int i = 0; i < M; i++) {
	            st = new StringTokenizer(br.readLine());
	            int s1 = atoi(st.nextToken());
	            int s2 = atoi(st.nextToken());
	            sb.append(cnt[s1][s2] + " ");
	        }
	 
	        System.out.println(sb);
	    }
	 
	
}
