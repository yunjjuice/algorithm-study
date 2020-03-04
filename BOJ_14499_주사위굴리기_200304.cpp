/* 14499. 주사위 굴리기 Gold 5
https://www.acmicpc.net/problem/14499
-> 시뮬
 */
#include <iostream>
#include <algorithm>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int N, M, x, y, K;
    cin >> N >> M >> x >> y >> K;
    int map[N][M];
    for (int i=0; i<N; i++) {
        for (int j=0; j<M; j++) {
            cin >> map[i][j];
        }
    }

    int dx[5] = { -1, 0, 0, -1, 1 }, dy[5] = { -1, 1, -1, 0, 0 }; //0은 쓰지 않고, 1동 2서 3북 4남
    int top = 0, left = 0, right = 0, back = 0, front = 0, bottom = 0; //주사위 각 면의 숫자
    int command;
    while (K-- > 0) { //end of input
        cin >> command;
        int nx = x + dx[command], ny = y + dy[command];
        if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue; //지도의 바깥으로는 이동할 수 없음
        x = nx; y = ny; //칸 이동
        
        int tmp = top;
        switch(command){
            case 1: //동쪽으로 이동
                top = left; left = bottom; bottom = right; right = tmp; break;
            case 2: //서쪽으로 이동
                top = right; right = bottom; bottom = left; left = tmp; break;
            case 3: //북쪽으로 이동
                top = front; front = bottom; bottom = back; back = tmp; break;
            case 4: //남쪽으로 이동
                top = back; back = bottom; bottom = front; front = tmp;
        };
        
        if(map[x][y] == 0){ //주사위 바닥면 값이 칸에 복사
            map[x][y] = bottom;
        }else{ //칸에 있는 수가 바닥으로 복사, 칸에 있는 값은 0으로
            bottom = map[x][y];
            map[x][y] = 0;
        }
        cout << top << '\n'; //상단 값 출력
    }
}
