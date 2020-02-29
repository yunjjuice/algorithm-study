/* 2252. 줄 세우기 Gold 2
 https://www.acmicpc.net/problem/2252
 -> topological sort
 */

#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;

int main() {
    int N, M;
    cin >> N >> M;

    vector<queue<int>> vertex(N + 1, queue<int>());
    vector<int> incomings(N + 1, 0);

    while (M-- > 0) {
        int a, b;
        cin >> a >> b;
        vertex[a].push(b);
        incomings[b]++;
    } //end of input
    
    queue<int> q;
    for(int i=1; i<=N; i++){
        if(incomings[i] == 0)
            q.push(i);
    }
    
    while(!q.empty()){
        int v = q.front();
        q.pop();
        cout << v << " "; //output
        while(!vertex[v].empty()){
            int u = vertex[v].front();
            vertex[v].pop();
            if(--incomings[u] == 0)
                q.push(u);
        }
    }
}
