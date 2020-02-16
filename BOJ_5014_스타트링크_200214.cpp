#include <iostream>
#include <queue>
using namespace std;

int main() {
	//input
	int F, S, G, U, D;
	cin >> F >> S >> G >> U >> D;

	//solve
	int answer = -1;
	queue<int> q;
	vector<bool> visited(F + 1, false);
	q.push(S);
	visited[S] = true;
	bool flag = false;
	while (!q.empty() && !flag) {
		int q_size = q.size();
		while (q_size > 0) //같은 엘베 누르는 횟수 중에서
		{
			int now = q.front();
			q.pop();
			if (now == G) { //도착
				flag = true;
				break;
			}
			if (now + U <= F && !visited[now + U]) {
				q.push(now + U); visited[now + U] = true;
			}
			if (now - D > 0 && !visited[now - D]) {
				q.push(now - D); visited[now - D] = true;
			}
			q_size--;
		}
		answer++;
	}

	//output
	if (!flag)	cout << "use the stairs";
	else  cout << answer;
}
