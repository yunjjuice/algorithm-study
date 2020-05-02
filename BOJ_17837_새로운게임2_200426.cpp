/* 새로운 게임 2 Gold 2
https://www.acmicpc.net/problem/17837
 */
#include <iostream>
#include <vector>
using namespace std;

class Horse{
public:
    int x, y; //0~N
    int dir; //0~4
    
    Horse(int x, int y, int dir){
        this->x = x;
        this->y = y;
        this->dir = dir;
    }
};

int N, K;
int answer = 0;
vector<vector<int>> map; //맵 정보
vector<vector<vector<int>>> hmap; //각 칸에 몇 번 말이 올라가있는지
int dir[4][2] = {{0,1},{0,-1},{-1,0},{1,0}};
vector<Horse> horses; //말 목록

//h번 말을 (x, y)번 위치로 옮기기 (위에 있는 말들도 같이)
void move(int h, int x, int y, int nx, int ny, bool reverse){
    int index = 0;
    for (; index < hmap[x][y].size(); index++) {
        if(hmap[x][y][index] == h)  break;
    }
    for (int i=index; i<hmap[x][y].size(); i++) {
        horses[hmap[x][y][i]].x = nx;
        horses[hmap[x][y][i]].y = ny;
    }
    if (!reverse) { //그대로 옮기기
        hmap[nx][ny].insert(hmap[nx][ny].end(), hmap[x][y].begin()+index, hmap[x][y].end());
    }else{ //뒤집어서 옮기기
        hmap[nx][ny].insert(hmap[nx][ny].end(), hmap[x][y].rbegin(), hmap[x][y].rend()-index);
    }
    hmap[x][y].erase(hmap[x][y].begin()+index, hmap[x][y].end());
}

//종료조건을 만나면 false 리턴
bool turn(){
    for (int h=0; h<K; h++) {
        int nx = horses[h].x + dir[horses[h].dir][0], ny = horses[h].y + dir[horses[h].dir][1];
        if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 2) { //벗어남 or 파란색
            horses[h].dir = horses[h].dir < 2? (horses[h].dir == 0? 1:0) : (horses[h].dir == 2? 3:2); //방향 뒤집기
            nx = horses[h].x + dir[horses[h].dir][0];
            ny = horses[h].y + dir[horses[h].dir][1];
        }
        
        if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 2) { //벗어남 or 파란색 -> 가만히
            continue; //do nothing
        }else if(map[nx][ny] == 0){ //흰색
            move(h, horses[h].x, horses[h].y, nx, ny, false);
        }else if(map[nx][ny] == 1){ //빨간색
            move(h, horses[h].x, horses[h].y, nx, ny, true);
        }
        
        if(hmap[nx][ny].size() >= 4) return false;
    }
    return true;
}

int main() {
    ios_base :: sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> N >> K;
    map.assign(N, vector<int>(N));
    hmap.assign(N, vector<vector<int>>(N));
    horses.reserve(K);
    for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++) {
            cin >> map[i][j];
        }
    }
    for (int i=0; i<K; i++) {
        int x, y, d;
        cin >> x >> y >> d;
        horses.push_back(Horse(x-1, y-1, d-1)); //인덱스 0부터 시작할 것
        hmap[x-1][y-1].push_back(i);
    }
    
    //solve
    while(answer <= 1000){
        answer++;
        if(!turn()) break;
    }
    cout << (answer > 1000? -1 : answer);
}
