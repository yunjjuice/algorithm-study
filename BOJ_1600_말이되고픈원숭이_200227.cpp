/* 1600. 말이 되고픈 원숭이 Gold 5
 https://www.acmicpc.net/problem/1600
 -> BFS
 */

#include <iostream>
#include <algorithm>
#include <queue>
using namespace std;

class Pos{
public:
    int x, y, k;
    
    Pos(int _x, int _y, int _k){
        x = _x; y = _y; k = _k;
    }
};

int main() {
    int K, W, H;
    cin >> K >> W >> H;
    bool map[204][204]; //바운더리 체크 안하려고. 시작점은 [2][2], 도착점은 [H+1][W+1]
    fill(&map[0][0], &map[203][204], true);
    bool check[204][204][31] = {false,}; //방문 체크, 0:

    for (int i=2; i<H+2; i++) {
        for (int j=2; j<W+2; j++) {
            cin >> map[i][j];
        }
    }
    
    int dx[12] = { -1, 0, 1, 0, -1, -2, 1, 2, -1, -2, 1, 2 };
    int dy[12] = { 0, -1, 0, 1, -2, -1, -2, -1, 2, 1, 2, 1 };
    
    queue<Pos> q;
    q.push({2, 2, 0});
    check[2][2][0] = true;
    int len = 0;
    while(!q.empty()){
        int size_q = (int)q.size();
        while(size_q-- > 0){
            int x = q.front().x, y = q.front().y, k = q.front().k;
            q.pop();
            if(x == H+1 && y == W+1){ //도착함
                cout << len;
                return 0;
            }
            int nx, ny, nk = k+1, i=0;
            for (; i<4; i++) { //상하좌우 1칸 이동
                nx = x+dx[i];
                ny = y+dy[i];
                if(!map[nx][ny] && !check[nx][ny][k]){
                    check[nx][ny][k] = true;
                    q.push({nx, ny, k});
                }
            }
            
            if(nk > K)   continue; //말처럼 이동 못해
            for (; i<12; i++) { //말처럼 이동
                nx = x+dx[i];
                ny = y+dy[i];
                if(!map[nx][ny] && !check[nx][ny][nk]){
                    check[nx][ny][nk] = true;
                    q.push({nx, ny, nk});
                }
            }
        }
        len++;
    }
    
    cout << -1;
}
