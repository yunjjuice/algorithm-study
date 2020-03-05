/*
 * BOJ 14502 연구소
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502 {
	static int n, m;
	static int[][] map;
	static int[][] map2;
	static Queue<Pos> q;
	static ArrayList<Pos> list;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		map2 = new int[n][m];
		q = new LinkedList<Pos>();
		list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					list.add(new Pos(i, j));
				}
				if(map[i][j] == 2) {
					q.offer(new Pos(i, j));
				}
			}
		}
		
		// 입력 확인
//		for (int i = 0; i < n; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		ans = Integer.MIN_VALUE;
		// 벽을 3개 세우고
		makeWall(q);
		// bfs 돌리고
		// 0인 구역 개수 세기
		System.out.println(ans);
	}
	
	static void makeWall(Queue<Pos> v) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if(i >= j) continue;
				for (int k = 0; k < list.size(); k++) {
					if(i >= k || j >= k) continue;
//					System.out.println("i "+i+", j "+j+", k "+k);
					for (int k2 = 0; k2 < map.length; k2++) {
						map2[k2] = map[k2].clone();
					}
					map2[list.get(i).x][list.get(i).y] = 1;
					map2[list.get(j).x][list.get(j).y] = 1;
					map2[list.get(k).x][list.get(k).y] = 1;
					bfs();
				}
			}
		}
	}
	
	static void bfs() {
		Queue<Pos> tmp = new LinkedList<>();
		for(Pos p : q) {
			tmp.add(p);
		}
		
		while(!tmp.isEmpty()) {
			Pos p = tmp.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(nx < 0 || nx >= n || ny < 0 || ny >= m || map2[nx][ny] == 1 || map2[nx][ny] == 2) {
					continue;
				}
				map2[nx][ny] = 2;
				tmp.offer(new Pos(nx, ny));
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map2[i][j] == 0)
					cnt++;
			}
		}
		
		ans = Math.max(ans, cnt);
	}
}

class Pos {
	int x;
	int y;
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}