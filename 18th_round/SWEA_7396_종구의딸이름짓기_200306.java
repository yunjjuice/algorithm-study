package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_7396_종구의딸이름짓기_200306 {
	static class Pos {
		int x;
		int y;
		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static char[][] map;
	static int[] dx = {1, 0};
	static int[] dy = {0, 1};
	static String ans;
	static boolean[][] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= TC; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			map = new char[n][m];
			visited = new boolean[n][m];
			
			for(int i = 0; i < n; i++) {
				String str = br.readLine();
				for(int j = 0; j < m; j++) {
					map[i][j] = str.charAt(j);
				}
			} // input

			ans = "";
			ans += map[0][0];
			
			bfs();

			sb.append("#").append(testcase).append(" ").append(ans).append('\n');
		}
		System.out.print(sb);
	}
	
	private static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		Queue<Pos> tmp = new LinkedList<>();
		char ch = 'z';
		q.offer(new Pos(0, 0));
		while(!q.isEmpty()) {
			Pos p = q.poll();
			int px = p.x;
			int py = p.y;
			for(int i = 0; i < 2; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];
				if(nx < map.length && ny < map[0].length) {
					if(map[nx][ny] <= ch && !visited[nx][ny]) {
						visited[nx][ny] = true;
						tmp.offer(new Pos(nx, ny)); // 다음 글자가 될 수 있는 위치를 찾기 위해 tmp에 넣어줌
						ch = map[nx][ny]; // 가장 빠른 ch값 갱신
					}
				}
			}
			
			if(q.size() == 0 && !tmp.isEmpty()) {
				while(!tmp.isEmpty()) {
					Pos p2 = tmp.poll();
					if(map[p2.x][p2.y] == ch) { // tmp에서 가장 빠른 ch과 일치하는 position을 원래 queue에다 넣어줌
						q.offer(new Pos(p2.x, p2.y));
					}
				}
				ans += ch; // 답 갱신
				ch = 'z'; // ch 초기화
			}
		}
	}
}
