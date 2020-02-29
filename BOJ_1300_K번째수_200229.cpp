/* 1300. K번째 수 Gold 4
 https://www.acmicpc.net/problem/1300
 */

#include <iostream>
#include <algorithm>
using namespace std;

int main() {
    int N, k;
    cin >> N >> k;
    
    int answer = -1;
    int low = 1, high = k;
    while(low <= high){
        int mid = (low + high) / 2;
        int cnt = 0;
        for(int i=1; i<=N; i++)
            cnt += min(mid / i, N);
        //mid 이하인 수의 개수를 구한다.
        
        if(cnt < k) low = mid + 1;
        else {
            answer = mid; //정답 갱신
            high = mid - 1;
        }
    }
    cout << answer;
}
