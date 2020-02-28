/* 2352. 반도체 설계 Gold 4
 https://www.acmicpc.net/problem/2352
 -> DP, LIS
 */
#include <iostream>
#include <algorithm>
using namespace std;

int main() {
    int n;
    scanf("%d", &n);
    
    int lis[40000], last = 0;
    scanf("%d", &lis[0]);
    for (int i = 1; i < n; i++) {
        int now;
        scanf("%d", &now);
        if(lis[last] < now)
            lis[++last] = now;
        else{
            auto pos = lower_bound(lis, lis+last, now);
            *pos = now;
        }
    }
    cout << last + 1;
}
