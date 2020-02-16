/*
 * BOJ 17135 캐슬 디펜스
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17135 {
	static int N, M, D;
	static int[][] map;
	static boolean[] used; // 궁수 위치 놓을 때 쓸 것
	static List<Enemy> enemy = new ArrayList<>();
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		D = Integer.parseInt(st.nextToken()); // 궁수의 공격 거리 제한
		map = new int[N+1][M];
		for (int i = 0; i < map.length-1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); // 0은 빈 칸, 1은 적
				if(map[i][j] == 1) {
					enemy.add(new Enemy(i, j, false));
				}
			}
		}
		used = new boolean[M]; 
//		for (int i = 0; i < map[N].length; i++) {
//			map[N][i] = 2; // 성
//		}
		
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		ans = Integer.MIN_VALUE; // 궁수의 공격으로 제거할 수 있는 적의 최대 수
		// 궁수 3명을 배치한다
		pick(0, 0);
		// 3명 배치 완료되면 게임 시작
		// 1턴 - 궁수가 공격. 공격받은 적은 사라짐
		// 		적이 아래로 한 칸 이동. 성에 오거나 격자판 벗어나면 그 적은 사라짐
		// 모든 적이 제외되면 게임 끝
		// 궁수의 공격으로 제거할 수 있는 적의 최대 수 출력
		System.out.println(ans);
	}
	
	public static void pick(int idx, int cnt) { // idx : 배치할 인덱스, cnt : 배치한 개수-> 궁수는 총 세 명
		List<Archer> archer = new ArrayList<>();
		if(cnt == 3) { // 궁수는 세 명이니까
			for (int i = 0; i < M; i++) {
				if(used[i]) {
					archer.add(new Archer(N, i));
//					System.out.print(i); // 세 명이 놓여질 수 있는 위치
				}
			}
			game(archer);
//			System.out.println();
		}
		for (int i = idx; i < M; i++) {
			if(!used[i]) {
				used[i] = true;
				pick(i, cnt+1);
				used[i] = false;
			}
		}
	}
	
	public static void game(List<Archer> archer) {
//		System.out.println("archer");
//		for (int i = 0; i < archer.size(); i++) {
//			System.out.println("y : "+archer.get(i).y + " x : " + archer.get(i).x);
//		}
//		System.out.println("enemy");
//		for (int i = 0; i < enemy.size(); i++) {
//			System.out.println("y : "+enemy.get(i).y + " x : " + enemy.get(i).x);
//		}
		List<Enemy> en = new ArrayList<>();
		for (int i = 0; i < enemy.size(); i++) {
			en.add(new Enemy(enemy.get(i)));
		}
		
//		en = enemy;
		int count = 0; // 죽인 적의 수
		while(!en.isEmpty()) { // 적이 없어질때까지 게임이 진행된다
			// 궁수가 제한 거리 내에 있는 가장 가까운 적을 공격
			for (int i = 0; i < archer.size(); i++) {
				int minDist = D+1;
				int minY = N+1, minX = M; // 죽일 적 좌표 저장
				int minIdx = en.size(); // 죽일 적의 인덱스
				for (int j = 0; j < en.size(); j++) { // 적의 거리랑 비교
					int dist = Math.abs(archer.get(i).y-en.get(j).y) + Math.abs(archer.get(i).x-en.get(j).x);
					// 궁수를 위로 옮길 거라서 궁수의 y좌표가 적의 y좌표보다 항상 커야됨
					if(dist <= D /* && archer.get(i).y > en.get(j).y */) { // 제한 거리 내에 있으면
						// 근데 거리 내에 있는 것 중에서 최소거리인 애 찾아야함
						if(minDist > dist) {
							minDist = dist;
							minX = en.get(j).x;
							minY = en.get(j).y;
							minIdx = j;
						} else if(dist == minDist) { // 최소거리가 같으면 제일 왼쪽에 있는 애로 한다.
							if(minX > en.get(j).x) {
								minX = en.get(j).x;
								minY = en.get(j).y;
								minIdx = j;
							} 
//							else {
//								minX = en.get(j).x;
//								minY = en.get(j).y;
//								minIdx = j;
//							}
						}
					}
				} // 한 궁수가 모든 적과 거리 비교 끝
//				System.out.println("minIdx " + minIdx);
				if(minIdx < en.size())
					en.get(minIdx).isDead = true;
			} // 궁수 종료 끝
			for (int i = 0; i < en.size(); i++) {
				if(en.get(i).isDead) {
					en.remove(i);
					count++;
					i--;
				}
			}

			// 적 아래로 이동 -> 궁수를 위로 이동
			for (int i = 0; i < archer.size(); i++) {
				archer.get(i).y -= 1;
			}
			for (int i = 0; i < en.size(); i++) {
				if(en.get(i).y >= archer.get(0).y) {
					en.remove(i); 
					i--;
				}
			}
		}
		// 게임이 다 끝나면 최대로 죽인 수를 ans에 저장
//		System.out.println("count: " + count);
//		System.out.println("ans : "+ans);
		ans = Math.max(ans, count);
	}
}


class Archer { // 궁수의 좌표 저장
	int x;
	int y;
	public Archer(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

class Enemy { // 적의 좌표 저장
	int x;
	int y;
	boolean isDead; // 죽었는지 확인 -> 한번에 중복 공격이 가능하므로 만들어둠. 일단 죽었다 표시하고 한꺼번에 remove하려고
	public Enemy(int y, int x, boolean isDead) {
		this.y = y;
		this.x = x;
		this.isDead = isDead;
	}
	public Enemy(Enemy enemy) {
		this.y = enemy.y;
		this.x = enemy.x;
		this.isDead = enemy.isDead;
	}
	public void setDead(int y, int x, boolean dead) {
		if(y == this.y && x == this.x) {
			isDead = dead;
		}
	}
	
}