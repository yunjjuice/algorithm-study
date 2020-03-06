/* 2105. 디저트 카페 모의
 
 */
#include <iostream>
using namespace std;

int N;
int map[20][20];
bool desert[101]; //먹은 디저트 종류
int answer;

int dx[4] = { 1, 1, -1, -1 };
int dy[4] = {-1,1,1,-1};

void dfs(int i, int j, int dir, int len, int di, int dj) { //di, dj는 목표지점 (도착지점)
    if (i < 0 || j < 0 || i >= N || j >= N || dir == 4) return; //범위 초과
    if(desert[map[i][j]])   return; //이미 먹은 디저트
    if(dir == 3 && i == di && j == dj){ //도착
        if(answer < len)    answer = len;
        return;
    }
    desert[map[i][j]] = true;
    dfs(i + dx[dir], j+dy[dir], dir, len+1, di, dj); //동일한 진행 방향으로
    dir++;
    dfs(i + dx[dir], j+dy[dir], dir, len+1, di, dj); //다음 진행 방향으로
    desert[map[i][j]] = false;
}

int main() {
    int TC;
    scanf("%d", &TC);
    for (int testCase = 1; testCase <= TC; testCase++) {
        scanf("%d", &N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                scanf("%d", &map[i][j]);
            }
        } //end of input

        answer = -1;
        for (int i = 0; i < N - 2; i++) {
            for (int j = 1; j < N - 1; j++) { //탐방 시작점
                dfs(i + 1, j - 1, 0, 1, i, j);
            }
        }

        printf("#%d %d\n", testCase, answer);
    }
}
