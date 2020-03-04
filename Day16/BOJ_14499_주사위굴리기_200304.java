/*
 * BOJ 14499 주사위 굴리기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14499 {
	static int n, m, x, y, k;
	static int[][] map;
	static int[] dice = new int[7]; // 위1 바닥6, 주사위 인덱스 2156(세로), 4136(가로)끼리 한줄 (계산하기 쉬우려고 0 제외)
	static int[] dx = {0, 0, -1, 1}; // 1 동, 2 서, 3 북, 4 남
	static int[] dy = {1, -1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 입력 확인
//		for (int i = 0; i < n; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < k; i++) {
			int order = Integer.parseInt(st.nextToken());
//			System.out.println(i +" " + x + " " + y + " " + order);
			int nx = x + dx[order-1];
			int ny = y + dy[order-1];
			if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
			move(order);
//			System.out.println(Arrays.toString(dice));
			if(map[nx][ny] == 0) {
				map[nx][ny] = dice[6];
			} else {
				dice[6] = map[nx][ny];
				map[nx][ny] = 0;
			}
			System.out.println(dice[1]);
			x = nx;
			y = ny;
		}
	}
	
	static void move(int order) {
		int[] tmp = dice.clone();
//		System.out.println("tmp : " + Arrays.toString(tmp));
		
		if(order == 1) { // 동
			// 4136
			dice[4] = tmp[6];
			dice[1] = tmp[4];
			dice[3] = tmp[1];
			dice[6] = tmp[3];
		} else if(order == 2) { // 서
			// 4136
			dice[4] = tmp[1];
			dice[1] = tmp[3];
			dice[3] = tmp[6];
			dice[6] = tmp[4];
		} else if(order == 3) { // 북
			// 2156
			dice[2] = tmp[1];
			dice[1] = tmp[5];
			dice[5] = tmp[6];
			dice[6] = tmp[2];
		} else if(order == 4) { // 남
			// 2156
			dice[2] = tmp[6];
			dice[1] = tmp[2];
			dice[5] = tmp[1];
			dice[6] = tmp[5];
		}
	}
}
