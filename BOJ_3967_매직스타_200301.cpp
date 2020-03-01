/* 3967. 매직 스타 Gold 5
https://www.acmicpc.net/problem/3967
-> 백트래킹... 인데 이렇게 무식하게 하는 게 맞나? 변수 너무 많아 마음에 안들어 코드 너무 길어 마음에 안들어 ~~!!
 */

#include <iostream>
#include <algorithm>
#include <string>
using namespace std;

#define s(i, j) star[lines[i][j][0]][lines[i][j][1]]
#define toint(val) val - 'A' + 1 //A가 1이니까
#define pstar(i) star[priority[i][0]][priority[i][1]]

string star[5];
int lines[6][4][2] = {
    { { 0, 4 }, { 1, 3 }, { 2, 2 }, { 3, 1 } },
    { { 0, 4 }, { 1, 5 }, { 2, 6 }, { 3, 7 } },
    { { 3, 1 }, { 3, 3 }, { 3, 5 }, { 3, 7 } },
    { { 1, 1 }, { 1, 3 }, { 1, 5 }, { 1, 7 } },
    { { 1, 1 }, { 2, 2 }, { 3, 3 }, { 4, 4 } },
    { { 1, 7 }, { 2, 6 }, { 3, 5 }, { 4, 4 } },
};

int priority[12][2] = {
    { 0, 4 }, { 1, 1 }, { 1, 3 }, { 1, 5 }, { 1, 7 }, { 2, 2 }, { 2, 6 }, { 3, 1 }, { 3, 3 }, { 3, 5 }, { 3, 7 }, { 4, 4 }
};

int connect[12][2] = {
    { 0, 1 }, { 3, 4 }, { 0, 3 }, { 1, 3 }, { 3, 5 }, { 0, 4 }, { 1, 5 }, { 0, 2 }, { 2, 4 }, { 2, 5 }, { 1, 2 }, { 4, 5 }
}; //priority[0]인 위치의 수는 lines[0],[1]에 연결되어있음

bool used[13]; //숫자가 (문자가) 사용되었는지
int lnum[6]; //각 라인에 채워진 문자 개수

bool isend = false; //문자 채우기가 끝났는지

bool validCheck(int now) {
    if (lnum[connect[now][0]] == 4) {
        int sum = 0;
        for (int j = 0; j < 4; j++) {
            sum += toint(s(connect[now][0], j));
        }
        if (sum != 26)
            return false;
    }
    if (lnum[connect[now][1]] == 4) {
        int sum = 0;
        for (int j = 0; j < 4; j++) {
            sum += toint(s(connect[now][1], j));
        }
        if (sum != 26)
            return false;
    }
    return true;
}

void fill(int now) { //지금 채워야 하는 문자의 우선순위 번호
    if (now >= 12) {
        isend = true; //끝낫다
        return;
    }
    if (pstar(now) != 'x') { //채워졋으면
        fill(now + 1); //다음 문자 채우러 가기
        return;
    }

    lnum[connect[now][0]]++;
    lnum[connect[now][1]]++;
    for (int num = 1; num < 13; num++) { //작은 숫자부터 채워보기
        if (used[num])  continue; //이미 사용된 숫자면 넘어가기

        pstar(now) = num + 'A' - 1;
        used[num] = true;

        if (validCheck(now))    fill(now + 1);
        if (isend)  return;

        //원상복구
        pstar(now) = 'x';
        used[num] = false;
    }
    
    lnum[connect[now][0]]--;
    lnum[connect[now][1]]--;
}

int main() {
    for (int i = 0; i < 5; i++) {
        cin >> star[i];
    } //end of input

    //초기에 채워진 문자 체크
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 4; j++) {
            if (s(i, j) != 'x') { //문자가 채워져있으면
                lnum[i]++;
                used[toint(s(i, j))] = true; //사용됨
            }
        }
    }

    //문자 세 개가 정해져있으면 나머지 하나는 고정
    bool flag = true;
    while (flag) {
        flag = false;
        for (int i = 0; i < 6; i++) {
            if (lnum[i] != 3)
                continue; //3개가 정해져있는 게 아니면 넘어가
            int sum = 0, idx = 0;
            for (int j = 0; j < 4; j++) {
                if (s(i, j) != 'x') { //문자가 채워져있으면
                    sum += toint(s(i,j));
                }else   idx = j;
            }
            s(i,idx) = 25 - sum + 'A';
            used[toint(s(i,idx))] = true;
            lnum[i]++;
            flag = true;
        }
    }
    
    //나머지 찾기
    fill(0);
    
    //output
    for(int i=0; i<5; i++){
        cout << star[i] << '\n';
    }
}
