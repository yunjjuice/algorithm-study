/* 16637. 괄호 추가하기
https://www.acmicpc.net/problem/16637

 - 각 연산자는 괄호를 하는 경우와 하지 않는 경우, 2가지 경우의 수 보유
 - 앞부터 누적 연산 필요 -> 뒤의 연산자에 괄호가 있을 경우를 고려해야
	-> 연산자는 최대 8개 : 2^8 회 연산
*/
#include <iostream>
#include <climits>
#include <string>
using namespace std;

int answer = INT_MIN;
string str;

int cal(int a, char op, int b) {
	switch (op)
	{
	case '+':
		return a + b;
	case '-':
		return a - b;
	case '*':
		return a * b;
	}
}

void dfs(int res, int index) { //res: 누적 값, index: 현재 연산자
	if (index + 2 >= str.length()) { //다음 연산자 없음 -> 계산 끝
		int r = cal(res, str[index], str[index + 1] - 48);
		if (r > answer)	answer = r; //결과 업데이트
		return; //밑 문장 실행 시 index out of range 에러
	}else
		dfs(cal(res, str[index], str[index + 1] - 48), index + 2); //괄호 x
	if (index + 4 >= str.length()) { //다다음 연산자 없음 -> 계산 끝
		int r = cal(res, str[index], cal(str[index + 1] - 48, str[index + 2], str[index + 3] - 48)); //다음 연산자에 괄호 o
		if (r > answer)	answer = r; //결과 업데이트
	}else
		dfs(cal(res, str[index], cal(str[index+1]-48, str[index+2], str[index+3]-48)), index+4); //다음 연산자에 괄호 o
}

int main() {
	//input
	int N;
	cin >> N >> str;

	//solve
	if (str.length() == 1) { //예외 처리
		cout << str[0] - 48;
		return 0;
	}
	dfs(str[0]-48, 1);

	//output
	cout << answer;
}
