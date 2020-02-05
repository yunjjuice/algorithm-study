/*
 1216. [S/W 문제해결 기본] 3일차 - 회문2 D3
 https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14Rq5aABUCFAYi&categoryId=AV14Rq5aABUCFAYi&categoryType=CODE
 */
#include <iostream>
#include <string>
using namespace std;

bool isPalindrome(string s){
    for (int i=0; i<s.length()/2; i++) {
        if(s[i] != s[s.length()-i-1]){
            return false;
        }
    }
    return true;
}

int main(){
    for (int testCase=1; testCase<=10; testCase++) {
        //input
        cin>>testCase;
        string str[100];
        for (int i=0; i<100; i++) {
            cin>>str[i];
        }
        string str2[100];
        for (int i=0; i<100; i++) {
            for (int j=0; j<100; j++) {
                str2[i] += str[j][i];
            }
        }
        
        //solve
        int answer = 1;
        bool flag = false;
        for (int i=100; i>1; i--) { //문자열 길이
            for (int x=0; x<=100-i; x++) { //회문 시작지점 x좌표
                for (int y=0; y<=100-i; y++) { //회문 시작지점 y좌표
                    if(isPalindrome(str[x].substr(y, i)) || isPalindrome(str2[y].substr(x, i))){ //가로로 자르기, 세로로 자르기
                        answer = i;
                        flag = true;
                        break;
                    }
                }
                if(flag)    break;
            }
            if(flag)    break;
        }
        
        //output
        cout << "#" << testCase << " " << answer << '\n';
    }
    
} //end of main
