package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Shark {
	int speed;
	int dir;
	int size;
	
	public Shark() { }
	
	public Shark(int speed, int dir, int size) {
		this.speed = speed;
		this.dir = dir;
		this.size = size;
	}
}

public class BOJ_17143_낚시왕_200213 {
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int r = 0;
		int c = 0;
		int s = 0;
		int d = 0;
		int z = 0;
		int ans = 0;
		
		Shark[][] map = new Shark[R + 1][C + 1];
		Shark[][] mapNext;
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			map[r][c] = new Shark(s, d, z);
		}
		
		for(int j = 1; j <= C; j++) {
			// 해당 열에서 가장 가까운 상어 없애기
			for(int i = 1; i <= R; i++) {
				if(map[i][j] != null) {
					ans += map[i][j].size;
					map[i][j] = null;
					break;
				}
			}
			
			// 상어 이동하기
			mapNext = new Shark[R + 1][C + 1];
			for(int x = 1; x <= R; x++) {
				for(int y = 1; y <= C; y++) {
					if(map[x][y] != null) { // 상어객체가 존재한다면
						int dir = map[x][y].dir;
						int xx = (dx[map[x][y].dir] * map[x][y].speed) % ((R - 1) * 2); // 다시 제자리로 돌아오는 경우 나머지 구하기
						int yy = (dy[map[x][y].dir] * map[x][y].speed) % ((C - 1) * 2);	// 다시 제자리로 돌아오는 경우 나머지 구하기			
						if(map[x][y].speed == 0 || (xx == 0 && yy == 0)) { // 스피드가 0이거나 다시 제자리로 돌아올때
							xx = x;
							yy = y;
						} else if(xx > 0) { // 방향이 아래쪽일 때
							if(xx + x > R) {
								xx -= (R - x);
								if(xx > (R - 1)) {
									xx -= (R - 1);
									xx += 1;
								} else {
									xx = R - xx;
									dir = map[x][y].dir - 1;
								}
							} else {
								xx += x;
							}
							yy = y;
						} else if(yy > 0) { // 방향이 오른쪽일 때
							if(yy + y > C) {
								yy -= (C - y);
								if(yy > (C - 1)) {
									yy -= (C - 1);
									yy += 1;
								} else {
									yy = C - yy;
									dir = map[x][y].dir + 1;
								}
							} else {
								yy += y;
							}
							xx = x;
						} else if(xx < 0) { // 방향이 위쪽일 때
							if(xx + x < 1) {
								xx += (x - 1);
								if(-xx > (R - 1)) {
									xx += (R - 1);
									xx = R + xx;
								} else {
									xx = -xx + 1;
									dir = map[x][y].dir + 1;
								}
							} else {
								xx += x;
							}
							yy = y;
						} else if(yy < 0) { // 방향이 왼쪽일 때
							if(yy + y < 1) {
								yy += (y - 1);
								if(-yy > (C - 1)) {
									yy += (C - 1);
									yy = C + yy;
								} else {
									yy = -yy + 1;
									dir = map[x][y].dir - 1;
								}
							} else {
								yy += y;
							}
							xx = x;
						}
						
						// 이동한 상어 새로운 맵에다가 넣기
						if(mapNext[xx][yy] == null) {
							mapNext[xx][yy] = new Shark(map[x][y].speed, dir, map[x][y].size);
						} else {
							if(mapNext[xx][yy].size < map[x][y].size) {
								mapNext[xx][yy] = new Shark(map[x][y].speed, dir, map[x][y].size);
							}
						}
					}
				}
			}
			
			// map update
			for(int x = 1; x <= R; x++) {
				map[x] = mapNext[x].clone();
			}
		}
		
		System.out.println(ans);
	}
}
