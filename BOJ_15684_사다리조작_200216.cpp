/* 15684. 사다리 조작
 https://www.acmicpc.net/problem/15684
 
 - 초기 가로선이 0개면 0 출력
 - 가로선을 추가할 수 있는 모든 경우 탐색
 - 가로선 개수는 0개 ~ 3개
 */

#include <iostream>
using namespace std;

int N, M, H; //세로선의 개수 N, 가로선의 개수 M, 가로선을 놓을 수 있는 위치의 개수 H
int map[31][11];

bool validCheck(){
    for (int i = 1; i <= N; i++) {
        int now = i;
        for (int h = 1; h <= H; h++) {
            if (map[h][now - 1])
                now--; //왼쪽으로 이동
            else if (map[h][now])
                now++; //오른쪽으로 이동
        } //끝까지 내려감
        if (now != i)
            return false; //정답 x
    }
    return true;
}

void addGaro(int count, int target, int a, int b){
    if (count == target) {
        if (validCheck()) {
            cout << target;
            exit(0);
        }
        return;
    }
    //라인 넣기
    for (int j = b; j <= N - 1; j++) {
        if (!map[a][j - 1] && !map[a][j]) { //삽입 가능
            map[a][j] = true;
            addGaro(count + 1, target, a, j + 1);
            map[a][j] = false;
        }
    }
    for (int i = a + 1; i <= H; i++) {
        for (int j = 1; j <= N - 1; j++) {
            if (!map[i][j - 1] && !map[i][j]) { //삽입 가능
                map[i][j] = true;
                addGaro(count + 1, target, i, j + 1);
                map[i][j] = false;
            }
        }
    }
}

int main(){
    // input
    cin >> N >> M >> H;
    for (int m = 0; m < M; m++) {
        int a, b;
        cin >> a >> b;
        map[a][b] = true;
    }
    
    // solve
    if (M == 0 || validCheck()) { //바로 끝내기
        cout << 0;
        return 0;
    }
    
    for (int i=1; i<4; i++) {
        addGaro(0, i, 1, 1);
    }
    
    // output
    cout << -1;
}
