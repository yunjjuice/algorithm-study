/* 1208. [S/W 臾몄닿껐 湲곕낯] 1쇱감 - Flatten D3
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV139KOaABgCFAYh&categoryId=AV139KOaABgCFAYh&categoryType=CODE

(諛蹂)
	- ㅻ李⑥쇰 
	-> num[99]-num[0]  李
	->   猷 諛蹂 醫猷
	->  踰 대   理 諛 대

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
