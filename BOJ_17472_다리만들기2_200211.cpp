/*
17472. 다리 만들기 2 Gold 3
https://www.acmicpc.net/problem/17472

1. 섬 분리해서 번호 부여
2. 각 섬마다 이을 수 있는 다리 찾기
	-> 섬의 각 땅마다
	-> 이어지는 섬의 번호, 가장 짧은 다리의 길이 저장
3. 모든 섬을 연결할 수 있는, 가장 짧은 다리 길이 찾기
	-> MST (최소 신장 트리) : Prim
*/

#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;

int N, M;
int map[10][10];
int bridge[7][7]; //각 섬을 잇는 다리의 최단 길이를 저장하는 배열, 무향그래프, 섬은 1번부터 isLandsNum(최대 6)번 index까지
int islandsNum = 0;
int answer = 10000; //max_int

void findIslands() {
	//1. 섬 분리하기
	queue<pair<int, int>> q;
	bool checked[10][10] = { false, };
	int dx[4] = { 0,0,1,-1 }; //왼쪽, 오른쪽, 아래, 위
	int dy[4] = { -1,1,0,0 };
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (!checked[i][j] && map[i][j] == 1) { //탐색하지 않은 땅
				islandsNum++;
				//bfs
				q.push({ i,j });
				map[i][j] = islandsNum; //섬 번호 부여 -> 1부터
				checked[i][j] = true;
				while (!q.empty()) {
					int x = q.front().first, y = q.front().second;
					q.pop();

					//4방향 탐색
					for (int k = 0; k < 4; k++) {
						int nx = x + dx[k], ny = y + dy[k];
						if (nx < N && ny < M && nx >= 0 && ny >= 0 && !checked[nx][ny] && map[nx][ny] == 1) { //땅 있음
							map[nx][ny] = islandsNum; //섬 번호 부여
							checked[nx][ny] = true;
							q.push({ nx, ny });
						}
					}

				}
			}
		}
	}
  //cout << "***********************\n";
	//for (int i = 0; i < N; i++) {
	//	for (int j = 0; j < M; j++) {
	//		cout << map[i][j] << " ";
	//	}
	//	cout << '\n';
	//}
	//cout << "***********************\n";
}

void findBridges() {
	//2. 다리 찾기
	fill(&bridge[0][0], &bridge[6][6]+1, 10); //10으로 초기화
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (map[i][j] != 0) { //섬 도착
				//다리 잇기 -> 오른쪽, 아래만 탐색해도 ok
				for (int len = 1; len < M - j; len++) {//우측 탐색
					if (map[i][j + len] == map[i][j])	break; //내 땅임 -> 다리 x
					if (map[i][j + len] != 0) { //다른 섬 도착!
						if (len > 2 && bridge[map[i][j]][map[i][j + len]] > len - 1) { //누적 값이 더 크면
							bridge[map[i][j]][map[i][j + len]] = len - 1; //다리 길이 갱신
							bridge[map[i][j + len]][map[i][j]] = len - 1;
						}
						break;
					}
				}
				for (int len = 1; len < N - i; len++) {//아래쪽 탐색
					if (map[i + len][j] == map[i][j])	break; //내 땅임 -> 다리 x
					if (map[i + len][j] != 0) { //다른 섬 도착!
						if (len > 2 && bridge[map[i][j]][map[i + len][j]] > len - 1) { //누적 값이 더 크고, 다리 길이가 1 초과
							bridge[map[i][j]][map[i + len][j]] = len - 1; //다리 길이 갱신
							bridge[map[i + len][j]][map[i][j]] = len - 1;
						}
						break;
					}
				}
			}
		}
	}
	//for (int i = 2; i <= islandsNum; i++)
	//{
	//	for (int j = 2; j <= islandsNum; j++)
	//	{
	//		if (bridge[i][j] != 10)
	//			cout << i << "," << j << "번 연결 다리 -> " << bridge[i][j] << '\n';
	//	}
	//}

	//for (int i = 1; i < 7; i++) {
	//	for (int j = 1; j < 7; j++) {
	//		cout << bridge[i][j] << " ";
	//	}
	//	cout << '\n';
	//}
	//cout << "***********************\n";
}

void findMin(int v, int vnum, int len) {
	bool visited[7] = { false, };
	visited[v] = true;
	while (vnum < islandsNum) {
		int index, min = 10000;
		for (int j = 1; j <= islandsNum; j++)
		{
			if (visited[j])	continue;
			if (bridge[v][j] != 10) {
				if (min > bridge[v][j]) {
					min = bridge[v][j];
					index = j;
				}
			}
		}

		if (min == 10000)	break; //이어진 섬 x
		vnum++;
		len += min;
		visited[index] = true;
		for (int j = 1; j <= islandsNum; j++)
		{
			if (j == v)	continue;
			if (bridge[index][j] < bridge[v][j]) {
				bridge[v][j] = bridge[index][j];
			}
		}
	}

	if (vnum == islandsNum) { //전부 방문
		if (answer > len)	answer = len; //갱신
		return;
	}
}

/* 아래는 위 함수의 다른 버전 (이것도 Prim인지 아닌지 모르겠음)
bool has(vector<int> v, int a) {
	for (int i = 0; i < v.size(); i++) {
		if (v[i] == a)
			return true;
	}
	return false;
}

void findMin(vector<int> visited, int len) {
	if(visited.size() == islandsNum - 1 ) { //전부 방문
		if (answer > len)	answer = len; //갱신
		return;
	}

	int index, min = 10000;
	for (int i = 0; i < visited.size(); i++) {
		int v = visited[i];
		for (int j = 2; j <= islandsNum; j++)
		{
			if (has(visited, j))	continue;
			if (bridge[v][j] != 10) {
				if (min > bridge[v][j]) {
					min = bridge[v][j];
					index = j;
				}
			}
		}
	}

	if (min == 10000)	return;
	else
	{
		visited.push_back(index);
		findMin(visited, len + min);
	}
}
*/

int main() {
	//input
	cin >> N >> M;
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			cin >> map[i][j];
		}
	}

	//solve
	findIslands();
	findBridges();
	findMin(1, 1, 0); //1번 섬에서 시작

	//output
	cout << (answer == 10000? -1 : answer);
}
