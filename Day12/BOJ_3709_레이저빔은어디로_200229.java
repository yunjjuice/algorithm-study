/*
 * BOJ 3709 레이저빔은 어디로
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3709 {
	static int tc;
	static int n, r; // 보드 크기, 거울 개수
	static int[][] map;
	static int[] dx = {1, 0, -1, 0}; // 하좌상우
	static int[] dy = {0, -1, 0, 1};
	static Laser laser;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		tc = Integer.parseInt(st.nextToken());
		
		for (int TC = 0; TC < tc; TC++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			map = new int[n+2][n+2];
			for (int i = 0; i < r; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = 1;
			}
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
//			System.out.println("laser : " + x + " " + y);
//			laser = new Laser();
			// 시작점 보고 레이저 초기 방향 정해준다
			if(x == 0) { // 아래로 이동
				laser = new Laser(x, y, 0);
			} else if(x == n+1) { // 위로
				laser = new Laser(x, y, 2);
			} else if(y == 0) { // 우로 이동
				laser = new Laser(x, y, 3);
			} else if(y == n+1) { // 좌로 이동 
				laser = new Laser(x, y, 1);
			}
			
			game();
		}
	}
	
	static void game() {
		while(true) {
//			System.out.println("while : " + laser.x + " " + laser.y);
			// 이동하다가
			while(map[laser.x+dx[laser.dir]][laser.y+dy[laser.dir]] != 1) {
				laser.x = laser.x + dx[laser.dir];
				laser.y = laser.y + dy[laser.dir];
//				System.out.println("이동: " + laser.x + " " + laser.y);
				// 범위 벗어나면 끝
				if(laser.x == 0 || laser.y == 0 || laser.x == n+1 || laser.y == n+1) {
					System.out.println(laser.x + " " + laser.y);
					return;
				}
			}
			
//			System.out.println("이동 후 : " + laser.x + " " + laser.y);
			// 거울 만나면 방향 바꾸고
//			System.out.println("dir : " + laser.dir);
			laser.x = laser.x + dx[laser.dir];
			laser.y = laser.y + dy[laser.dir];
			laser.dir = (laser.dir + 1) % 4;
		}
	}
}

class Laser {
	int x;
	int y;
	int dir;
	public Laser(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}