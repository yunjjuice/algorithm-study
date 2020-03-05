package algo;

import java.io.*;
import java.util.*;

public class SWEA_미생물격리_200304 {
	static class Group {
		int x, y, cnt, dir, sum;
		public Group() {}
		public Group(int x, int y, int cnt, int dir) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
		}
	}
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	static Group[][] map;
	static Queue<Group> q;
	static int n, m, k;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= TC; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			q = new LinkedList<>();
			for(int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				q.offer(new Group(x, y, cnt, dir));
			} // input
				
			for(int i = 0; i < m; i++) simulate();
			
			// 남아있는 미생물 총 수 구하기
			int sum = 0;
			while(!q.isEmpty()) {
				sum += q.poll().cnt;
			}
			sb.append("#").append(testcase).append(" ").append(sum).append('\n');
		}
		System.out.print(sb);
	}
			
	private static void simulate() {
		int size = q.size();
		for(int i = 0; i < size; i++) {
			Group g = q.poll();
			g.x += dx[g.dir];
			g.y += dy[g.dir];
			// 규칙 1 : 가장자리 셀에 도착하면 미생물 절반 죽고 방향 전환
			if(g.x == 0 || g.y == 0 || g.x == n - 1 || g.y == n - 1) {
				g.cnt /= 2;
				g.dir = g.dir % 2 == 0 ? g.dir - 1 : g.dir + 1;
			}
			
			// 규칙 2 : 미생물 수가 0이되면 군집 사라짐
			if(g.cnt == 0) continue;
			q.offer(g);
		}
		
		// 규칙 3 : 한 셀에 군집들이 모이면 군집 합쳐짐 => 미생물은 합쳐지고 방향은 미생물 수가 가장 많은 군집의 방향을 따름
		map = new Group[n][n];
		while(!q.isEmpty()) {
			Group g = q.poll();
			if(map[g.x][g.y] == null) {
				map[g.x][g.y] = new Group(g.x, g.y, g.cnt, g.dir);
				map[g.x][g.y].sum = g.cnt;
			} else {
				if(g.cnt > map[g.x][g.y].cnt) {
					map[g.x][g.y].cnt = g.cnt;
					map[g.x][g.y].dir = g.dir;
				}
				map[g.x][g.y].sum += g.cnt;
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] != null) {
					Group g = map[i][j];
					q.offer(new Group(g.x, g.y, g.sum, g.dir));
				}
			}
		}
	}
}
