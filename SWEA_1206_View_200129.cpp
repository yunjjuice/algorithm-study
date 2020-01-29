/* 1206. [S/W 문제해결 기본] 1일차 - View D3
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV134DPqAA8CFAYh&categoryId=AV134DPqAA8CFAYh&categoryType=CODE

모든 빌딩에 대해 반복:
	빌딩이 좌우 4칸 중 가장 높으면 조망권 확보
		-> 높이의 차를 정답에 더해줌
		-> 우측 2칸은 확보 불가 => 바로 3칸 건너뛰기
*/
#include <iostream>
#include <algorithm>
using namespace std;

int main() {
	int N, map[1000]; //가로 길이는 항상 1000 이하
	int answer;
	for (int testCase = 1; testCase <= 10; testCase++) {
		cin >> N;
		for (int i = 0; i < N; i++)
		{
			cin >> map[i];
		}//end of input

		//solve
		answer = 0;
		int around;
		for (int i = 2; i < N - 2; i++) {
			around = max({ map[i - 2], map[i - 1], map[i + 1], map[i + 2] }); //좌우 4칸 중 가장 높은 빌딩의 높이
			if (map[i] > around) { //조망권 확보
				answer += map[i] - around;
				i += 2; //우측 두 칸은 조망권 확보 불가
			}
		}

		//answer
		cout << "#" << testCase << " " << answer << '\n';
	}
} //end of main