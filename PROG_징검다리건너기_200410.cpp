/* 징검다리 건너기 Level 3
https://programmers.co.kr/learn/courses/30/lessons/64062
 */
#include <string>
#include <vector>
#include <algorithm>
#include <iostream>
using namespace std;

// 효율성 13에서 터짐 ..
//int solution(vector<int> stones, int k) {
//    int answer = 200000001;
//    for (auto i = stones.begin(); i <= stones.end()-k;) {
//        auto tmpIter = max_element(i, i+k);
//        cout << *tmpIter << " ";
//        if(answer > *tmpIter) answer = *tmpIter;
//        i = tmpIter + 1;
//    }
//    return answer;
//}

int solution(vector<int> stones, int k) {
    int MIN = 0, MAX = *max_element(stones.begin(), stones.end());
    int M = MAX/2;
    while (MIN < MAX) {
        int dist = 0;
        for (int i=0; i<stones.size(); i++) { //0이 연속으로 K개 나오는 구간 있는지 확인하기
            if (stones[i] < M) { //M명이 건넜을 때 0
                dist++;
                if (dist == k) { //건널 수 없으
                    MAX = M-1;
                    break;
                }
            }else{
                dist = 0;
            }
        }
        
        if (dist < k)   MIN = M; //건널 수 있음
        M = (MIN + MAX) / 2 + 1;
    }
    return MIN; //이 시점에서 MIN == MAX
}

int main() {
    cout << solution({2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3) << '\n'; //3
    cout << solution({2, 4, 5}, 1) << '\n'; //3
}
