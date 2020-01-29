/* 1208. [S/W 문제해결 기본] 1일차 - Flatten D3
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV139KOaABgCFAYh&categoryId=AV139KOaABgCFAYh&categoryType=CODE

(반복)
	- 오름차순으로 정렬
	-> num[99]-num[0]이 높이 차
	-> ☆ 평탄화 완료시 반복 종료
	-> 한 번에 이동시킬 수 있는 최대 박스 이동

*/
#include <iostream>
#include <algorithm>
using namespace std;

int main() {
	int dump;
	int boxs[100];
	for (int testCase = 1; testCase <= 10; testCase++)
	{
		//input
		cin >> dump;
		for (int i = 0; i < 100; i++)
			cin >> boxs[i];

		//solve
		int answer;
		sort(boxs, boxs + 100);
		while (dump>0) {
			int move = min({ boxs[99] - boxs[98], boxs[1] - boxs[0], dump });
			if (move == 0)	move = 1;
			boxs[0] += move; boxs[99] -= move; dump -= move;
			sort(boxs, boxs + 100);
			if ((answer = boxs[99] - boxs[0]) <= 1)	break;
		}

		//output
		cout << "#" << testCase << " " << answer << '\n';
	}
}