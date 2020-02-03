/*
 1215. [S/W 문제해결 기본] 3일차 - 회문1 D3
 https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14QpAaAAwCFAYi&categoryId=AV14QpAaAAwCFAYi&categoryType=CODE
 
 - 회문의 길이만큼 배열 자르기
 -> 회문인지 판단
 */
#include <iostream>
#include <vector>
using namespace std;

bool isPalindrome(vector<char> str){ //회문인지 판단
    bool flag = true;
    for (int i=0, j=(int)str.size()-1; i<str.size()/2; i++, j--) {
        if(str[i] != str[j])    flag=false;
    }
    return flag;
}

int main(){
    vector<vector<char>> map(8, vector<char>(8));
    for (int testCase=1; testCase<=10; testCase++) {
        //input
        int length; //찾아야 하는 회문의 길이
        cin >> length;
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                cin >> map[i][j];
            }
        }
        
        //solve
        int answer = 0; //회문의 개수
        vector<char> tmp(length);
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                //가로로 자르고 -> map[i][j] ~ map[i][j+length-1]
                if (j + length <= 8) { //자를 수 있어야
                    for (int k = 0; k<length; k++) {
                        tmp[k] = map[i][j+k];
                    }
                    if(isPalindrome(tmp))   answer++;
                }
                
                //세로로 자르고 -> map[i][j] ~ map[i+length-1][j]
                if (i + length <= 8) {
                    for (int k = 0; k<length; k++) {
                        tmp[k] = map[i+k][j];
                    }
                    if(isPalindrome(tmp))   answer++;
                }
//
//                //오른쪽 대각선으로 자르기 -> map[i][j] ~ map[i+length-1][j+length-1]
//                if (i + length <= 8 && j + length <= 8) {
//                    for (int k = 0; k<length; k++) {
//                        tmp[k] = map[i+k][j+k];
//                    }
//                    if(isPalindrome(tmp))   answer++;
//                }
//
//                //왼쪽 대각선으로 자르기 -> map[i][j] ~ map[i+length-1][j-length+1]
//                if (i + length <= 8 && j - length + 1 >= 0) {
//                    for (int k = 0; k<length; k++) {
//                        tmp[k] = map[i+k][j-k];
//                    }
//                    if(isPalindrome(tmp))   answer++;
//                }
            }
        }
        
        //output
        cout << "#" << testCase << " " << answer << '\n';
    } //end of for : testcase
} //end of main


