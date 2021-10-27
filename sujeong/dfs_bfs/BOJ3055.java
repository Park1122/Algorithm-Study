package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.util.*;

public class BOJ3055 {
    // https://www.acmicpc.net/problem/3055

    // 소요시간 >>
    // 16:30 ~ 19:24 + 21:20 ~ 22:35 (총 약 4시간)

    // 아이디어 >>
    // 지도(0=빈곳, 1=돌, 2=물 3=시작점, 4=도착점)으로 int배열인 map에 저장.
    // 해당 문제는 bfs로, 큐에 상황(Situation 클래스)을 담아서
    // 각 상황에서 물과 고슴도치의 이동에 따른 상황을 큐에 담아가며
    // 고슴도치(S)가 목적지(D)에 도달할 때까지 탐색을 진행함.
    // 탐색 결과에 따라 고슴도치가 목적지에 도달한다면 걸린시간(second)을 출력하고
    // 도달하지 못한다면 KAKTUS룰 출력한다.

    // 에러로그 >>
    // 틀렸습니다 ->
    // clone메소드는 shallow copy라서 reference가 저장됨.
    // 때문에 bfs로 고슴도치나 물을 이동하는게 쌓이며 에러가 발생.
    // 이걸 잡는데 꽤 오랜 시간이 들었다.
    // 에러 잡는데 유용했던 질문 검색 : https://www.acmicpc.net/board/view/33209 (이곳의 맵이 안풀렸음... -> waterCrush로 해결)


    // Variable
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int r,c;
    private static Queue<Situation> queue;
    private static Point dPoint;
    private static boolean[][] visited;
    private static int ans = -1;

    // Constants
    private final static String[] mapSymbol = {".","X","*","S","D"}; // 기호를 인덱스로 바꾸기위해 만든 배열
    private final static int[] xDiff = {0,1,0,-1};
    private final static int[] yDiff = {1,0,-1,0};

    // Main
    public static void main(String[] args) throws IOException{
        // Input - basic information
        String[] basicInfo = reader.readLine().split(" ");
        r = Integer.parseInt(basicInfo[0]);
        c = Integer.parseInt(basicInfo[1]);

        // Input - initialize map & points
        int[][] map = new int[r][c];
        Point sPoint = null;
        LinkedHashSet<Point> wPoints = new LinkedHashSet<>();

        // Input - make initial Map
        for(int i=0;i<r;i++){
            // read row
            String line = reader.readLine();
            for(int j=0;j<c;j++){
                // read col
                String str = String.valueOf(line.charAt(j));

                // add values using mapSymbol
                for(int s=0;s<mapSymbol.length;s++){
                    if(mapSymbol[s].equals(str)){
                        // 심볼에 따라 인덱스 s를 map에 저장
                        map[i][j] = s;

                        // 시작점, 물의 위치, 목적지를 항상 맵에서 읽기엔 비효율적이니 미리 포인트를 따냄.
                        if (s<=1) break;
                        else if(s==2) wPoints.add(new Point(i,j));
                        else if(s==3) sPoint = new Point(i,j);
                        else dPoint = new Point(i,j);
                        break;
                    }
                }
            }
        }

        // Initialize - 방문여부
        visited = new boolean[r][c];
        assert sPoint != null; // 안전장치로 넣음.
        visited[sPoint.x][sPoint.y]=true;

        // Initialize - 상황(고슴도치의 위치, 물이 퍼진 정도, 현재 맵상태, 시간 상태)을 담을 큐 (bfs를 위해 큐사용)
        queue = new LinkedList<>();
        Situation s = new Situation(sPoint,wPoints,map,0);
        queue.add(s);

       // Logic
       while(!queue.isEmpty()) { // 큐가 빌때까지 logic을 반복
           logic();
       }

       // Output
        System.out.println(ans==-1?"KAKTUS":ans); // 결과가 없다면(-1) KAKTUS를 출력하고 아니라면 결과값(ans)를 출력

    }

