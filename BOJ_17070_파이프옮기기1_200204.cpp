/* 17070. 파이프 옮기기 1
https://www.acmicpc.net/problem/17070
*/
#include <iostream>
using namespace std;

enum Pipe
{
	horizontal, vertical, diagonal
};

int answer = 0; //방법의 수
int N; //집의 크기
int map[18][18]; //집의 상태. 인덱스는 1~N, N+1번 라인은 벽

void dfs(Pipe p, int r, int c) {
	//cout << "방향: " << p << ", row: " << r << ", col: " << c << '\n';
	if (r == N && c == N) {
		answer++;
		return;
	}
	switch(p){ //빈 칸인지 체크 후 이동
	case horizontal: //가로 or 대각선 이동
		if (map[r][c + 1] == 0)	dfs(horizontal, r, c + 1);
		if (map[r][c + 1] == 0 && map[r + 1][c] == 0 && map[r + 1][c + 1] == 0)	dfs(diagonal, r + 1, c + 1);
		break;
	case vertical: //세로 or 대각선 이동
		if (map[r + 1][c] == 0)	dfs(vertical, r + 1, c);
		if (map[r][c + 1] == 0 && map[r + 1][c] == 0 && map[r + 1][c + 1] == 0)	dfs(diagonal, r + 1, c + 1);
		break;
	case diagonal: //가로 or 세로 or 대각선 이동
		if (map[r][c + 1] == 0)	dfs(horizontal, r, c + 1);
		if (map[r + 1][c] == 0)	dfs(vertical, r + 1, c);
		if (map[r][c + 1] == 0 && map[r + 1][c] == 0 && map[r + 1][c + 1] == 0)	dfs(diagonal, r + 1, c + 1);
	}
}

int main() {
	//input
	cin >> N;
	for (int i = 1; i <= N; i++){ //집 상태 입력
		for (int j = 1; j <= N; j++){
			cin >> map[i][j];
		}
	}

	//바운더리 -> 우측이랑 하단 벽만 있으면 ok
	for (int i = 1; i <= N; i++)
	{
		map[N + 1][i] = 1;
		map[i][N + 1] = 1;
	} 
	map[N + 1][N + 1] = 1;

	//map print
	//for (int i = 0; i < 18; i++)
	//{
	//	for (int j = 0; j < 18; j++)
	//	{
	//		cout << map[i][j] << " ";
	//	}
	//	cout << '\n';
	//}

	//solve
	dfs(horizontal, 1, 2); //초기 상태 (1,2)

	//output
	cout << answer;
}
