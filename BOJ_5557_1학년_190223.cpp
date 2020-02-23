/* 5557. 1학년 Gold 5
 https://www.acmicpc.net/problem/5557
 -> DP
 
 디피는 .. 너무 어렵다
 가능한 수의 범위가 0~20 : 경우의 수 저장하는 dp 배열 만들기
 */

#include <iostream>
using namespace std;

int main() {
    int N;
    scanf("%d", &N);
    int cmd[101];
    for (int i = 1; i <= N; i++)    scanf("%d", &cmd[i]);
    //end of input
    
    long dp[101][21] = {0,};
    dp[1][cmd[1]] = 1; //초기값
    for (int i = 2; i < N; i++) { //cmd[N]은 결과값이므로 연산 x
        for (int j = 0; j <= 20; j++) {
            if (dp[i - 1][j] == 0)  continue;
            if (j + cmd[i] <= 20)   dp[i][j + cmd[i]] += dp[i - 1][j];
            if (j - cmd[i] >= 0)    dp[i][j - cmd[i]] += dp[i - 1][j];
        }
    }
    
    cout << dp[N-1][cmd[N]];
}
