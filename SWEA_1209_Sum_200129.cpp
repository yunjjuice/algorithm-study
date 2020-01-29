/* 1209. [S/W 臾몄닿껐 湲곕낯] 2쇱감 - Sum D3
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV13_BWKACUCFAYh&categoryId=AV13_BWKACUCFAYh&categoryType=CODE

 , 댁 , 媛(2媛)  援ы댁 理媛 異
*/
#include <iostream>
#include <algorithm>
using namespace std;

int main() {
	int arr[100][100];
	for (int testCase = 1; testCase <= 10; testCase++)
	{
		//input
		cin >> testCase;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++){
				cin >> arr[i][j];
			}
		}

		//solve
		int answer = 0;
		int tmp;
		for (int i = 0; i < 100; i++) // 
		{
			tmp = 0;
			for (int j = 0;  j < 100;  j++)
			{
				tmp += arr[i][j];
			}
			if (tmp > answer)	answer = tmp;
		}
		for (int i = 0; i < 100; i++) //댁 
		{
			tmp = 0;
			for (int j = 0; j < 100; j++)
			{
				tmp += arr[j][i];
			}
			if (tmp > answer)	answer = tmp;
		}
		tmp = 0;
		for (int j = 0; j < 100; j++) //媛  -1
		{
			tmp += arr[j][j];
		}
		if (tmp > answer)	answer = tmp;
		tmp = 0;
		for (int j = 0; j < 100; j++) //媛  -2
		{
			tmp += arr[j][99-j];
		}
		if (tmp > answer)	answer = tmp;

		//output
		cout << "#" << testCase << " " << answer << '\n';
	}
}
