/*
 * BOJ 17822 원판 돌리기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_17822_원판돌리기_200420 {
	static int N, M, T, ans;
	static int[][] map, command;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); // 원판의 개수
		M = Integer.parseInt(st.nextToken()); // 원판에 적혀있는 숫자 개수
		T = Integer.parseInt(st.nextToken()); // 회전 횟수
		
		map = new int[N+1][M];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		command = new int[T][3]; // 0:x -> x배수의 원판, 1:d -> 방향 0:시계 1:반시계, 2:k -> 회전 개수
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				command[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 끝
		
		// 입력확인
//		for (int i = 1; i <= N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		
//		for (int i = 0; i < T; i++) {
//			System.out.println(Arrays.toString(command[i]));
//		}
		
		for (int i = 0; i < T; i++) { // 명령 개수만큼 돈다
			if(command[i][1] == 0)
				rotate(command[i][0], command[i][2]);
			else 
				antiRotate(command[i][0], command[i][2]);
			
			// 회전 확인
//			System.out.println(i+"번째 명령");
//			for (int j = 1; j <= N; j++) {
//				System.out.println(Arrays.toString(map[j]));
//			}
			
//			인접한 숫자를 찾는다
//			인접한 수가 같은 경우에는 원판에서 인접하면서 같은 수를 모두 지운다.
//			없는 경우에는 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
			search();
		}
		
		ans = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				ans += map[i][j];
			}
		}
		
		System.out.println(ans);
		
	} // end of main
	
	public static void search() {
		// 인접한 숫자를 찾는다
		HashSet<Pos> set = new HashSet<Pos>();
		// 첫번째 원판
		for (int i = 0; i < M; i++) {
			if(map[1][i] != 0 && (map[1][i] == map[1][(i+M-1) % M])) { // 앞
				set.add(new Pos(1, i));
			}
			if(map[1][i] != 0 && (map[1][i] == map[1][(i+1) % M])) { // 뒤
				set.add(new Pos(1, i));
			}
			if(map[1][i] != 0 && (map[1][i] == map[2][i])) { // 다음 원판
				set.add(new Pos(1, i));
			}
		}
		
		// 중간 원판
		if(N >= 3) {
			for (int i = 2; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] != 0 && (map[i][j] == map[i][(j+M-1) % M])) { // 앞
						set.add(new Pos(i, j));
					}
					if(map[i][j] != 0 && (map[i][j] == map[i][(j+1) % M])) { // 뒤
						set.add(new Pos(i, j));
					}
					if(map[i][j] != 0 && (map[i][j] == map[i-1][j])) { // 이전 원판
						set.add(new Pos(i, j));
					}
					if(map[i][j] != 0 && (map[i][j] == map[i+1][(j) % M])) { // 다음 원판
						set.add(new Pos(i, j));
					}
				}
			}
		}
		
		// 마지막 원판
		if(N >= 2) {
			for (int i = 0; i < M; i++) {
				if(map[N][i] != 0 && (map[N][i] == map[N][(i+M-1) % M])) { // 앞
					set.add(new Pos(N, i));
				}
				if(map[N][i] != 0 && (map[N][i] == map[N][(i+1) % M])) { // 뒤
					set.add(new Pos(N, i));
				}
				if(map[N][i] != 0 && (map[N][i] == map[N-1][i])) { // 이전 원판
					set.add(new Pos(N, i));
				}
			}
		}
		
		if(set.size() == 0) { // 똑같은 게 없으면 원판 평균값 구해서 평균값보다 크면 -1, 작으면 +1
			int sum = 0;
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] != 0) {
						sum += map[i][j];
						cnt++;
					}
				}
			}
			
			if(cnt == 0) return;
			
			float avg = (float)sum/cnt;
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] != 0) {
						if(map[i][j] > avg) {
							map[i][j] -= 1;
						}
						else if(map[i][j] < avg){
							map[i][j] += 1;
						}
					}
				}
			}
		} else {
			for (Pos pos : set) {
				map[pos.x][pos.y] = 0;
			}
		}
		
	}

	public static void rotate(int x, int k) { // 시계방향
		// x배수 원판들이 
		// d 방향으로 (0:시계 1:반시계)
		// k만큼 회전
		for (int i = x; i <= N; i=i+x) {
			int[] tmp = map[i].clone();
			for (int j = 0; j < M; j++) {
				map[i][j] = tmp[(j+(M-k)) % M];
			}
		}
	}
	
	public static void antiRotate(int x, int k) { // 반시계방향
		for (int i = x; i <= N; i=i+x) {
			int[] tmp = map[i].clone();
			for (int j = 0; j < M; j++) {
				map[i][j] = tmp[(j+k) % M];
			}
		}
	}
	
	static class Pos {
		int x;
		int y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public boolean equals(Object obj) {
			Pos other = (Pos) obj;
			if(x == other.x && y == other.y) return true;
			return false;
		}
	}
}
