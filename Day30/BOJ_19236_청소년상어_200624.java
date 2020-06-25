/*
 * BOJ 19236 청소년 상어
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_19236_청소년상어_200624 {
	static int ans;
	static int[][] dir = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int[][] map = new int[4][4];
		Fish[] fish = new Fish[17];
		
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken()) - 1;
				map[i][j] = a;
				fish[a] = new Fish(i, j, b, false);
			}
		}
		
		int die = map[0][0];
		map[0][0] = 0;
		fish[die].dead = true;
		int sharkDir = fish[die].dir;
		
		move(map, fish, 0, 0, sharkDir, die); // 맵, 물고기, 상어 좌표 x, y, 상어 방향, 합
		
		System.out.println(ans);
	}
	
	private static void move(int[][] map, Fish[] fish, int sx, int sy, int sd, int sum) {
		// 물고기 이동
		for (int i = 1; i <= 16; i++) {
			if(fish[i].dead) continue;
			
			int fx = fish[i].x;
			int fy = fish[i].y;
			int fd = fish[i].dir;
			
			for (int j = 0; j < 8; j++) {
				int nd = (fd + j) % 8;
				int nx = fx + dir[nd][0];
				int ny = fy + dir[nd][1];
				
				// 범위를 벗어나거나 상어가 있으면 방향을 바꾼다
				if(!isRange(nx, ny)) continue;
				if(map[nx][ny] == 0) continue;
				
				if(map[nx][ny] > 0) { // 다른 물고기가 있으면 서로 자리를 바꿈
					int tmp = map[nx][ny];
					map[nx][ny] = i;
					map[fx][fy] = tmp;
					fish[i].x = nx;
					fish[i].y = ny;
					fish[i].dir = nd;
					fish[tmp].x = fx;
					fish[tmp].y = fy;
					break;
				} else { // 빈칸이면
					map[nx][ny] = i;
					map[fx][fy] = -1;
					fish[i].x = nx;
					fish[i].y = ny;
					fish[i].dir = nd;
					break;
				}
			}
		}
		
//		for (int i = 0; i < 4; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		System.out.println(Arrays.toString(fish));
		
		// 상어 이동
		boolean flag = false;
		
		for (int i = 1; i <= 3; i++) {
			int nsx = sx + i * dir[sd][0];
			int nsy = sy + i * dir[sd][1];
		
			int[][] map2 = new int[4][4];
			Fish[] fish2 = new Fish[17];
			
			for (int j = 0; j < 4; j++) {
				for (int j2 = 0; j2 < 4; j2++) {
					map2[j][j2] = map[j][j2];
				}
			}
			
			for (int j = 1; j <= 16; j++) {
				fish2[j] = new Fish();
				fish2[j].x = fish[j].x;
				fish2[j].y = fish[j].y;
				fish2[j].dir = fish[j].dir;
				fish2[j].dead = fish[j].dead;
			}
			

			if(isRange(nsx, nsy) && map2[nsx][nsy] > 0) {
				int die = map2[nsx][nsy];
				map2[nsx][nsy] = 0;
				map2[sx][sy] = -1;
				fish2[die].dead = true;
				int nsd = fish2[die].dir;
				
				move(map2, fish2, nsx, nsy, nsd, sum+die);

				flag = true;
			}
		}
		
		if(!flag) {
			ans = Math.max(ans, sum);
			return;
		}
	}
	
	static boolean isRange(int x, int y) {
		return 0<=x && x<=3 && 0<=y && y<=3;
	}

	static class Fish {
		int x, y, dir;
		boolean dead;
		
		public Fish() { }
		
		public Fish(int x, int y, int dir, boolean dead) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.dead = dead;
		}

		@Override
		public String toString() {
			return "Fish [x=" + x + ", y=" + y + ", dir=" + dir + ", dead=" + dead + "]";
		}
	}
}
