/*
17471. 게리맨더링 Gold V
https://www.acmicpc.net/problem/17471
*/

#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;

#define INT_MAX 1000000

int N; //구역의 개수
int population[11] = { 0, }; //각 구역의 인구 수

vector<vector<int>> connected; //각 구역의 인접리스트

int answer = INT_MAX;

bool isConnected(vector<int> &zone) {
	bool flag = true;
	queue<int> q;
	vector<bool> visited(N + 1, false);
	q.push(zone[0]);
	visited[zone[0]] = true;
	while (!q.empty()) {
		int v = q.front();
		q.pop();

		for (int i = 0; i < connected[v].size(); i++)
		{
			int u = connected[v][i];
			if (!visited[u] && (find(zone.begin(), zone.end(), u) != zone.end())) {
				q.push(u);
				visited[u] = true;
			}
		}
	}

	//전부 연결되어있을 경우 visited[zone[0~zone.size()-1]은 true
	for (int i = 0; i < zone.size(); i++)
	{
		if (!visited[zone[i]]) //방문 x -> 연결 x
			flag = false;
	}
	return flag;
}

int getPopulation(vector<int> &zone) {
	int sum = 0;
	for (int i = 0; i < zone.size(); i++)
	{
		sum += population[zone[i]];
	}
	return sum;
}

int main() {
	//input
	cin >> N;
	for (int i = 1; i <= N; i++)
	{
		cin >> population[i];
	}

	connected = vector<vector<int>>(N + 1);
	for (int i = 1; i <= N; i++)
	{
		int tmp;
		cin >> tmp; //i번 선거구에 인접한 구역의 수
		connected[i] = vector<int>(tmp);
		for (int j = 0; j < tmp; j++)
		{
			cin >> connected[i][j];
		}
	}

	//solve
	//N개를 2묶음으로 나누는 방법 -> 1~N/2개를 고르는 방법 (조합)
	for (int i = 1; i <= N/2; i++)
	{
		vector<int> index(N + 1, 1); //0인 인덱스와 1인 인덱스 두 구역으로 나눔 -> 1개 ~ N/2개는 0, N-1개 ~ N/2(+1)개는 1
		for (int k = 1; k <= i; k++)
		{
			index[k] = 0;
		}

		do
		{
			vector<int> zone1, zone2; //1구역, 2구역
			for (int z = 1;  z <= N;  z++) //구역 분리
			{
				if (index[z] == 0)	zone1.push_back(z);
				else	zone2.push_back(z);
			}
			if (isConnected(zone1) && isConnected(zone2)) { //두 구역 모두 연결됨
				int diff = abs(getPopulation(zone1) - getPopulation(zone2));
				answer = answer > diff ? diff : answer; //갱신
			}

		} while (next_permutation(index.begin() + 1, index.end()));
	}

	if (answer == INT_MAX)
		answer = -1;

	//output
	cout << answer;
}
