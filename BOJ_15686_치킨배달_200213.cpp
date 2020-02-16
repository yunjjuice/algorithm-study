#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

#define INFINITY 999999

class Pair {
public:
	int r, c;
	Pair(int _r, int _c) {
		r = _r;
		c = _c;
	}
};

int main() {
	//input
	int N, M; //N은 도시의 크기, M은 골라야 할 치킨 집의 "최대" 개수
	cin >> N >> M;
	//int map[51][51];

	vector<Pair> houses;
	houses.reserve(2 * N); // 1 < 집의 개수 <= 2N
	vector<Pair> chickens;
	chickens.reserve(13); // M < 치킨집 개수 < 13

	int tmp;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> tmp; //map[i][j];
			//if (map[i][j] == 1)	houses.push_back({ i,j });
			//else if (map[i][j] == 2)	chickens.push_back({ i,j });
			if (tmp == 1)	houses.push_back({ i,j });
			else if (tmp == 2)	chickens.push_back({ i,j });
		}
	}

	//solve
	//각 집에 대한 각 치킨집의 거리 -> 치킨집을 고를 수 있는 모든 경우의 수에 대해 탐색 (최솟값)
	vector<vector<int>> dist(chickens.size(), vector<int>(houses.size())); //각 치킨집과 집 사이의 거리 - 치킨집이 행, 집이 열
	for (int ch = 0; ch < chickens.size(); ch++) {
		for (int ho = 0; ho < houses.size(); ho++) {
			dist[ch][ho] = abs(chickens[ch].r - houses[ho].r) + abs(chickens[ch].c - houses[ho].c);
		}
	}

	int answer = INFINITY; //infinity
	vector<int> arr(chickens.size(), 0);
	for (int i = 1; i <= M; i++)
	{
		arr[chickens.size() - i] = 1;
	} //고른 치킨집 배열 (1이면 고르기)

	do {
		vector<int> chickenDist(houses.size(), INFINITY);
		for (int i = 0; i < arr.size(); i++)
		{
			if (arr[i] == 0)	continue;
			for (int j = 0; j < chickenDist.size(); j++)
			{
				if (dist[i][j] < chickenDist[j])	chickenDist[j] = dist[i][j];
			}
		}

		int sum = 0;
		for (int i = 0; i < chickenDist.size(); i++)
		{
			sum += chickenDist[i];
		}
		if (answer > sum)	answer = sum;
	} while (next_permutation(arr.begin(), arr.end()));

	//output
	cout << answer;
}
