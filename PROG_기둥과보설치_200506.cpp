/* 기둥과 보 설치 Level 3
https://programmers.co.kr/learn/courses/30/lessons/60061
 */
#include <algorithm>
#include <vector>

using namespace std;

#define x frame[0]
#define y frame[1]

bool isok(vector<vector<vector<bool>>>& map){ //조건을 만족하는지
    for (int i=0; i<map.size(); i++) {
        for (int j=0; j<map.size(); j++) {
            if (map[i][j][0] //기둥이 있는데
                && !(i == 0 || map[i-1][j][0] || map[i][j][1] || (j>0 && map[i][j-1][1]))) { //조건 만족 x
                return false;
            }
            if (map[i][j][1] //보가 있는데
                && !(map[i-1][j][0] || map[i-1][j+1][0] || ((j>0 && map[i][j-1][1]) && map[i][j+1][1]))) { //조건 만족 x
                return false;
            }
        }
    }
    return true;
}

vector<vector<int>> solution(int n, vector<vector<int>> build_frame) {
    vector<vector<vector<bool>>>map(n+1, vector<vector<bool>>(n+1, vector<bool>(2, false))); //[n+1][n+1][2] 인 3차원 배열, [][][0]은 기둥, [][][1]은 보
    for (vector<int> &frame : build_frame) {
        if (frame[3] == 1) { //설치
            if (frame[2] == 0) { //기둥
                if (y == 0 //바닥 위에 있거나
                    || map[y-1][x][0] //다른 기둥 위에 있거나
                    || map[y][x][1] || (x>0 && map[y][x-1][1]) //보의 한쪽 끝 부분 위에 있거나
                    ) {
                    map[y][x][0] = true;
                }
            }else{ //보
                if (map[y-1][x][0] || map[y-1][x+1][0] //다른 기둥 위에 있거나
                    || ((x>0 && map[y][x-1][1]) && map[y][x+1][1]) //양쪽 끝 부분이 다른 보와 동시에 연결
                    ) {
                    map[y][x][1] = true;
                }
            }
        }else{ //삭제
            map[y][x][frame[2]] = false; //일단 삭제해보고
            if(!isok(map)) map[y][x][frame[2]] = true; //조건 만족 x -> 원상복구
        }
    }
    
    //리턴 형식 맞추기
    vector<vector<int>> answer;
    answer.reserve(n*n*2);
    for (int i=0; i<=n; i++) {
        for (int j=0; j<=n; j++) {
            for (int k=0; k<2; k++) {
                if(map[i][j][k]) answer.push_back({j, i, k});
            }
        }
    }
    sort(answer.begin(), answer.end());
    return answer;
}
