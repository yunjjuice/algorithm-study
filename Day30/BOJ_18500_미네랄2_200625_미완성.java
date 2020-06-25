/*
 * BOJ 18500 미네랄 2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18500_미네랄2_200625_미완성 {
	static int R, C, N;
	static char[][] map;
//	static boolean[][] visited;
	static int[][] visited;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
//		for (int i = 0; i < R; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		int n = 0;
		for (int i = 0; i < N; i++) {
			n = Integer.parseInt(st.nextToken());
			// R-n을 하면 진자 행 위치가 나온다
			// 막대 던지기
			destroy(R-n, i%2==0?0:1);
			
			
//			for (int j = 0; j < R; j++) {
//				System.out.println(Arrays.toString(map[j]));
//			}
//			System.out.println();
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	private static void destroy(int r, int d) { // n번째 행, 왼/오
		if(d == 0) { // 왼 -> 오
			for (int i = 0; i < C; i++) {
				// 미네랄이 파괴되었다면 주변을 파악한다.
				if(map[r][i] == 'x') {
					map[r][i] = '.';
					for (int j = 0; j < 4; j++) {
						int nx = r + dir[j][0];
						int ny = i + dir[j][1];
						if(isLen(nx, ny) && map[nx][ny] == 'x') {
							bfs(nx, ny);
						}
					}
					break;
				}
			}
		} else { // 오 -> 왼
			for (int i = C-1; i >= 0; i--) {
				if(map[r][i] == 'x') {
					map[r][i] = '.';
					for (int j = 0; j < 4; j++) {
						int nx = r + dir[j][0];
						int ny = i + dir[j][1];
						if(isLen(nx, ny) && map[nx][ny] == 'x') {
							bfs(nx, ny);
						}
					}
					break;
				}
			}
		}
	}
	
	private static void bfs(int r, int c) {
		Queue<Pos> q = new LinkedList<Pos>();
		q.offer(new Pos(r, c));
		boolean flag = false; // 땅에 닿는지 아닌지
		if(r == R-1) flag = true;
		
		// 떨어뜨려야 할 애들이 누군지 저장해둠
		ArrayList<Pos> list = new ArrayList<>();
		list.add(new Pos(r, c));
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dir[i][0];
				int ny = cur.y + dir[i][1];
				
				if(isLen(nx, ny)) {
					if(nx == R-1) flag = true;
					q.offer(new Pos(nx, ny));
					list.add(new Pos(nx, ny));
				}
			}
		}
		
		// 땅에 닿지 않는 클러스터라면 내려준다
		if(!flag) {
			down(list);
		}
	}
	
	private static boolean isLen(int x, int y) {
		return 0<=x && x<R && 0<=y && y<C;
	}
	
	static class Pos implements Comparable<Pos> {
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}

		@Override
		public int compareTo(Pos o) {
			return this.x - o.x;
		}
	}
}
