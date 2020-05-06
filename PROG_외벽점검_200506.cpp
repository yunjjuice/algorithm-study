/* 외벽 점검 Level 3
 https://programmers.co.kr/learn/courses/30/lessons/60062
 */
#include <algorithm>
#include <vector>
using namespace std;

bool isok(int n, vector<int>& between, vector<int>& perm, vector<int>& dist){
    vector<int> tmp(n, 0);
    int tmpIndex = 0;
    int idx = 0;
    while(perm[idx++] == 0);
    idx %= perm.size();

    for (int i=0; i<perm.size(); i++, ++idx%=perm.size()) {
        if (perm[idx] == 1) { //선택x
            tmpIndex++; continue;
        }
        tmp[tmpIndex] += between[idx];
    }
    
    sort(tmp.rbegin(), tmp.rend()); //내림차순으로 정렬
    for (int i=0; i<n; i++) {
        if (tmp[i] > dist[i]) { //점검 불가
            return false;
        }
    }
    return true;
}

//n명이 점검할 수 있는지 리턴
bool ableToCheck(int n, vector<int>& between, vector<int>& dist){
    vector<int> perm(between.size(), 0);
    for (int i=0; i<n; i++) {
        perm[perm.size()-i-1] = 1;
    }
    
    do{
        if (isok(n, between, perm, dist)) return true;
    }while(next_permutation(perm.begin(), perm.end()));
    return false;
}

int solution(int n, vector<int> weak, vector<int> dist) {
    if(weak.size() == 1) return 1;
    int answer = 0;
    
    sort(dist.rbegin(), dist.rend()); //내림차순으로
    
    //취약 지점 사이 거리 구하기
    vector<int> betweenWeak(weak.size());
    for (int i=0; i<weak.size()-1; i++) {
        betweenWeak[i] = weak[i+1]-weak[i];
    }
    betweenWeak[weak.size()-1] = weak[0] + n - weak[weak.size()-1];
    
    while (answer++ < dist.size()) { //친구들 수만큼
        if(ableToCheck(answer, betweenWeak, dist)){
            return answer;
        }
    }
    
    return -1;
}
