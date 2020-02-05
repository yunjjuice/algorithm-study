/*
 1213. [S/W 문제해결 기본] 3일차 - String D3
 https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14P0c6AAUCFAYi&categoryId=AV14P0c6AAUCFAYi&categoryType=CODE
 
 - 문자열 길이만큼 루프 -> 문자열 비교
 */
#include <iostream>
using namespace std;

int main(){
    for (int testCase=1; testCase<=10; testCase++) {
        //input
        cin>>testCase;
        string str, target;
        cin >> target >> str;
        
        //solve
        int answer = 0;
        for (int i = 0; i<=str.length()-target.length(); i++) {
            if(str[i] == target[0]){ //탐색 시작
                bool flag = false;
                for (int k=1; k<target.length(); k++) {
                    if (str[i+k] != target[k]) {
                        flag = true;
                        break;
                    }
                }
                if(!flag){ //틀린 문자가 없었음
                    answer++;
                }
            }
        }
        
        //output
        cout << "#" << testCase << " " << answer << '\n';
    }
    
} //end of main
