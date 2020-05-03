/* 17142. 연구소 3 Gold 4
https://www.acmicpc.net/problem/17142
 */
#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;

int answer = 0x3f3f3f3f; //INF
int N, M;
int emptyNum;
vector<vector<int>> map;
vector<pair<int, int>> possibleVirusLocations;
vector<int> virusIn;

int dir[4][2] = {{-1,0}, {1,0}, {0,-1}, {0,1}};

void bfs(){
    int time = 0, fillNum = 0;
    queue<pair<int, int>> q;
    vector<vector<bool>> visit(map.size(), vector<bool>(map.size(), false));
    
    //초기 위치 q에 넣기
    for (int i=0; i<virusIn.size(); i++) {
        if (virusIn[i]) {
            q.push(possibleVirusLocations[i]);
            visit[possibleVirusLocations[i].first][possibleVirusLocations[i].second] = true;
        }
    }
    
    while (!q.empty() && fillNum < emptyNum) {
        for (int q_size = q.size(); q_size>0; q_size--) {
            int x = q.front().first, y = q.front().second;
            q.pop();
            for (int i=0; i<4; i++) {
                int nx = x+dir[i][0], ny = y+dir[i][1];
                if(map[nx][ny] != 1 && !visit[nx][ny]){
                    if (map[nx][ny] == 0) {
                        fillNum++;
                    }
                    visit[nx][ny] = true;
                    q.push({nx, ny});
                }
            }
        }
        time++;
    }
    
    //빈 칸 없는지 확인
    if (fillNum < emptyNum) {
        return;
    }
    
    //정답 갱신
    if (time < answer) {
        answer = time;
    }
}

int main() {
    ios_base :: sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> N >> M;
    map.assign(N+2, vector<int>(N+2, 1));
    possibleVirusLocations.reserve(10);
    for (int i=1; i<=N; i++) {
        for (int j=1; j<=N; j++) {
            cin >> map[i][j];
            if (map[i][j] == 0) {
                emptyNum++;
            }else if (map[i][j] == 2) {
                possibleVirusLocations.push_back({i, j});
            }
        }
    }
    
    virusIn.assign(possibleVirusLocations.size(), 1);
    for (int i=0; i<possibleVirusLocations.size()-M; i++) {
        virusIn[i]=0;
    }
    
    do{
        bfs();
    }while (next_permutation(virusIn.begin(), virusIn.end()));
    
    cout << (answer == 0x3f3f3f3f? -1 : answer);
}
