/* 17822. 원판 돌리기
https://www.acmicpc.net/problem/17822
*/
#include <iostream>
#include <vector>
using namespace std;

int N, M, T;
vector<vector<int>> plate;
vector<int> pos; //0번의 위치

void turn(int x, int d, int k){
    //회전시키기
    for (int i=x; i<=N; i+=x) { //x의 배수 i
        if(d==1){ //시계
            pos[i] += k;
        } else{ //반시계
            pos[i] += (M-k);
        }
        pos[i] %= M;
    }

    //인접하면서 수가 같은 것 찾기
    bool flag = false;
    vector<vector<bool>> del(N+1, vector<bool>(M, false));
    int sum = 0, cnt = 0;
    for (int i=1; i<=N; i++) {
        for (int j=0; j<M; j++) {
            if(plate[i][(j+pos[i])%M] == 0) continue; //숫자 없음
            
            if(plate[i][(j+pos[i])%M] == plate[i][(j+1+pos[i])%M]){ //인접
                flag=true;
                del[i][(j+pos[i])%M] = true;
                del[i][(j+1+pos[i])%M] = true;
            }
            
            if(plate[i][(j+pos[i])%M] == plate[i][(j+M-1+pos[i])%M]){ //인접
                flag=true;
                del[i][(j+pos[i])%M] = true;
                del[i][(j+M-1+pos[i])%M] = true;
            }
            
            if(i < N && plate[i][(j+pos[i])%M] == plate[i+1][(j+pos[i+1])%M]){ //인접
                flag=true;
                del[i][(j+pos[i])%M] = true;
                del[i+1][(j+pos[i+1])%M] = true;
            }
            
            if(!flag){
                sum += plate[i][(j+pos[i])%M];
                cnt++;
            }
        }
    }

    if(flag){ //있음 -> 지워주기
        for (int i=1; i<=N; i++) {
            for (int j=0; j<M; j++) {
                if(del[i][j])   plate[i][j] = 0;
            }
        }
    }
    else{ //없음
        double avg = (double)sum/cnt;
        for (int i=1; i<=N; i++) {
            for (int j=0; j<M; j++) {
                if(plate[i][j] == 0)    continue;
                if(plate[i][j] > avg) plate[i][j]--;
                else if(plate[i][j] < avg)    plate[i][j]++;
            }
        }
    }
}

int main() {
    ios_base :: sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    
    cin >> N >> M >> T;
    plate.assign(N+1, vector<int>(M));
    pos.assign(N+1, 0);
    for (int i=1; i<=N; i++) {
        for (int j=0; j<M; j++) {
            cin >> plate[i][j];
        }
    }
    int x, d, k;
    for (int i=0; i<T; i++) {
        cin >> x >> d >> k;
        turn(x, d, k);
    }
    
    int sum = 0;
    for (int i=1; i<=N; i++) {
        for (int j=0; j<M; j++) {
            sum += plate[i][j];
        }
    }
    cout << sum;
}
