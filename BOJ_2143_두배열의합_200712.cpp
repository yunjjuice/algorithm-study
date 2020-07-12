/* 2143. 두 배열의 합 Gold 3
https://www.acmicpc.net/problem/2143
*/

#include <iostream>
#include <algorithm>

using namespace std;

int T, N, M;
int A[500500], B[500500]; //500500 -> 최댓값: N, M이 1000일 때 idx의 값
long long int answer; //최대 250,500,250,000

int main() {
    int tmp;
    scanf("%d %d", &T, &N);
    scanf("%d", &A[0]);
    for (int i = 1; i < N; i++) {
        scanf("%d", &tmp);
        A[i] = tmp + A[i-1];
    }
    scanf("%d", &M);
    scanf("%d", &B[0]);
    for (int i = 1; i < M; i++) {
        scanf("%d", &tmp);
        B[i] = tmp + B[i-1];
    }

    // 부 배열의 합 구하기
    int idxA = N;
    for (int start = 0; start < N-1; start++) {
        for (int end = start+1; end < N; end++) {
            A[idxA++] = A[end] - A[start];
        }
    }

    int idxB = M;
    for (int start = 0; start < M-1; start++) {
        for (int end = start+1; end < M; end++) {
            B[idxB++] = B[end] - B[start];
        }
    }
    
    // 부 배열 쌍의 개수 구하기
    sort(&A[0], &A[idxA]);
    sort(&B[0], &B[idxB]);

    int a=0, b=idxB-1;
    while (a<idxA && b>=0) {
        int sum = A[a] + B[b];
        if(sum < T) a++;
        else if(sum > T) b--;
        else {
            // 중복 값 한번에 찾기
            int cnt1=1, cnt2=1;
            while (a+cnt1 < idxA && A[a] == A[a+cnt1]) cnt1++;
            while (b-cnt2 >= 0 && B[b] == B[b - cnt2]) cnt2++;

            answer += (long long int)cnt1*cnt2;
            a += cnt1; b-= cnt2;
        }
    }

    printf("%lld", answer);
}