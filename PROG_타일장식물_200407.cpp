/* 타일 장식물 Level 3
https://programmers.co.kr/learn/courses/30/lessons/43104
 */
#include <string>
#include <vector>
#include <iostream>
using namespace std;

long long solution(int N) {
    if (N == 1) return 4;
    vector<long long> fibo(N+1);
    fibo[0] = fibo[1] = 1;
    for (int i=2; i<=N; i++) {
        fibo[i] = fibo[i-1]+fibo[i-2];
    }
    return fibo[N]*2 + fibo[N-1]*2;
}

int main() {
    cout << solution(5) << '\n' << solution(6);
}
