/* 2631. 줄세우기 Gold 5
 https://www.acmicpc.net/problem/2631
 -> DP, LIS 찾는 문제
 O(n^2)로 했는데 풀리내용 .. 뀨
 */
#include <iostream>
#include <algorithm>
using namespace std;

int main()
{
    int N;
    cin >> N;
    int val[200], len[200];
    fill(len, len+200, 1);

    int lis = 1;
    cin >> val[0]; //initialize
    for (int i = 1; i < N; i++) {
        cin >> val[i];
        for (int j = 0; j < i; j++) {
            if (val[j] <= val[i] && len[i] < len[j] + 1) {
                len[i] = len[j] + 1;
            }
        }
        if (lis < len[i])
            lis = len[i];
    }
    cout << N - lis;
}