    private static void logic(){
        // initialize
        Point waterCrushed = null;

        // get Situation
        Situation tmpSit = queue.poll();
        // TEST
        // System.out.println(tmpSit.toString());

        // set Situation
        Point sPoint = tmpSit.sPoint;
        LinkedHashSet<Point> copyWPoints =tmpSit.wPoints;
        int[][] map = cloneMap(tmpSit.map);
        int second = tmpSit.second+1;

        // move Water
        LinkedHashSet<Point> newWPoints = cloneWater(tmpSit.wPoints);
        for (Point w : copyWPoints) {
            for (int i = 0; i < 4; i++) {
                int tmpX = w.x + xDiff[i];
                int tmpY = w.y + yDiff[i];

                // 범위 체크
                if(!checkBound(tmpX,tmpY)) continue;

                // 빈 곳이라면 물을 퍼뜨려 map과 newWPoint(새로운 물의 위치 저장 셋)에 임시 좌표(tmpX,tmpY)를 저장
                if (map[tmpX][tmpY] == 0) {
                    map[tmpX][tmpY] = 2;
                    newWPoints.add(new Point(tmpX, tmpY));
                // 고슴도치가 현재 있는 위치라면, 고슴도치가 곧 이동할테니까 이동 후 map과 newWPoint 변경을 위해 처리
                } else if(map[tmpX][tmpY] == 3) {
                    waterCrushed = new Point(tmpX,tmpY);
                }
            }
        }

        // move Hedgehog
        for (int i = 0; i < 4; i++) {
            int tmpX = sPoint.x + xDiff[i];
            int tmpY = sPoint.y + yDiff[i];

            // 범위 체크
            if(!checkBound(tmpX,tmpY)) continue;

            // 종료 조건 - 목적지에 도달했다면 ans 값을 세팅하고 큐를 비워 while을 탈출하게 한다.
            if(dPoint.x == tmpX && dPoint.y == tmpY) { // 종료조건
                ans = second;
                queue = new LinkedList<>();
                return;
            }


            // 방문한 적 없는 빈공간이라면(고슴도치가 갈 수 있는 곳은 D와.뿐인데 D는 이미 처리해서)
            if(map[tmpX][tmpY]==0 && !visited[tmpX][tmpY]){
                // waterCrush 처리
                if(waterCrushed!=null) {
                    // waterCrushed(고슴도치가 있는 곳에 물이 와야하는 상태)라면,
                    // move Water 과정에서 못이동한 물의 이동을 처리
                    map[waterCrushed.x][waterCrushed.y] = 2;
                    newWPoints.add(waterCrushed);
                }
                // make next Situation (방문여부, 고슴도치 이동)
                visited[tmpX][tmpY] = true;
                map[sPoint.x][sPoint.y] = 0; // 현재 고슴도치가 있는 좌표는 빈곳으로
                map[tmpX][tmpY] = 3; // 고슴도치가 이동할 좌표는 고슴도치로

                // add next Situation into queue
                Situation next = new Situation(new Point(tmpX, tmpY), newWPoints, map, second);
                queue.add(next);
            }
        }
    }

    // Method
    // 범위체크용 함수
    private static boolean checkBound(int x,int y){ return 0<=x && x<r && 0<=y && y<c;}

    // water point 복제하는 함수 (for deep copy)
    private static LinkedHashSet<Point> cloneWater(LinkedHashSet<Point> wPoints){
        LinkedHashSet<Point> clone = new LinkedHashSet<>();
        for(int i=0;i<wPoints.size();i++) clone.add(wPoints.iterator().next());
        return clone;
    }

    // map 복제하는 함수 (for deep copy)
    private static int[][] cloneMap(int[][] map){
        int[][] clone = new int[r][c];
        for(int i=0;i<r;i++){
            if (c >= 0) System.arraycopy(map[i], 0, clone[i], 0, c);
        }
        return clone;
    }

    // Inner Class
    // 좌표값을 저장하기 위해 만든 클래스
    private static class Point implements Cloneable{
        // Variable
        int x,y;

        // Constructor
        public Point(int x,int y){
            this.x=x;
            this.y=y;
        }

        // Method - for test
        public String toString(){ return "("+x+" , "+y+")";}

    }

    // 상황(고슴도치의 위치, 물이 있는 좌표, 현재 지도, 시간)을 한번에 저장하기 위해 만든 클래스
    private static class Situation{
        // Variable
        Point sPoint;
        LinkedHashSet<Point> wPoints; // 물이동시킬때 물이 있는 곳을 찾기위해 모든 곳을 방문하긴 불필요해서.
        int[][] map;
        int second;

        // Constructor
        public Situation(Point sPoint, LinkedHashSet<Point> wPoints, int[][] map, int second){
            this.sPoint = sPoint;
            this.wPoints = wPoints;
            this.map = map;
            this.second = second;
        }

        // Method - for test
        public String toString(){
            String retVal = "sPoint : "+sPoint+"/ second : "+second+"\n"+"wPoints :\n";
            for(Point p : wPoints) retVal+= p.toString()+" ";
            retVal+="\nmap :\n";
            for(int x=0;x<r;x++) retVal+= Arrays.toString(map[x])+"\n";
            return  retVal;
        }
    }
}


