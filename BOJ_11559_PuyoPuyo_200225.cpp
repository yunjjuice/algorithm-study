/* 11559. Puyo Puyo Gold 5
 https://www.acmicpc.net/problem/11559
 -> 시뮬 ~
 */

#include <iostream>
#include <string>
#include <queue>
using namespace std;

string map[12];
bool checked[12][6];

int dx[4] = { -1, 0, 1, 0 }, dy[4] = { 0, -1, 0, 1 };
bool puyo(int i, int j) {
    int num = 0;
    queue<pair<int, int>> q;
    queue<pair<int, int>> remove;
    q.push({ i, j });
    while (!q.empty()) {
        int r = q.front().first, c = q.front().second;
        q.pop();
        //4방향 탐색
        for (int n = 0; n < 4; n++) {
            if (dx[n] + r >= 0 && dx[n] + r < 12 && dy[n] + c >= 0 && dy[n] + c < 6 && //바운더리 체크
                !checked[dx[n] + r][dy[n] + c] && //이미 q에 넣음
                map[dx[n] + r][dy[n] + c] == map[i][j]) {
                num++;
                remove.push({ dx[n] + r, dy[n] + c });
                q.push({ dx[n] + r, dy[n] + c });
                checked[dx[n] + r][dy[n] + c] = true;
            }
        }
    }
    if (num < 4)
        return false;
    map[i][j] = '.';
    while (!remove.empty()) {
        map[remove.front().first][remove.front().second] = '.';
        remove.pop();
    }
    return true;
}

bool bomb() { //터지면 true, 안터지면 false
    bool flag = false;
    fill(&checked[0][0], &checked[11][6], false);
    for (int i = 0; i < 12; i++) {
        if (map[i] == "......")
            continue; //compare
        for (int j = 0; j < 6; j++) {
            if (map[i][j] == '.' || checked[i][j])
                continue;
            if (puyo(i, j))
                flag = true;
        }
    }
    return flag;
}

int findBottomDot(int i, int j) {
    for (; i >= 0; i--) {
        if (map[i][j] == '.') {
            return i;
        }
    }
    return -1;
}

void down() { //터지면 아래로 내리기
    for (int j = 0; j < 6; j++) {
        int bottom = findBottomDot(11, j); //find bottom '.'
        if (bottom == -1)
            continue; //모두 뿌요가 차있음
        for (int i = bottom - 1; i >= 0; i--) {
            if (map[i][j] == '.')
                continue;
            map[bottom][j] = map[i][j];
            map[i][j] = '.';
            bottom = findBottomDot(bottom - 1, j);
        }
    }
}

int main() {
    for (int i = 0; i < 12; i++) {
        cin >> map[i];
    }

    int ans = 0;
    while(bomb()) {
        down();
        ans++;
    }
    
    cout << ans;
}
