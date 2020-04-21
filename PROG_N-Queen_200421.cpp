/* N-Queen Level 3
 https://programmers.co.kr/learn/courses/30/lessons/12952
 */

#include <vector>
using namespace std;

int dir[4][2] = {{-1,-1},{-1,1},{1,-1},{1,1}}; //대각선 방향
vector<vector<bool>> map;

//map[i][j]에 queen을 놓을 수 있는가 : 가로, 세로, 대각선에 다른 queen이 있으면 false, 없으면 true
bool available(int i, int j){
    for (int r=0; r<map.size(); r++) { //세로
        if(map[r][j])   return false;
    }
    
    for (int k=0; k<4; k++) { //대각선
        for (int l=1; ; l++) {
            int ni = i + dir[k][0]*l, nj = j+dir[k][1]*l;
            if(ni < 0 || ni >= map.size() || nj<0 || nj>=map.size())    break;
            if(map[ni][nj]) return false;
        }
    }
    return true;
}

int nqueen(int m, int n){
    if(n==m)    return 1;
    
    int ret = 0;
    //m번째 행에 queen 놓기 -> n가지 경우의 수
    for (int i=0; i<n; i++) {
        if(available(m, i)){ //놓을 수 있음
            map[m][i] = true;
            ret += nqueen(m+1, n);
            map[m][i] = false;
        }
    }
    return ret;
}

int solution(int n) {
    map.assign(n, vector<bool>(n,false));
    return nqueen(0, n);
}
