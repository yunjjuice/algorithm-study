/* 17825. 주사위 윷놀이 Gold 2
https://www.acmicpc.net/problem/17825
*/
#include <iostream>
#include <vector>
using namespace std;

vector<vector<int>> map(5);
vector<int> dice(11);
vector<pair<int, int>> horses(4, {0,0});
int answer = 0;

//n번째 dice 굴리기
void dfs(int n, int score){
    if(n==11){
        if(answer < score)  answer = score;
        return;
    }
    
    pair<int,int> tmp;
    for (int i=0; i<(n>4? 4 : n); i++) { //이번 주사위에서 선택할 수 있는 말 모두 탐색
        if(horses[i].first == -1)  continue; //고를 수 없음
        tmp=horses[i];
        horses[i].second += dice[n];
        if(horses[i].second > map[horses[i].first].size()){ //도착 칸 이상 감 -> 이제 선택 불가
            horses[i].first = -1;
            dfs(n+1, score);
            horses[i]=tmp; //원상복구
            continue;
        }
        
        //다른 길로 이동해야 하는지?
        if(horses[i].first == 0){
            switch (horses[i].second) {
                case 5: //10
                    horses[i].first = 1;
                    horses[i].second = 0;
                    break;
                case 10: //20
                    horses[i].first = 2;
                    horses[i].second = 1;
                    break;
                case 15: //30
                    horses[i].first = 3;
                    horses[i].second = 0;
                    break;
                case 20: //40
                    horses[i].first = 4;
                    horses[i].second = 7;
            }
        }else{ //horses[i].first == 1 || 2 || 3 (|| 4)
            if(horses[i].second >= 4){ //4번부터는 겹치는 경로
                horses[i].first = 4;
            }
        }
        
        //동일한 칸에 올라가려고 하는 건지?
        bool flag = false;
        for (int j=0; j<4; j++) {
            if(i==j || horses[j].first == -1) continue;
            if(horses[i] == horses[j]){
                flag = true;
                break;
            }
        }
        if(!flag) dfs(n+1, score+map[horses[i].first][horses[i].second]); //이동 가능할 때만 진행
        horses[i]=tmp; //원상복구
    }
}

int main() {
    ios_base :: sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    
    map[0] = {0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40};
    map[1] = {10,13,16,19,25,30,35,40};
    map[2] = {-1,20,22,24,25,30,35,40}; //index의 편의성을 위해 앞에 -1 추가해줌
    map[3] = {30,28,27,26,25,30,35,40};
    map[4] = {-1,-1,-1,-1,25,30,35,40}; //마찬가지로 index의 편의성

    for (int i=1; i<=10; i++) {
        cin >> dice[i];
    }
    
    dfs(1, 0);
    cout << answer;
}
