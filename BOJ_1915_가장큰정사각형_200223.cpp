/* 1915. 가장 큰 정사각형 Gold 5
 https://www.acmicpc.net/problem/1915
 -> DP

정사각형을 왜 dp로 만드러 ~~~!
 내 위치가 (i, j)이고 값이 1일 때,
    (i-1, j-1), (i-1, j), (i, j-1) 세 칸의 값 중 최솟값에 1 더한 값이 내가 가질 수 있는 최대 크기 (한 변의 길이).
 */
#include <iostream>
#include <algorithm>
using namespace std;

int main()
{
    int map[1001][1001] = {0,};
    int n, m;
    cin >> n >> m;
    char c;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> c;
            map[i][j] = c-'0';
        }
    } //end of input

    //정사각형 크기 입력
    for (int i = 1; i < n; i++) {
        for (int j = 1; j < m; j++) {
            if(map[i][j] == 1)
                map[i][j] = min({ map[i][j - 1], map[i - 1][j - 1], map[i - 1][j] }) + 1;
        }
    }

    //가장 큰 값 찾기
    int ans = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if(ans < map[i][j])   ans = map[i][j];
        }
    }
    
    cout << ans*ans;
}
