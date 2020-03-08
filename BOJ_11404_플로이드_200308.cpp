/* 11404. 플로이드 Gold 4
https://www.acmicpc.net/problem/14890
 -> Floyd Warshall
 https://hsp1116.tistory.com/45 를 참고했다
 */
#include <iostream>
#include <algorithm>
using namespace std;

#define INF 0x3f3f3f3f

int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL); //입출력 시간 단축 용
    int n, m;
    cin >> n >> m;
    
    //initialize
    int D[n][n];
    fill(&D[0][0], &D[n-1][n], INF);
    for (int i=0; i<n; i++) {
        D[i][i] = 0;
    }
    
    for (int i=0; i<m; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        if(D[a-1][b-1] < c) continue;
        D[a-1][b-1] = c;
    }
    //end of input

    for (int i=0; i<n; i++) {
        for (int j=0; j<n; j++) {
            for (int k=0; k<n; k++) {
                if(D[k][j] > D[k][i] + D[i][j]){
                    D[k][j] = D[k][i] + D[i][j];
                }
            }
        }
    }
    
    //output
    for (int i=0; i<n; i++) {
        for(int j=0; j<n; j++){
            cout << (D[i][j] == INF? 0 : D[i][j]) << ' ';
        }
        cout << '\n';
    }
}
