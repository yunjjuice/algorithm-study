/* 스킬트리 Level 2
https://programmers.co.kr/learn/courses/30/lessons/49993
 */
#include <string>
#include <vector>
using namespace std;

int solution(string skill, vector<string> skill_trees) {
    int answer = 0;
    bool alphabet[26] = {0,};
    for (char c : skill) {
        alphabet[c - 'A'] = true;
    }
    for(string s : skill_trees){
        int i = 0;
        bool flag = false;
        for (char c : s) {
            if(!alphabet[c - 'A']) continue;
            if(skill[i] != c){
                flag = true; break;
            }
            i++;
        }
        if(!flag) answer++;
    }
    return answer;
}
