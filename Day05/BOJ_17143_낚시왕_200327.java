/*
 * BOJ 17143 낚시왕
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17143 {
	static int R, C, M, ans, king;
	static Shark[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new Shark[R][C];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			map[r][c] = new Shark(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		king = ans = 0;
		
		while(king < C) {
			take(); // 낚시왕 움직이면서 상어를 잡는다.
			move(); // 상어들이 움직인다
			king++;
		}
		
		System.out.println(ans);
	}
	
	public static void take() {
		for (int i = 0; i < R; i++) {
			if(map[i][king] != null) {
				ans += map[i][king].size;
				map[i][king] = null; // 상어를 잡는다
				break;
			}
		}
	}

	public static void move() {
		Shark[][] tmp = new Shark[R][C];
		
		int d=0, s=0, k=0, x=0, y = 0; // speed, dir, size, x, y 좌표
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				Shark cur = map[i][j];
				if(cur == null) continue; // 자리에 상어 없으면 다음으로 넘어감
				if(cur.dir == 1) { // 위
					k = i;
					s = cur.speed % (2*(R-1));
					d = -1; // 위로 가야 하니까
					while(s-- > 0) { // 남은 스피드가 0이 될때까지 이동
						if(k+d < 0 || k+d>=R) d = -d;
						k += d;
					}
					x = k;
					y = j;
					d = d<0 ? 1 : 2;
				} else if(cur.dir == 2) { // 아래
					k = i;
					s = cur.speed % (2*(R-1));
					d = 1;
					while(s-- > 0) {
						if(k+d < 0 || k+d >= R) d = -d;
						k += d;
					}
					x = k;
					y = j;
					d = d<0 ? 1 : 2;
				} else if(cur.dir == 3) { // 오른쪽
					k = j;
					s = cur.speed % (2*(C-1));
					d = 1;
					while(s-- > 0) {
						if(k+d < 0 || k+d >= C) d = -d;
						k += d;
					}
					x = i;
					y = k;
					d = d>0 ? 3 : 4; 
				} else if(cur.dir == 4){ // 왼쪽
					k = j;
					s = cur.speed % (2*(C-1));
					d = -1;
					while(s-- > 0) {
						if(k+d < 0 || k+d >= C) d = -d;
						k += d;
					}
					x = i;
					y = k;
					d = d>0 ? 3 : 4;
				}
				
				cur.dir = d;
				
				if(tmp[x][y] != null) {
					if(cur.size > tmp[x][y].size)
						tmp[x][y] = cur;
				} else {
					tmp[x][y] = cur;
				}
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = tmp[i][j]; // 이동시킨 맵을 원래 맵으로 복사해준다.
			}
		}
	}
	
	static class Shark {
		int speed;
		int dir; // 1:위, 2:아래, 3:오른쪽, 4:왼쪽
		int size;

		public Shark(int speed, int dir, int size) {
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}
}
