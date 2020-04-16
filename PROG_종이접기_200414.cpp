/* 종이접기 Level 3
https://programmers.co.kr/learn/courses/30/lessons/62049
 */
#include <string>
#include <vector>
#include <cmath>
using namespace std;

vector<int> solution(int n) {
    //접힌 자국들은 중점 기준 (반대로) 대칭! -> 가장 앞 조각인 0을 기준으로 바꿔주자
    vector<int> answer(pow(2, n) - 1); //접힌 자국 개수
    for (int i=1; i<n; i++) {
        int mid = pow(2,i)-1; //중점
        for (int l=0, r=pow(2,i+1)-2; l<mid; l++, r--) { //중점 기준 오른쪽 좌표들을 바꿔준다
            if(answer[l] == 0) answer[r] = 1;
        }
    }
    return answer;
}
