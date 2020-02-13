	/* 17143. 낚시왕 Gold 3
	https://www.acmicpc.net/problem/17143

	- R(물의 깊이)*C(땅의 크기), 인덱스는 1부터
	- 각 칸에는 상어가 최대 한 마리, 상어는 크기와 속도를 가지고 있음
	- 초기 위치: 1번 열의 한 칸 왼쪽 (0번 열)
	- 매 초마다
		1. 우측으로 한 칸 이동 (++열), 배열 범위를 벗어날 경우 중단
		2. 동일한 열에 있는 상어 중 행 번호가 가장 작은 상어를 잡는다 (상어 삭제)
		3. 상어 이동
			- 입력으로 주어진 속도만큼 이동
			- 격자판의 경계에 부딪힐 경우 방향을 바꿔 이동
		4. 동일한 칸에 있는 상어들 -> 가장 큰 상어만 남는다
	- 낚시왕이 잡은 상어 크기의 합 구하기
	*/

	#include <iostream>
	#include <algorithm>
	#include <vector>
	#include <climits>
	using namespace std;

	#define INFINITY 999999

	enum Dir {
		non = 0,
		up = 1,
		down = 2,
		right = 3,
		left = 4
	};

	class Shark {
	public:
		int s, d, z;
		Shark() {
			s = d = z = 0;
		}
		Shark(int _s, int _d, int _z) { //속력, 이동 방향, 크기 설정
			s = _s;
			d = _d;
			z = _z;
		}

		void die() {
			s = d = z = 0;
		}
	};

	struct {
		int r, c;
		Shark s;
	}typedef Tuple;

	int R, C, M;
	vector<vector<Shark>> map;

	int sharkMove(int r, int c, Shark &s) { //상어가 제자리로 돌아오는 경우는 없다.
		int dist = s.s; //움직여야 할 칸 수
		int mm; //움직일 방향 
		switch (s.d)
		{
		case Dir::up:
		case Dir::down:
			mm = (s.d == Dir::up ? -1 : 1); //위 -> r 감소, 아래 -> r 증가
			while (dist > 0) {
				if (r == 1)	mm = 1;
				if (r == R) mm = -1;
				r += mm;
				dist--;
			}
			s.d = (mm == -1 ? Dir::up : Dir::down);
			return r; //이동한 칸 알려주기
			break;
		case Dir::left:
		case Dir::right:
			mm = (s.d == Dir::left ? -1 : 1); //왼쪽 -> r 감소, 오른쪽 -> r 증가
			while (dist > 0) {
				if (c == 1)	mm = 1;
				if (c == C) mm = -1;
				c += mm;
				dist--;
			}
			s.d = (mm == -1 ? Dir::left : Dir::right);
			return c;
			break;
		}
	}

	int main() {
		//input
		cin >> R >> C >> M;
		map.assign(R + 1, vector<Shark>(C + 1));
		int comebackR = (R - 1) * 2, comebackC = (C - 1) * 2; //제자리로 돌아오는 데 걸리는 이동 횟수 (== 속력)
		int r, c, s, d, z;
		for (; M > 0; M--) {
			cin >> r >> c >> s >> d >> z;
			if (d == up || d == down)	s %= comebackR; //차피제자리 ~
			else s %= comebackC;
			map[r][c] = { s,d,z };
		}

		//solve
		int answer = 0; //잡은 상어 크기의 합
		int fisher = 0; //낚시왕의 위치
		while (++fisher <= C) { //1. 우측으로 한 칸 이동
			//2. 상어 잡기
			for (int depth = 1; depth <= R; depth++) {
				if (map[depth][fisher].z >= 1) { //상어가 있음
					answer += map[depth][fisher].z;//잡음
					map[depth][fisher].die();
					break;
				}
			}

			//3. 상어 이동
			vector<Tuple> movingSharks;//움직인 상어 리스트
			for (r = 1; r <= R; r++)
			{
				for (c = 1; c <= C; c++)
				{
					if (map[r][c].s != 0) {
						int k = sharkMove(r, c, map[r][c]);
						if (map[r][c].d == Dir::up || map[r][c].d == Dir::down) {
							//리스트에 넣기 (움직일 위치도)
							movingSharks.push_back({ k, c, map[r][c] });
						}
						else {
							movingSharks.push_back({ r, k, map[r][c] });
						}//end of if else .d
						map[r][c].die();
					} // end of if s!= 0
				}
			}

			//4.한 칸에 한 상어만 남기기
			//for every shark
			for (int i = 0; i < movingSharks.size(); i++)
			{
				int r = movingSharks[i].r, c = movingSharks[i].c;
				if (map[r][c].z == 0) { //이동하려는 칸에 다른 상어가 없으면
					map[r][c] = movingSharks[i].s;
				}
				else { //있으면 -> 크기 비교 후 큰 쪽만 살아남는다
					if (map[r][c].z < movingSharks[i].s.z) {
						map[r][c] = movingSharks[i].s;
					}
				}
			}

		} //end of fisher moving
		//output
		cout << answer;
	}
