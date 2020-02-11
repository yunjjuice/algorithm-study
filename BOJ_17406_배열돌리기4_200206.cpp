/* 17406. 배열 돌리기 4 Gold V
https://www.acmicpc.net/problem/17406

- 가능한 회전 연산 실행 순서를 모두 실행하며 최솟값 갱신하기
	-> 연산 수행 순서에 따라 배열의 모양이 다르다 

실행 결과
메모리 1988KB 시간 12ms
*/
#include <iostream>
#include <tuple>
#include <vector>
#include <climits>
using namespace std;

int N, M, K;
vector<tuple<int, int, int>> turnOp;
int answer = INT_MAX;

vector<vector<int>> turn(int r, int c, int s, vector<vector<int>> arr) { //회전시키기 -> 매개변수 arr이 변한다
	//가장 왼쪽 윗 칸이 (r-s, c-s), 가장 오른쪽 아랫 칸이 (r+s, c+s)인 정사각형을 시계 방향으로 한 칸씩 돌린다
	// -> 반시계방향으로 진행하며 값을 가져온다
	for (int n = 0; n < s; n++) { //바깥 쪽 사각형부터 회전 -> s는 사각형 개수
		int len = 2*(s-n); //정사각형의 한 변의 길이 -1
		int x = r - s + n, y = c - s + n; //가장 왼쪽 윗 칸의 좌표
		int leftTop = arr[x][y]; //가장 왼쪽 윗 칸의 값

		//좌변
		for (int i = 0; i < len; i++, x++){
			arr[x][y] = arr[x + 1][y];
		}
		//밑변
		for (int i = 0; i < len; i++, y++) {
			arr[x][y] = arr[x][y + 1];
		}
		//우변
		for (int i = 0; i < len; i++, x--) {
			arr[x][y] = arr[x - 1][y];
		}
		//윗변
		for (int i = 0; i < len-1; i++, y--) { //마지막 한 칸은 저장해둔 값으로 지정
			arr[x][y] = arr[x][y - 1];
		}
		arr[x][y] = leftTop;
	}
	return arr;
}

int getMinSum(vector<vector<int>> &arr) {
	int min = INT_MAX;
	for (int i = 1; i < arr.size(); i++)
	{
		int s = 0;
		for (int j = 0; j < arr[0].size(); j++)
		{
			s += arr[i][j];
		}
		if (s < min)	min = s;
	}
	return min;
}

void dfs(int n, vector<bool> turned, vector<vector<int>> arr) { 
	//n은 현재까지 수행한 연산의 개수, turned & arr - call by value
	if (n == K) { //연산 수행 끝 -> 계산
		int tmp = getMinSum(arr);
		if (tmp < answer)	answer = tmp;
		return;
	}
	for (int i = 0; i < K; i++)
	{
		if (!turned[i]) { //아직 안돌았어
			turned[i] = true;
			dfs(n + 1, turned, turn(get<0>(turnOp[i]), get<1>(turnOp[i]), get<2>(turnOp[i]), arr));
			turned[i] = false;
		}
	}
}

int main() {
	//input
	cin >> N >> M >> K;
	vector<vector<int>> arr(N+1, vector<int>(M+1)); //index는 1~N * 1~M
	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= M; j++)
		{
			cin >> arr[i][j];
		}
	}

	turnOp = vector<tuple<int,int,int>>(K); //인스턴스 생성
	for (int i = 0; i < K; i++){
		cin >> get<0>(turnOp[i]) >> get<1>(turnOp[i]) >> get<2>(turnOp[i]);
	}

	//solve
	//가능한 연산 실행 순서의 모든 경우의 수 구하기 -> 순열 (재귀)
	vector<bool> turned(K, false);
	dfs(0, turned, arr);

	//output
	cout << answer;
}
