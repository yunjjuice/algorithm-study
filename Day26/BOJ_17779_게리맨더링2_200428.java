/*
 * BOJ 17779 게리맨더링 2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17779_게리맨더링2_200428 {
	static int N, x, y, d1, d2, ans;
	static int[][] map;
	static boolean[][] visited;
	static int[] po; // 각 구역별 인구수
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		ans = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		// 기준점 x, y와 길이 d1, d2를 정한다
		// 1 <= x < x+d1+d2 <= N
		// 1 <= y-d1 < y < y+d2 <= N
		// 기준점 x, y => for문으로 돌면서 하나씩 하고
		// d1, d2는 조합..? 1,1부터 범위에 포함되는 수까지 구해보자
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				x = i;
				y = j;
				for (int k1 = 1; k1 <= N; k1++) {
					for (int k2 = 1; k2 <= N; k2++) {
						d1 = k1;
						d2 = k2;
						if(x < x+d1+d2 && x+d1+d2 <= N && 1 <= y-d1 && y-d1 < y && y < y+d2 && y+d2 <= N) {
							po = new int[6];
							divide(); // 5번 선거구 만듦
							makeGroup(); // 선거구별로 인구수 계산
							calc(); // 방문 안 한 곳 있는지 체크하고 인구차이 계산
						}
					}
				}
			}
		}
		
		System.out.println(ans);
	}
	
	private static void calc() {
//		System.out.println("인구수 : "+ Arrays.toString(po));
		boolean flag = true;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(!visited[i][j])
					flag = false;
			}
		}
		if(!flag) return;
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= 5; i++) {
			max = Math.max(max, po[i]);
			min = Math.min(min, po[i]);
		}
		ans = Math.min(ans, max-min);
//		System.out.println("max : "+max+", min : "+min+", ans : "+ans);
//		System.out.println();
	}

	private static void makeGroup() {
		// 5번 선거구를 다 true로 해두어서 먼저 계산해준다
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(visited[i][j])
					po[5] += map[i][j];
			}
		}
		
		bfs(1, 1, 1);
		bfs(1, N, 2);
		bfs(N, 1, 3);
		bfs(N, N, 4);
		
	}

	private static void bfs(int r, int c, int type) {
		int sx=0, sy=0, ex=0, ey=0;
		if(type == 1) { // 1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
			sx = 1; ex = x+d1-1;
			sy = 1; ey = y; 
		} else if(type == 2) { // 2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
			sx = 1; ex = x+d2;
			sy = y+1; ey = N; 
		} else if(type == 3) { // 3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
			sx = x+d1; ex = N;
			sy = 1; ey = y-d1+d2-1; 
		} else if(type == 4) { // 4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
			sx = x+d2+1; ex = N;
			sy = y-d1+d2; ey = N; 
		}
//		System.out.println("type:"+type+", sx:"+sx+", ex:"+ex+", sy:"+sy+", ey:"+ey);
		
		Queue<Pos> q = new LinkedList<Pos>();
		q.offer(new Pos(r, c));
		visited[r][c] = true;
		po[type] = map[r][c];
		while(!q.isEmpty()) {
			Pos now = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = now.r + dir[i][0];
				int ny = now.c + dir[i][1];
//				System.out.println("nx : "+nx + ", ny : "+ny);
				if((sx <= nx) && (nx <= ex) && (sy <= ny) && (ny <= ey) && !visited[nx][ny]) {
					po[type] += map[nx][ny];
//					System.out.println("po : ["+type+"]: " + po[type]);
					visited[nx][ny] = true;
					q.offer(new Pos(nx, ny));
				}
			}
		}
	}

	private static void divide() {
		visited = new boolean[N+1][N+1];
		for (int i = 0; i <= d1; i++) {
			visited[x+i][y-i] = true;
		}
		for (int i = 0; i <= d2; i++) {
			visited[x+i][y+i] = true;
		}
		for (int i = 0; i <= d2; i++) {
			visited[x+d1+i][y-d1+i] = true;
		}
		for (int i = 0; i <= d1; i++) {
			visited[x+d2+i][y+d2-i] = true;
		}
		
//		System.out.println("x:"+x+", y:"+y+", d1:"+d1+", d2:"+d2);
//		for (int i = 1; i < map.length; i++) {
//			System.out.println(Arrays.toString(visited[i]));
//		}
//		System.out.println();

		for (int i = x+1; i < x+d1+d2; i++) {
			boolean flag = false;
			for (int j = 1; j <= N; j++) {
				if(visited[i][j]) {
					flag = !flag;
				}
				if(flag) {
					visited[i][j] = true;
				}
			}
		}
		
//		System.out.println("x:"+x+", y:"+y+", d1:"+d1+", d2:"+d2);
//		for (int i = 1; i < map.length; i++) {
//			System.out.println(Arrays.toString(visited[i]));
//		}
//		System.out.println();
	}
	
	static class Pos {
		int r;
		int c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
