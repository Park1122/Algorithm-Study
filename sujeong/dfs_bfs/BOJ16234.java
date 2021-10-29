package sujeong.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16234 {
    // https://www.acmicpc.net/problem/16234

    // 소요시간 >>
    // 약 1시간 30분

    // 아이디어 >>
    // 크게 보면 3가지 일을 하면 된다.
    // 0. 이동이 가능한 곳이 있는지 확인 (isEnd()->끝났다면 while을 나와 day출력 후 종료. 아니라면 1을 진행)
    // 1. 인구수가 L~R인 나라를 그룹으로 묶기 (makeGroup())
    // 2. 그룹 내 나라들의 인구수를 그룹 평균으로 셋해준 뒤 다음 날짜로 넘겨 0부터 반복

    // 에러로그 >>
    // X

    // Constants
    // Diffs -> 12시방향을 기준으로 시계방향
    private static final int[] xDiff = {0,1,0,-1};
    private static final int[] yDiff = {1,0,-1,0};

    // Variable
    private static int N,L,R; // 땅의 한변 길이, 허용 인구이동 최소수, 허용 인구이동 최다수
    private static Country[][] orgArr; // [y][x]에 따라 나라(Country클래스)를 담은 배열
    private static boolean[][] visited; // [y][x]위치의 나라의 방문 여부를 담은 배열

    // Main
    public static void main(String[] args) throws IOException{
        // Input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        orgArr = new Country[N][N];
        for(int y=0;y<N;y++){
            st = new StringTokenizer(reader.readLine()," ");
            for(int x=0;x<N;x++){
                // 좌표(x,y),인구수를 담은 클래스 Country를 만들어 orgArr에 넣어줌.
                orgArr[y][x] = new Country(x,y,Integer.parseInt(st.nextToken()));
            }
        }

        // Initialize
        int day = 0; // 인구이동이 이뤄진 날짜

        // Logic
        while(!isEnd()){  // 인구 이동이 더이상 이뤄질 수 없을 때까지 반복

            // sub-Initialize
            Queue<GroupCountry> groupQueue = new LinkedList<>(); // 그룹으로 묶인 나라들을 가져와 평균계산 시 사용하기 위해 만든 큐
            visited = new boolean[N][N]; // 이번 날짜에 인구이동 그룹검사를 했는지 여부

            // make group
            for(int y=0;y<N;y++){
                for(int x=0;x<N;x++){
                    if (visited[y][x]) continue; // 방문한 적 있다면 넘어감.
                    groupQueue.add(makeGroup(orgArr[y][x]));
                }
            }

            while(!groupQueue.isEmpty()) {
                // get a country
                GroupCountry gc = groupQueue.poll();

                // calculate group average
                if(gc.size()==0) continue; // for avoid error(num/0)
                int avg = gc.sum()/gc.size();

                // set the result in the group
                for(Country subCountry : gc){
                    orgArr[subCountry.y][subCountry.x].population = avg;
                }
            }

            // reinitialize
            day++; // passing the day
        }

        // Output
        System.out.println(day);

    }

    // Method - 인구 이동이 일어날 수 있는 곳이 남았는지 확인하는 함수
    private static boolean isEnd(){
        for(int y=0;y<N;y++){
            for(int x=0;x<N;x++){
                for(int i=0;i<2;i++){
                    // make value tmpCountry coordinate
                    Country country = orgArr[y][x];
                    int tmpX =country.x+xDiff[i];
                    int tmpY =country.y+yDiff[i];

                    // 범위 체크
                    if(!checkBound(tmpX,tmpY)) continue;

                    // 지금 나라와 tmp 나라가 이동이 가능하다 = 더 계산해야할 게 남았다 => false 리턴
                    if(isAvailableMoving(country.population , orgArr[tmpY][tmpX].population)) return false;
                }
            }
        }
        // 더이상 인구이동이 일어날 수 없는 경우
        return true;
    }

    // Method - 인구이동이 가능한 나라끼리의 그룹을 리턴하는 함수
    private static GroupCountry makeGroup(Country country){
        GroupCountry group = new GroupCountry();
        for (int i = 0; i < 4; i++) {
            // make tmpCountry coordinate
            int tmpX =country.x+xDiff[i];
            int tmpY =country.y+yDiff[i];

            // 좌표평면 이외거나 방문한적 있다면 넘어감.
            if (!checkBound(tmpX,tmpY) || visited[tmpY][tmpX]) continue;

            // 지금 나라와 tmp 나라가 이동이 가능하다면
            if(isAvailableMoving(country.population,orgArr[tmpY][tmpX].population)){
                // 방문체크
                visited[tmpY][tmpX] = true;

                // tmp나라를 그룹에 추가하고 tmp나라의 그룹 국가를 가져와 묶어줌.
                group.add(orgArr[tmpY][tmpX]);
                group.addAll(makeGroup(orgArr[tmpY][tmpX]));
            }
        }
        return group;
    }

    // sub-Method - 입력받은 좌표값이 좌표평면 상에 있는가?
    private static boolean checkBound(int tmpX, int tmpY){ return 0<=tmpX && tmpX<N && 0<=tmpY && tmpY<N;}

    // sub-Method - 입력받은 두 나라가 이동이 가능한 상태인가?
    public static boolean isAvailableMoving(int onePop, int otherPop){
        int pop = Math.abs(onePop-otherPop);
        return L<=pop && pop<=R;
    }

    // Inner Class
    private static class Country{
        // x좌표, y좌표, 인구수
        int x,y,population;
        public Country(int x, int y, int population){
            this.x=x;
            this.y=y;
            this.population=population;
        }
    }

    private static class GroupCountry extends ArrayList<Country>{
        // 해당 그룹 내 총 인구수를 리턴 (계산 및 결과 깔끔하게 하기위해 사용)
        public int sum(){
            int sum = 0;
            for(Country c:this){sum+=c.population;}
            return sum;
        }
    }
}
