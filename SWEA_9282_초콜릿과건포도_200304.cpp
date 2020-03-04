/* 9282. 초콜릿과 건포도 D4
 https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AW9j-qfacIEDFAUY
 -> 완탐 + 메모이제이션 & 부분합
 */
#include <iostream>
#include <algorithm>
using namespace std;

#define INF 0x3f3f3f3f
int cache[51][51][51][51]; //메모이제이션
int raisins[50][50];

int dfs(int sr, int sc, int er, int ec) {
    if (er - sr == 1 && ec - sc == 1) // 1*1임
        return 0;
    if (cache[sr][sc][er][ec] != -1)    return cache[sr][sc][er][ec];

    int n = raisins[er - 1][ec - 1]; //이번 자르기에서 지불해야 할 개수
    if (sr != 0)    n -= raisins[sr - 1][ec - 1];
    if (sc != 0)    n -= raisins[er - 1][sc - 1];
    if (sr != 0 && sc != 0) n += raisins[sr - 1][sc - 1];

    //모든 경우의 수만큼 잘라보기
    int ret = INF;
    for (int r = sr + 1; r < er; r++) { //가로로 자르기
        int tmp = dfs(sr, sc, r, ec) + dfs(r, sc, er, ec);
        if (ret > tmp)  ret = tmp;
    }
    for (int c = sc + 1; c < ec; c++) { //세로로 자르기
        int tmp = dfs(sr, sc, er, c) + dfs(sr, c, er, ec);
        if (ret > tmp)  ret = tmp;
    }

    cache[sr][sc][er][ec] = ret + n;
    return cache[sr][sc][er][ec];
}

int main() {
    int TC;
    scanf("%d", &TC);
    for (int testCase = 1; testCase <= TC; testCase++) {
        int n, m;
        scanf("%d %d", &n, &m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                scanf("%d", &raisins[i][j]);
            }
        } //end of input

        //initialize
        fill(&cache[0][0][0][0], &cache[50][50][50][51], -1);
        for (int i = 0; i < n; i++)
            for (int j = 1; j < m; j++)
                raisins[i][j] += raisins[i][j - 1];
        for (int i = 1; i < n; i++)
            for (int j = 0; j < m; j++)
                raisins[i][j] += raisins[i - 1][j];

        printf("#%d %d\n", testCase, dfs(0, 0, n, m));
    }
}
