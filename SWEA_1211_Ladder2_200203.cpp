/* 1211. [S/W 문제해결 기본] 2일차 - Ladder2 D4
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14BgD6AEECFAYh&categoryId=AV14BgD6AEECFAYh&categoryType=CODE

*/
#include <iostream>
#include <climits>
using namespace std;

int main() {
	int arr[100][102];
	for (int testCase = 1; testCase <= 10; testCase++)
	{
		//input
		cin >> testCase;
		for (int i = 0; i < 100; i++) {
			for (int j = 1; j < 101; j++){
				cin >> arr[i][j];
			}
		}

		//solve
		int answer = 0; //가장 이동거리가 짧은 시작점의 Y좌표
		int min_dist = INT_MAX; //가장 작은 이동거리

		for (int i = 1; i < 101; i++){ //가장 윗줄의 Y좌표마다
			if (arr[0][i]==1) { //막대가 있으면
				//이동거리 탐색
				int tmp_dist = 0; //현재 출발점의 이동거리 -> 통로만 (막대 길이는 같음)
				int y = i;
				for (int x = 1; x < 100; x++) { // 사다리 타고 내려가기
					if (arr[x][y + 1]==1)
						while (arr[x][y + 1]==1) {
							tmp_dist++;
							y++; //통로 따라서 이동 -> 우측
						}
					else if (arr[x][y - 1]==1)
						while (arr[x][y - 1]==1) {
							tmp_dist++;
							y--; //통로 따라서 이동 -> 좌측
						}
				} //사다리 타고 내려가기 끝
				if (min_dist > tmp_dist) { //현재 출발점의 이동거리가 더 짧으면
					min_dist = tmp_dist; //거리 갱신
					answer = i-1; //출발점 좌표 갱신
				}
			}
		}
		//output
		cout << "#" << testCase << " " << answer << '\n';
	}
}
