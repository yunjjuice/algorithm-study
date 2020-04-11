/* 다트 게임 Level 1
https://programmers.co.kr/learn/courses/30/lessons/17682
 */
#include <iostream>
#include <string>
#include <vector>
using namespace std;

int solution(string dartResult) {
    vector<int> scores(3);
    int cnt = 0;
    for (int i=0; i<dartResult.size(); i++) {
        //옵션 -> cnt-1점수에 적용됨
        if (dartResult[i] == '*') { //스타상
            scores[cnt-1] *= 2;
            if(cnt > 1) scores[cnt-2] *= 2; //첫 번째 다트가 아닐 경우
            i++;
        }else if(dartResult[i] == '#'){ //아차상
            scores[cnt-1] *= -1; //마이너스됨
            i++;
        }
        //기본 점수
        int tmpScore = dartResult[i]-'0';
        if(dartResult[i+1] == '0'){ //10
            tmpScore = 10;
            i++;
        }
        switch (dartResult[++i]) {
            case 'D':
                tmpScore *= tmpScore;
                break;
            case 'T':
                tmpScore *= tmpScore * tmpScore;
        }
        scores[cnt++] = tmpScore;
    }
    int answer = 0;
    for (int i : scores) {
        answer += i;
    }
    return answer;
}

int main() {
    cout << solution("1D2S#10S") << '\n'; //37
}
