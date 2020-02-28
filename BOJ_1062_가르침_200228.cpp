/* 1062. 가르침 Gold 4
 https://www.acmicpc.net/problem/1062
 -> 완전탐색 : 알파벳을 고르는 모든 조합에 대해 판단
 */

#include <iostream>
#include <algorithm>
#include <string>
#include <unordered_map>
using namespace std;

int main() {
    int N, K;
    cin >> N >> K;
    string words[N];
    for (int i = 0; i < N; i++) {
        cin >> words[i];
    }

    if (K < 5) { //anta tica 읽기 불가 -> 0개
        cout << 0;
        return 0;
    }

    int answer = 0;
    //a, c, i, n, t 는 기본! 나머지 K-5개에 대해서 조합 & 판단
    unordered_map<char, int> alphabet = {
        { 'b', 0 }, { 'd', 1 }, { 'e', 2 }, { 'f', 3 }, { 'g', 4 }, { 'h', 5 }, { 'j', 6 }, { 'k', 7 }, { 'l', 8 }, { 'm', 9 }, { 'o', 10 }, { 'p', 11 }, { 'q', 12 }, { 'r', 13 }, { 's', 14 }, { 'u', 15 }, { 'v', 16 }, { 'w', 17 }, { 'x', 18 }, { 'y', 19 }, { 'z', 20 }
    };
    bool select[21] = { false, };
    for (int i = 20; i > 26 - K - 1; i--) { //알파벳 K-5개 고르기
        select[i] = true;
    }
    do {
        int tmp = 0;
        for (int i = 0; i < N; i++) {
            bool flag = true;
            for (int w = 4; w < words[i].length() - 4; w++) {
                if(alphabet.find(words[i][w]) == alphabet.end())    continue; //a, c, i, n, t 중 하나임
                if(!select[alphabet.find(words[i][w])->second]){
                    flag = false;
                    break;
                }
            }
            if (flag)  tmp++;
        }
        if (tmp > answer)
            answer = tmp;
    } while (next_permutation(select, select + 21));

    cout << answer;
}
