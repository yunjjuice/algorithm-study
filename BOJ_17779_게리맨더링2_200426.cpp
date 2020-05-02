/* 게리맨더링 2 Gold 4
https://www.acmicpc.net/problem/17779
 */
#include <iostream>
#include <vector>
using namespace std;

int N;
vector<vector<int>> map;
int answer = 0x3f3f3f3f; //MAX값으로 초기화

void gerry(int x, int y, int d1, int d2){
    int people[6] = {0,}; //각 선거구의 인구
    
    //선거구 나누기
    vector<vector<int>> area(N+1, vector<int>(N+1));
    for (int r=1; r<=N; r++) {
        for(int c=1; c<=N; c++){
            if(r<x+d1 && c<=y){ //1번 선거구
                area[r][c] = 1;
            }else if(r<=x+d2 && y<c){ //2번 선거구
                area[r][c] = 2;
            }else if(x+d1 <= r && c<y-d1+d2){ //3번 선거구
                area[r][c] = 3;
            }else if(x+d2 < r && y-d1+d2<=c){ //4번 선거구
                area[r][c] = 4;
            }
        }
    }
    
    //경계선
    for (int i=0; i<=d1; i++) {
        area[x+i][y-i] = 5;
        area[x+d2+i][y+d2-i] = 5;
    }
    for (int i=0; i<=d2; i++) {
        area[x+i][y+i] = 5;
        area[x+d1+i][y-d1+i] = 5;
    }
    //경계선 안 쪽 채우기
    for (int i=1, r; i<d1+d2; i++) {
        r=x+i;
        for (int c=y-d1; c<y+d2; c++) {
            if (area[r][c] == 5) {
                for (c++; area[r][c] != 5; c++) {
                    area[r][c] = 5;
                }
            }
        }
    }
    
    //인구 구하기
    for (int r=1; r<=N; r++) {
        for(int c=1; c<=N; c++){
            people[area[r][c]] += map[r][c];
        }
    }
    
    //인구가 가장 많은 선거구, 적은 선거구 찾기
    int max = people[1], min = people[1];
    for (int i=2; i<=5; i++) {
        if(people[i] > max) max = people[i];
        if(people[i] < min) min = people[i];
    }
    if(answer > max-min)    answer = max-min; //최솟값 갱신
}

int main() {
    ios_base :: sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    
    cin >> N;
    map.assign(N+1, vector<int>(N+1));
    for (int i=1; i<=N; i++) {
        for (int j=1; j<=N; j++) {
            cin >> map[i][j];
        }
    }
    
    //d1, d2, x, y의 모든 경우의 수
    for (int d1 = 1; d1 < N; d1++) {
        for (int d2=1; d2<=N-d1+1; d2++) {
            for (int x=1; x <= N-d1-d2; x++) {
                for (int y=d1+1; y<=N-d2; y++) {
                    gerry(x, y, d1, d2);
                }
            }
        }
    }
    
    cout << answer;
}
