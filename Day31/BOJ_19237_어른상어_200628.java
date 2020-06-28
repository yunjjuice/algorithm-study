/*
 * BOJ 19237 어른 상어
 * https://www.acmicpc.net/problem/19237
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_19237_어른상어_200628 {
	static int N, M, k; 
	static Shark[][] map;
	static Shark[] sharks;
	static int time;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][][] sDir;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new Shark[N][N];
		sharks = new Shark[M+1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if(a > 0)
					sharks[a] = new Shark(a, i, j, 0, k, true);
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= M; i++) {
			sharks[i].dir = Integer.parseInt(st.nextToken()) - 1;		
		}
		
		for (int i = 1; i <= M; i++) {
			int x = sharks[i].x;
			int y = sharks[i].y;
			map[x][y] = new Shark(i, x, y, sharks[i].dir, sharks[i].remain, true);
		}
		
		sDir = new int[M+1][4][4];
		
		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int l = 0; l < 4; l++) {
					sDir[i][j][l] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}
		
//		for (int i = 1; i <= M; i++) {
//			for (int j = 0; j < 4; j++) {
//				System.out.println(Arrays.toString(sDir[i][j]));
//			}
//		}
//		
//		System.out.println(Arrays.toString(sharks));
		
		move();
		
		System.out.println(time<=1000?time:-1);
	}
	
	private static void move() {
		time = 0;
		Shark[][] copy;
		while(time <= 1001) {
			copy = new Shark[N][N];
			time++;
			
			// 상어 이동
			for (int i = 1; i <= M; i++) {
				// 죽으면 이동 안 함
				if(!sharks[i].real) continue;
				int cx = sharks[i].x;
				int cy = sharks[i].y;
				int cd = sharks[i].dir;
				int nx = cx;
				int ny = cy;
				int nd = -1;
				int fx = 0, fy = 0, fd = 0; // 최종(final) x,y,방향 
				int high = 5; // 우선순위
				boolean flag = false; // 이동할 곳이 있는지
				for (int j = 0; j < 4; j++) {
					nd = sDir[i][cd][j];
					nx = cx + dir[nd][0];
					ny = cy + dir[nd][1];
					// 빈칸이 있으면 그곳으로, 빈칸 없으면 자기 냄새 있던 곳으로
					if(isRange(nx,ny)) {
						if(map[nx][ny] == null) {
							flag = true;
							fx = nx;
							fy = ny;
							fd = nd;
							break;
						} else if(map[nx][ny].num == i) {
							flag = true;
							if(high > j) { // 내가 갈 수 있는 방향이 여러군데면 우선순위 큰 곳으로 간다
								high = j;
								fx = nx;
								fy = ny;
								fd = nd;
							}
							continue;
						}
					}
				}
				
				if(flag) {
					if(copy[fx][fy] == null || copy[fx][fy].num > i) {
						copy[fx][fy] = new Shark(i, fx, fy, fd, k, true);
						sharks[i].x = fx;
						sharks[i].y = fy;
						sharks[i].dir = fd;
					} else if(copy[fx][fy].num < i) {
						sharks[i].real = false;
					}
				}
			}
			
			// 상어들 이동 다 끝나면 기존거 정리
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] != null) {
						map[i][j].remain -= 1;
						map[i][j].real = false;
						if(map[i][j].remain <= 0)
							map[i][j] = null;
					}
				}
			}
			
			boolean flag = true;
			for (int i = 1; i <= M; i++) {
				if(!sharks[i].real) continue;
				int x = sharks[i].x;
				int y = sharks[i].y;
				int d = sharks[i].dir;
				map[x][y] = new Shark(i, x, y, d, k, true);
				if(i != 1 && sharks[i].real)
					flag = false;
			}
			
//			System.out.println(time);
//			System.out.println(Arrays.toString(sharks));
//			for (int i = 0; i < N; i++){
//				for (int j = 0; j < N; j++) {
//					if(map[i][j] != null)
//						System.out.print(map[i][j].num + " ");
//					else
//						System.out.print("0 ");
//				}
//				System.out.println();
//			}
			
			if(flag) return;
		}
		
//		// 시간 넘으면 -1 출력 
//		if(time > 1000) {
//			time = -1;
//			return;
//		}
	}
	
	static boolean isRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<N;
	}

	static class Shark {
		int num;
		int x, y;
		int dir;
		int remain; // 남은 시간
		boolean real; // 상어인지 남은 냄새인지

		public Shark(int num, int x, int y, int dir, int remain, boolean real) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.remain = remain;
			this.real = real;
		}
	}
}
