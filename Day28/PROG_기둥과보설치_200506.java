class Solution {
    static int N;
    static boolean[][][] map;
    static int cnt = 0;
    public int[][] solution(int n, int[][] build_frame) {
        N = n;
        map = new boolean[N+3][N+3][2]; //0:기둥, 1:보. + 양옆 비교때문에 배열 확장.
        for(int i=0; i<build_frame.length; i++){
            //System.out.println(cnt);
            int dx = build_frame[i][0] + 1;
            int dy = build_frame[i][1] + 1;
            int type = build_frame[i][2];
            int mode = build_frame[i][3];
            if(mode == 0){
                remove(dx,dy,type);
            }
            else{
                if(can_make(dx,dy,type)){
                    map[dx][dy][type] = true;
                    cnt++;
                }
            }
        }//end for.
        int[][] answer = new int[cnt][3];
        int top = 0;
        for (int i = 1; i <= N + 1; i++) {
            for (int j = 1; j <= N + 1; j++) {
                if (map[i][j][0]) answer[top++] = new int[] {i-1, j-1, 0};
                if (map[i][j][1]) answer[top++] = new int[] {i-1, j-1, 1};
            }
        }
        return answer;
    }//end main.
    public static boolean can_make(int x, int y, int type){
        if(type == 0){//기둥일때.
            if(y == 1 || map[x][y-1][0] || map[x][y][1] || map[x-1][y][1]){//바닥 + 기둥위 + 보위 2가지 가능.
                return true;
            }
            return false;
        }
        else{//보일때.
            if(map[x][y-1][0] || map[x+1][y-1][0] || (map[x-1][y][1] && map[x+1][y][1])){ //기둥위 2가지 + 보사이에 가능.
               return true;
            }
            return false;
        }
    }//end make.
    public static void remove(int x, int y, int type){
        map[x][y][type] = false;
        boolean can = true;
ex:     for (int i = 1; i <= N + 1; i++) {
            for (int j = 1; j <= N + 1; j++) {
                if (map[i][j][0] && !can_make(i, j, 0)) { //현재 설치된 기둥 + 조건성립 X.
                    can = false;
                    break ex;
                }
                if (map[i][j][1] && !can_make(i, j, 1)) { //현재 설치된 보 + 조건성립 X.
                    can = false;
                    break ex;
                }
            }
        }
        if(!can) map[x][y][type] = true;
        else cnt--;
    }//end remove.
}//end class.