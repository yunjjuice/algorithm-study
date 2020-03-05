/* 14502. 연구소 Gold 5
https://www.acmicpc.net/problem/14502
 */
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int N, M;
vector<pair<int, int>> viruses; //초기 바이러스 목록
int answer = 0;

//안전영역 사이즈 구하기
void getSize(vector<vector<int>> map) { //map은 call by value
    //바이러스 확산
    queue<pair<int, int>> q;
    int dx[4] = { -1, 0, 1, 0 }, dy[4] = { 0, -1, 0, 1 };
    for (int i = 0; i < viruses.size(); i++) {
        q.push(viruses[i]);
    }
    while (!q.empty()) { //BFS
        int x = q.front().first, y = q.front().second;
        q.pop();
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (map[nx][ny] == 0) { //빈 공간일때만 확산됨
                map[nx][ny] = 2;
                q.push({ nx, ny });
            }
        }
    }
    //0 개수 구하기
    int n = 0;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= M; j++) {
            if (map[i][j] == 0) n++;
        }
    }
    if (answer < n) answer = n; //정답 갱신
}

void dfs(vector<vector<int>> &map, int r, int c, int num) { //num은 세운 벽 개수
    if (num == 3) {
        getSize(map);
        return;
    }
    for (int j = c + 1; j <= M; j++) { //r행
        if (map[r][j] == 0) {
            map[r][j] = 1;
            dfs(map, r, j, num + 1);
            map[r][j] = 0; //원상복구
        }
    }
    for (int i = r + 1; i <= N; i++) { //r+1 ~ N행
        for (int j = 0; j <= M; j++) {
            if (map[i][j] == 0) {
                map[i][j] = 1;
                dfs(map, i, j, num + 1);
                map[i][j] = 0; //원상복구
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);   cin.tie(NULL);  cout.tie(NULL);
    viruses.reserve(10); //초기 바이러스는 최대 10개
    
    cin >> N >> M;
    vector<vector<int> > map(N + 2, vector<int>(M + 2, -1)); //바운더리 체크 안하려고 크게 잡음
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= M; j++) {
            cin >> map[i][j];
            if (map[i][j] == 2)  viruses.push_back({ i, j }); //바이러스 목록 미리 저장
        }
    } //end of input
    
    dfs(map, 1, 0, 0); //1,1부터 채우기
    cout << answer;
}
