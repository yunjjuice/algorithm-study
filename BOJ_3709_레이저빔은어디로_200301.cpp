/* 3709. 레이저빔은 어디로 Gold 5
https://www.acmicpc.net/problem/3709
-> 그래프 탐색이라네요
 */

#include <iostream>
#include <algorithm>
using namespace std;

#define go      X += dx[dir]; Y += dy[dir];
#define valid   (X == 0 || Y == 0 || X > N || Y > N)

int main() {
    int Tnum;
    int dx[4] = { -1, 0, 1, 0 }, dy[4] = { 0, 1, 0, -1 }; //up, right, down, left (우측으로 90도씩 꺾는 순서대로)
    scanf("%d", &Tnum);
    for (; Tnum > 0; Tnum--) {
        bool map[51][51]; //true면 거울 있음
        fill(&map[0][0], &map[50][51], false); //initialize
        int N, r; //보드 크기, 거울 개수
        scanf("%d %d", &N, &r);
        for (int i = 0; i < r; i++) {
            int tx, ty;
            scanf("%d %d", &tx, &ty);
            map[tx][ty] = true;
        }
        int X, Y; //laser의 위치
        scanf("%d %d", &X, &Y);
        //end of input
        
        int dir = (X > N ? 0 : (Y == 0 ? 1 : (X == 0 ? 2 : 3)));
        //사이클 처리?
        while (true) {
            go //전진하고
            if (valid) break; //빠져나왓어
            if (map[X][Y]) //거울있음
                dir = (dir == 3 ? 0 : dir + 1); //우향우
        }
        
        //output
        printf("%d %d\n", X, Y);
    } //end of for testcases
}
